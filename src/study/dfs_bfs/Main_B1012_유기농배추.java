package study.dfs_bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B1012_유기농배추 {

	static int M, N, K;
	static int[][] land;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			land = new int[N][M];	// 배열 생성
			visited = new boolean[N][M];	// 배열 생성
			for (int i = 0; i < K; i++) {	// 배추 위치 입력 및 표시
				st = new StringTokenizer(br.readLine());
				int posX = Integer.parseInt(st.nextToken());
				int posY = Integer.parseInt(st.nextToken());
				land[posY][posX] = 1;
			}
			
			int cnt = 0;
			// 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (land[i][j] == 1 && visited[i][j] == false) {
						bfs(new Pos(i, j));
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
					//	상 하 좌 우
	static int dy4[] = {-1, 1, 0, 0};
	static int dx4[] = {0, 0, -1, 1};
	
	private static void bfs(Pos pos) {
		Queue<Pos> queue = new LinkedList<Pos>();
		visited[pos.i][pos.j] = true;
		queue.offer(pos);
		while(!queue.isEmpty()) {
			Pos out = queue.poll();	// 한개 정점 Dequeue
			// 4방 탐색하면서 인접한 정점 파악
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny >= 0 && nx >= 0 && ny < N && nx < M && visited[ny][nx] == false && land[ny][nx] == 1 ) {
					visited[ny][nx] = true;			// Enqueue 하기 전에 visit 처리
					queue.offer(new Pos(ny, nx));	// 인접한 정점은 Enqueue
				}
			}
		}
	}

	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
