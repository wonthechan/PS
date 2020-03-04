package study.date0304;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_9282_초콜릿과건포도 {
	static final int MAX = Integer.MAX_VALUE;
	static int N, M, answer;
	static int[][] map;
	static int[][][][] memo;
	static int[][][][] memoSum;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s9282.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 987654321;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			memo = new int[N][M][N][M];
			memoSum = new int[N][M][N][M];
			
			for (int[][][] a : memo) {
				for (int[][] b : a) {
					for (int[] c : b) Arrays.fill(c, MAX);
				}
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = divide(0,0,N-1,M-1);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	private static int divide(int sy, int sx, int ey, int ex) {
		if (sy == ey && sx == ex) {
//			System.out.println(sy + " " + sx + " " + ey + " " + ex);
			return 0;
		}
		
		if (memo[sy][sx][ey][ex] < MAX) return memo[sy][sx][ey][ex];
		
		if (memoSum[sy][sx][ey][ex] == 0) {
			int sum = 0;
			for (int i = sy; i <= ey; i++) {
				for (int j = sx; j <= ex; j++) sum += map[i][j];
			}
			memoSum[sy][sx][ey][ex] = sum;
		}
		
		for (int j = sx; j < ex; j++) {
			if (memo[sy][sx][ey][j] == MAX) {
				memo[sy][sx][ey][j] = divide(sy, sx, ey, j);
			}
			if (memo[sy][j+1][ey][ex] == MAX) {
				memo[sy][j+1][ey][ex] = divide(sy, j+1, ey, ex);
			}
			memo[sy][sx][ey][ex] = Math.min(memo[sy][sx][ey][ex], memoSum[sy][sx][ey][ex] + memo[sy][sx][ey][j] + memo[sy][j+1][ey][ex]);
		}
		
		for (int i = sy; i < ey; i++) {
			if (memo[sy][sx][i][ex] == MAX) {
				memo[sy][sx][i][ex] = divide(sy, sx, i, ex);
			}
			if (memo[i+1][sx][ey][ex] == MAX) {
				memo[i+1][sx][ey][ex] = divide(i+1, sx, ey, ex);
			}
			memo[sy][sx][ey][ex] = Math.min(memo[sy][sx][ey][ex], memoSum[sy][sx][ey][ex] + memo[sy][sx][i][ex] + memo[i+1][sx][ey][ex]);
		}
		
		return memo[sy][sx][ey][ex];
	}

}
