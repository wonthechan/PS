package study.date0309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 한개의 큐로 관리 (넣는 순서에 주의해야함) */
public class Solution_D5_7793_오나의여신님2 {

	static int N, M, answer;
	static char[][] map;
	static Queue<Pos> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s7793.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			queue.clear();
			
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
			
			// 악마와 수연의 위치 넣기
			Pos player = null;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					switch (map[i][j]) {
					case '*':
						queue.offer(new Pos(i, j, true));
						break;
					case 'S':
						player = new Pos(i, j, false);
						break;
					}
				}
			}
			queue.offer(player);
			
			sb.append("#").append(tc).append(" ").append(bfs()? answer+1 : "GAME OVER").append("\n");
		}
		
		System.out.print(sb.toString());
	}
		//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static boolean bfs() {
		int size;
		while(!queue.isEmpty()) {
			size = queue.size();
			while (size-- > 0) {
				Pos out = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.i + dy4[dir];
					int nx = out.j + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
					if (out.isDevil) { // 악마인 경우
						if (map[ny][nx] == '.' || map[ny][nx] == 'S') {
							map[ny][nx] = '*';
							queue.offer(new Pos(ny, nx, true));
						}
					} else {
						if (map[ny][nx] == '.') {
							map[ny][nx] = 'S';
							queue.offer(new Pos(ny, nx, false));
						} else if (map[ny][nx] == 'D') {
							return true;
						}
					}
				}
			}
			++answer;
		}
		return false;
	}

	static class Pos {
		int i, j;
		boolean isDevil;
		public Pos(int i, int j, boolean isDevil) {
			this.i = i;
			this.j = j;
			this.isDevil = isDevil;
		}
	}
}