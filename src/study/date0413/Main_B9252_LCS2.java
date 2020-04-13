package study.date0413;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B9252_LCS2 {

	static final int BRIDGE = 1, TOP = 2, LEFT = 3;
	static char[] A, B;
	static int[][] dp;
	static int[][] sol;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b9252.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		dp = new int[A.length + 1][B.length + 1];
		sol = new int[A.length + 1][B.length + 1];
		
		sb.append(getLCSLen()).append("\n");
		sb.append(getLCSStr());
		
		System.out.println(sb.toString());
	}
	
	/** LCS 문자열을 출력 */
	private static String getLCSStr() {
		StringBuilder sb = new StringBuilder();
		int y = A.length;
		int x = B.length;
		while (sol[y][x] > 0) {
			switch (sol[y][x]) {
			case BRIDGE:
				sb.append(A[y-1]);
				--y;--x;
				break;
			case TOP:
				--y;
				break;
			case LEFT:
				--x;
				break;
			}
			
		}
		return sb.reverse().toString();
	}

	/** LCS dp 배열 완성 후 LCS의 길이 리턴 */
	private static int getLCSLen() {
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
					sol[i][j] = BRIDGE;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					if (dp[i][j] == dp[i-1][j]) sol[i][j] = TOP;
					else sol[i][j] = LEFT;
				}
			}
		}
		return dp[A.length][B.length];
	}

	
	
}
