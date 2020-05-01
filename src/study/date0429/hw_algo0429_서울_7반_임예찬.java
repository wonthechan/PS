package study.date0429;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class hw_algo0429_서울_7반_임예찬 {

	static int N;			// 사고자 하는 옷의 벌 수 N (1 ≤ N ≤ 100,000)
	static Integer[] clothes;	//
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/s4050.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			long answer = 0L;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			clothes = new Integer[N];
			for (int i = 0; i < N; i++) clothes[i] = Integer.parseInt(st.nextToken());
			
			if (N < 3) {
				for (int i = 0; i < N; i++) answer += clothes[i];
			} else {
				// 적어도 3개 이상인 경우
				Arrays.sort(clothes, Collections.reverseOrder());
				int i;
				for (i = 0; i + 2 < N; i += 3) {
					answer += clothes[i];
					answer += clothes[i+1];
				}
				while (i < N) {
					answer += clothes[i++];
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}
