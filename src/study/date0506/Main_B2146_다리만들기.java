package study.date0506;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 간척사업 유형
 * 참고1 : https://mygumi.tistory.com/109
 * 참고2 : https://jaimemin.tistory.com/655
 * 
 * 다리 만들기 2 문제와 비슷한점
 * - 섬을 구분하기 위해 마킹한다.
 * 다른점
 * - 다리가 직선이 아니라 꺽일수도 있다.
 */
public class Main_B2146_다리만들기 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int totalCnt = 0;
	static int minDist = 987654321;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b2146.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	// 입력 끝
		
		// 구역 마킹 (BFS)
		int area = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					markAreaBFS(i, j, area);
					++area;
				}
			}
		}
		
		totalCnt = area - 1;	// 모든 섬의 갯수

		// 각 섬마다 간척사업을 진행해보면서 다른 섬을 만나는 경우를 찾는다 => 최소 다리 길이
		for (int i = 0; i < totalCnt; i++) {
			for (boolean[] v : visited) Arrays.fill(v, false); // visit 배열 초기화
			minDist = Math.min(minDist, makeBridgeBFS(i+1));
		}
		
		System.out.println(minDist);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	
	/* 섬 구분을 위해 마킹하기
	 * 
	 */
	private static void markAreaBFS(int i, int j, int area) {
		Queue<Pos> queue = new LinkedList<>();
		visited[i][j] = true;
		map[i][j] = area;
		queue.offer(new Pos(i, j));
		
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if (map[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					queue.offer(new Pos(ny, nx));
					map[ny][nx] = area; // 구역 마
				}
			}
		}
	}
	/* BFS를 통해 간척 사업 진행
	 * 
	 */
	private static int makeBridgeBFS(int area) {
		Queue<Pos> queue = new LinkedList<>();
		// 섬의 모든 면적을 큐에 넣는다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == area) {
					visited[i][j] = true;
					queue.offer(new Pos(i, j));
				}
			}
		}
		
		int dist = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos out = queue.poll();
				// 사방으로 바다가 있는지 확인
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if (map[ny][nx] == 0 && !visited[ny][nx]) { // 다리 확장 (계속 간척 진행)
						visited[ny][nx] = true;
						queue.offer(new Pos(ny, nx));
					} else if (map[ny][nx] != 0 && map[ny][nx] != area) {	// 다른 섬을 만난 경우!!
						return dist;
					}
				}
				
			}
			++dist;
		}
		return -1;
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
