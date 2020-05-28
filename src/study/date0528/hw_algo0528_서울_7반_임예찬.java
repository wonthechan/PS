package study.date0528;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class hw_algo0528_서울_7반_임예찬 {

	// 상 우 하 좌
	static final int[] dy4 = {-1, 0, 1, 0};
	static final int[] dx4 = {0, 1, 0, -1};
	
	static final int[][] typeTunnel = {	// 터널 종류 별로 갈 수 있는 방향 hardcoding
			{},	
			{0, 1, 2, 3},	
			{0, 2},	
			{1, 3},	
			{0, 1},	
			{1, 2},	
			{2, 3},	
			{0, 3},	
	};
	
	static int N, M, holeY, holeX, L;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/s1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			holeY = Integer.parseInt(st.nextToken());
			holeX= Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝
			
			System.out.println("#" + tc + " " + bfs());
//			for (boolean[] b : visited) System.out.println(Arrays.toString(b));
		}
	}
	
	private static int bfs() {
		int answer = 1;
		int hours = 0;
		visited[holeY][holeX] = true;
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(holeY, holeX));
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			if (hours == L - 1) break;	// 시간이 다 지나면 탐색 중지 (한시간은 이미 씀)
			
			while (size-- > 0) {
				Pos out = queue.poll();
				
				// 터널별로 갈 수 있는 방향이 전부 다르다.
				int[] dirs = typeTunnel[map[out.y][out.x]];
				for (int k = 0; k < dirs.length; k++) {
					int dir = dirs[k];
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
					if (map[ny][nx] == 0) continue;	// 다음칸에 터널이 아예 없는 경우
					if (visited[ny][nx]) continue;
					switch (dir) {	// 다음 칸에서 터널이 이어지는 경우에만 진행
					case 0:	// 위 방향
						if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
							visited[ny][nx] = true;
							queue.offer(new Pos (ny, nx));
							++answer;
						}
						break;
					case 1:	// 오른쪽
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
							visited[ny][nx] = true;
							queue.offer(new Pos (ny, nx));
							++answer;
						}
						break;
					case 2:	// 아래쪽
						if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
							visited[ny][nx] = true;
							queue.offer(new Pos (ny, nx));
							++answer;
						}
						break;
					case 3:	// 왼쪽
						if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
							visited[ny][nx] = true;
							queue.offer(new Pos (ny, nx));
							++answer;
						}
						break;
					}
				}
			}
			++hours;
		}
		return answer;
	}
	
	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + "]";
		}
	}

}
