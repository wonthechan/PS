package study.date0415;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B17837_새로운게임2 {

	static int N, K;
	static int[][] map;				// 칸 정보를 담고 있는 2차원 배열
	static Player[] players; 		// 말의 객체를 담고 있는 배열
	static Stack<Integer>[][] pan;	// 말을 쌓을 수 있도록 스택으로 초기화한 판 배열
	static Stack<Integer> tempStack = new Stack<>();	// 말을 옳기면서 임시로 사용할 스택
					//     우 좌 상 하
	static int[] dy4 = {0, 0, 0, -1, 1};
	static int[] dx4 = {0, 1, -1, 0, 0};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17837.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N]; 		// 판 배열 초기화
		players = new Player[K];	// 말 배열 초기화
		pan = new Stack[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				pan[i][j] = new Stack<Integer>();
			}
		} // pan 배열 초기화
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = K; i > 0; i--) {	// 행과 열의 번호는 1부터 시작
			st = new StringTokenizer(br.readLine());
			players[K - i] = new Player(Integer.parseInt(st.nextToken()) - 1, 
					Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		} // 입력 끝
		
		// 판 초기 셋팅 (초기 상태의 말을 스택에 쌓아주기)
		for (int i = 0; i < K; i++) {
			Player player = players[i];
			pan[player.y][player.x].push(i);
		}
		
		// 게임 시작
		int cnt = 1;
	L1:	while (cnt < 1001) { // 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.			
			// 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것
			for (int i = 0; i < K; i++) {
				Player player = players[i];
				int ny = player.y + dy4[player.dir];
				int nx = player.x + dx4[player.dir]; // 이동할 칸의 좌표
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 2) {
					// 체스판을 벗어나는 경우에는 파란색과 같은 경우이다. (180도 방향바꾸기)
					switchDir(player); // 방향 바꾸고
					ny = player.y + dy4[player.dir];
					nx = player.x + dx4[player.dir]; // 반대 방향으로 한 칸 이동 하기
					if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 2) continue; // 또 움직일 수 없다면 가만히 있는다.
					if (map[ny][nx] == 1) {	// 빨간색 칸인 경우 (다음칸에 순서를 거꾸로 쌓기)
						// i번째 말을 다음 칸으로 옳기기 (역순으로 쌓기)
						if (moveToRedArea(i, ny, nx, pan[player.y][player.x], pan[ny][nx])) { // 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.
							break L1;
						}
					} 
					else {					// 흰색 칸인 경우 (순서를 유지하여 다음칸에 쌓기)
						// i번째 말을 다음 칸으로 옳기기
						if (moveToWhiteArea(i, ny, nx, pan[player.y][player.x], pan[ny][nx])) {
							break L1;
						}
					}
				} 
				else { // 정상 범위 인 경우 (이미 말이 있는지 여부와 바닥 색깔을 확인)
					if (map[ny][nx] == 1) {	// 빨간색 칸인 경우 (다음칸에 순서를 거꾸로 쌓기)
						// i번째 말을 다음 칸으로 옳기기 (역순으로 쌓기)
						if (moveToRedArea(i, ny, nx, pan[player.y][player.x], pan[ny][nx])) {
							break L1;
						}
						
					} 
					else {					// 흰색 칸인 경우 (순서를 유지하여 다음칸에 쌓기)
						// i번째 말을 다음 칸으로 옳기기
						if (moveToWhiteArea(i, ny, nx, pan[player.y][player.x], pan[ny][nx])) {
							break L1;
						}
					}
				}
			} // K개의 모든 플레이어들이 이동을 마침
			++cnt; // 판 카운트 증가
		}
		
		if (cnt > 1000) {	// 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력
			System.out.println(-1);
		} else {			// 정상적으로 게임이 종료된 경우 턴의 번호를 출력
			System.out.println(cnt);
		}
	}

	/** 다음 칸이 빨간 칸인 경우 (말이 4개 이상 쌓이는 경우 true 리턴) */
	private static boolean moveToRedArea(int i, int ny, int nx, Stack<Integer> currentStack, Stack<Integer> nextStack) {
		int out;
		while (true) {
			out = currentStack.pop();
			nextStack.push(out);		// 바로 다음 칸에 쌓아준다.
			players[out].y = ny;		// 옳겨가는 말 위치 업데이트
			players[out].x = nx;
			if (out == i) {
				break;
			}
		}
		
		return nextStack.size() > 3 ? true : false; //말이 4개 이상 쌓이는 순간 게임이 종료
	}

	/** 다음 칸이 흰색 칸인 경우 (말이 4개 이상 쌓이는 경우 true 리턴) */
	private static boolean moveToWhiteArea(int i, int ny, int nx, Stack<Integer> currentStack, Stack<Integer> nextStack) {
		int out;
		tempStack.clear();
		while (true) {
			out = currentStack.pop();
			tempStack.push(out);
			players[out].y = ny;
			players[out].x = nx;
			if (out == i) {
				break;
			}
		} // 해당 말까지 차례로 꺼내서 임시 스택에 쌓아둔다.
		
		while (!tempStack.isEmpty()) {
			nextStack.push(tempStack.pop());
		} // 임시 스택에 있던 말들을 다시 꺼내서 다음 판에 있는 스택에 차례로 넣어준다. (말 이동)
		
		return nextStack.size() > 3 ? true : false;
	}

	// 180도 방향 전환
	static private void switchDir(Player player) {
		switch (player.dir) {
		case 1:
			player.dir = 2;
			break;
		case 2:
			player.dir = 1;
			break;
		case 3:
			player.dir = 4;
			break;
		case 4:
			player.dir = 3;
			break;
		}
	}
	
	static class Player {
		int y, x, dir; // 현재 말의 위치와 방향 정보
		public Player(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Player [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
	}
}
