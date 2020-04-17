package study.date0417;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 동전거스름돈 문제를 참고하여 DP로 풀 수 있다.
 * */
public class Main_B2839_설탕배달_DP {

	static final int INF = 987654321;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		dp[0] = 0;
		dp[1] = dp[2] = INF;
		// 3kg 짜리를 사용했을 때 (3kg으로 못 채우는 경우도 있음)
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i-3] + 1;
		}
		// 3, 5kg 짜리 모두를 사용했을 때
		for (int i = 5; i <= N; i++) {
			dp[i] = Math.min(dp[i], dp[i-5] + 1);
		}
		
		System.out.println(dp[N] >= INF ? -1 : dp[N]);
	}
}
