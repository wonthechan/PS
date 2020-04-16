package study.date0416;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B1463_1로만들기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[1] = 0;
		
		for (int i = 2; i <= n; i++) {
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
			}
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			}
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
		}
		
		System.out.println(dp[n]);
	}

}
