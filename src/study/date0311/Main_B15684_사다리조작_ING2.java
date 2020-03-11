package study.date0311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B15684_사다리조작_ING2 {

	static int N, M, H, answer = -1;
	static int[][] adj; // 인접 행렬
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
		
		
//		System.out.println(check(adj));
		
		// 모든 가로선 조합 찾아보기 (완탐) - DFS
		dfs(1, 1, 0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int si, int sd, int cnt) {
//		if (cnt ==1 ) System.out.println("*** " + si + ", " + sd);
//		else System.out.println(si + ", " + sd);
		
		if (check()) {
			answer = cnt;
			return;
		}
		if (cnt == 3) {
//			System.out.println("END");
			return;
		}
		
		for (int i = si; i < N; i++) {
			for (int d = (i == si)? sd : 1; d <= H; d++) {
				// 가로선이 연속되는 경우는 피해야 한다. adj 행렬 값 확인해서 -1이 아닌 경우를 피하자 (이미 가로선이 있으니까)
				if (adj[i][d] > -1 || adj[i+1][d] > -1) continue;
				adj[i][d] = i + 1;
				adj[i + 1][d] = i;
				dfs(i, d, cnt + 1);
				adj[i][d] = -1;
				adj[i + 1][d] = -1;
			}
		}
	}
	
	// 연결성 검사 (i번 세로선의 결과가 i번이 나오도록)
	private static boolean check() {
		for (int i = 1; i <= N; i++) {
			if (findDest(i, 1) != i) return false;
		}
		return true;
	}
	
	private static int findDest(int i, int depth) {
		if (depth > H) return i;
		return adj[i][depth] < 0 ? findDest(i, depth+1) : findDest(adj[i][depth], depth+1);
	}
}
