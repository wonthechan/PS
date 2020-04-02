package study.date0402;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_5607_조합 {

	static final long P = 1234567891;
	static int N, R;
	static long answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s5607.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = 0L;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			
			// N! 구하기
			answer = 1L;
			for (int i = 2; i <= N; i++) {
				answer = (answer * i) % P;
			}
			
			// R! * (N-R)! 구하기
			long fact_NR = 1L;
			for (int i = 2, end = N - R; i <= end; i++) {
				fact_NR = (fact_NR * i) % P;
			}
			
			long fact_R = 1L;
			for (int i = 2, end = R; i <= end; i++) {
				fact_R = (fact_R * i) % P;
			}
			
			long fact_RNR = (fact_NR * fact_R) % P;
			
			// (R! * (N-R)!)의 P-2승 구하기
			answer = (answer * pow(fact_RNR, P - 2)) % P;
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	
	/** a의 n 거듭제곱 : logN의 시간복잡도 */
	static long pow(long a, long n) {
		long res = 1L;
		
		while (n > 0) {
			if (n % 2 == 1) {
				res *= a;
				res %= P;
			}
			a *= a;
			a %= P;
			n /= 2;
		}
		return res;
	}

}
