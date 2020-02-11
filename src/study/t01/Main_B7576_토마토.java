package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B7576_토마토 {

	static int M, N;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b7576.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visited[i][j] == false) {
					System.out.println(bfs(new Pos(i, j)));
				}
			}
		}
		
	}
	
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int bfs(Pos pos) {		
		Queue<Pos> queue = new LinkedList<Pos>();
		int ret = 0;
		visited[pos.i][pos.j] = true;
		queue.offer(pos);
		while(!queue.isEmpty()) {
			Pos out = (Pos) queue.poll();
			for(int dir = 0; dir < 4; dir++) {	// 4방 탐색
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny >= 0 && nx >= 0 && ny < N && nx < N && 
						visited[ny][nx] == false && map[ny][nx] == 0) {
					visited[ny][nx] = true;
					queue.offer(new Pos(ny, nx));
					ret++;
				}
			}
		}
		return ret;
	}

	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
