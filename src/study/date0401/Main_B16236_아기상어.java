package study.date0401;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B16236_아기상어 {

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static PriorityQueue<Fish> fishes = new PriorityQueue<>();
	static Shark shark = new Shark();	// 아기 상어의 위치와 크기 정보
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b16236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark.y = i;
					shark.x = j;
				}
			}
		} // 입력 끝 & 아기 상어 위치 파악
		
		int answer = 0;
		// 먹이 후보가 1개 이상일 때만 반복
		while (bfs() > 0) {
			Fish fish = fishes.poll();	// 최종 먹이 후보
			// 물고기를 먹으면, 그 칸은 빈 칸이 된다.
			// 원래 아기 상어가 있던 자리도 빈 칸이 되나?
			map[shark.y][shark.x] = map[fish.y][fish.x] = 0;
			// 물고기 먹고 크기 커지고 그 위치로 이동
			shark.y = fish.y;
			shark.x = fish.x;
			// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
			if (++shark.point == shark.size) {
				++shark.size; 
				shark.point = 0;
			}
			// 이동하는데 걸린 시간 누적
			answer += fish.dist;
		}
		
		System.out.println(answer);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};

	/** 먹이를 찾는 과정 - 탐색 */
	static int bfs () {
		fishes.clear(); // 먹이 후보 큐 초기화
		for (boolean[] arr : visit) Arrays.fill(arr, false); // visit 배열 초기화
		Queue<Pos> queue = new LinkedList<Pos>();
		visit[shark.y][shark.x] = true;
		queue.add(new Pos(shark.y, shark.x));
		int dist = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos out = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if (!visit[ny][nx] && map[ny][nx] <= shark.size) {
						if (map[ny][nx] > 0 && map[ny][nx] < shark.size) {
							// 먹이 후보 큐에 추가
							visit[ny][nx] = true;
							fishes.offer(new Fish(ny, nx, map[ny][nx], dist + 1));
						} else {
							visit[ny][nx] = true;
							queue.offer(new Pos(ny, nx));
						}
					}
				}
			}
			++dist;
		}
		return fishes.size();
	}

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Shark {
		int y, x, size = 2, point = 0;
	}
	
	static class Fish implements Comparable<Fish>{
		int y, x, size, dist;

		public Fish(int y, int x, int size, int dist) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			return this.dist == o.dist? (this.y == o.y? this.x - o.x : this.y - o.y) : this.dist - o.dist;
		}
	}
}
