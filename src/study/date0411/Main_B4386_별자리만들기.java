package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B4386_별자리만들기 {

	static int N;
	static double[] Y;	// 정점의 Y좌표
	static double[] X;	// 정점의 X좌표
	static Edge[] edges;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b4386.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		double answer = 0D;
		N = Integer.parseInt(br.readLine());
		
		if (N == 1) {	// 정점이 한개라면 최소 비용은 항상 똑같다.
			System.out.println(0);
			return;
		}
		
		Y = new double[N];
		X = new double[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			X[i] = Double.parseDouble(st.nextToken());
			Y[i] = Double.parseDouble(st.nextToken());
		} // 정점 좌표 정보 입력
		
		edges = new Edge[N * (N-1) / 2]; // N개의 정점 사이에서 나올 수 있는 모든 간선의 개수 : N개에서 2개를 뽑는 조합
		int idx = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				edges[idx++] = new Edge(i, j, Math.sqrt(Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2)));
			}
		}
		
		makeSet(); // 유니온파인드 준비
		
		Arrays.sort(edges);	// 먼저 간선리스트를 비용의 오름차순으로 정렬된 상태 유지
		
		int cnt = 0;
		for (Edge edge : edges) {
			if (!union(edge.from, edge.to)) continue; // cycle이 생기면 skip!
			answer += edge.cost;
			if (++cnt == N - 1) break;
		}
		
		System.out.println(answer);
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
		int from, to;
		double cost;
		public Edge(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
		
	}
}
