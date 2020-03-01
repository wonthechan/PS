package study.date0301;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Deque(덱)을 이용한 풀이 : 뱀의 몸통을 덱으로 생각한다.
 * 덱을 이용하면 머리와 꼬리를 따로 관리할 필요가 업어서 간단해진다.
 */
public class Main_B3190_뱀_Deque {
	
	static int N, K, L;	// 보드의 크기, 사과의 갯수, 방향 변환 횟수
	static int[][] map, cmds;
	static ArrayDeque<Pos> deque;
	static int sec, dir; // 현재 흐른 초, 현재 뱀의 방향
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b3190.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];	// 경계를 만들기 위해 크기를 2씩 늘림 (경계는 0으로 마킹)
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) map[i][j] = 1;	// 정상 영역을 1로 마킹
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {	// 사과가 있는 곳은 7으로 마킹
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 7;
		}
		L = Integer.parseInt(br.readLine());
		cmds = new int[L][2];			// 0 인덱스: 초, 1 인덱스: 방향 정보 (0이면 왼쪽, 1이면 오른쪽)
		for (int i = 0; i < L; i++) {	// 방향 변환 정보 입력
			st = new StringTokenizer(br.readLine());
			cmds[i] = new int[] {Integer.parseInt(st.nextToken()), st.nextToken().charAt(0) == 'L'? 0 : 1};
		}
		
		deque = new ArrayDeque<>();
		deque.addFirst(new Pos(1,1)); // 초기 시작위치 삽입 (앞쪽에)
		sec = 0;
		dir = 1;
		boolean flag = true;
		// 게임 시작
		for (int i = 0; i < L; i++) {
			// 주어진 명령에 따라 해당 초까지 진행하고 방향을 바꾼다.
			flag = gameStart(cmds[i][0], cmds[i][1]);
			if (!flag) break;
		}
		
		// 게임은 계속 진행 (끝나면 종료)
		if (flag) gameStart();
		System.out.println(sec);
		
	}
					// 상 우 하 좌 (시계 방향)
	static int[] dy4 = {-1, 0, 1, 0};
	static int[] dx4 = {0, 1, 0, -1};
	private static boolean gameStart(int cmdSec, int cmdDir) {
		while(sec < cmdSec) {	// 뱀은 2로 마킹
			++sec; // 1초의 시간이 흐른다.
			int ny = deque.peekFirst().i + dy4[dir];
			int nx = deque.peekFirst().j + dx4[dir];
			
			switch (map[ny][nx]) {
			case 7:			// 사과를 먹은 경우 (꼬리는 그대로)
				deque.addFirst(new Pos(ny, nx));
				map[ny][nx] = 2;
				continue;
			case 1:			// 정상 영역인 경우 (전진 : 꼬리 길이도 줄여준다)
				deque.addFirst(new Pos(ny, nx));
				map[ny][nx] = 2;
				Pos tail = deque.pollLast();
				map[tail.i][tail.j] = 1;// 원래 위치는 정상 영역으로 다시 마킹해준다.
				break;
			default:			// 이외의 경우 (경계 또는 자신의 몸에 부딪힌 경우 게임 종료)
//				System.out.println(sec + " ny: " + ny + ", nx; " + nx);
				return false;	
			}
		}
		// 방향을 바꿔주자 (1:오른쪽이면 기존 방향에 1을 더해주고, 0: 왼쪽이면 1을 빼준다)
		dir += (cmdDir == 0)? -1 : 1;
		if (dir == 4) dir = 0;
		if (dir == -1) dir = 3;
		return true; // 게임을 정상적으로 진행한 경우
	}
	
	private static void gameStart() {
		while(true) {	// 뱀은 2로 마킹
			++sec; // 1초의 시간이 흐른다.
			int ny = deque.peekFirst().i + dy4[dir];
			int nx = deque.peekFirst().j + dx4[dir];
			
			switch (map[ny][nx]) {
			case 7:			// 사과를 먹은 경우 (꼬리는 그대로)
				deque.addFirst(new Pos(ny, nx));
				map[ny][nx] = 2;
				continue;
			case 1:			// 정상 영역인 경우 (전진 : 꼬리 길이도 줄여준다)
				deque.addFirst(new Pos(ny, nx));
				map[ny][nx] = 2;
				Pos tail = deque.pollLast();
				map[tail.i][tail.j] = 1;// 원래 위치는 정상 영역으로 다시 마킹해준다.
				break;
			default:			// 이외의 경우 (경계 또는 자신의 몸에 부딪힌 경우 게임 종료)
				return;	
			}
		}
	}
	
	static class Pos {
		int i, j;
		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
