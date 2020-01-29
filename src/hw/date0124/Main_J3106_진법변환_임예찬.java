package hw.date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J3106_진법변환_임예찬 {
	// 입력 진법 (2 <= A <= 36)
	static int A;
	// 출력 진법 (2 <= B <= 36)
	static int B;
	// 변환 대상 수
	static String N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String answer;
		
		while (true) {
			// 입력 진법, 변환 대상, 출력 진법을 차례로 입력 받는다.
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			if (A == 0) break; // 입력 종료 조건
			N = st.nextToken();
			B = Integer.parseInt(st.nextToken());
			
			answer = doBaseConversion(A, N, B);
			System.out.println(answer);
		}
	}

	private static String doBaseConversion(int A, String N, int B) {
		/* A진법을 10진수로 변환 후 다시 B진법으로 변환한다. */
		
		// 0이 입력된 경우 진법 변환 결과는 항상 0이다.
		if (N.equals("0")) return "0";
		
		// 10진수로 변환
		int len = N.length();
		long decimalNum = 0;
		long power = 1;
		for (int i = len - 1; i >= 0; i--) {
			decimalNum += charToInt(N.charAt(i)) * power;
			power *= A;
		}
		
		// 다시 B진법으로 변환
		String result = "";
		while (decimalNum > 0) {
			result = intToChar((int) (decimalNum % B)) + result;
			decimalNum /= B;
		}
		
		return result;
	}

	private static char intToChar(int i) {
		// i는 0 ~ 35 사이에 있어야 한다.
		if (i >= 0 && i <= 9) return (char) ('0' + i);
		
		return (char) ('A' + (i - 10));
	}

	private static int charToInt(char c) {
		// c는 '0' ~ '9' 또는 'A' ~ 'Z' 사이에 있어야 한다.
		if (c >= '0' && c <= '9') return (int) c-'0';
		
		return (c-'A') + 10;
	}
	
}
