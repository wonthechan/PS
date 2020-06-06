package study.date0606;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 파이프 옮기기1과 같은 유형이지만 입력 범위가 늘어남! */
public class Main_B17069_파이프옮기기2_Memo {

	static final boolean[][] possibleDir = 
		{
				{true, true, false},
				{true, true, true},
				{false, true, true}
		};
	static int N;	// 집의 크기 N (3 ≤ N ≤ 16)
	static int[][] map;
	static long[][][] dp;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17069.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N][3];
		for (long[][] a : dp) {
			for (long[] b : a) {
				Arrays.fill(b, -1L);
			}
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		 // 초기 좌표와 방향
		System.out.println(dfs(0, 1, 0));
	}
					//	우 우하 하
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 1, 0};
	private static long dfs(int y, int x, int type) {
		if (dp[y][x][type] > -1) {
			return dp[y][x][type];
		}
		dp[y][x][type] = 0;
		if (y == N - 1 && x == N - 1) {
			return ++dp[y][x][type];
		}
		
		for (int dir = 0; dir < 3; dir++) {
			if (!possibleDir[type][dir]) continue;
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if (map[ny][nx] == 1) continue;
			if (dir == 1) {
				if (map[y + dy[0]][x + dx[0]] == 1 || map[y + dy[2]][x + dx[2]] == 1) continue;
			}
			dp[y][x][type] += dfs(ny, nx, dir);
		}
		return dp[y][x][type];
	}

}
