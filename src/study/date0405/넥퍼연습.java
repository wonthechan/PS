package study.date0405;

import java.util.Arrays;

public class 넥퍼연습 {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		
		do {
			System.out.println(Arrays.toString(nums));
		} while (np(nums));
	}

	private static boolean np(int[] arr) {
		int N = arr.length;
		// 꼭대기 i찾기
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		// j 지점 찾기
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		// swap
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		// re sort
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i; --j;
		}
		return true;
	}

}
