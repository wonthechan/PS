package study.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 연습문제3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for (int i = 4; i <= N; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		System.out.println(dp[N]);
	}

}
