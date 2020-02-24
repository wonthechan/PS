package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 분류는 유니온파인드로 되어 있지만, 그래프의 연결성 확인 문제인만큼 DFS나 BFS를 통해서도 풀이가 가능했다. */
public class Main_B1976_여행가자_DFS {

	static int N, M;
	static int[][] adj;
	static boolean[] visited;
	static int[] path;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1976.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 인접행렬 및 방문관리 배열 생성과 초기화
		adj = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		path = new int[M + 1];
		
		// 인접행렬 입력
		for (int i = 1; i <= N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		// DFS를 통한 인접 확인
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			path[i] = Integer.parseInt(st.nextToken());
		}
		
		DFS(path[1]);
		
		for (int i = 1; i <= M; i++) {
			if (!visited[path[i]]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	private static void DFS(int from) {
		visited[from] = true;	// 방문 처리
		for (int i = 1; i <= N; i++) {
			if (visited[i] == false && adj[from][i] == 1) {
				DFS(i);
			}
		}
	}
}
