package study.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선리스트 + 우선순위큐 조합으로 구현해본 다익스트라 알고리즘
// 일반적인 간선 빈도에서는 간선리스트 + 우선순위큐 조합이 권장됨.
public class Dijkstra_PQ {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_dijkstra.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); 	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		List<Edge>[] adj = new ArrayList[V];		// 인접리스트 생성
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {	// 유향 그래프임을 잊지 말자. (단방향)
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to,weight));
		} // 간선 정보 입력
		
		
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		boolean[] check = new boolean[V];	// 처리했는지 안했는지 여부를 저장하는 배열
		Edge[] D = new Edge[V];			// 도달하기 까지 걸리는 거리 저장 배열 
		
		// 0번에서 출발하는걸로.
		for (int i = 0; i < V; i++) {
			// 원하는 출발지
			if (i == 0) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			edges.add(D[i]);
		}
		
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			
			for (Edge next : adj[edge.v]) {
				// check되지 않았으면서 , D[next.v]가 D[edge.v] + next.weight 보다 더 크다면
				if (!check[next.v]&& D[next.v].weight > D[edge.v].weight + next.weight ) {
					D[next.v].weight = D[edge.v].weight + next.weight;
					// decrease key (갱신)
					edges.remove(D[next.v]);
					edges.add(D[next.v]);
				}
			}
			
			check[edge.v] = true; 
		}
		
		// A에서 각 정점으로 도달 할 수 있는 최소 거리를 출력
		System.out.println(Arrays.toString(D));
	}

	static class Edge implements Comparable<Edge>{
		int v, weight;	// 도착 정점과 가중치

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return weight + "";
		}
	}
}
