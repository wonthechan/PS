package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D2_1966_숫자를정렬하자 {
	
	static int[] arr;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1966.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			for (int i = 0; i < N; i++) sb.append(arr[i]).append(" ");
			
			System.out.println(sb.toString());
		}
	}
}
