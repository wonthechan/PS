package study.date0323;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_kakao20공채7_블록이동하기 {

	static int N;
	static int[][] map;
	static int[][] visit;
	public static void main(String[] args) throws Exception {
		int[][] board = {
				{0, 0, 0, 1, 1},
				{0, 0, 0, 1, 0},
				{0, 1, 0, 1, 1},
				{1, 1, 0, 0, 1},
				{0, 0, 0, 0, 0}};
		System.out.println(solution(board));
	}
	
	public static int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        map = new int[N+2][N+2];	// 경계체크를 위해 4면을 1로 둘러싼다.
        for (int[] arr : map) Arrays.fill(arr, 1);
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		map[i+1][j+1] = board[i][j];
        	}
        }
        visit = new int[N+2][N+2];
        // bfs 탐색
        answer = bfs(1, 1, 3);
        return answer;

	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	static int[] rotate = {-1, 1};
	private static int bfs(int i, int j, int k) {
		Queue<Pos> queue = new LinkedList<>();
		visit[i][j] |= 1 << k;
		queue.offer(new Pos(i, j, k, 0));
		while(!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos out = queue.poll();
				// 도달 확인
				if ((out.i == N  && out.j == N) || (out.i + dy4[out.dir] == N && out.j + dx4[out.dir] == N)) {
					System.out.println("sdfsd");
					return out.dist;
				}
				
				// 한칸 가거나
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.i + dy4[dir];
					int nx = out.j + dx4[dir];
					if (map[ny][nx] == 1 || map[ny + dy4[out.dir]][nx + dx4[out.dir]] == 1 || (visit[ny][nx] & 1 << out.dir) > 0) continue;
					visit[ny][nx] |= 1 << out.dir; // visit 체크 : 기준점에 해당 방향을 비트마스킹
					queue.offer(new Pos(ny, nx, out.dir, out.dist + 1));
				}
				// 회전 하거나
				if (out.dir == 3) {
					/// ㅡ 방향인 경우
					//// 위로 회전 가능한 경우와 아래로 회전 가능한 경우를 확인
					for (int r = 0; r < 2; r++) {
						if (map[out.i + rotate[r]][out.j] == 0 && map[out.i + rotate[r]][out.j + 1] == 0) {
							if (r == 0) {	// 위로 회전
								if ((visit[out.i + rotate[r]][out.j] & 1 << 1) == 0) {
									visit[out.i + rotate[r]][out.j] |= 1 << 1;
									queue.offer(new Pos(out.i + rotate[r], out.j, 1, out.dist + 1));
								}
								if ((visit[out.i + rotate[r]][out.j + 1] & 1 << 1) == 0) {
									visit[out.i + rotate[r]][out.j + 1] |= 1 << 1;
									queue.offer(new Pos(out.i + rotate[r], out.j + 1, 1, out.dist + 1));
								}
							} else {		// 아래로 회전
								if ((visit[out.i][out.j] & 1 << 1) == 0) {
									visit[out.i][out.j] |= 1 << 1;
									queue.offer(new Pos(out.i, out.j, 1, out.dist + 1));
								}
								if ((visit[out.i][out.j + 1] & 1 << 1) == 0) {
									visit[out.i][out.j + 1] |= 1 << 1;
									queue.offer(new Pos(out.i, out.j + 1, 1, out.dist + 1));
								}
							}
						}
					}
				} else {
					/// ㅣ 방향인 경우
					//// 위로 회전 가능한 경우와 아래로 회전 가능한 경우를 확인
					for (int r = 0; r < 2; r++) {
						if (map[out.i][out.j + rotate[r]] == 0 && map[out.i + 1][out.j + rotate[r]] == 0) {
							if (r == 0) {
								if ((visit[out.i][out.j + rotate[r]] & 1 << 3) == 0) {
									visit[out.i][out.j + rotate[r]] |= 1 << 3;
									queue.offer(new Pos(out.i, out.j + rotate[r], 3, out.dist + 1));
								}
								if ((visit[out.i + 1][out.j + rotate[r]] & 1 << 3) == 0) {
									visit[out.i + 1][out.j + rotate[r]] |= 1 << 3;
									queue.offer(new Pos(out.i + 1, out.j + rotate[r], 3, out.dist + 1));
								}
							} else {
								if ((visit[out.i][out.j] & 1 << 3) == 0) {
									visit[out.i][out.j] |= 1 << 3;
									queue.offer(new Pos(out.i, out.j, 3, out.dist + 1));
								}
								if ((visit[out.i + 1][out.j] & 1 << 3) == 0) {
									visit[out.i + 1][out.j] |= 1 << 3;
									queue.offer(new Pos(out.i + 1, out.j, 3, out.dist + 1));
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}
	
	static class Pos {
		int i, j, dir, dist;
		public Pos (int i, int j, int dir, int dist) {
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.dist = dist;
		}
	}
}
