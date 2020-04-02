package study.date0402;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_6719_성수의프로그래밍강좌시청 {

	static int N, K;
	static double answer;
	static int[] M;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s6719.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) M[i] = Integer.parseInt(st.nextToken()); // 입력 끝
			
			Arrays.sort(M); // 오름 차순 정렬
			
			for (int i = N - K; i < N; i++) {
				answer = (answer + (double) M[i]) / 2;
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

}
