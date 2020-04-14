package study.date0414;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 양의 가중치가 있는 그래프의 최단 거리는 다익스트라 알고리즘 적용 가능 */
public class Main_B4485_녹색옷입은애가젤다지 {

	static int N; // 맵(동굴)의 크기
	static int[][] map;
	static int[][] dist; // 거리 저장 배열
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b4485.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int pnum = 1;
		N = Integer.parseInt(br.readLine());
		while (N > 0) {
			sb.append("Problem ").append(pnum++).append(": ");
			map = new int[N][N];
			dist = new int[N][N];
			for (int[] arr : dist) Arrays.fill(arr, Integer.MAX_VALUE);
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(bfsDijkstra()).append("\n");
			N = Integer.parseInt(br.readLine());
		}
		System.out.println(sb.toString());
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int bfsDijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0, map[0][0]));
		dist[0][0] = map[0][0];
		while (!pq.isEmpty()) {
			Pos out = pq.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				// 거리 업데이트
				if (dist[out.y][out.x] + map[ny][nx] < dist[ny][nx]) {
					// 별도로 visit 체크를 하지 않음. (되돌아가면 어차피 비용이 더 클테니까)
					dist[ny][nx] = dist[out.y][out.x] + map[ny][nx];
					pq.offer(new Pos(ny, nx, dist[ny][nx]));
				}
			}
		}
		return dist[N-1][N-1];
	}
	
	
	static class Pos implements Comparable<Pos>{
		int y, x, cost;
		public Pos(int y, int x, int cost) {
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
