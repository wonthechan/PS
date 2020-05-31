package study.date0531;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 상향식 동적계획법으로 풀어보기 (점화식)
 * 참고: https://travelbeeee.tistory.com/277
 */
public class Main_B17070_파이프옳기기_DP_BottomUp {

	static int N;
	static int[][] map;
					//	우, 우하, 하 (갈 수 있는 방향)
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 1, 0};
	
	static int[][] possibleDirs = {
			{1, 1, 0},	// type 0 에서 모든 방향에 대해서 갈 수 있는지에 대한 여부 (1: 가능, 0: 불가능)
			{1, 1, 1},
			{0, 1, 1}
	};
	
	static int[][][] DP;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		DP = new int[3][N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0: 가로 모양, 1: 대각선 모양, 2: 세로 모양
		// 초기값 설정
		DP[0][0][1] = 1;	// 처음 상태에서는 1가지의 수만 가진다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (check(i, j - 1) && check(i, j)) { // 가로 모양 확인
					DP[0][i][j] += DP[0][i][j - 1] + DP[1][i][j - 1];
				}
				if (check(i - 1, j) && check(i, j)) { // 세로 모양 확인
					DP[2][i][j] += DP[1][i - 1][j] + DP[2][i - 1][j];
				}
				if (check(i - 1, j - 1) && check(i - 1, j) && check (i, j - 1) && check (i, j)) { // 대각선 모양 확인
					DP[1][i][j] += DP[0][i - 1][j - 1] + DP[1][i - 1][j - 1] + DP[2][i - 1][j - 1];
				}
			}
		}
		System.out.println(DP[0][N - 1][N - 1] + DP[1][N - 1][N - 1] + DP[2][N - 1][N - 1]);
	}
	
	// 현재 위치를 사용할 수 있는지 확인 (범위체크 && 벽이 없는지)
	public static boolean check (int y, int x) {
		if (y >= 0 && x >= 0 && x < N && y < N && map[y][x] == 0) return true;
		return false;
	}
}
