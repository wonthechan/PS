package study.date0418;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B4883_삼각그래프 {

	static int N;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b4883.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		int tc = 1;
		while (N != 0) {
			map = new int[N][3];
			dp = new int[N][3];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 3; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 정점별 비용 입력
			
			dp[0][1] = map[0][1];
			dp[0][2] += dp[0][1] + map[0][2];
			dp[1][0] += dp[0][1] + map[1][0];
			dp[1][1] = Math.min(Math.min(dp[1][0], dp[0][1]), dp[0][2]) + map[1][1];
			dp[1][2] = Math.min(Math.min(dp[1][1], dp[0][1]), dp[0][2]) + map[1][2]; // 두번째 행까지는 미리 계산해놓기
			
			for (int i = 2; i < N; i++) {
				// 첫번째 열
				dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + map[i][0];
				// 가운데 열
				dp[i][1] = Math.min(Math.min(Math.min(dp[i][0], dp[i-1][0]), dp[i-1][1]), dp[i-1][2]) + map[i][1];
				// 마지막 열
				dp[i][2] = Math.min(Math.min(dp[i][1], dp[i-1][1]), dp[i-1][2]) + map[i][2];
			}
			
			System.out.println(tc+ ". " + dp[N-1][1]);
			N = Integer.parseInt(br.readLine());
			++tc;
		}
		
	}

}
