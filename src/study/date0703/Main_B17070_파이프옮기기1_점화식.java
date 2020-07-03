package study.date0703;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B17070_파이프옮기기1_점화식 {

	static int N;		// 집의 크기 N(3 ≤ N ≤ 16)
	static int[][] map;	// 2차원 맵
	static int answer;	// 파이프의 한쪽 끝을 (N, N)으로 이동시키는 모든 방법의 수
	static boolean[][] canMoveTo = { // 현재 방향에서 가로, 대각선, 세로 방향으로 움직일 수 있는지 여부
			{true, true, false},	// 현재 방향이 가로인 경우
			{true, true, true},		// 현재 방향이 대각선인 경우
			{false, true, true} 	// 현재 방향이 세로인 경우
	};
	static int[][][] dp;
					//	우, 우하, 하
	static int[] dy4 = {0, 1, 1};
	static int[] dx4 = {1, 1, 0};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/b17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		// 점화식 (DP)
		dp[0][1][0] = 1;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 1) continue;
				if (check(y, x - 1)) {
					dp[y][x][0] += dp[y][x - 1][0] + dp[y][x - 1][1];
				}
				if (check(y - 1, x - 1) && map[y - 1][x] == 0 && map[y][x - 1] == 0) {
					dp[y][x][1] += dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
				}
				if (check(y - 1, x)) {
					dp[y][x][2] += dp[y - 1][x][1] + dp[y - 1][x][2];
				}
			}
		}
		
		answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		System.out.println(answer);
	}
	
	public static boolean check(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N || map[y][x] == 1) return false;
		return true;
	}
}
