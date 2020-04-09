package study.date0409;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로 {
	static int N;
	static double E;
	static int[] parents, Y, X;
	static List<Edge> edges = new ArrayList<Edge>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			double answer = 0;
			
			// N 입력 (정점 개수)
			N = Integer.parseInt(br.readLine());
			
			if (N == 1) {
				System.out.println("#" + tc + " " + 0);
			}
			makeSet();
			
			// 정점 위치 정보 입력 (X, Y 좌표)
			Y = new int[N];
			X = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			
			// 간선 리스트 구성 (cost 계산)
			edges.clear();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					edges.add(new Edge(i, j, Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2)));
				}
			}
			
			// Cost 오름차순으로 간선 리스트 정렬
			Collections.sort(edges);
			
			// 크루스칼
			int cnt = 0;
			for (Edge edge : edges) {
				if (union(edge.from, edge.to)) { // Union이 실패한 경우: 사이클이 형성된 경우
					answer += edge.cost * E;
					if (++cnt == N - 1) break;
				}
			}
			
			System.out.println("#" + tc + " " + Math.round(answer));
		}
	}

	private static void makeSet() {
		parents = new int[N];
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
		Double cost;
		public Edge(int y, int x, Double cost) {
			this.from = y;
			this.to = x;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost.compareTo(o.cost);
		}
	}
}
