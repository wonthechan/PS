package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 두번째 풀이 (BFS)
public class Main_B2206_벽부수고이동하기 {

	static int N, M;
	static int[][] map;
	static boolean[][][] visit;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b2206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		} // 입력 끝
		
		int answer = bfs();
		System.out.println(answer);
	}
					// 상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int bfs() {
		Queue<Pos> queue = new LinkedList<>();
		visit[0][0][1] = true;
		queue.offer(new Pos(0, 0, true));	// 시작점 : (0,0)
		int dist = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos out = queue.poll();
				
				if (out.y == N - 1 && out.x == M - 1) {
					return dist + 1;
				}
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
					if (map[ny][nx] == 0 && !visit[ny][nx][out.hasDrill? 1 : 0]) { // 길이 있고 방문하지 않았던 곳이라면
						visit[ny][nx][out.hasDrill? 1 : 0] = true;
						queue.offer(new Pos(ny, nx, out.hasDrill));
					} else {	// 벽에 막힌 경우
						if (out.hasDrill && !visit[ny][nx][0]) { // 현재 드릴이 남아 있고 벽을 부수고 방문한적이 없는 곳이라면
							visit[ny][nx][0] = true;
							queue.offer(new Pos(ny, nx, false));
						}
					}
				}
			}
			++dist;
		}
		return -1;
	}
	
	static class Pos {
		int y, x;
		boolean hasDrill;
		public Pos(int y, int x, boolean hasDrill) {
			this.y = y;
			this.x = x;
			this.hasDrill = hasDrill;
		}
	}
}
