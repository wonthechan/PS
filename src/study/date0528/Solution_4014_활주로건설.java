package study.date0528;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {

	static int N, X;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모든 행과 열에 대해서 활주로 건설 가능 검사
			for (int i = 0; i < N; i++) {
				answer += checkValidRow(i);
				answer += checkValidCol(i);
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	// row 열 검사 223222
	private static int checkValidRow(int row) {
		int curH = map[row][0]; // 최초 높이
		int hCnt = 1;			// 현재 높이 누적
		int j = 1;
		while (j < N) {	// 한칸 씩 검사
			if (curH != map[row][j]) {	// 높이가 다른 경우
				if (Math.abs(curH - map[row][j]) > 1) return 0;	// 높이 차이가 1을 초과하는 경우는 무조건 건설 불가
				// 높이 차이가 나긴 나는데 1인 경우에는
				// 경사로를 만들 수 있는지 확인
				if (map[row][j] > curH) {	// 더 높은 경우
					if (hCnt < X) return 0;	// 경사로를 못 만드는 경우 (높이누적 부족)
					hCnt = 1;			// 누적 카운트 초기화
					curH = map[row][j]; // 현재 높이 갱신
				} else {	// 더 낮은 경우
					// 앞으로 만들 공간이 있는지 확인해야 됨.
					if (j + X > N) return 0;	// 범위를 초과해서 만들 공간 자체가 없음
					for (int k = j + 1; k < j + X; k++) if (map[row][k] != map[row][j]) return 0;	// 높이가 불균등
					curH = map[row][j]; // 현재 높이 갱신
					hCnt = 0;			// 누적 카운트 초기화
					j += X - 1;			// 경사로의 마지막 위치로 jump;
				}
			} else {	// 현재 높이가 직전 높이와 같은 경우
				++hCnt;
			}
			++j;
		}
//		System.out.println("row: " + row);
		return 1;
	}
	
	// column 열 검사
	private static int checkValidCol(int col) {
		int curH = map[0][col]; // 최초 높이
		int hCnt = 1;			// 현재 높이 누적
		int i = 1;
		while (i < N) {	// 한칸 씩 검사
			if (curH != map[i][col]) {	// 높이가 다른 경우
				if (Math.abs(curH - map[i][col]) > 1) return 0;	// 높이 차이가 1을 초과하는 경우는 무조건 건설 불가
				// 높이 차이가 나긴 나는데 1인 경우에는
				// 경사로를 만들 수 있는지 확인
				if (map[i][col] > curH) {	// 더 높은 경우
					if (hCnt < X) return 0;	// 경사로를 못 만드는 경우 (높이누적 부족)
					hCnt = 1;			// 누적 카운트 초기화
					curH = map[i][col]; // 현재 높이 갱신
				} else {	// 더 낮은 경우
					// 앞으로 만들 공간이 있는지 확인해야 됨.
					if (i + X > N) return 0;	// 범위를 초과해서 만들 공간 자체가 없음
					for (int k = i + 1; k < i + X; k++) if (map[k][col] != map[i][col]) return 0;	// 높이가 불균등
					curH = map[i][col]; // 현재 높이 갱신
					hCnt = 0;			// 누적 카운트 초기화
					i += X - 1;			// 경사로의 마지막 위치로 jump;
				}
			} else {	// 현재 높이가 직전 높이와 같은 경우
				++hCnt;
			}
			++i;
		}
//		System.out.println("col: " + col);
		return 1;
	}
}
