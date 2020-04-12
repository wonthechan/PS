package study.date0412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// 참고 : https://mygumi.tistory.com/126
public class Main_B9251_LCS {

	static final int BRIDGE = 3, LEFT = 2, TOP = 1;
	static char[] A, B; // 비교 문자열 두개
	static int[][] dp;	// LCS 저장할 배열
	static int[][] sol;	// LCS 문자 찾는 정보 배열
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b9251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		
		dp = new int[A.length + 1][B.length + 1];
		sol = new int[A.length + 1][B.length + 1];
		
		System.out.println(getLCS());
//		System.out.println(getStringLCS());
	}
	
	/** LCS 문자열을 완성하여 리턴 */
	private static String getStringLCS() {
		StringBuilder sb = new StringBuilder();

		// dp[A.length][B.length] 지점에서 원점까지 올라오면서 문자열 완성
		int y = A.length;
		int x = B.length;
		while (sol[y][x] > 0) {
			int dir = sol[y][x];
			if (dir == BRIDGE) {
				sb.append(A[y-1]);
				--y; --x;
			} else if (dir == LEFT) {
				--x;
			} else if (dir == TOP) {
				--y;
			}
		}
		
		return sb.reverse().toString();
	}

	private static int getLCS() {
		// dp(LCS) 배열 완성하기
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i-1] == B[j-1]) { // 해당 자리의 문자가 서로 같은 경우
					// 해당 문자를 포함하기 직전의 LCS 값에 1을 더해준다.
					dp[i][j] = dp[i - 1][j - 1] + 1;
					// 다리(Bridge)가 생기는 지점
					sol[i][j] = BRIDGE;
				} else {				// 서로 다른 문자인 경우
					// 해당 문자를 포함한 직전의 LCS 값 두개 중 큰 값을 저장한다.
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					if (dp[i][j] == dp[i-1][j]) sol[i][j] = TOP;
					else sol[i][j] = LEFT;
				}
			}
		}
		return dp[A.length][B.length];
	}

}
