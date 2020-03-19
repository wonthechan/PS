package study.date0319;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B1019_책페이지 {

	static long[] nums = new long[10];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1019.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long B = Integer.parseInt(br.readLine());
		long A = 1;
		long pow = 1;
		while (A <= B) {
			while (A % 10 > 0 && A <= B) {
				cal(A++, pow);
			}
			
			while (B % 10 < 9 && A <= B) {
				cal(B--, pow);
			}
			
			if (A > B) break;
			
			A /= 10;
			B /= 10;
			long res = (B - A + 1) * pow;
			for (int i = 0; i < 10; i++) {
				nums[i] += res;
			}
			pow *= 10;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.print(nums[i] + " ");
		}
	}
	
	private static void cal(long num, long pow) {
		while (num > 0) {
			nums[(int) num % 10] += pow;
			num /= 10;
		}
	}

}
