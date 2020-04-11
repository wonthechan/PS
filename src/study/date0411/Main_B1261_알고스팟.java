package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  2206번. '벽 부수고 이동하기'와 비슷해보이지만, 이 문제에서는 이동거리의 최소를 구하는 것이 아니라
 *  벽을 최소로 부수면서 목적지까지 이동할 수 있음을 묻고 있다.
 */
// 참고 : https://kim6394.tistory.com/178
public class Main_B1261_알고스팟 {

	static int N, M;
	static int[][] map;
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	static PriorityQueue<Pos> pq = new PriorityQueue<>();
	static int[][] D;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1261.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		} // 입력 끝
		
		// Dijkstra 준비
		boolean[][] check = new boolean[N][M];
		D = new int[N][M];
		for (int[] arr : D) Arrays.fill(arr, Integer.MAX_VALUE);
		
		System.out.println(bfsDijkstra());
	}
	
	private static int bfsDijkstra() {
		// 시작점 셋팅
		D[0][0] = 0;
		pq.add(new Pos(0, 0, 0));
		while (!pq.isEmpty()) {
			Pos out = pq.poll();	// 가중치가 가장 낮은 정점 꺼내기
			
			if (out.y == N - 1 && out.x == M - 1) return D[N - 1][M - 1];

			// 가중치 갱신 (Dijkstra)
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (D[out.y][out.x] + map[ny][nx] < D[ny][nx]) {
					D[ny][nx] = D[out.y][out.x] + map[ny][nx];
					pq.offer(new Pos(ny, nx, D[ny][nx]));
				}
			}
		}
		return -1;
	}

	static class Pos implements Comparable<Pos>{
		int y, x, cost;
		public Pos(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
