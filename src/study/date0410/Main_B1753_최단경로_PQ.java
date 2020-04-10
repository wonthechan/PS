package study.date0410;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 우선순위큐 + 인접리스트 조합
 * 일반적인 간선의 개수 상황에 적합함.
 * @author packe
 */
public class Main_B1753_최단경로_PQ {
	
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
		K = Integer.parseInt(br.readLine());
		
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
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		Edge[] D = new Edge[V];				// 가중치 저장할 배열
		boolean[] check = new boolean[V];	// 처리했는지 여부 저장할 배열
		
		// 가중치 배열 초기화
		// 이렇게 하는 이유? 나중에 우선순위큐에서 Edge 갱신하려고! (주소값이 같아야하니까)
		for (int i = 0; i < V; i++) {
			// 원하는 출발지 K
			if (i == K - 1) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, MAX);
			}
			edges.add(D[i]);
		}
		
		// Dijkstra 시작
		while (!edges.isEmpty()) {	// 최대 (정점-1)번 만큼만 확인하면 된다 (최대 간선 개수)
			// 후보(아직 처리하지 않은 것들)중에서 가장 가중치가 작은 정점을 고르자
			Edge out = edges.poll();
			
			// 연결그래프가 아닌 경우를 고려 (처음에 빠트린 조건) : 더이상 진행할 필요가 없음!!
			if (out.w == MAX) break;
			
			// 처리 시작 
			// (아직 처리하지 않았고 idx 정점으로 부터 진출할 수 있고 더 작은 가중치인 경우)
			for (Edge next : adj[out.v]) {
				if (!check[next.v] && D[out.v].w + next.w < D[next.v].w ) {
					D[next.v].w = D[out.v].w + next.w;
					// decrease key (갱신)
					edges.remove(D[next.v]);
					edges.add(D[next.v]);
				}
			}
			
			// 처리 완료
			check[out.v] = true;
		}
		
		// 정점의 가중치를 차례대로 출력
		// (경로가 존재하지 않는 경우에는 INF를 출력)
		for (int i = 0; i < V; i++) {
			sb.append(D[i].w == MAX ? "INF" : D[i].w).append("\n");
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
