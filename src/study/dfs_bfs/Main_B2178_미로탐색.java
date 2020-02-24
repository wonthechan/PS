package study.dfs_bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2178_미로탐색 {
	/* 정점마다 거리 정보를 추가해주고 탐색시마다 1씩 증가 */
	static int N, M;
	static char[][] land;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b2178.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		land = new char[N][M];	// 배열 생성
		visited = new boolean[N][M];	// 배열 생성
		for (int i = 0; i < N; i++) {	// 배추 위치 입력 및 표시
			land[i] = br.readLine().toCharArray();
		}

		// bfs 탐색 => (0,0) 에서 출발하여 (N-1,M-1)에 도착
		int answer = bfs(new Pos(0,0,1));
		
		System.out.println(answer);
	}
					//	상 하 좌 우
	static int dy4[] = {-1, 1, 0, 0};
	static int dx4[] = {0, 0, -1, 1};
	
	private static int bfs(Pos pos) {
		int ret = 0;
		Queue<Pos> queue = new LinkedList<Pos>();
		visited[pos.i][pos.j] = true;
		queue.offer(pos);
		while(!queue.isEmpty()) {
			Pos out = (Pos) queue.poll();	// 한개 정점 Dequeue
			ret = out.dist;
			// 목적지 도착 확인
			if (out.i == N-1 && out.j == M-1 && land[out.i][out.j] == '1') {
				break;
			}
			// 4방 탐색하면서 인접한 정점 파악
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny >= 0 && nx >= 0 && ny < N && nx < M && visited[ny][nx] == false && land[ny][nx] == '1' ) {
					visited[ny][nx] = true;			// Enqueue 하기 전에 visit 처리
					queue.offer(new Pos(ny, nx, out.dist+1));	// 인접한 정점은 Enqueue
				}
			}
		}
		return ret;
	}

	static class Pos {
		int i, j, dist;
		public Pos(int i, int j, int dist) {
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
	}

}
