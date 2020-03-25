package study.date0325;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B16234_인구이동 {

	static int N, L, R, M = 1, answer = 0;
	static int[][] map;
	static boolean[][] visit;
	static int[][] mark;
	static int[] avgs = new int[1251];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		mark = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		int avg = 0;
		while (true) {
			M = 1;
			for (boolean[] arr : visit) Arrays.fill(arr, false); // visit 초기화
			for (int[] arr : mark) Arrays.fill(arr, 0); // visit 초기화
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						avg = bfs(i, j);
						if (avg > -1) {
							avgs[M++] = avg;
						}
					}
				}
			}
			// 인구이동해야되는지 확인
			if (M == 1) break;
			++answer; // 인구 이동 횟수 1 증가
			// 인구 업데이트
			for (int k = 1; k < M; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (mark[i][j] == k) {
							map[i][j] = avgs[k];
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int bfs(int i, int j) {
		Queue<Pos> queue = new LinkedList<>();
		visit[i][j] = true;
		mark[i][j] = M;
		queue.offer(new Pos(i, j));
		int cnt = 1;
		int sum = map[i][j];
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;
				int diff = Math.abs(map[out.i][out.j] - map[ny][nx]);
				if (diff >= L && diff <= R) {
					++cnt;
					sum += map[ny][nx];
					visit[ny][nx] = true;
					mark[ny][nx] = M;
					queue.offer(new Pos(ny, nx));
				}
			}
		}
		
		if (cnt > 1) {
			return sum / cnt;
		}
		mark[i][j] = 0;
		return -1;
	}

	static class Pos {
		int i, j;
		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
