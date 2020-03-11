package study.date0311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B15684_사다리조작_ING1 {

	static int N, M, H;
	static int[][] adj; // 인접 행렬
	static Edge[] edges;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b15684.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 세로선의 개수
		M = Integer.parseInt(st.nextToken());	// 현재 놓여져 있는 가로선의 개수
		H = Integer.parseInt(st.nextToken());	// 놓을 수 있는 가로선의 위치 개수
		
		adj = new int[N+1][H+1]; // N번 세로선에서 H가로선 위치에서 adj[N][H]로 갈 수 있음
		for (int[] arr : adj) Arrays.fill(arr, -1);

		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			// from 세로선에서 from + 1 세로선으로 depth 위치에서 연결됨
			int depth = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			adj[from][depth] = from + 1;
			adj[from+1][depth] = from; 	// 양방향
		}
		
		// 연결성 검사 (i번 세로선의 결과가 i번이 나오도록)
//		System.out.println(check(adj));
		
		// 모든 가로선 조합 찾아보기 (완탐) - 부분집합?
		// 놓을 수 있는 가로선의 개수와 위치 파악
		List<Edge> temp = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			for (int d = 1; d <= H; d++) {
				if (adj[i][d] == -1) {
					temp.add(new Edge(i, d));
				}
			}
		}
		
		edges = new Edge[temp.size()];
		for (int i = 0, end = edges.length; i < end; i++) {
			edges[i] = temp.get(i);
//			System.out.println(edges[i]);
		}
		
//		System.out.println(edges.length);
//		System.out.println("start");
		int bit = (int) Math.pow(2, edges.length);
		int cnt = 0;
		boolean flag = false;
	L1:	for (int b = 0; b < bit; b++) {
			cnt = 0;
			// clone
			int[][] cloned = new int[N+1][H+1];
			for (int i = 1; i <= N; i++) cloned[i] = adj[i].clone();
			
			for (int i = 0, end = edges.length; i < end; i++) {
				if ((b & 1 << i) > 0 && cloned[edges[i].from][edges[i].depth] == -1 && cloned[edges[i].from + 1][edges[i].depth] == -1) {
//					System.out.println(edges[i]);
					++cnt;
					// 가로선이 연속되는 경우는 피해야 한다. adj 행렬 값 확인해서 -1이 아닌 경우를 피하자 (이미 가로선이 있으니까)
					cloned[edges[i].from][edges[i].depth] = edges[i].from + 1;
					cloned[edges[i].from + 1][edges[i].depth] = edges[i].from;
				}
			}
//			System.out.println(check(cloned));
			if (check(cloned)) {
//				System.out.println(cnt);
//				System.out.println("dsfsdf");
				// 연결된 경우 (정답을 찾은 경우)
				for (int i = 1; i <= N; i++) System.out.println(Arrays.toString(cloned[i]));
				flag = true;
				break L1;
			}
//			if (cnt > 3) break L1;
		}
		
		System.out.println(cnt > 3 || !flag ? -1 : cnt);
	}
	
	private static boolean check(int[][] arr) {
		for (int i = 1; i <= N; i++) {
			if (findDest(i, 1, arr) != i) return false;
		}
		return true;
	}
	
	private static int findDest(int i, int depth, int[][] arr) {
		if (depth > H) return i;
		return arr[i][depth] < 0 ? findDest(i, depth+1, arr) : findDest(arr[i][depth], depth+1, arr);
	}

	static class Edge {
		int from, depth; // depth 가로선 위치에서 from 세로선이 from+1 세로선과 연결된다.
		public Edge (int from, int depth) {
			this.from = from;
			this.depth = depth;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[").append("from: ").append(this.from).append(", depth: ").append(this.depth).append("]");
			return sb.toString();
		}
	}
}
