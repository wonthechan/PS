package study.date0528;

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

	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int left = 0;	// 남은 빈공간의 개수
	static List<Pos> viruses;
	static Queue<Pos> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17142.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		viruses = new ArrayList<>(); // 모든 바이러스의 위치 정보
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0;j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {			// 바이러스 위치
					viruses.add(new Pos(i,j));
				} else if (map[i][j] == 0) {	// 빈칸
					++left;
				}
			}
		} // 입력 끝
		
		// 1. M개의 활성 바이러스 뽑기 (조합 - 넥퍼)
		int virusTotalCnt = viruses.size();
		int[] arr = new int[virusTotalCnt];
		for (int i = 0; i < M; i++) arr[virusTotalCnt - 1 - i] = 1;
		do {
			// 나온 조합의 바이러스만 활성이므로 큐에 넣고 BFS 시뮬 시작
			queue.clear();	// 초기화
			for (boolean[] v : visited) Arrays.fill(v, false); // 초기화
			
			for (int i = 0; i < virusTotalCnt; i++) {
				if (arr[i] == 1) {
					Pos virus = viruses.get(i);
					queue.add(new Pos(virus.y, virus.x));
					visited[virus.y][virus.x] = true;
				}
			}
			
			answer = Math.min(answer, bfs());
		} while (np(arr));
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	
	private static int bfs() {
		int leftCnt = left;	// 원래 left 값 보존
		int sec = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size(); 		// 모두 동시에 퍼져나가기 위함
			if (leftCnt == 0) return sec;	// leftCnt 체크 위치 중요!! 밑에 while문 안에 들어가 있으면 안됨.
			while (size-- > 0) { // 1초 흐름
				Pos out = queue.poll();
				
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
					if (visited[ny][nx]) continue;
					if (map[ny][nx] == 0) {	// 빈 칸인 경우
						visited[ny][nx] = true;
						queue.offer(new Pos(ny, nx));
						--leftCnt;
					} else if (map[ny][nx] == 2) {	// 비활성 바이러스 인 경우
						visited[ny][nx] = true;
						queue.offer(new Pos(ny, nx));
					}
				}
			}
			++sec;
		}
		
		// 더 이상 퍼질 바이러스가 없는데도 빈칸이 남은 경우
		return Integer.MAX_VALUE; // 모두 퍼뜨릴 수 없는 경우 : -1 출력
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
			++i; --j;
		}
		return true;
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
