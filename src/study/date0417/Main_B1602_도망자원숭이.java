package study.date0417;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 도시가 정점에, 소요되는 시간이 비용에 해당되는 문제 
 * 	최단거리에다가 이 경로를 거치는 정점들 중 가장 큰 패널티값까지 더해야 하고 그 결과들 중 가장 작은 걸 찾아야 함.
 *  참고: https://www.acmicpc.net/board/view/8589*/
public class Main_B1602_도망자원숭이 {

	static final int INF = 987654321;
	
	static int N, M, Q; // 도시의 개수 N (2 ≦ N ≦ 500), 도로의 개수 M (0 ≦ M ≦ 10,000), 질문의 개수 Q (0 ≦ Q ≦ 40,000) 
	static int[][] adj;	// 인접 행렬이면서 최소 비용을 저장하는 DP 배열
	static int[][] res;	// adj에서 추가비용을 적용한 결과가 들어있는 DP 배열
	static int[] plusCost;	// 각 정점을 경유할 때 추가로 드는 비용 배열
	static PlusCost[] pcOrder; // 추가 비용을 오름차순으로 정렬하기 위한 배열
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1602.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		adj = new int[N][N];
		res = new int[N][N];
		for (int[] arr : adj) Arrays.fill(arr, INF); // 비용 배열 초기화
		for (int[] arr : res) Arrays.fill(arr, INF); // 비용 배열 초기화
		
		plusCost = new int[N];
		pcOrder = new PlusCost[N]; // 정점 개수로 초기화
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			plusCost[i] = Integer.parseInt(st.nextToken()); // 경유 추가 비용 입력
			pcOrder[i] = new PlusCost(i, plusCost[i]); 
		}
		Arrays.sort(pcOrder);	// 비용의 오름차순으로 정렬 상태 유지
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1; // 정점 번호는 1부터 시작
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			adj[from][to] = adj[to][from] = cost;	// 양방향 그래프
			res[from][to] = res[to][from] = cost + Math.max(plusCost[from], plusCost[to]);	// 양방향 그래프
		}
		
		// 플로이드-와샬 알고리즘을 통해 모든 정점쌍의 최소 비용을 구하여 adj배열에 저장한다.
		// 추가 비용이 적은 정점부터 경유지로 설정하여 비교해 나가야 한다!
		for (int t = 0; t < N; t++) {
			int k = pcOrder[t].i; 		// 경유지의 정점 번호 k에 대하여
			for (int i = 0; i < N; i++) {		// 출발지 i에서
				if (i == k) continue;
				for (int j = 0; j < N; j++) {	// 도착지 j까지의 최소 비용을 구한다.
					if (j == k || j == i) continue;
					// 경유지 k를 거쳐서 가는 비용과 그냥 바로 가는 비용을 비교. (이때 경유지 추가 비용도 함께 계산)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					res[i][j] = Math.min(res[i][j], adj[i][k] + adj[k][j] + Math.max(Math.max(plusCost[i], plusCost[j]), plusCost[k]));
				}
			}
		}
		
		// 결과 출력 (Q개)
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1; // 정점 번호는 1부터 시작
			int to = Integer.parseInt(st.nextToken()) - 1;
			sb.append(res[from][to] == INF ? -1 : res[from][to]).append("\n");
		}
		System.out.println(sb.toString());
	}

	static class PlusCost implements Comparable<PlusCost> { // 추가비용을 정렬하기 위해 만든 클래스
		int i, cost;

		public PlusCost(int i, int cost) {
			this.i = i;
			this.cost = cost;
		}

		@Override
		public int compareTo(PlusCost o) {
			return this.cost - o.cost;
		}
		
	}
}
