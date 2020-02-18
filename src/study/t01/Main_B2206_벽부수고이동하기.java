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
		queue.offer(new Pos(0,0));
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
							System.out.println(ny + ", " + nx);
							visit[ny][nx] = true;
							queue.offer(new Pos(ny, nx));
						} else {
							// 
						}
					}
				}
			}
			++dist;
		}
		return false;
	}
	
	static class Pos {
		int x, y;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}