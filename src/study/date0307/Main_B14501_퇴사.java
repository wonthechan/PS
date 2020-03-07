package study.date0307;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B14501_퇴사 {

	static int N;
	static int[] T;
	static int[] P;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14501.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		// 부분집합을 구해서 모든 경우를 살펴보자
		int max = (int) Math.pow(2, N);
		int sum, visit;	// 비트마스킹
		for (int bit = 1; bit <= max; bit++) {
			sum = 0;
			visit = 0;
			for (int i = 0; i < N; i++) {
				if ((bit & 1 << i) > 0) {
					if (i + T[i] > N || (visit & 1 << i) > 0) continue; // 회사에 없는 경우 | 이미 예약된 경우 상담 못함
					for (int j = i, end = i + T[i]; j < end; j++) visit |= 1 << j;
					sum += P[i];
				}
			}
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
	}

}
