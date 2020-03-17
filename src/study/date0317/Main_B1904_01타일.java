package study.date0317;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 메모이제이션을 이용해 푸는 피보나치 수열과 동일한 문제 */
public class Main_B1904_01타일 {

	static int[] dp = new int[1000001];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1904.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// for문을 이용해서 상향식으로 풀어나간다.
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 15746;
		}
		System.out.println(dp[N]);
	}
}
