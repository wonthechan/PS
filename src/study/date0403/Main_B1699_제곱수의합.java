package study.date0403;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B1699_제곱수의합 {

	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		for (int i = 0; i <= N; i++) {
			dp[i] = i;
		} // 초기화 작업
		
		for (int i = 2; i <= N; i++) {
			for (int j = 2; i - j * j >= 0; j++) {
				dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
			} 
		}
		
		System.out.println(dp[N]);
	}
}
