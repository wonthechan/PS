package study.date0418;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B2133_타일채우기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N+1];
		
		if (N % 2 == 1) {
			System.out.println(0);
		} else {
			dp[2] = 3;
			if (N >= 4) {
				dp[4] = 21;
				for (int i = 6; i <= N; i++) {
					dp[i] = dp[i-2] * dp[2];
				}
			}
			System.out.println(dp[N]);
		}
	}
}
