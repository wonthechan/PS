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
//		permutation1(0, 0, "");
	}

	private static void permutation1(int index, int sum, String str) {
		/* 뽑은 주사위 눈을 매개변수로 가져간다. */
		if (index == N) {
			// 지금 까지 뽑은 숫자가 담겨있는 배열의 합 구하기
			if(sum == M) {
				System.out.println(str);
			}
			return;
		}
		for (int i = 1; i <= 6; i++) {
			// sum 매개변수에 방금 뽑은 수를 누적.
			permutation1(index+1, sum + i, str + i + " ");
		}
	}

	private static void permutation(int index, int sum) {
		// 현재까지 던진 주사위눈의 합이 목표합을 이미 초과해버린 경우 나머지 경우를 더이상 보지 않는다. => return
		// 가지치기
		if (sum > M) return;
		
		if (index == N) {
			// 지금 까지 뽑은 숫자가 담겨있는 배열의 합 구하기
			if(sum == M) {
				printNumbers();
				System.out.println();
			}
			return;
		}
		
		// 6개의 선택지를 가진다.
		for (int i = 1; i <= 6; i++) {
			numbers[index] = i;
			// sum 매개변수에 방금 뽑은 수를 누적.
			permutation(index+1, sum + i);
		}
		
		/* 아래는 더 직관적인 방법 */
//		numbers[index] = 1;
//		permutation(index+1, sum + 1);
//		numbers[index] = 2;
//		permutation(index+1, sum + 2);
//		numbers[index] = 3;
//		permutation(index+1, sum + 3);
//		numbers[index] = 4;
//		permutation(index+1, sum + 4);
//		numbers[index] = 5;
//		permutation(index+1, sum + 5);
//		numbers[index] = 6;
//		permutation(index+1, sum + 6);
	}
	
	private static void printNumbers() {
		for (int i = 0; i < N; i++) {
			System.out.print(numbers[i] + " ");
		}
	}
}
