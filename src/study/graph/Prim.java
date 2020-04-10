package study.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prim {
	static int[] parents;
	static int N = 5;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); 	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		int[][] adj = new int[V][V];	// 인접 행렬은 프림과 궁합이 좋다. (둘다 간선이 많을때 유리)
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from][to] = adj[to][from] = Integer.parseInt(st.nextToken());
		} // 간선 정보 입력
		
		boolean[] check = new boolean[V];	// 니편, 내편 flag
		int[] key = new int[V];				// 현재 선택된 정점들로부터 도달할 수 있는 최소거리
		int[] p = new int[V];				// 최소 신장트리의 구조를 저장할 배열
		
		// key의 초기값은 무한대!
		Arrays.fill(key, Integer.MAX_VALUE);
		
		// 아무거나 하나 선택! (처음선택되는 친구가 루트이므로, 부모는 없는 걸로, 거리는 0인걸로)
		p[0] = -1;
		key[0] = 0;
		
		// 이미 하나 골랐으니 나머지 V-1개를 골라보자.
		for(int i = 0; i < V - 1; i++) {
			int min = Integer.MAX_VALUE;
			// 안골라진 친구들 중에서 key가 가장 작은 친구를 뽑자.
			int idx = -1;	// 찾으면 그놈의 위치를 여기에 저장할꺼야.
			for (int j = 0; j < V; j++) {
				// 일단, 안골라진 상태인지 검사하고 key의 최소값을 기억해야함.
				if (!check[j] && key[j] < min) {
					idx = j;
					min = key[j];
				}
			}
			// idx : 선택이 안된 정점 중 key가 젤 작은 친구가 들어있다. :)
			check[idx] = true; // 이제 내편!!
			// idx 정점에서 출발하는 모든 간선에 대해서 key를 업데이트
			for (int j = 0; j < V; j++) {
				// check가 안되어있으면서, 간선은 존재하고, 그간선이 key값보다 작다면, key값을 업데이트!!
				if (!check[j] && adj[idx][j] > 0 && key[j] > adj[idx][j]) {
					p[j] = idx;
					key[j] = adj[idx][j];
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < V; i++) {
			result += key[i];
		}
		System.out.println(result);
		System.out.println(Arrays.toString(p));
	}
}
