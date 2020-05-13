package study.date0509;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_kakao20인턴십4 {

	static int[][] map;
	static boolean[][] visited;
	static int[][] dist; // 거리 저장 배열
	static int N;
	static int answer;
	public static void main(String[] args) {
		int[][] board = {
				{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}
		};
		System.out.println(solution(board));
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	static public int solution(int[][] board) {
        answer = 0;
        N = board.length;
        map = board;
        dist = new int[N][N];
        for (int[] arr : dist) Arrays.fill(arr, Integer.MAX_VALUE);
        answer = bfsDijkstra();
        return answer;
    }
	
	private static void bfs() {
		Queue<Pos> queue = new LinkedList<>();
		visited[0][0] = true;
		queue.offer(new Pos(0, 0, 0, -1));
		
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;
				if (map[ny][nx] == 0) {
					if (out.pastDir > -1 && out.pastDir != dir) {
						if (ny == N - 1 && nx == N - 1) {
							answer = Math.min(answer, out.cost + 600);
							continue;
						}
						visited[ny][nx] = true;
						queue.offer(new Pos(ny, nx, out.cost + 600, dir));
					} else {
						if (ny == N - 1 && nx == N - 1) {
							answer = Math.min(answer, out.cost + 100);
							continue;
						}
						visited[ny][nx] = true;
						queue.offer(new Pos(ny, nx, out.cost + 100, dir));
					}
				}
			}
		}
	}
	
	private static int bfsDijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0, 0, -1));
		dist[0][0] = 0;
		while (!pq.isEmpty()) {
			Pos out = pq.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1) continue;
				// 거리 업데이트
				if (out.pastDir > -1 && out.pastDir != dir) {
					if (dist[out.y][out.x] + 600 < dist[ny][nx]) {
						// 별도로 visit 체크를 하지 않음. (되돌아가면 어차피 비용이 더 클테니까)
						dist[ny][nx] = dist[out.y][out.x] + 600;
						pq.offer(new Pos(ny, nx, dist[ny][nx], dir));
					}
					
				} else {
					if (dist[out.y][out.x] + 100 < dist[ny][nx]) {
						// 별도로 visit 체크를 하지 않음. (되돌아가면 어차피 비용이 더 클테니까)
						dist[ny][nx] = dist[out.y][out.x] + 100;
						pq.offer(new Pos(ny, nx, dist[ny][nx], dir));
					}
					
				}
			}
		}
		return dist[N-1][N-1];
	}
	
	
	static class Pos implements Comparable<Pos> {
		int y, x, cost, pastDir;

		public Pos(int y, int x, int cost, int pastDir) {
			this.y = y;
			this.x = x;
			this.cost = cost;
			this.pastDir = pastDir;
		}
		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
