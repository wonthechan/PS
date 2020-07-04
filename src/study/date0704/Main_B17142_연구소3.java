package study.date0704;

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

	static int N;		// 연구소의 크기 N(4 ≤ N ≤ 50)
	static int M;		// 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)
	static int[][] map;	// 2차원 맵 배열
	static boolean[][] visited;
	static int leftRoomCnt = 0, answer = Integer.MAX_VALUE;	// 남은 빈칸의 개수와 최소 시간(정답)
	static List<Virus> viruses;		// 바이러스 객체 리스트
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17142.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		viruses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {			// 0은 빈 칸, 1은 벽, 2는 바이러스
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					++leftRoomCnt;					// 빈칸의 개수를 카운트
				}
				else if (map[i][j] == 2) {
					viruses.add(new Virus(i, j));	// 바이러스의 위치 정보를 리스트로 관리
				}
			}
		}	// 입력 끝
		
		// M개의 바이러스를 활성화 하자! (조합-넥퍼활용)
		int[] arr = new int[viruses.size()];
		for (int i = 0; i < M; i++) {
			arr[arr.length - 1 - i] = 1;
		}
		do {
			for (boolean[] a : visited) Arrays.fill(a, false);
			answer = Math.min(answer, bfs(arr));	// 조합 정보가 담겨 있는 배열을 bfs 시뮬 메소드로 넘긴다.
		} while (np(arr));
		// 결과 출력
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	
	// BFS 탐색 (시뮬)
	private static int bfs(int[] arr) {
		Queue<Pos> queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				queue.add(new Pos(viruses.get(i).y, viruses.get(i).x));
				visited[viruses.get(i).y][viruses.get(i).x] = true;
			}
		}
		int sec = 0;
		int leftCnt = leftRoomCnt;
		while (!queue.isEmpty()) {
			if (leftCnt == 0) {	// 전부 감염시킨 경우 BFS 종료
				break;
			}
			int size = queue.size();
			while (size-- > 0) {
				Pos out = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int ny = out.y + dy4[dir];
					int nx = out.x + dx4[dir];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;	// 범위 이탈한 경우 Pass
					if (visited[ny][nx]) continue;							// 이미 방문한 경우 Pass
					if (map[ny][nx] == 1) continue;							// 벽인 경우 Pass
					if (map[ny][nx] == 0) --leftCnt;						// 빈칸인 경우 남은 빈칸의 개수 감소
					visited[ny][nx] = true;
					queue.offer(new Pos(ny, nx));
				}
			}
			++sec;
		}
		if (leftCnt > 0) {
			return Integer.MAX_VALUE;
		}
		return sec;
	}


	// 넥퍼
	private static boolean np(int[] arr) {
		int len = arr.length;
		//
		int i = len - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = len - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
		j = len - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}


	// 바이러스 클래스 (위치정보)
	static class Virus {
		int y, x;
		public Virus(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Virus [y=" + y + ", x=" + x + "]";
		}
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
