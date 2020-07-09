package study.date0709;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B1197_최소스패닝트리 {

	static int V, E; // 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)
	static int[] parents;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(edges);

		makeSet();
		int cnt = 0;
		int answer = 0;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				answer += edge.weight;
				if (++cnt == V - 1) break;
			}
		}
		System.out.println(answer);
	}

	private static void makeSet() {
		parents = new int[V + 1];
		Arrays.fill(parents, -1);
	}

	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if (rootA < rootB) {
			parents[rootB] = rootA;
			return true;
		} else if (rootB < rootA) {
			parents[rootA] = rootB;
			return true;
		}
		return false;
	}
	static class Edge implements Comparable<Edge> {
		int from, to;
		Integer weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight.compareTo(o.weight);
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
}
