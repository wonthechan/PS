package study.date0417;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * 문자열 3개의 LCS를 구하기
 * => 기존 2차원 배열을 3차원으로 늘려서 구해주면 됨!
 * 참고: https://travelbeeee.tistory.com/152
 */
public class Main_B1958_LCS3 {

	static char[] A, B, C;
	static int[][][] LCS;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1958.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		C = br.readLine().toCharArray();
		
		LCS = new int[A.length + 1][B.length + 1][C.length + 1];
		
		System.out.println(LCS());
	}

	public static int LCS() {
		
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				for (int k = 1; k <= C.length; k++) {
					if (A[i-1] == B[j-1] && B[j-1] == C[k-1]) {
						LCS[i][j][k] = LCS[i-1][j-1][k-1] + 1;
					} else {
						// LCS[i][j][k-1] 과 LCS[i-1][j][k], LCS[i][j-1][k] 세개의 max값을 저장
						LCS[i][j][k] = Math.max(LCS[i-1][j][k], LCS[i][j][k-1]);
						LCS[i][j][k] = Math.max(LCS[i][j-1][k], LCS[i][j][k]);
					}
				}
			}
		}
		return LCS[A.length][B.length][C.length];
	}
}
