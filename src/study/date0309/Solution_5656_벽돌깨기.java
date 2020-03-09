package study.date0309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {

	static int N, W, H, answer;
	static int[][] map;
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝
			
			// 0 ~ W-1 인덱스 범위내에서 구슬을 하나 쏠 수 있다. => 모든 경우의 수 생각해보기
			for (int j = 0; j < W; j++) {
				int topI = getTopY(map, j);
				startShooting_dfs(map, topI, j, 0);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	// 기존 맵을 새로운 맵으로 복사해다가 상태를 업데이트하고 그 새로운 맵을 리턴.
	private static void breakBricks(int[][] arr, int topY, int x, int range) {
		if (arr[topY][x] == 1) {
			arr[topY][x] = 0;	// 자신은 무조건 폭발한다.
			return;
		}
		arr[topY][x] = 0;
		for (int dir = 0; dir < 4; dir++) {
			int ny = topY;
			int nx = x;
			int r = range - 1; // 자신은 이미 폭발되었다고 생각
			while (r-- > 0) {	
				ny += dy4[dir];
				nx += dx4[dir];
				if (ny < 0 || nx < 0 || ny >= H || nx >= W) break;
				if (arr[ny][nx] != 0) {
					breakBricks(arr, ny, nx, arr[ny][nx]);
				}
			}
		}
	}
	
	private static int getTopY(int[][] arr, int j) { // j열에 벽돌이 남아 있는지? 없으면 -1, 있으면 가장 높이 있는 벽돌의 높이 반환
		int i = 0;
		while (i < H && arr[i][j] == 0) ++i;
		return i == H? -1 : i;
	}
	private static void startShooting_dfs(int[][] arr, int topY, int x, int cnt) {
		if (emptyCheck(arr)) {
			answer = 0;
			return;
		}
		if (cnt == N) {
			// 남은 벽돌의 수가 최소가 될때?
			int left = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (arr[i][j] > 0) ++left;
				}
			}
			answer = Math.min(answer, left);
			return;
		}
		
		// 구슬 깨트리기 (투하 지점 찾은 (topY, x))
		int[][] updatedMap = getCopiedMap(arr);
		if (topY > -1) {
			breakBricks(updatedMap, topY, x, updatedMap[topY][x]);
			// 밑으로 밀어주기 (중간에 있는 공백 제거)
			allPushDown(updatedMap);
		}
		
		for (int j = 0; j < W; j++) {
			int topI = getTopY(updatedMap, j);
			startShooting_dfs(updatedMap, topI, j, cnt + 1);
		}
	}

	private static void allPushDown(int[][] arr) {
		for (int j = 0; j < W; j++) {
			queue.clear();
			for (int i = H - 1; i >= 0; i--) {
				if (arr[i][j] > 0) {
					queue.offer(arr[i][j]);
					arr[i][j] = 0;
				}
			}
			int i = H - 1;
			while(!queue.isEmpty()) {
				arr[i--][j] = queue.poll();
			}
		}
	}

	private static int[][] getCopiedMap(int[][] arr){
		int[][] copiedMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copiedMap[i][j] = arr[i][j];
			}
		}
		return copiedMap;
	}
	
	private static boolean emptyCheck(int[][] arr) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] > 0) return false;
			}
		}
		return true;
	}
}
