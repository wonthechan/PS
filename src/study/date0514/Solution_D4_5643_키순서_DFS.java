package study.date0514;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/* DFS를 정방향, 역방향으로 타보자
 * (인접행렬) */
public class Solution_D4_5643_키순서_DFS {

	static int N, M;			// 학생들의 수 N (2 ≤ N ≤ 500), 두 학생의 키를 비교한 횟수 M (0 ≤ M ≤ N*(N-1)/2)
	static boolean[][] adj;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s5643.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().replace(" ", ""));
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			
			N = Integer.parseInt(br.readLine().trim().replace(" ", ""));
			M = Integer.parseInt(br.readLine().trim().replace(" ", ""));
			
			// 인접 행렬 초기화
			adj = new boolean[N + 1][N + 1];
			
			// 방문 배열 초기화
			visited = new boolean[N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().replace(" ", ""));
				int curNode = Integer.parseInt(st.nextToken());
				int childNode = Integer.parseInt(st.nextToken());
				adj[curNode][childNode] = true;
			} // 인접 정보 입력 끝
			
			// 구현 : 모든 정점에서 나보다 큰 사람들 수와 작은 사람들의 수의 합이 N-1 이면 카운트.
			for (int i = 1; i <= N; i++) {
				Arrays.fill(visited, false);
				if (tallerThanMeCnt(i) + shorterThanMeCnt(i) == N - 1) {
					++answer;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	/** DFS (정방향) */
	private static int tallerThanMeCnt(int idx) {
		visited[idx] = true;
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			if (!adj[idx][i]) continue;
			++cnt;
			cnt += tallerThanMeCnt(i);
		}
		return cnt;
	}

	/** DFS (역방향) */
	private static int shorterThanMeCnt(int idx) {
		visited[idx] = true;
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			if (!adj[i][idx]) continue;
			++cnt;
			cnt += shorterThanMeCnt(i);
		}
		return cnt;
	}
}
