package study.dfs_bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2606_바이러스 {

	static int N; // 정점의 개수
	static int M; // 간선의 개수
	
	static boolean[][] adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_2606.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		
		// 간선 정보 입력 (인접 행렬 입력)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = true;
			adj[b][a] = true;
		}
		
		System.out.println(bfs(1));
	}

	private static int bfs(int item) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[item] = true;
		queue.offer(item);
		while(!queue.isEmpty()) {
			int out = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (adj[out][i] == true && visited[i] == false) {
					cnt++;
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		return cnt;
	}

}
