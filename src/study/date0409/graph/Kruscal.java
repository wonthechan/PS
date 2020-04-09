package study.date0409.graph;

import java.util.Arrays;

public class Kruscal {
	static int[] parents;
	static int N = 5;
	public static void main(String[] args) {
		makeSet();
		
		// 간선 입력 및 간선 리스트 구성
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

	private static int findSet(int i) {
		if (parents[i] < 0) return i;
		return parents[i] = findSet(parents[i]);
	}
	
	private static void makeSet() {
		parents = new int[N];
		Arrays.fill(parents, -1);
	}
	

	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
}
