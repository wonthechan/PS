package study.date0522;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_보호필름 {

	static int D, W, K;	// 보호 필름의 두께 D (3≤D≤13), 보호 필름의 가로크기 W (1≤W≤20), 합격기준 K (1≤K≤D)
	static int[][] map;
	static int[][] initMap;
	static int[] selected;
	static boolean[] type;
	static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			initMap = new int[D][W];
			selected = new int[D]; // 0: 약품 x, 1: 약품 A, 2: 약품 B
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					initMap[i][j] = map[i][j];
				}
			} // 입력 끝
			
			if (K == 1 || isValid()) { // 초기 상태에서 합격하면 0 찍고 다음 테케로 이동
				sb.append(0).append("\n");
				continue;
			}
			
			answer = 987654321;
			// 아예 3진 부분집합을 구해버리자 with DFS => 3^D 개의 경우의 수
			makeSubSet(0, 0);
			sb.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static void makeSubSet(int level, int count) {
		if (level == D) {
			if (isValid()) { // 약품을 투여해서 통과한 경우
				answer = Math.min(answer, count);
			}
			return;
		}
		
		if (count >= answer) return; // 시간 엄청 줄임
		
		// 투여하지 않은 경우
		selected[level] = 0;
		makeSubSet(level + 1, count);
		
		// A를 투여
		selected[level] = 1;
		makeSubSet(level + 1, count + 1);
		
		// B를 투여
		selected[level] = 2;
		makeSubSet(level + 1, count + 1);
		
	}

	/** 모든 열에 대해서 합격 기준을 만족하는지 검사 */
	private static boolean isValid() {
		for (int j = 0; j < W; j++) {
			int cnt = 1;
			int last = selected[0] > 0 ? selected[0] - 1 : map[0][j];
			boolean flag = false;
			for (int i = 1; i < D; i++) {
				int cur = selected[i] > 0 ? selected[i] - 1 : map[i][j]; // 맵을 수정하지 않고 약품 투여한 행인지 확인
				if (cur == last) {
					++cnt;
					if (cnt == K) { // 합격 기준 만족 (탐색 중지)
						flag = true;
						break;
					}
				}
				else {
					cnt = 1;
				}
				last = cur;
			}
			if (!flag) return false;
		}
		return true;
	}

}
