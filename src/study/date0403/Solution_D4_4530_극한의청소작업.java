package study.date0403;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 한자리씩 보면서 1,2,3 인 경우 그대로 두고 5, 6, 7, 8, 9 인 경우
 * 1씩 줄여서 9진수로 만들어준다.
 * 마지막으로 9진수의 수를 다시 10진수로 변환하는 과정을 거친다.
 * @author Yechan
 * 참고: https://sxngho.githㅅub.io/ps/2020/02/09/SWEA-4530/
 */
public class Solution_D4_4530_극한의청소작업 {

	static long A, B, answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s4530.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;

			st = new StringTokenizer(br.readLine());
			String strA = st.nextToken();
			String strB = st.nextToken();
			
			String nineA = toNine(strA);
			String nineB = toNine(strB);
					
			A = toDecimal(nineA);
			B = toDecimal(nineB);
			
			answer = B - A;
			if (A < 0 && B > 0) --answer;
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	private static long toDecimal(String nineA) {
		long res = 0L;
		long pow = 1;
		for (int i = nineA.length() - 1; i >= 0; i--) {
			if (nineA.charAt(i) == '-') continue;
			res += (nineA.charAt(i) - '0') * pow;
			pow *= 9;
		}
		return nineA.charAt(0) == '-'? -res : res;
	}

	private static String toNine(String strA) {
		StringBuilder sb = new StringBuilder();
		for (char num : strA.toCharArray()) {
			if (num == '-') {
				sb.append('-');
			} else if ((num - '0') > 4) {
				sb.append(num - '0' - 1);
			} else {
				sb.append(num);
			}
		}
		return sb.toString();
	}

}
