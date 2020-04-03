package study.date0403;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B1699_제곱수의합 {

	static int N, answer;
	static int[][] dp = new int[101][100001];
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b1699.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		answer = 0;
		
		for (int i = 1; i <= 100; i++) {
			
		}
		while (N > 0) {
			int d = (int) Math.sqrt(N);
			System.out.println(d);
			N -= Math.pow(d, 2);
			++answer;
		}
		
		System.out.println(answer);
	}
}
