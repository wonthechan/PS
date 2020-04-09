package study.date0409;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B1197_최소스패닝트리 {

	static int V, E;		// 정점과 간선의 개수
	static int[] parents;	// 유니온파인드에 사용할 parents 배열
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[E]; // 나올 수 있는 간선의 개수만큼 배열 공간 할당
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()) - 1,
									Integer.parseInt(st.nextToken()) - 1,
										Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edges);	// 미리 가중치 오름차순으로 정렬
		makeSet(); // 정점의 개수만큼 makeSet!
		
		// Kruskal
		int cnt = 0;
		int answer = 0;
		for (int i = 0; i < E; i++) {
			if (union(edges[i].from, edges[i].to)) {
				answer += edges[i].cost;
				if (++cnt == V - 1) break;
			}
		}
		
		System.out.println(answer);
	}
	
	private static void makeSet() {
		parents = new int[V];
		Arrays.fill(parents, -1);
	}
	
	private static int findSet(int i) {
		if (parents[i] < 0) return i;
		return parents[i] = findSet(parents[i]);
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
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		Integer cost;
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost.compareTo(o.cost);
		}
	}
}
