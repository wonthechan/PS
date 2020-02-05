package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B1912_연속합 {

	static int[] arr;
	static int[] sum;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_1912.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		// 배열 크기 입력
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sum = new int[N];
		// 배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		sum[0] = arr[0];
		
		// memoization
		for(int i = 1; i < N; i++) {
			sum[i] = (sum[i-1] + arr[i] > arr[i])? sum[i-1] + arr[i] : arr[i];
		}
		
		int max = sum[0];
		
		for(int i = 1; i < N; i++) max = (sum[i] > max)? sum[i] : max;
		
		
		System.out.println(max);
	}
}
