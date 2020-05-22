package study.date0522;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hw_algo0522_서울_7반_임예찬 {

	static final int exp[] = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };				// 0부터 9까지의 Exp
	static final int fact[] = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 }; 	// 0부터 9꺄지의 팩토리얼 수
	
	static int N;
	static int[] weights;
	static int totalWeight;
	static int answer;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/s3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			weights = new int[N];
			totalWeight = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
				totalWeight += weights[i];
			}
			
			answer = 0;
			// 순열을 먼저 구하기
			makePerm(0, 0, 0, 0);
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	private static void makePerm(int level, int visit, int lWeight, int rWeight) {
		if (lWeight > totalWeight / 2) {
			answer += exp[N - level] * fact[N - level];
			return;
		}
		if (level == N) {
			++answer;
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((visit & 1 << i) > 0) continue;
			makePerm(level + 1, visit | 1 << i, lWeight + weights[i], rWeight);
			if (rWeight + weights[i] <= lWeight) {
				makePerm(level + 1, visit | 1 << i, lWeight, rWeight + weights[i]);
			}
		}
	}

}
