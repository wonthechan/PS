package study.date0429;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/** 계수 정렬 */
public class hw_algo0429_서울_7반_임예찬2 {

	static int N;			// 사고자 하는 옷의 벌 수 N (1 ≤ N ≤ 100,000)
	static int[] freq = new int[100001];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s4050.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			long answer = 0L;
			Arrays.fill(freq, 0);
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				++freq[Integer.parseInt(st.nextToken())];
			}
			
			int cnt = 1;
			for (int i = 100000; i > 0; i--) {
				while (freq[i]-- > 0) {
					if (cnt++ % 3 == 0) continue;
					answer += i;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}
