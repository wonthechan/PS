package study.date0514;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/* 플로이드 알고리즘을 이용 */
public class Main_B2458_키순서_Floyd {
	static final int INF = 987654321;
	
	static int N, M;	// 학생들의 수 N (2 ≤ N ≤ 500), 두 학생의 키를 비교한 횟수 M (0 ≤ M ≤ N*(N-1)/2)
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b2458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int answer = 0;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 인접 행렬 초기화
		adj = new int[N + 1][N + 1];
		for (int[] b : adj) Arrays.fill(b, INF);
		for (int i = 1; i <= N; i++) adj[i][i] = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from][to] = 1;
		} // 인접 정보 입력 끝
		
		/* Floyd */
		// k = 거쳐가는 노드
		for (int k = 1; k <= N; k++) {
			// i = 출발 노드 
			for (int i = 1; i <= N; i++) {
				// i = 도착 노드 
				for (int j = 1; j <= N; j++) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		// i번째 행과 열을 조사해서 1 이상인 것을 카운트하여 총합이 N - 1인 경우 정답!
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int k = 1; k <= N; k++) {
				if (adj[i][k] != INF) {
					cnt += adj[i][k] > 0 ? 1 : 0; // i 번째 행
				}
				if (adj[k][i] != INF) {
					cnt += adj[k][i] > 0 ? 1 : 0; // i 번째 열
				}
			}
			if (cnt == N - 1) ++answer;
		}
		System.out.println(answer);
	}
}
