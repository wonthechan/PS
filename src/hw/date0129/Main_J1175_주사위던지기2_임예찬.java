package hw.date0129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1175_주사위던지기2_임예찬 {
	/* 자연수 N과 M을 입력 받아서 
	 * 주사위를 N번 던져서 나온 눈의 합이 M이 나올 수 있는 모든 경우를 출력 (중복 순열 문제)*/
	static int N, M;	// 주사위를 던진 횟수 N(2≤N≤7)과 눈의 합 M(1≤M≤40)
	static int[] numbers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// N 과 M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];	// 뽑는 개수 만큼 크기 생성 (유동적)
		
		permutation(0, 0);
	}

	private static void permutation(int index, int sum) {
		if (index == N) {
			// 지금 까지 뽑은 숫자가 담겨있는 배열의 합 구하기
			if(sum == M) {
				printNumbers();
				System.out.println();
			}
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			numbers[index] = i;
			// sum 매개변수에 방금 뽑은 수를 누적.
			permutation(index+1, sum + i);
		}
	}
	
	private static void printNumbers() {
		for (int i = 0; i < N; i++) {
			System.out.print(numbers[i] + " ");
		}
	}
}
