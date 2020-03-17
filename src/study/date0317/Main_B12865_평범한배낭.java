package study.date0317;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 전형적인 Knapsack 문제 */
public class Main_B12865_평범한배낭 {
	
	static int N, K;
	static int[] W, V;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b12865.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		W = new int[N+1];
		V = new int[N+1];
		// 배낭에 넣은 물품의 무게 합이 j일때 얻을 수 있는 최대 가치
		dp = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		// 각 물품번호 i에 따라서 최대 가치 테이블 dp[i][j]를 갱신.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j < W[i]) {
					dp[i][j] = dp[i-1][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
				}
			}
		}
		
		// 전부 갱신이 끝나면 무게가 최종 K일 때 얻을 수 있는 최대 가치를 출력.
		System.out.println(dp[N][K]);
	}
}
