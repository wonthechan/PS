package date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1309_팩토리얼_임예찬 {

	// 팩토리얼의 숫자 n (1<=n<=15)
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			// 시작범위와 끝 범위를 입력 받는다.
			n = Integer.parseInt(br.readLine());
			if ((n >= 1 && n <= 15)) {
				// 올바른 범위에 있는 경우
				break;
			} else {
				// 주어진 범위를 벗어날 경우
				System.out.println("INPUT ERROR!");
			}
		}
		
		System.out.println(printFactorial(n));
	}
	private static long printFactorial(int n) {
		if (n == 1) {
			System.out.printf("%d! = 1\n", n);
			return 1;
		} else {
			System.out.printf("%d! = %d * %d!\n", n, n, n - 1);
		}
		return n * printFactorial(n-1);
	}
}
