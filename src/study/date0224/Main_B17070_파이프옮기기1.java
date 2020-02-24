package study.date0224;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17070_파이프옮기기1 {

	static int N;
	static int[][] map;
	static int cnt = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 가로 방향 : 0, 대각선 방향 : 1, 세로 방향 : 2
		dfs(1, 2, 0);	// start
		
		System.out.println(cnt);
	}
	// 우, 우하, 하
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 1, 0};
	
	private static void dfs(int i, int j, int direction) {
		if (i == N && j == N) {
			++cnt;
			return;
		}
		int ny, nx;
		boolean flag;
		switch (direction) {
		case 0:	// 가로 방향으로 놓여져 있는 경우 (우, 우좌 방향 이동 가능)
			flag = true;
			for (int dir = 0; dir < 3; dir++) {
				ny = i + dy[dir];
				nx = j + dx[dir];
				if (ny > N || nx > N || map[ny][nx] != 0) {
					flag = false;
					continue;
				}
				if (dir == 0) {
					dfs(ny, nx, 0);
				}
			}
			if (flag) dfs(i + dy[1], j + dx[1], 1);
			break;
		case 1:	// 대각선 방향으로 놓여져 있는 경우 (모든 방향 이동 가능)
			flag = true;
			for (int dir = 0; dir < 3; dir++) {
				ny = i + dy[dir];
				nx = j + dx[dir];
				if (ny > N || nx > N || map[ny][nx] != 0) {
					flag = false;
					continue;
				}
				if (dir == 0) {
					dfs(ny, nx, 0);
				} else if (dir == 2) {
					dfs(ny, nx, 2);
				}
			}
			if (flag) dfs(i + dy[1], j + dx[1], 1);
			break;
		case 2:	// 세로 방향으로 놓여져 있는 경우 (하, 우좌 방향 이동 가능)
			flag = true;
			for (int dir = 0; dir < 3; dir++) {
				ny = i + dy[dir];
				nx = j + dx[dir];
				if (ny > N || nx > N || map[ny][nx] != 0) {
					flag = false;
					continue;
				}
				if (dir == 2) {
					dfs(ny, nx, 2);
				}
			}
			if (flag) dfs(i + dy[1], j + dx[1], 1);
			break;
		}
	}
}
