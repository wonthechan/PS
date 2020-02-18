package supplement.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//20.02.18
public class Main_B1260_DFS와BFS_서울7반_임예찬 {
	
	static int N, M, V;
	static boolean[][] adj;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adj = new boolean[N+1][N+1];
		visit = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			adj[first][second] = true;
			adj[second][first] = true;
		}
		
		dfs(V);
		System.out.println();
		bfs(V);
	}

	private static void dfs(int e) {
		visit[e] = true;
		System.out.print(e + " ");
		for (int i = 1; i <= N; i++) {
			if (visit[i] == false && adj[e][i] == true) {
				dfs(i);
			}
		}
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		visit[start] = true;
		queue.offer(start);
		while(!queue.isEmpty()) {
			int out = queue.poll();
			System.out.print(out + " ");
			for (int i = 1; i <= N; i++) {
				if (visit[i] == false && adj[out][i] == true) {
					visit[i] = true;
					queue.offer(i);
				}
			}
		}
	}

}