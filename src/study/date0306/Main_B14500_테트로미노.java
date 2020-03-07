package study.date0306;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 16:55
public class Main_B14500_테트로미노 {

	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14500.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, map[i][j], 1);	// 자신을 포함해서 시작 (T 모양을 제외한 모든 모양의 테트로미노 확인)
				checkTShape(i, j);			// T 모양 테트로미노 확인
			}
		}
		System.out.println(answer);
	}
	
	//	상 우 하 좌 (시계방향)
	static int[] dy4 = {-1, 0, 1, 0};
	static int[] dx4 = {0, 1, 0, -1};
	private static void checkTShape(int i, int j) {
		// ㅏ ㅜ ㅓ ㅗ 순서로 확인
		int sum;
	L1:	for (int startDir = 0; startDir < 4; startDir++) {
			sum = map[i][j];
			for (int dir = startDir, end = dir + 3; dir < end; dir++) {
				int ny = i + dy4[dir%4];
				int nx = j + dx4[dir%4];
				if (ny < 0|| nx < 0 || ny >= N || nx >= M) continue L1;
				sum += map[ny][nx];
			}
			answer = Math.max(answer, sum);
		}
	}
	/** DFS 탐색을 통해 재귀적으로
	 * 1x1 정사각형을 현재에서 하나씩 이어붙여 나가는 식으로 테트로미노 모양을 완성
	 */
	private static void dfs(int i, int j, int sum, int cnt) {
		if (cnt == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		visit[i][j] = true;
		for (int dir = 0; dir < 4; dir++) {
			int ny = i + dy4[dir];
			int nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]) continue;
			dfs(ny, nx, sum + map[ny][nx], cnt + 1);
		}
		visit[i][j] = false;
	}
}
