package study.date0224;

import java.util.Arrays;

// 참고 : https://heekim0719.tistory.com/289
public class MergeSort_Practice {

	static int[] sorted_arr;
	public static void main(String[] args) {
		int[] init_arr = {9,8,7,6,5,4,3,2,1};
		sorted_arr = new int[init_arr.length];	// merge 단계에서 정렬된 결과를 저장할 배열 생성
		
		mergeSort(init_arr, 0, init_arr.length - 1);
		System.out.println(Arrays.toString(init_arr));
	}

	/** Divide part */
	private static void mergeSort(int[] arr, int start, int end) {
		if (start < end) {	// start가 end 보다 같거나 커지는 순간 원소는 한개임을 알 수 있다.
			int mid = ( start + end ) / 2;
			mergeSort(arr, start, mid);		// 절반으로 분할한 앞 부분 배열
			mergeSort(arr, mid + 1, end);	// 절반으로 분할한 뒷 부분 배열
			merge(arr, start, mid, end);	// 병합 부분
		}
	}

	/** Conquer & Merge part */
	private static void merge(int[] arr, int start, int mid, int end) {
		int i = start;		// 앞 부분 배열의 시작 인덱스
		int j = mid + 1;	// 뒷 부분 배열의 시작 인덱스
		int k = start;		// 정렬된 결과를 저장할 배열의 시작 인덱스
		
		while (i <= mid && j <= end) {
			if (arr[i] < arr[j]) { // 앞 부분 배열의 인덱스 1 증가 (해당 자리의 원소를 정렬된 배열에 넣었으니까)
				sorted_arr[k++] = arr[i++];
			} else {
				sorted_arr[k++] = arr[j++];
			}
		}
		
		// 남은 부분은 정렬 배열에 덧붙여준다.
		if (i > mid) {		// 뒷부분 배열에 남은 원소가 있는 경우
			while (j <= end) {
				sorted_arr[k++] = arr[j++];
			}
		} else {			// 앞부분 배열에 남은 원소가 있는 경우
			while (i <= mid) {
				sorted_arr[k++] = arr[i++];
			}
		}
		
		// 원본 배열에 정렬 결과를 반영한다.
		// 현재 정렬 배열에는 인덱스 start부터 end까지 정렬된 결과가 들어 있다.
		for (int t = start; t <= end; t++) {
			arr[t] = sorted_arr[t];
		}
	}
}
