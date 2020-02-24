package study.date0221;

import java.util.Arrays;
public class 넥퍼연습 {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		Arrays.sort(arr);

		do {
			System.out.println(Arrays.toString(arr));
		} while (np(arr));
	}

	private static boolean np(int[] arr) {
		int N = arr.length;
		// 꼭대기 i 찾기
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		// 종료 조건
		if (i == 0) return false;
		
		// 교환지점 j 찾기
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		
		// swap
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		// 재정렬
		int k = N - 1;
		while (i < k) {
			temp = arr[i];
			arr[i] = arr[k];
			arr[k] = temp;
			++i;--k;
		}
		return true;
	}
}
