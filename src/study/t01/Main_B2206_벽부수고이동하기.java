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
	static boolean[][][] visit;
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
		/*
		 * visit[y][x][0] : 벽을 한번 부순적이 있고 방문한 경우 (true/false) => no dirll now
		 * visit[y][x][1] : 아직 벽을 부수지 않고 방문한 경우 => has drill
		 */
		visit = new boolean[N][M][2];
		
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
		visit[0][0][1] = true;	// 처음이라서 벽을 부술 기회가 있음
		queue.offer(new Pos(0, 0, 1));
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
					if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;	// 범위 이탈
					
					if (map[ny][nx] == '0' && visit[ny][nx][out.hasDrill] == false) {	// 	벽이 없고 방문하지 않은 경우 그냥 간다
						visit[ny][nx][out.hasDrill] = true;
						queue.offer(new Pos(ny, nx, out.hasDrill));
					} else if (map[ny][nx] == '1' && out.hasDrill == 1 && visit[ny][nx][0] == false) {
						// 벽을 만났고 현재 드릴을 쓸 수 있는 상태이고 이전에 드릴을 사용하여 방문한적이 없었던 경우 => 방문!
						visit[ny][nx][0] = true;
						queue.offer(new Pos(ny, nx, 0));	// hasDrill 값을 0으로 (드릴 사용함)
					}
				}
			}
			++dist;
		}
		return false;
	}
	
	static class Pos {
		int x, y, hasDrill;
		public Pos(int y, int x, int hasDrill) {
			this.y = y;
			this.x = x;
			this.hasDrill = hasDrill;
		}
	}
}