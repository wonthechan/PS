package study.date0530;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17070_파이프옳기기1 {

	static int N, answer;
	static int[][] map;
					//	우, 우하, 하 (갈 수 있는 방향)
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 1, 0};
	
	static int[][] possibleDirs = {
			{1, 1, 0},	// type 0 에서 모든 방향에 대해서 갈 수 있는지에 대한 여부 (1: 가능, 0: 불가능)
			{1, 1, 1},
			{0, 1, 1}
	};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		dfs(0, 1, 0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int y, int x, int type) {
		if (y == N -1 && x == N - 1) {
			++answer;
			return;
		}
		
		for (int dir = 0; dir < 3; dir++) {
			if (possibleDirs[type][dir] == 0) continue;
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue; // 범위를 벗어나는 경우
			if (map[ny][nx] == 1) continue;						  // 벽이 있는 경우
			
			// 대각선 방향으로 가려는 경우 (우, 우하, 하) 방향 칸이 모두 비어 있어야 한다.
			if (dir == 1) {
				if (map[y + dy[0]][x + dx[0]] == 1 || map[y + dy[2]][x + dx[2]] == 1) continue;
			}
			
			dfs(ny, nx, dir);
		}
	}

}
