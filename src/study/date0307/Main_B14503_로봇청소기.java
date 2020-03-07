package study.date0307;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B14503_로봇청소기 {

	static int N, M, answer = 0;
	static int[][] map;
	static boolean[][] visit;
	//	북 동 남 서
	static int[] dy4 = {-1, 0, 1, 0};
	static int[] dx4 = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14503.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cy = r, cx = c;
		int ny, nx, nd;
		// 1. 현재 위치를 청소한다.
		++answer;
		visit[cy][cx] = true;
	L1:	while(true) {
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 진행한다. (왼쪽방향으로 회전)
			for (int dir = d-1, end = d-4; dir >= end; dir--) {
				nd = dir<0?dir+4:dir;
				ny = cy + dy4[nd];
				nx = cx + dx4[nd];
				// 2-a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
				if (ny >= 0 && nx >= 0 && ny < N && nx < M && !visit[ny][nx] && map[ny][nx] == 0) {
					cy = ny;
					cx = nx;
					d = nd;
					// 1. 현재 위치를 청소한다.
					++answer;
					visit[cy][cx] = true;
					continue L1;
				}
			}
			// 2-c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
			cy -= dy4[d];
			cx -= dx4[d];
			// 2-d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
			if (cy < 0 || cx < 0 || cy >= N || cx >= M || map[cy][cx] == 1) {
				break L1;
			}
		}
		System.out.println(answer);
	}
	
}
