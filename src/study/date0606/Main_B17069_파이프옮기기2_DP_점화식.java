package study.date0606;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 파이프 옮기기1과 같은 유형이지만 입력 범위가 늘어남! */
public class Main_B17069_파이프옮기기2_DP_점화식 {

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
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝

		dp[0][1][0] = 1;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (check(y, x) && check(y, x - 1)) {
					dp[y][x][0] += dp[y][x - 1][0] + dp[y][x - 1][1];
				}
				if (check(y, x) && check(y - 1, x - 1) && check(y - 1, x) && check(y, x - 1)) {
					dp[y][x][1] += dp[y - 1][x - 1][0] + dp[y - 1][x - 1][2] + dp[y - 1][x - 1][1];
				}
				if (check(y, x) && check(y - 1, x)) {
					dp[y][x][2] += dp[y - 1][x][1] + dp[y - 1][x][2];
				}
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}
	
	static boolean check (int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N || map[y][x] == 1) return false;
		return true;
	}
}
