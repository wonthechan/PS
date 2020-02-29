package study.date0229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1) 빨간공과 파란공을 위한 별도의 큐를 두개 만드는 것도 아니고
 * 하나의 큐에 차례로 두개의 공을 넣는것도 아니다. => 너무 복잡
 * 빨간공과 파란공의 좌표 4개를 하나의 객체로 관리하여 큐에 넣고 뺀다.
 * 2) 2차원 배열 맵을 직접 수정하지 않는다. 오직 위치 객체로만 관리.
 * 참고: https://overaction.tistory.com/8
 */
public class Main_B13460_구슬탈출2 {

	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static int dy, dx;	// 도착지(구멍)의 좌표
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
		
		// 빨간 구슬, 파란 구슬, 구멍 위치 찾기
		int ry, rx, by, bx;
		ry = rx = by = bx = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				char ch = map[i][j];
				switch (ch) {
				case 'R':
					ry = i;
					rx = j;
					break;
				case 'B':
					by = i;
					bx = j;
					break;
				case 'O':
					dy = i;
					dx = j;
					break;
				}
			}
		}
		
		// 탐색
		int answer = bfs(ry, rx, by, bx);
		System.out.println(answer);
	}
				// 상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	
	private static int bfs(int ry, int rx, int by, int bx) {
		Queue<Pos> queue = new LinkedList<>();
		visited[ry][rx][by][bx] = true;
		queue.offer(new Pos(ry, rx, by, bx));
		
		int size, cnt = 0;
		Pos out;
		while(!queue.isEmpty()) {
			size = queue.size();
			while (size-- > 0) {
				out = queue.poll();
				// 10번을 초과하여 움직인 경우 더 이상 진행할 필요 X
				if (cnt > 10) return -1;
				// 목적지 도착 확인
				if (out.ry == dy && out.rx == dx) {	// 빨간공이 구멍에 들어간 경우
					return cnt;
				}
				for (int dir = 0; dir < 4; dir++) {
					int nry = out.ry; int nrx = out.rx;
					int nby = out.by; int nbx = out.bx;
					// 빨간공을 먼저 굴려본다. (끝까지)
					while (true) {
						nry += dy4[dir];
						nrx += dx4[dir];
						// 다음 위치에 뭐가 있는지에 따라 다음 위치 조정
						if (map[nry][nrx] == '#') {	// 다음 위치에 벽이 있는 경우
							nry -= dy4[dir];		// 다시 원위치로 한칸 땡긴다
							nrx -= dx4[dir];
							break;					// 더 이상 앞으로 갈 수 없음.
						}
						if (map[nry][nrx] == 'O') {	// 다음 위치에 구멍이 있는 경우
							break;					// 큐에서 뽑을 때 확인할 것
						} // 다음 위치에 '.' 이 있는 경우 계속 앞으로 갈 수 있으므로 break 하지 않는다.
					}
					
					// 그 다음 파란공을 굴려 본다.
					while (true) {
						nby += dy4[dir];
						nbx += dx4[dir];
						// 다음 위치에 뭐가 있는지에 따라 다음 위치 조정
						if (map[nby][nbx] == '#') {	// 다음 위치에 벽이 있는 경우
							nby -= dy4[dir];		// 다시 원위치로 한칸 땡긴다
							nbx -= dx4[dir];
							break;					// 더 이상 앞으로 갈 수 없음.
						}
						if (map[nby][nbx] == 'O') {	// 다음 위치에 구멍이 있는 경우
							break;					// 아래에서 체크 함
						} // 다음 위치에 '.' 이 있는 경우 계속 앞으로 갈 수 있으므로 break 하지 않는다.
					}
					
					// 파란공이 구멍에 빠진 경우 다음 경우의 수(다음 방향)를 본다.
					if (nby == dy && nbx == dx) continue;
					
					// 두개의 공이 겹친 경우 (원래 처음 공의 순서에 따라 위치를 조정해준다)
					if (nry == nby && nrx == nbx) {
						switch (dir) {
						case 0: // 상
							if (out.ry < out.by) ++nby;
							else ++nry;
							break;
						case 1: // 하
							if (out.ry < out.by) --nry;
							else --nby;
							break;
						case 2: // 좌
							if (out.rx < out.bx) ++nbx;
							else ++nrx;
							break;
						case 3: // 우
							if (out.rx < out.bx) --nrx;
							else --nbx;
							break;
						}
					}
					if (visited[nry][nrx][nby][nbx]) continue; // 방문한적이 있는 경우 continue
					visited[nry][nrx][nby][nbx] = true;
					queue.offer(new Pos(nry, nrx, nby, nbx));
				}
			}
			++cnt;
		}
		return -1;
	}

	static class Pos {	// c가 0이면 빨간공, 1이면 파란공.
		int ry, rx, by, bx;
		public Pos(int ry, int rx, int by, int bx) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("red : (").append(this.ry).append(", ").append(this.rx).append(") / ")
				.append("blue : (").append(this.by).append(", ").append(this.bx).append(")");
			return sb.toString();
		}
	}

}
