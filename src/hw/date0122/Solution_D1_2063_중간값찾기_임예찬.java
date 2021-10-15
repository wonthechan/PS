package hw.date0122;

import java.util.Scanner;

public class Solution_D1_2063_중간값찾기_임예찬 {

	public static int[] scores;
	public static int N;
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int temp;
		int medianKey;
		
		// 점수 개수 입력
		N = sc.nextInt();
			
		// 점수를 담을 배열 생성
		scores = new int[N];
		
		// 점수를 입력 받아 배열에 저장
		for (int i = 0; i < N; i++) {
			scores[i] = sc.nextInt();
		}
		
		// 오름차순으로 정렬 (선택 정렬) 진행
		for (int i = 0; i < N - 1; i++) {
			int minKey = i;
			for (int j = i + 1; j < N; j++) {
				if (scores[j] < scores[minKey]) {
					minKey = j;
				}
			}
			if (i == minKey) continue;
			// swap
			temp = scores[minKey];
			scores[minKey] = scores[i];
			scores[i] = temp;
		}
		
		// 중간값 인덱스 설정
		medianKey = (N / 2) + 1;
		
		// 중간값 출력
		System.out.println(scores[medianKey-1]);
		
		sc.close();
	}
}
