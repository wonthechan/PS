package study.date0412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B17142_연구소3 {

	static int N, M;	// 연구소의 크기 N(4 ≤ N ≤ 50), 활성화 할 바이러스의 개수 M(1 ≤ M ≤ 10)
	static int K, R;		// 모든 바이러스의 개수, 방의 개수
	static List<Pos> virus = new ArrayList<>();	// 바이러스 좌표 리스트
	static int[][] map; // 연구소 맵
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17142.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		// 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 위치
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					++R;	// 빈 방 개수 카운트
				} else if (map[i][j] == 2) {
					virus.add(new Pos(i, j));
					++K; 	// 바이러스 개수 카운트
				}
			}
		} // 입력 끝
		
		// 바이러스는 최대 10개일 수 있고 그중 활성화 할 수 있는 바이러스의 개수도 최대 10개
		// 완탐할 경우 시간 복잡도는 10C1 ~ 10C10 : 수가 작아서 완탐 가능!!
		
		// 정답 초기화
		int answer = Integer.MAX_VALUE;
		
		// 조합 생성 (넥퍼)
		int[] arr = new int[K];
		for (int i = K - 1; i >= K - M; i--) arr[i] = 1;
		do {
			answer = Math.min(answer, bfs(arr));
		} while (np(arr));

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int bfs(int[] arr) {
		// visit 배열 초기화
		for (boolean[] v : visited) Arrays.fill(v, false);
		// 지정된 활성 바이러스들을 모두 큐에 넣고 시작 (토마토 문제와 비슷)
		Queue<Pos> queue = new LinkedList<>();
		for (int i = 0; i < K; i++) {
			if (arr[i] == 1) {
				Pos v = virus.get(i);
				visited[v.y][v.x] = true;
				queue.offer(new Pos(v.y, v.x));
			}
		}
		
		// 탐색 시작
		int cnt = 0; 	// 감염시킨 방의 개수 (R개가 되면 탐색 종료)
		int sec = 0;	// 모든 방을 감염시키는 걸린 시간
	L1:	while (!queue.isEmpty()){
			int size = queue.size();
			if (cnt == R) break L1;	// 모든 방이 감염되면 종료 (위치가 중요!!!!, 밑에 while문안에 넣으면 안됨)
			while (size-- > 0){
				Pos out = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if (!visited[ny][nx] && map[ny][nx] != 1) { // 방문한적이 없고 (감염시킨적이 없고) 벽이 아니라면
						if (map[ny][nx] == 0) {
							// 방인 경우
							++cnt;
							visited[ny][nx] = true;
							queue.offer(new Pos(ny, nx));
						} else {
							// 다른 비활성 바이러스 인 경우
							visited[ny][nx] = true;
							queue.offer(new Pos(ny, nx));
						}
					}
				}
			}
			++sec;
		}
		
		if (cnt != R) return Integer.MAX_VALUE; // 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력
		return sec;	// 연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력
	}
	
	
	private static boolean np(int[] arr) {
		int N = arr.length;
		//
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}
	
	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
