package study.date0301;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제를 읽고 제시한 조건에 따라 구현하는 시뮬레이션 문제.
 * 조건이 복잡하고 까다로울수록 구현할 때 실수를 많이 할 수 있음..
 * 시뮬유형은 반드시 코딩하기 전에 대강의 구조를 잡고 시작할 것.
 */
public class Main_B3190_뱀 {
	
	static int N, K, L;	// 보드의 크기, 사과의 갯수, 방향 변환 횟수
	static int[][] map, cmds;
	static int hy, hx, ty, tx, hd, td; // 현재 머리 와 꼬리의  좌표, 방향(머리, 꼬리 각각)
	static int sec; // 현재 흐른 초
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
		
		hy = hx = ty = tx = hd = td = 1;	// 시작 위치를 (1,1)로 초기화 , 방향은 오른쪽으로 초기화
		sec = 0;
		boolean flag = true;
		// 게임 시작
		for (int i = 0; i < L; i++) {
			// 주어진 명령에 따라 해당 초까지 진행하고 방향을 바꾼다.
			flag = gameStart(cmds[i][0], cmds[i][1]);
//			System.out.println(sec + " hy: " + hy + ", hx; " + hx + "/ ty: " + ty + ", tx; " + tx);
			if (!flag) break;
		}
		
//		for (int[] a : map) System.out.println(Arrays.toString(a));
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
			hy += dy4[hd];	// 머리는 현재 방향으로 다음 칸에 위치시킨다.
			hx += dx4[hd];
			
			// 꼬리도 방향을 바꿔야 하는 경우
			if (map[ty][tx] == 3) {	// 왼쪽
				td -= 1;
				if (td == -1) td = 3;
				map[ty][tx] = 2;	// 원래대로 돌려놓기
			} else if (map[ty][tx] == 4) {
				td += 1;
				if (td == 4) td = 0;
				map[ty][tx] = 2;
			}
			
			switch (map[hy][hx]) {
			case 7:			// 사과를 먹은 경우 (꼬리는 그대로)
				map[hy][hx] = 2;
				continue;
			case 1:			// 정상 영역인 경우 (전진 : 꼬리 길이도 줄여준다)
				map[hy][hx] = 2;
				map[ty][tx] = 1;// 원래 위치는 정상 영역으로 다시 마킹해준다.
				ty += dy4[td];
				tx += dx4[td];
				break;
			default:			// 이외의 경우 (경계 또는 자신의 몸에 부딪힌 경우 게임 종료)
				return false;	
			}
			
		}
		// 방향을 바꿔주자 (1:오른쪽이면 기존 방향에 1을 더해주고, 0: 왼쪽이면 1을 빼준다)
		hd += (cmdDir == 0)? -1 : 1;
		if (hd == 4) hd = 0;
		if (hd == -1) hd = 3;
//		System.out.println(sec + " hy: " + hy + ", hx; " + hx + "/ ty: " + ty + ", tx; " + tx);
		map[hy][hx] = (cmdDir == 0)? 3 : 4; // 꼬리 방향도 바꿔주기 위해 해당 위치에 방향 마킹 (3: 왼쪽, 4: 오른쪽)
		return true; // 게임을 정상적으로 진행한 경우
	}
	
	private static void gameStart() {
		while(true) {	// 뱀은 2로 마킹
			++sec; // 1초의 시간이 흐른다.
			hy += dy4[hd];	// 머리는 현재 방향으로 다음 칸에 위치시킨다.
			hx += dx4[hd];
			
			// 꼬리도 방향을 바꿔야 하는 경우
			if (map[ty][tx] == 3) {	// 왼쪽
				td -= 1;
				if (td == -1) td = 3;
				map[ty][tx] = 2;	// 원래대로 돌려놓기
			} else if (map[ty][tx] == 4) {
				td += 1;
				if (td == 4) td = 0;
				map[ty][tx] = 2;	// 원래대로 돌려놓기
			}
			
			switch (map[hy][hx]) {
			case 7:			// 사과를 먹은 경우 (꼬리는 그대로)
				map[hy][hx] = 2;
				continue;
			case 1:			// 정상 영역인 경우 (전진 : 꼬리 길이도 줄여준다)
				map[hy][hx] = 2;
				map[ty][tx] = 1;// 원래 위치는 정상 영역으로 다시 마킹해준다.
				ty += dy4[td];	// 머리는 현재 방향으로 다음 칸에 위치시킨다.
				tx += dx4[td];
				break;
			default:			// 이외의 경우 (경계 또는 자신의 몸에 부딪힌 경우 게임 종료)
//				System.out.println(sec + " **hy: " + hy + ", hx; " + hx + "/ ty: " + ty + ", tx; " + tx);
				return;	
			}
		}
	}
}
