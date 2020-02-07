package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B1260_DFS와BFS {

	static int N, M, V;

	static boolean[][] adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_1260.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 인접 행렬 생성
		adj = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = true;
			adj[b][a] = true;	// 양방향
		}
		
		dfs(V);
		System.out.println();
		Arrays.fill(visited, false);
		bfs(V);
	}

	
	private static void dfs(int item) {
		visited[item] = true;
		System.out.print(item + " ");
		for (int i = 1; i <= N; i++) {
			if (adj[item][i] == true && visited[i] == false) {
				dfs(i);
			}
		}
	}


	private static void bfs(int item) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[item] = true;
		queue.offer(item);
		
		while(!queue.isEmpty()) {
			int out = queue.poll();
			System.out.print(out + " ");
			// 인접 정점이 있는 지 확인
			for (int i = 1; i <= N; i++) {
				if (adj[out][i] == true && visited[i] == false) {
					visited[i] = true; 	// 인접 정점은 큐에 넣기 전에 visit 처리
					queue.offer(i); 	// 인접 정점은 큐에 삽입
				}
			}
		}
	}

}
