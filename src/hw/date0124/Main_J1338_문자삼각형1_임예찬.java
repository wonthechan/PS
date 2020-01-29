package hw.date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_J1338_문자삼각형1_임예찬 {
	// 문자삼각형의 높이 N (100 이하의 양의 정수)
	static int N;
	// 문자삼각형 배열
	static char[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			// 시작범위와 끝 범위를 입력 받는다.
			N = Integer.parseInt(br.readLine());
			if ((N > 0) && (N <= 100)) {
				// 올바른 범위에 있는 경우
				break;
			} else {
				// 주어진 범위를 벗어날 경우
				System.out.println("INPUT ERROR!");
			}
		}
		
		// N x N 크기의 배열 생성
		arr = new char[N][N];

		// 공백으로 배열 초기화
		initArr();
		
		// 규칙에 따라 알파벳 채우는 과정
		fillArr();
		
		// 완성된 삼각형 출력
		printArr();
	}

	private static void printArr() {
		// 완성된 삼각형 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void fillArr() {
		// 규칙에 따라 알파벳 채우는 과정
		char c = 'A';
		for (int k = 0; k < N; k++) {
			for (int i = k, j = N - 1; i < N; i++, j--) {
				arr[i][j] = c++; // 문자 'A'부터 차례대로 채워나간다.
				if (c == 'Z' + 1) c = 'A'; // 문자 'Z'다음에는 'A'부터 다시 시작한다.
			}	
		}
	}

	private static void initArr() {
		// 공백으로 배열 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], ' ');
		}
	}	
}
