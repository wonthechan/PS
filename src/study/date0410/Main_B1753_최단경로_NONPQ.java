package study.date0410;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/** 인접 행렬 대신 인접 리스트를 사용하고 입출력을 최적화하면 AC 가능 (아슬아슬하게) - PQ는 안쓴 버전
 * @author packe
 */
public class Main_B1753_최단경로_NONPQ {
	
	static final int MAX = Integer.MAX_VALUE;
	static int V, E; 	// 정점의 개수 V와 간선의 개수 E가 주어진다. (1≤V≤20,000, 1≤E≤300,000)
	static int K;		// 시작 정점 K
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1753.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine()) - 1;
		
		List<Edge>[] adj = new ArrayList[V];	// 인접 리스트 생성
		for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
		}
		
		// Dijkstra 준비
		int[] D = new int[V];				// 가중치 저장할 배열
		Arrays.fill(D, MAX); 				// 가중치 배열 초기화 (큰값으로)
		boolean[] check = new boolean[V];	// 처리했는지 여부 저장할 배열
		
		// 시작 정점 셋팅
		D[K] = 0;
		
		// Dijkstra 시작
		for (int i = 0; i < V - 1; i++) {	// 최대 (정점-1)번 만큼만 확인하면 된다 (최대 간선 개수)
			// 후보(아직 처리하지 않은 것들)중에서 가장 가중치가 작은 정점을 고르자
			int min = MAX;
			int idx = -1;
			for (int j = 0; j < V; j++) {
				if (!check[j] && D[j] < min) {
					min = D[j];
					idx = j;
				}
			}
			
			// 해당 정점에서 연결될 수 있는 곳이 없음 (경로가 존재하지 않음)
			if (idx == -1) break;
			
			// 처리 시작 
			// (아직 처리하지 않았고 idx 정점으로 부터 진출할 수 있고 더 작은 가중치인 경우)
			for (Edge next : adj[idx]) {
				if (!check[next.v] && D[idx] + next.w < D[next.v]) {
					D[next.v] = D[idx] + next.w; // j 정점의 가중치 갱신 (더 작은 걸로)
				}
			}
			
			// 처리 완료
			check[idx] = true;
		}
		
		// 정점의 가중치를 차례대로 출력
		// (경로가 존재하지 않는 경우에는 INF를 출력)
		for (int i = 0; i < V; i++) {
			sb.append(D[i] == MAX ? "INF" : D[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 인접리스트를 쓰기 위함
	static class Edge implements Comparable<Edge>{
		int v, w; // 도착 정점과 가중치

		public Edge(int v, int weight) {
			this.v = v;
			this.w = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}

		@Override
		public String toString() {
			return w + "";
		}
	}

}
