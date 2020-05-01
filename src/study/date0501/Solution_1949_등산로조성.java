package study.date0501;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성 {

	static int N, K;	// 지도의 한 변의 길이 N (3 ≤ N ≤ 8), 최대 공사 가능 깊이 K (1 ≤ K ≤ 5)
	static int[][] map;
	static boolean[][] visited;
	static List<Pos> list = new ArrayList<>();
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int maxH = -1;
			list.clear();
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, map[i][j]); // 가장 높은 봉우리를 찾는다.
				}
			} // 입력 완료
			
			// output: 만들 수 있는 가장 긴 등산로의 길이
			
			// 가장 높은 봉우리를 찾는다. (개수는 최대 5개)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxH) list.add(new Pos(i, j));
				}
			}
			
			// 봉우리 지점에서 dfs 탐색 시작 (규칙 만족 하면서)
			for (Pos pos : list) {
				dfs(pos.y, pos.x, 1, false);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}

	//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static void dfs(int y, int x, int curH, boolean useChance) {
		visited[y][x] = true;
		answer = Math.max(answer, curH);
		
		// 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
		for (int dir = 0; dir < 4; dir++) {
			int ny = y + dy4[dir];
			int nx = x + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;
			if (map[ny][nx] < map[y][x]) { // 반드시 높은 지형에서 낮은 지형으로
				dfs(ny, nx, curH + 1, useChance);
			} else if (!useChance && map[ny][nx] - K < map[y][x]) {
				// 공사를 통해서 지나갈 수 있는 경우
				// clone map
				for (int k = 1; k <= K; k++) {
					if (map[ny][nx] - k < map[y][x]) {
						map[ny][nx] -= k;
						dfs(ny, nx, curH + 1, true);
						map[ny][nx] += k;	// 원상복귀 (굳이 clone map 할필요 없음)
					}
				}
			}
		}
		visited[y][x] = false;
	}
	
	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
