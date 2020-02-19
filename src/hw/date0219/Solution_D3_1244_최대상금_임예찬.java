package hw.date0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금_임예찬 {

	static int[] numbers;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int r = Integer.parseInt(st.nextToken());
			N = str.length();
			numbers = new int[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = str.charAt(i) - '0';
			}
			
			
			
			
			
			
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	private static void permutation(int idx) {
		if (idx == R) {
			++totalCount;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 0; i < N; i++) {
			swap(idx, i);
			permutation(idx + 1);
			swap(idx, i);
		}
	}
	
	private static void swap(int a, int b) {	// 인자는 인덱스를 받는다.
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}

}