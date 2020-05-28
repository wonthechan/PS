package study.date0527;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B1446_지름길 {

	static int N, D;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1446.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		List<Edge>[] adj = new ArrayList[D + 1];

		for (int i = 0; i < D; i++) {
			adj[i] = new ArrayList<>();
			adj[i].add(new Edge(i + 1, 1));
		}
		
		adj[D] = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if (to > D) continue;
			adj[from].add(new Edge(to, weight));
		}
		
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		boolean[] checked = new boolean[D + 1];
		Edge[] dist = new Edge[D + 1];
		
		for (int i = 0;i <= D; i++) {
			if (i == 0) {
				dist[i] = new Edge(i, 0);
			} else {
				dist[i] = new Edge(i, Integer.MAX_VALUE);
			}
			edges.offer(dist[i]);
		}
		
		while (!edges.isEmpty()) {
			Edge current = edges.poll();
			for (Edge next : adj[current.v]) {
				if (checked[next.v]) continue;
				if (dist[current.v].weight + next.weight < dist[next.v].weight) {
					dist[next.v].weight = dist[current.v].weight + next.weight;
					edges.remove(dist[next.v]);
					edges.add(dist[next.v]);
				}
			}
			checked[current.v] = true;
		}
		System.out.println(dist[D].weight);
	}

	static class Edge implements Comparable<Edge> {
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Edge [v=" + v + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
