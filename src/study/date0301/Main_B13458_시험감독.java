package study.date0301;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정답이 담기는 변수의 범위가 int형을 벗어나는 경우도 고려해야한다.
 * 무작정 풀기전에 입력 데이터의 크기를 미리 파악해본다.(최악의경우 고려)
 */
public class Main_B13458_시험감독 {

	static int N, B, C;	// 시험장의 갯수, 총감독관 관리 가능 응시자 수, 부감독관
	static int[] A;		// 시험장마다 응시생의 수
	static long answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b13458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		answer = 0;
		
		// Greedy?
		for (int i = 0; i < N; i++) {
			int total = A[i];
			++answer;
			total -= B;
			if (total > 0) {
				answer += Math.ceil((float) total / C);
			}
		}
		System.out.println(answer);
	}

}
