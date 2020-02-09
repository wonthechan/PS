package study.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B11399_ATM {

	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		// 배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 오름차순 정렬
		Arrays.sort(arr);
		
		// 최소 합 계산
		int total = 0;
		int acc = 0;
		for (int i = 0; i < N; i++) {
			acc += arr[i];
			total += acc;
		}
		System.out.println(total);
	}
}
