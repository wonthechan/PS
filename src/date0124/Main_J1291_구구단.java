package date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1291_구구단 {

	// 삼각형의 높이 n (1부터 30사이의 정수)과 종류 m (1부터 3사이의 정수)
	static int n;
	static int m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			// 시작범위와 끝 범위를 입력 받는다.
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if ((n >= 1 && n <= 30) && (m >= 1 && m <= 3)) {
				// 올바른 범위에 있는 경우
				break;
			} else {
				// 주어진 범위를 벗어날 경우
				System.out.println("INPUT ERROR!");
			}
		}
		
		printPascal(n, m);
	}
	
	private static void printPascal(int n, int m) {
		for (int i = 0; i < n; i++) {
			
		}
	}

	private static void getPascalNums(int n) {
		
	}
}
