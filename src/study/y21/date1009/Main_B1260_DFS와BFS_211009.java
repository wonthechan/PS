package study.y21.date1009;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B1260_DFS와BFS_211009 {
	
	// 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V
	static int N;
	static int M;
	static int V;
	
	static boolean[][] adj;		// 인접 여부 2차원 배열 
	static boolean[] visited;	// 정점 방문 여부 배열 
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1260.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 먼저 인접 행렬 초기화
		adj = new boolean[N + 1][N + 1];
		// 방문 배열도 초기화
		visited = new boolean[N + 1];
		
		// 인접 정보 읽어서 2차원 배열에 기록
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from][to] = adj[to][from] = true;
		}
		
		// 이제 방문 시작
		dfs(V);
		System.out.println();
		Arrays.fill(visited, false); // 방문 기록은 초기화!
		bfs(V);
	}
	
	public static void dfs(int current) {
		visited[current] = true; // 방문했습니다~ 
		System.out.print(current + " ");
		// 지금 정점에서 다른 어떤 정점들과 인접해있는지 확인해본다.
		// 그리고 방문한점이 없는 인접 정점이라면 방문한다!
		for (int next = 1; next <= N; next++) {
			if (adj[current][next] == true && visited[next] == false) {
				dfs(next);
			}
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int out = queue.poll();
			System.out.print(out + " ");
			for (int next = 1; next <= N; next++) {
				if (adj[out][next] == true && visited[next] == false) {
					visited[next] = true;
					queue.offer(next);
				}
			}
			
		}
	}
}
