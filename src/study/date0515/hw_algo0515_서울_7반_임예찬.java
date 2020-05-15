package study.date0515;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class hw_algo0515_서울_7반_임예찬 {

	static int N, M;	// 판의 세로 가로 길이
	static int[][] map;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/b2636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	// 입력 끝
		
		int hours = 0;
		int leftCnt = 0;
		int removeCnt = 0;
		while (true) {
			++hours;
			
			// 1. 공기칸을 BFS 탐색하여 2로 마킹한다. (치즈구멍칸과 구분하기 위함)
			checkAirAreaBFS();
			
			// 2. 전체 탐색하면서 공기칸(2)과 맞닿아 있는 치즈 경계칸을 3으로 마킹
			// 2-1. 3으로 마킹하여 녹아버린 치즈의 개수를 카운트
			removeCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 1) continue;	// 치즈칸만 고려함.
					for (int dir = 0; dir < 4; dir++) {
						int ny = i + dy4[dir];
						int nx = j + dx4[dir];
						if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
						if (map[ny][nx] == 2) {
							map[i][j] = 3;	// 현재 칸을 3으로 마킹
							++removeCnt;
							break;
						}
					}
				}
			}
			
			// 3. 전 단계에서 3으로 마킹한 경계칸과 2로 마킹한 칸을 전부 다시 0으로 마킹
			// 3-1. 그와 동시에 현재 남아 있는 치즈의 최종 개수를 카운트
			leftCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 1) {
						map[i][j] = 0;
					} else if (map[i][j] == 1) {
						++leftCnt;
					}
				}
			}
			
			// 4. 현재 남아 있는 치즈의 개수를 확인한다.
			if (leftCnt == 0) { // 남아 있는 치즈가 없다면
				break;
			}
		}
		
		// 첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력
		// 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력
		System.out.println(hours);
		System.out.println(removeCnt);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	
	static private void checkAirAreaBFS() {
		Queue<Pos> queue = new LinkedList<>();
		map[0][0] = 2;	// 항상 원점부터 탐색 시작
		queue.offer(new Pos(0, 0));
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (map[ny][nx] > 0) continue;	// 공기칸이 아니면 pass
				map[ny][nx] = 2;
				queue.offer(new Pos(ny, nx));
			}
		}
	}
	
	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
