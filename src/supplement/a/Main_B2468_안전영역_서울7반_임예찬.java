package supplement.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2468_안전영역_서울7반_임예찬 {

	static int N;
	static int[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int maxHeight = 0;
		int minHeight = 100;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
				minHeight = Math.min(minHeight, map[i][j]);
			}
		}
		
		int maxCnt = 0;
		for (int h = minHeight; h < maxHeight; h++) {
			visit = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > h && visit[i][j] == false) {
//						bfs(new Pos(i, j), h);
						dfs(i, j, h);
						++cnt;
					}
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}
		
		if (maxCnt == 0) maxCnt = 1; // 최소높이와 최대 높이가 같은 경우 (루프문을 아예 돌지 않음)
		System.out.println(maxCnt);
	}
					// 상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0}; 
	static int[] dx4 = {0, 0, -1, 1}; 
	
	
	private static void dfs(int i, int j, int h) {
		visit[i][j] = true;
		for (int dir = 0; dir < 4; dir++) {
			int ny = i + dy4[dir];
			int nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N || 
					visit[ny][nx] == true || map[ny][nx] <= h) continue;
			dfs(ny, nx, h);
		}
	}
	private static void bfs(Pos pos, int height) {
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(pos.i, pos.j));
		
		while(!queue.isEmpty()) {
			Pos out = queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || 
						visit[ny][nx] == true || map[ny][nx] <= height) continue;
				
				visit[ny][nx] = true;
				queue.add(new Pos(ny, nx));
			}
		}
		
	}
	
	static class Pos {
		int i, j;
		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
