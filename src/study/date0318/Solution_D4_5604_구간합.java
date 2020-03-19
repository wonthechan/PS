package study.date0318;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_5604_구간합 {

	static long A, B, answer;
	static long[] nums;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s5604.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			nums = new long[10];
			
			st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			
			long pow = 1;
			while (A <= B) {
				// B의 일의 자리 수를 9로 맞춰준다.
				while (B % 10 < 9 && A <= B) {
					calc(B--, pow);
				}
				if (A > B) break;
				// A의 일의 자리 수를 0으로 맞춰준다.
				while (A % 10 > 0 && A <= B) {
					calc(A++, pow);
				}
				
				A /= 10;
				B /= 10;
				
				long res = (B - A + 1) * pow;
				for (int i = 1; i <= 9; i++) {
					nums[i] += res;
				}
				
				pow *= 10;
			}
			
			for (int i = 1; i <= 9; i++) {
				answer += (nums[i] * i);
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	private static void calc(long num, long pow) {
		while (num > 0) {
			nums[(int) (num%10)] += pow;
			num /= 10;
		}
	}
}
