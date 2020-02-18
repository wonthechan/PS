package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2206_벽부수고이동하기 {

	static int N, M;
	static char[][] map;
	static boolean[][] visit;
	static int dist = 1;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b2206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// map과 visit 배열 초기화
		map = new char[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
//		System.out.println(bfs());	// (0,0) 부터 시작 해서 (N-1, M-1)에 도달
		if (bfs()) System.out.println(dist);
		else System.out.println(-1);
	}
					//	상	하	좌	우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	
	private static boolean bfs() {
		Queue<Pos> queue = new LinkedList<>();
		visit[0][0] = true;
		queue.offer(new Pos(0,0, false));
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				Pos out = queue.poll();
				if (out.y == N-1 && out.x == M-1) {
					return true;
				}
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny >= 0 && nx >= 0 && ny < N && nx < M 
							&& visit[ny][nx] == false ) {
						if (map[ny][nx] == '0') {
							System.out.println("* " + ny + ", " + nx + ", " + out.chance);
							visit[ny][nx] = true;
							queue.offer(new Pos(ny, nx, out.chance));
						} else {
							if (!out.chance) {
								for (int dir2 = 0; dir2 < 4; dir2++) {
									int nny = ny + dy4[dir2];
									int nnx = nx + dx4[dir2];
									if (nny >= 0 && nnx >= 0 && nny < N && nnx < M 
											&& visit[nny][nnx] == false && map[nny][nnx] == '0') {
//										System.out.println(ny + ", " + nx);
										System.out.println("** " + ny + ", " + nx + ", " + out.chance);
//										out.chance = true;
										map[ny][nx] = '0';
										visit[ny][nx] = true;
										queue.offer(new Pos(ny, nx, true));
									}
								}
							}
						}
					}
				}
			}
			++dist;
		}
		return false;
	}
	
	static class Pos {
		int x, y, dir;
		boolean chance;
		public Pos(int y, int x, boolean chance) {
			this.y = y;
			this.x = x;
			this.chance = chance;
		}
	}
}
