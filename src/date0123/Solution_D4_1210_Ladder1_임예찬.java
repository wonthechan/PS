package date0123;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_1210_Ladder1_임예찬 {
	// 사다리 배열  선언
	static int[][] ladder;
	static boolean[][] visit;
	// 사다리 배열 Size (100 x 100)
	static final int N = 100;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		
		// 사다리 배열 생성
		ladder = new int[N][N];
		visit = new boolean[N][N];
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int tcNum = Integer.parseInt(br.readLine());
			int answer = 0;
			
			// 사다리 배열 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 0; j < N; j++) {
				// visit 배열 초기화
				initVisit();
				if (dfs(0, j) == true) {
					answer = j;
					break;
				}
			}
			
			System.out.println("#" + tcNum + " " + answer);
		}
	}

	private static void initVisit() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}
	}
	private static boolean dfs(int i, int j) {
//		System.out.println("START: " + "i = " + i + ", j = " + j);
		if (visit[i][j] == true) {
			// 이미 방문한적이 있는 경우
			return false;
		} else {
			visit[i][j] = true;
		}
		
		if (ladder[i][j] == 0) {
			return false;
		} else if (ladder[i][j] == 1) {
			if (i == N-1) {
				// 끝에 도달한 경우
				return false;
			}
			// 경계값 검사
			if (j == 0) {
				// 첫 행에 있는 경우
				if (ladder[i][j+1] == 1 && visit[i][j+1] == false) {
					// 오른쪽으로 갈 수 있는 경우
//					System.out.println(1);
					return dfs(i, j+1);
				} else {
//					System.out.println(2);
					return dfs(i+1, j);
				}
			} else if (j == N-1) {
				// 마지막 행에 있는 경우
				if (ladder[i][j-1] == 1 && visit[i][j-1] == false) {
					// 왼쪽으로 갈 수 있는 경우
//					System.out.println(3);
					return dfs(i, j-1);
				} else {
//					System.out.println(4);
					return dfs(i+1, j);
				}
			} else {
				// 중간 행에 있는 경우
				if (ladder[i][j+1] == 1 && visit[i][j+1] == false) {
					// 왼쪽으로 갈 수 있는 경우
//					System.out.println(5);
					return dfs(i, j+1);
				}
				if (ladder[i][j-1] == 1 && visit[i][j-1] == false) {
					return dfs(i, j-1);
				}
//					System.out.println(6);
				return dfs(i+1, j);
			}
		} else if (ladder[i][j] == 2) {
			// 정답을 찾은 경우
			return true;
		}
		
//		System.out.println("i = " + i + ", j = " + j);
		return false;
	}
	
	
}
