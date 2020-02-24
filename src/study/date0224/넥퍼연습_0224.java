package study.date0224;

import java.util.Arrays;

public class 넥퍼연습_0224 {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		Arrays.sort(arr);
		
		do {
			System.out.println(Arrays.toString(arr));
		} while (np(arr));
	}

	private static boolean np(int[] arr) {
		int N = arr.length;
		// 1. 꼭대기 지점 i 찾기 (역순으로 훝으면서)
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		
		// 종료 조건 : 꼭대기 지점 i를 찾지 못했다. -> 이미 가장 큰 순열에 도달했으므로 더이상 다음 순열이 존재 하지 않음
		if (i == 0) return false;
		
		// 2. i-1 인덱스의 원소와 교환할 지점 j를 찾는다.
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		
		// 3. swap
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		// 4. i부터 재정렬 (오름차순으로)
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}
}
