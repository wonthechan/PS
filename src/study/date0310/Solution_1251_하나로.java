package study.date0310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_하나로 {
	
	static int N;				// 섬의 개수
	static int[] X, Y, parents;	// 섬의 X, Y 좌표를 담는 배열
	static double answer;
	static double E;			// 환경 부담 세율 실수 E
	static PriorityQueue<Edge> edges = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			edges.clear();
			
			// 입력
			N = Integer.parseInt(br.readLine());	
			X = new int[N];
			Y = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) X[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) Y[i] = Integer.parseInt(st.nextToken());
			E = Double.parseDouble(br.readLine());
			
			if (N == 1) {	// N이 1일 수도 있음 : 섬이 하나일 수 도 있다? => 부담금 없음.
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}
			
			// 유니온-파인드 준비
			makeSet();
			
			// 가중치가 있는 최소신장트리 구하기 => 크루스칼 활용 (물론 완탐으로 해도됨)
			
			// 일단 섬들간에 이을 수 있는 모든 간선을 구해서 우선순위큐로 저장하자 (가중치(거리)도 함께)
			// 기본적으로 섬들은 자신을 제외한 모든 섬과 인접해 있다.
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					edges.add(new Edge(i, j, E * (Math.pow(Y[i] - Y[j], 2) + Math.pow(X[i] - X[j], 2))  ));
				}
			}
			// 큐에서 하나씩 꺼내면서 MST 만들기 (그때마다 비용 계산)
			while(!edges.isEmpty()) {
				Edge edge = edges.poll();	// 간선 하나 꺼내기
				if (union(edge.from, edge.to)) {
					answer += edge.cost;
				}
			}
			sb.append("#").append(tc).append(" ").append(Math.round(answer)).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if (rootA < rootB) {
			parents[rootB] = rootA;
			return true;
		} else if (rootA > rootB) {
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
		public Edge (int from, int to, double cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge other) {	// cost(가중치)가 작은순으로 정렬
			return this.cost - other.cost < 0 ? -1 : 1;
		}
		
	}

}
