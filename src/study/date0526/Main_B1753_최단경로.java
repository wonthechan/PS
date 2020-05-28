package study.date0526;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B1753_최단경로 {

	static int V, E; // 정점의 개수 V와 간선의 개수 E (1≤V≤20,000, 1≤E≤300,000)
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1753.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		List<Edge>[] adj = new ArrayList[V];
		for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
		int startV = Integer.parseInt(br.readLine()) - 1;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			adj[from].add(new Edge(to, weight));
		}// 간선 정보 입력 끝 (간선리스트 구성)

		PriorityQueue<Edge> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
		
		Edge[] D = new Edge[V];
		for (int i = 0; i < V; i++) {
			if (i == startV) {
				D[i] = new Edge(i, 0);
			} else {
				D[i] = new Edge(i, Integer.MAX_VALUE);
			}
			queue.add(D[i]);
		}
		
		
		// 다익스트라 알고리즘
		while (!queue.isEmpty()) {
			Edge current = queue.poll();
			
			if (current.weight == Integer.MAX_VALUE) break; // 연결이 끊어진 경우
			
			for (Edge next : adj[current.vertex]) {
				if (visited[next.vertex]) continue; // 이미 거쳐 갔으면 pass~
				if (D[current.vertex].weight + next.weight < D[next.vertex].weight) {
					D[next.vertex].weight = D[current.vertex].weight + next.weight;
					queue.remove(D[next.vertex]); // 다시 정렬시키기 위해 뺏다가 넣기
					queue.add(D[next.vertex]);
				}
			}
			
			visited[current.vertex] = true;
		}
		
		for (Edge edge : D) {
			sb.append(edge.weight == Integer.MAX_VALUE ? "INF" : edge.weight).append("\n");
		}
		
		System.out.print(sb.toString());
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
	}
}
