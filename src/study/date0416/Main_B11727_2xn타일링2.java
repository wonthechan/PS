package study.date0416;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B11727_2xn타일링2 {

	final static int DIV = 10007;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b11727.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(startDP(n));
	}
	
	static int startDP(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 3;
		}
		int[] dp = new int[n+1];
		
		// base case set
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + (dp[i-2] * 2) % DIV) % DIV;
		}
		return dp[n];
	}
}
