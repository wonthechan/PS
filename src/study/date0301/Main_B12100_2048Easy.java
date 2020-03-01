package study.date0301;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * DFS를 이용해  모든 경우의 수를 따져보는 완탐문제
 * 다만 게임의 규칙을 정확하게 이해하고 코드로 풀어내야 디버깅 시간을 줄일 수 있다.
 */
public class Main_B12100_2048Easy {

	static int N;
	static int[][] map;
	static int answer = -987654321;
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b12100.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {	// 초기 상태 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (N == 1) {	// 크기가 1인 경우 아무리 움직여도 변함 없다.
			System.out.println(map[0][0]);
			return;
		}
		
		dfs(map, 0);	// 완전 탐색 (상하좌우로 한번씩 움직여보기)
		System.out.println(answer);
	}
	
	
	private static void dfs(int[][] origin, int cnt) {
		if (cnt == 5) {	// 다섯번 움직이면 끝
			updateAnswer(origin);
			return;
		}
		// 상 하 좌 우로 옳기기
		dfs(move2Upper(origin), cnt + 1);
		dfs(move2Lower(origin), cnt + 1);
		dfs(move2Left(origin), cnt + 1);
		dfs(move2Right(origin), cnt + 1);
	}
	
	private static void updateAnswer(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) answer = Math.max(answer, map[i][j]);
		}
	}

	private static int[][] move2Lower(int[][] map){
		int[][] newMap = new int[N][N];
		for (int j = 0; j < N; j++) {
			queue.clear();
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] == 0) continue;
				queue.offer(map[i][j]);
			}
			int now = 0;
			int size = queue.size();
			while (size-- > 0) {
				if (now == 0) {
					now = queue.poll();
					continue;
				}
				// now가 0이 아닌 경우
				int out = queue.poll();
				if (now == out) {
					queue.offer(now*2);
					now = 0;
				} else {
					queue.offer(now);
					now = out;
				}
			}
			if (now != 0) queue.offer(now);
			
			int k = N - 1;
			while (!queue.isEmpty()) {
				newMap[k--][j] = queue.poll();
			}
		}
		return newMap;
	}
	private static int[][] move2Upper(int[][] map){
		int[][] newMap = new int[N][N];
		for (int j = 0; j < N; j++) {
			queue.clear();
			for (int i = 0; i < N; i++) {
				if (map[i][j] == 0) continue;
				queue.offer(map[i][j]);
			}
			int now = 0;
			int size = queue.size();
			while (size-- > 0) {
				if (now == 0) {
					now = queue.poll();
					continue;
				}
				// now가 0이 아닌 경우
				int out = queue.poll();
				if (now == out) {
					queue.offer(now*2);
					now = 0;
				} else {
					queue.offer(now);
					now = out;
				}
			}
			if (now != 0) queue.offer(now);
			
			int k = 0;
			while (!queue.isEmpty()) {
				newMap[k++][j] = queue.poll();
			}
		}
		return newMap;
	}
	private static int[][] move2Left(int[][] map) {	// 왼쪽으로 옳기는 경우
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			queue.clear();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				queue.offer(map[i][j]);
			}
			int now = 0;
			int size = queue.size();
			while (size-- > 0) {
				if (now == 0) {
					now = queue.poll();
					continue;
				}
				// now가 0이 아닌 경우
				int out = queue.poll();
				if (now == out) {
					queue.offer(now*2);
					now = 0;
				} else {
					queue.offer(now);
					now = out;
				}
			}
			if (now != 0) queue.offer(now);
			
			int k = 0;
			while (!queue.isEmpty()) {
				newMap[i][k++] = queue.poll();
			}
		}
		return newMap;
	}
	
	private static int[][] move2Right(int[][] map) { // 오른쪽으로 옳기는 경우
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			queue.clear();
			for (int j = N - 1; j >= 0; j--) {
				if (map[i][j] == 0) continue;
				queue.offer(map[i][j]);
			}
			int now = 0;
			int size = queue.size();
			while (size-- > 0) {
				if (now == 0) {
					now = queue.poll();
					continue;
				}
				// now가 0이 아닌 경우
				int out = queue.poll();
				if (now == out) {
					queue.offer(now*2);
					now = 0;
				} else {
					queue.offer(now);
					now = out;
				}
			}
			if (now != 0) queue.offer(now);
			
			int k = N - 1;
			while (!queue.isEmpty()) {
				newMap[i][k--] = queue.poll();
			}
		}
		return newMap;
	}
}
