package study.date0306;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 비트마스킹을 이용한 다음 순열 생성 (nPn = n!) */
public class Main_B10974_모든순열_BM {

	static int N;
	static int[] arr;
	static int[] result;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b10974.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 입력 및 배열 초기화
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		result = new int [N];
		for (int i = 0; i < N; i++) arr[i] = i+1;
		
		generatePerm(0, 0);
		
		System.out.print(sb.toString());
	}

	private static void generatePerm(int step, int visit) {
		if (step == N) {
			for (int i = 0; i < N; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((visit & 1 << i) > 0) continue; // 이미 선택했다면 continue
			result[step] = arr[i];
			generatePerm(step + 1, visit | 1 << i);
		}
	}

}
