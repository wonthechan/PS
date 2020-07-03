package study.date0703;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B17070_파이프옮기기1_Memo {

	static int N;		// 집의 크기 N(3 ≤ N ≤ 16)
	static int[][] map;	// 2차원 맵
	static int answer;	// 파이프의 한쪽 끝을 (N, N)으로 이동시키는 모든 방법의 수
	static boolean[][] canMoveTo = { // 현재 방향에서 가로, 대각선, 세로 방향으로 움직일 수 있는지 여부
			{true, true, false},	// 현재 방향이 가로인 경우
			{true, true, true},		// 현재 방향이 대각선인 경우
			{false, true, true} 	// 현재 방향이 세로인 경우
	};
	static int[][][] memo;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/b17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new int[N][N][3];
		for (int[][] a : memo) {
			for (int[] b : a) Arrays.fill(b, -1);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		// DFS 탐색
		answer = dfs(0, 1, 0);
		
		// 정답 출력
		System.out.println(answer);
	}
					//	우, 우하, 하
	static int[] dy4 = {0, 1, 1};
	static int[] dx4 = {1, 1, 0};
	private static int dfs(int currentY, int currentX, int currentDir) {
		if (memo[currentY][currentX][currentDir] > -1) return memo[currentY][currentX][currentDir];
		memo[currentY][currentX][currentDir] = 0;
		// (N, N)에 도달한 경우 카운트 증가
		if (currentY == N - 1 && currentX == N - 1) {
			return memo[currentY][currentX][currentDir] = 1;
		}
		
		// 현재 방향에서 갈 수 있는 방법 확인
		for (int dir = 0; dir < 3; dir++) { // dir이 0인 경우 가로, 1인 경우 대각선, 2인 경우 세로
			if (canMoveTo[currentDir][dir]) {
				int ny = currentY + dy4[dir];
				int nx = currentX + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;	// 맵을 벗어나는 경우 넘어가기
				if (map[ny][nx] == 1) continue;							// 벽이 있는 경우 넘어가기
				if (dir == 1) {	// 대각선 방향으로 가는 경우에만 추가적으로 주변 칸에 벽이 있는지 확인
					if (map[currentY + dy4[0]][currentX + dx4[0]] == 1 
							|| map[currentY + dy4[2]][currentX + dx4[2]] == 1) {
						continue;
					}
				}
				memo[currentY][currentX][currentDir] += dfs(ny, nx, dir);	// 문제 없다면 다음 재귀 호출
			}
		}
		
		return memo[currentY][currentX][currentDir];
	}	

}
