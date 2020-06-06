package study.date0531;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B9376_탈옥 {

	static int N, M;
	static char[][] map;
	static Queue<Pos> queue = new LinkedList<>();
	static int sy1, sx1, sy2, sx2;	// 죄수 두명의 시작 좌표
	static boolean[][][][] visited = new boolean[100][100][100][100];
	static List<int[]> list = new ArrayList<>();
	static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b9376.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			list.clear();
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					map[i][j] = line[j];
					if (line[j] == '$') {
						list.add(new int[] {i, j});
					}
				}
			}	// 입력 끝
			
			sy1 = list.get(0)[0];
			sx1 = list.get(0)[1];
			sy2 = list.get(1)[0];
			sx2 = list.get(1)[1];
			
			for (boolean[][][] a : visited) {
				for (boolean[][] b : a) {
					for (boolean[] c : b) {
						Arrays.fill(c, false);
					}
				}
			}
			answer = Integer.MAX_VALUE;
			bfs();
			System.out.println(answer);
		}
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static void bfs() {
		queue.clear();
		visited[sy1][sx1][sy2][sx2] = true;
		
		boolean flag1 = false;
		boolean flag2 = false;
		if ((sy1 == 0 || sy1 == N - 1 || sx1 == 0 || sx1 == M - 1)) {
			// 다음칸에서 탈옥한다면
			flag1 = true;
		}
		if ((sy2 == 0 || sy2 == N - 1 || sx2 == 0 || sx2 == M - 1)) {
			// 다음칸에서 탈옥한다면
			flag2 = true;
		}
		
		queue.add(new Pos(sy1, sx1, sy2, sx2, 0, flag1, flag2));
		
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			
			if (out.finished1 && out.finished2) {
				System.out.println(out);
				answer = Math.min(answer, out.cnt);
				continue;
			} else if (out.finished2) {
				for (int dir = 0; dir < 4; dir++) {
					int ny1 = out.y1 + dy4[dir];
					int nx1 = out.x1 + dx4[dir];
					
					if (map[ny1][nx1] == '*') continue; // 벽은 pass
					if (visited[ny1][nx1][out.y2][out.x2]) continue;
					
					visited[ny1][nx1][out.y2][out.x2] = true; 
					
					flag1 = false;
					if ((ny1 == 0 || ny1 == N - 1 || nx1 == 0 || nx1 == M - 1)) {
						// 다음칸에서 탈옥한다면
						flag1 = true;
					}
					
					if (map[ny1][nx1] == '#') {
						queue.add(new Pos(ny1, nx1, out.y2, out.x2, out.cnt + 1, flag1, out.finished2));
					} else {
						queue.add(new Pos(ny1, nx1, out.y2, out.x2, out.cnt, flag1, out.finished2));
					}
				}
				
			} else if (out.finished1) {
				for (int dir = 0; dir < 4; dir++) {
					int ny2 = out.y2 + dy4[dir];
					int nx2 = out.x2 + dx4[dir];
					
					if (map[ny2][nx2] == '*') continue; // 벽은 pass
					if (visited[out.y1][out.x1][ny2][nx2]) continue;
					
					visited[out.y1][out.x1][ny2][nx2] = true; 
					
					flag2 = false;
					if ((ny2 == 0 || ny2 == N - 1 || nx2 == 0 || nx2 == M - 1)) {
						// 다음칸에서 탈옥한다면
						flag2 = true;
					}
					
					if (map[ny2][nx2] == '#') {
						queue.add(new Pos(out.y1, out.x1, ny2, nx2, out.cnt + 1, out.finished1, flag2));
					} else {
						queue.add(new Pos(out.y1, out.x1, ny2, nx2, out.cnt, out.finished1, flag2));
					}
				}
				
			} else {	// 어느 죄수도 탈옥하지 못한 경우
				for (int dir = 0; dir < 4; dir++) {
					int ny1 = out.y1 + dy4[dir];
					int nx1 = out.x1 + dx4[dir];
					// 범위체크 굳이 필요할까?
					if (map[ny1][nx1] == '*') continue; // 벽은 pass
					for (int dir2 = 0; dir2 < 4; dir2++) {
						int ny2 = out.y2 + dy4[dir2];
						int nx2 = out.x2 + dx4[dir2];
						if (map[ny2][nx2] == '*') continue; // 벽은 pass
						if (visited[ny1][nx1][ny2][nx2]) continue;
						
						visited[ny1][nx1][ny2][nx2] = true; // 어찌됬든 두 죄수 모두 한칸씩 이동함.
						
						flag1 = false;
						if ((ny1 == 0 || ny1 == N - 1 || nx1 == 0 || nx1 == M - 1)) {
							// 다음칸에서 탈옥한다면
							flag1 = true;
						}
						
						flag2 = false;
						if ((ny2 == 0 || ny2 == N - 1 || nx2 == 0 || nx2 == M - 1)) {
							// 다음칸에서 탈옥한다면
							flag2 = true;
						}
						
						if (map[ny1][nx1] == '#' && map[ny2][nx2] == '#') {
							// 두 죄수 모두 벽에 막힌 경우
							queue.add(new Pos(ny1, nx1, ny2, nx2, out.cnt + 2, flag1, flag2));
						} else if (map[ny1][nx1] == '#' || map[ny1][nx1] == '#') {
							// 한 죄수만 벽에 막힌 경우
							queue.add(new Pos(ny1, nx1, ny2, nx2, out.cnt + 1, flag1, flag2));
						} else { // 두 죄수 모두 그냥 갈 수 있는 경우
							queue.add(new Pos(ny1, nx1, ny2, nx2, out.cnt, flag1, flag2));
						}
					}
				}
				
			}
		}
	}

	static class Pos {
		int y1, x1, y2, x2, cnt;
		boolean finished1, finished2;
		public Pos(int y1, int x1, int y2, int x2, int cnt, boolean finished1, boolean finished2) {
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
			this.cnt = cnt;
			this.finished1 = finished1;
			this.finished2 = finished2;
		}
		@Override
		public String toString() {
			return "Pos [y1=" + y1 + ", x1=" + x1 + ", y2=" + y2 + ", x2=" + x2 + ", cnt=" + cnt + ", finished1="
					+ finished1 + ", finished2=" + finished2 + "]";
		}
		
	}
}