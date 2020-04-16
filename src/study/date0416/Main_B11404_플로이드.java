package study.date0416;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B11404_플로이드 {

	final static int INF = 987654321;
	static int N, M;	// 도시의 개수 N(1 ≤ n ≤ 100)과 버스의 개수 M (1 ≤ m ≤ 100,000)
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b11404.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine()); // 버스의 개수 == 간선의 개수
		adj = new int[N][N];	// 인접 행렬 (동시에 최소 비용을 저장)
		
		for (int[] arr : adj) Arrays.fill(arr, INF);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj[from][to] = Math.min(adj[from][to], cost); // 같은 노선이지만 다른 비용을 가진 버스가 여러대일 수 있음
		} // 초기 가중치 입력

		// 플로이드-와샬 알고리즘
		for (int k = 0; k < N; k++) { // 모든 경유지 k에 대해서
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = 0; j < N; j++) { // 출발지 i에서 경유지 k를 거쳐 도착지 j까지의 최소 비용을 구한다.
					if (j == k || j == i) continue;
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		
		// 결과 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(adj[i][j] == INF ? 0 : adj[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
