package hw.date0122;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D2_1204_최빈수구하기_임예찬 {

	public static int[] scoreFreqArr;
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int score;
		int maxKey;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 점수 빈도를 기록할 배열 생성 (0으로 초기화)
			// 매번 배열을 새로 생성하는 경우 공간복잡도 증가 가능성 있음
			// Arrays.fill(scoreFreqArr, 0);
			scoreFreqArr = new int[101];
			// 테스트 케이스 수 입력 받은 것 날려버리기
			sc.nextInt();
			
			// 1000명의 점수를 입력받는다.
			for (int i = 0; i < 1000; i++) {
				score = sc.nextInt();
				// 해당 점수에 해당되는 배열의 원소 값을 증가
				scoreFreqArr[score]++;
			}
			
			// 빈도 배열을 순회하면서 최댓값 원소의 인덱스를 구한다.
			maxKey = 1;
			for (int i = 2; i < 101; i++) {
				if (scoreFreqArr[i] >= scoreFreqArr[maxKey]) {
					// 점수의 오름차순으로 탐색하기 때문에 빈도수가 같은 경우 높은 점수의 빈도수로 교체된다.
					maxKey = i;
				}
//				if (scoreFreqArr[i] > scoreFreqArr[maxKey]) {
//					maxKey = i;
//				} else if (scoreFreqArr[i] == scoreFreqArr[maxKey]) {
//					// 최빈수가 여러 개 일 때에는 가장 큰 점수를 출력
//					maxKey = (i > maxKey)? i : maxKey;
//				}	
			}
			
			// 빈도 배열의 인덱스가 곧 점수 이므로 최빈수는 maxKey가 된다.
			System.out.println("#" + test_case + " " + maxKey);
		}
		sc.close();
	}
}
