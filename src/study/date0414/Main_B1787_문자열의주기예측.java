package study.date0414;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// 참고 : https://kesakiyo.tistory.com/8
// 참고2: https://hellogaon.tistory.com/61
public class Main_B1787_문자열의주기예측 {

	static int N;
	static char[] str;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1787.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		str = br.readLine().toCharArray();
		
		int[] pi = getPi();
//		System.out.println(Arrays.toString(pi));
		long answer = 0;
		
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			if (pi[i] > 0) {
				if (dp[pi[i]-1] > 0) { // 이전에 구해 놓은 값을 재활용
					dp[i] = dp[pi[i]-1];
				} else {
					dp[i] = pi[i];
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (dp[i] > 0) {
				answer += (i + 1 - dp[i]);
			}
		}
		System.out.println(answer);
	}
	
	private static int[] getPi() {
		int[] pi = new int[N];
		
		for (int i = 1, j = 0; i < N; i++) {
			while (j > 0 && str[j] != str[i]) {
				j = pi[j-1];
			}
			if (str[j] == str[i]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
}
