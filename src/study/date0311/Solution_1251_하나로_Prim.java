package study.date0311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 프림 알고리즘을 이용한 풀이 */
public class Solution_1251_하나로_Prim {
	
	static int N;				// 섬의 개수
	static int[] X, Y;			// 섬의 X, Y 좌표를 담는 배열
	static double answer;
	static double E;			// 환경 부담 세율 실수 E
	static double[][] adj;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());	
			X = new int[N];
			Y = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) X[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) Y[i] = Integer.parseInt(st.nextToken());
			E = Double.parseDouble(br.readLine());

			// 입력된 자료를 기반으로 섬 간의 가중치 그래프를 구해보자!
			adj = new double[N][N];
			for (int from = 0; from < N; from++) {
				for (int to = from + 1; to < N; to++) {
					adj[from][to] = adj[to][from] = E * (Math.pow(X[from] - X[to], 2) + Math.pow(Y[from] - Y[to], 2)); 
				}
			}
			
			sb.append("#").append(tc).append(" ").append(Math.round(prim2(0))).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static double prim2(int start) {
		// MST에 들어가지 않은 녀석들.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 모든 정점들을 다 관리....
		Edge[] dynamicGraph = new Edge[N];
		
		for(int n=0; n<dynamicGraph.length; n++) {
			dynamicGraph[n] = new Edge(n, Double.MAX_VALUE);
			//pq.add(dynamicGraph[n]);
			if(n==start) {
				dynamicGraph[n].cost = 0.0;
			}
			pq.add(dynamicGraph[n]);
		}
		
		double cost = 0;
		
		while(!pq.isEmpty()) {
			Edge front = pq.poll();// MST에 포함되는 녀석
			
			cost += front.cost;
			
			// 자식 탐색
			for(int i=0; i<dynamicGraph.length; i++) {
				Edge child =  dynamicGraph[i];
				// pq: 비 MST 
				if(pq.contains(child)) {
					double tempCost = adj[front.idx][child.idx];
					if(tempCost < child.cost) {
						child.cost = tempCost;
						pq.remove(child); // 정렬을 위함
						pq.offer(child);
					}
				}
			}
		}
		return cost;
	}

	static class Edge implements Comparable<Edge> {
		int idx;
		Double cost;
		public Edge(int idx, double cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Edge [idx=" + idx + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}

}
