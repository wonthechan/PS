package study.date0709;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 힙으로 다익스트라 구현 NlogN
public class Main_B1753_최단경로 {

	static int V, E; // 정점의 개수 V와 간선의 개수 E (1≤V≤20,000, 1≤E≤300,000)
	static final int MAX = 987654321;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1753.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		// 인접 리스트 생성
		List<Edge>[] adj = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) adj[i] = new ArrayList<>();
		 
		int startV = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
		}
		
		// 다익스트라 사전 준비
		PriorityQueue<Edge> pq = new PriorityQueue<>(); // 항상 가중치가 가장 낮은 간선을 뽑는다.
		boolean[] visited = new boolean[V + 1];			// 방문 관리 배열
		Edge[] dist = new Edge[V + 1];
		for (int i = 1; i < V + 1; i++) {
			if (i == startV) {
				dist[i] = new Edge(i, 0);
			} else {
				dist[i] = new Edge(i, MAX);
			}
			pq.offer(dist[i]);
		}
		
		// 다익스트라 시작
		while (!pq.isEmpty()) {
			Edge currentV = pq.poll();
			for (Edge edge : adj[currentV.vertex]) {
				if (visited[edge.vertex]) continue;
				if (dist[currentV.vertex].weight + edge.weight < dist[edge.vertex].weight) {
					dist[edge.vertex].weight = dist[currentV.vertex].weight + edge.weight;
					pq.remove(dist[edge.vertex]);
					pq.offer(dist[edge.vertex]);
				}
			}
			visited[currentV.vertex] = true;
		}
		
		for (int i = 1; i < V + 1; i++) {
			sb.append(dist[i].weight == MAX ? "INF" : dist[i].weight).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static class Edge implements Comparable<Edge> {
		int vertex, weight;
		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		@Override
		public String toString() {
			return "Edge [vertex=" + vertex + ", weight=" + weight + "]";
		}
	}
}
