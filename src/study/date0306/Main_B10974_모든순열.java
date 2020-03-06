package study.date0306;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B10974_모든순열 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b10974.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = i+1;
		
		// 넥퍼를 이용한 순열 생성
		do {
			for (int i = 0; i < N; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
		} while(np(arr));
		
		System.out.print(sb.toString());
	}

	private static boolean np(int[] arr) {
		int N = arr.length;
		//
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
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
