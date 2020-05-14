package study.date0514;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* BFS를 사용하는 전형적인 문제
 * 3차원 visit 배열과 비트마스킹을 이용하는 문제.
 * : 기존 2차원 배열로는 열쇠를 획득 후 왔던길을 다시 돌아갈 수 없다.
 * : 따라서 키가 없는 상태와 있는 상태에서의 visit을 따로 관리해야 한다 => 3차원 방문체크가 필요한 이유
 * : 키를 가지고 있는 상태는 비트마스킹으로 관리한다. (키의 개수만큼의 비트)
 * 좋은 문제.
 */
public class Main_B1194_달이차오른다가자 {

	static int N, M;
	static char[][] map;
	static boolean[][][] visited; // 키 유무에 따른 방문 체크를 위해 3차원 배열 사용
	static Pos start;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64];	// 2의 6제곱인 64만큼의 크기 할당 (키가 6개 => 비트가 6개)
		for (int i = 0; i < N; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = row[j];
				if (row[j] == '0') {
					start = new Pos(i, j, 0, 0);
				}
			}
		}
		
		int result = bfs();
		System.out.println(result);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int bfs() {
		Queue<Pos> queue = new LinkedList<>();
		visited[start.y][start.x][start.key] = true;
		queue.offer(new Pos(start.y, start.x, start.dist, start.key));
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			if (map[out.y][out.x] == '1') return out.dist;	// 목적지에 도착하면 거리 리턴
		L1:	for (int dir = 0; dir < 4; dir++) {
				int ny = out.y + dy4[dir];
				int nx = out.x + dx4[dir];
				int curKey = out.key;	// 현재 가지고 있는 키 비트
				// 1. 범위 벗어난 것 무시
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				// 2. 방문했던 곳이면 무시
				if (visited[ny][nx][curKey]) continue;
				// 3. 벽이면 무시
				if (map[ny][nx] == '#') continue;
				// 4. 키가 있으면 줍기
				if (map[ny][nx] >= 'a' && map[ny][nx] <= 'f') {
					curKey |= 1 << (map[ny][nx] - 'a');	// 현재 가지고 있는 키 비트 업데이트
				}
				// 5. 문인데 키가 없으면 무시
				if (map[ny][nx] >= 'A' && map[ny][nx] <= 'F' && (curKey & 1 << (map[ny][nx] - 'A')) == 0) {
					continue;
				}
				// 6. 방문체크하고 큐에 재삽입
				visited[ny][nx][curKey] = true;
				queue.offer(new Pos(ny, nx, out.dist + 1, curKey));
			}
		}
		return -1; // 미로를 탈출 할 수 없으면, -1을 출력한다.
	}
	
	static class Pos {
		int y, x, dist, key;

		public Pos(int y, int x, int dist, int key) {
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + ", dist=" + dist + ", key=" + key + "]";
		}
	}
}
