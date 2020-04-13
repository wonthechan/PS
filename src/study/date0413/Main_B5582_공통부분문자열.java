package study.date0413;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B5582_공통부분문자열 {

	static int[][] dp;
	static char[] A, B;
	static int lenA, lenB;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b5582.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		lenA = A.length;
		lenB = B.length;
		dp = new int[lenA + 1][lenB + 1];
		
		System.out.println(doDP());
	}
	
	/** LCS의 dp배열을 구하는 과정을 조금 변형 */
	private static int doDP() {
		int max = 0;
		for (int i = 1; i <= lenA; i++) {
			for (int j = 1; j <= lenB; j++) {
				// 해당 자리가 서로 같은 경우는 직전 문자가 가지고 있는 값에 1을 더해주고 기억한다.
				if (A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
					max = Math.max(max, dp[i][j]);
				}
				// 해당 자리가 서로 다른 경우는 그냥 Skip (우린 연속된 문자열만 고려한다.)
			}
		}
		return max;
	}
}
