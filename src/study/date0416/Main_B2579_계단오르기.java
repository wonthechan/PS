package study.date0416;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B2579_계단오르기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b2579.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		} // 점수 입력 끝
		
		int[] dp = new int[n + 1];		// i번째 계단에서 얻을 수 있는 최대 점수
		
		dp[1] = stairs[1];
		if (n > 1) {
			dp[2] = Math.max(stairs[2], stairs[1] + stairs[2]);
		}
		if (n > 2) {
			dp[3] = Math.max(stairs[2] + stairs[3], stairs[1] + stairs[3]);
		}
		
		// 오른 계단의 수는 중요하지 않음
		for (int i = 4; i <= n; i++) {
			dp[i] = Math.max(dp[i-3] + stairs[i-1] + stairs[i], dp[i-2] + stairs[i]);
		}
		
		System.out.println(dp[n]);
	}
}
