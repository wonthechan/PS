package study.date0506;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hw_algo0506_서울_7반_임예찬 {

	static int[][] score = new int[6][3];	// 만들어질 수 있는 경기 결과 (0: win, 1: tie, 2: lose)
	static int[][] result = new int[6][3];	// 주어진 경기 결과
	static int[] teamAs = new int[15];
	static int[] teamBs = new int[15];
	static int answer = 0;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/b6978.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// for문을 이용해 서로 맞붙는 경우의 수 6C2(=15)를 미리 구해두고 배열에 저장.
		// teamA : [0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4]
		// teamB : [1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5]
		int idx = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				teamAs[idx] = i;
				teamBs[idx] = j;
				++idx;
			}
		}
		
		// 네 가지의 결과가 주어질 때 각각의 결과에 대하여 가능하면 1, 불가능하면 0을 출력
	L1:	for (int tc = 0; tc < 4; tc++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				int sum = 0;
				for (int j = 0; j < 3; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
					sum += result[i][j];
					score[i][j] = 0;	// 함께 초기화 해둠.
				}
				if (sum != 5) {	// 승, 무승부, 패의 합은 항상 5여야 한다.
					System.out.print("0 ");
					continue L1;
				}
			}
			dfs(0);
			System.out.print(answer + " ");
		}
	}
	
	/** 깊이: 15(대진표), 선택가짓수: 3 (승, 무승부, 패)
	 * 	총 3^15 가짓수
	 */
	private static void dfs(int level) {
		if (level == 15) { // 나올 수 있는 모든 결과에 속하는 경우
			answer = 1;
			return;
		}
		
		int teamA = teamAs[level];
		int teamB = teamBs[level];
		
		// A팀이 이기고 B팀이 지는 경우
		++score[teamA][0]; ++score[teamB][2];
		if (score[teamA][0] <= result[teamA][0] && score[teamB][2] <= result[teamB][2]) {
			dfs(level + 1);	// backtracking & cutting
		}
		--score[teamA][0]; --score[teamB][2];	// 원상 복구
		
		// A팀이 지고 B팀이 이기는 경우
		++score[teamA][2]; ++score[teamB][0];
		if (score[teamA][2] <= result[teamA][2] && score[teamB][0] <= result[teamB][0]) {
			dfs(level + 1);	// backtracking & cutting
		}
		--score[teamA][2]; --score[teamB][0];
		
		// 무승부인 경우
		++score[teamA][1]; ++score[teamB][1];
		if (score[teamA][1] <= result[teamA][1] && score[teamB][1] <= result[teamB][1]) {
			dfs(level + 1);	// backtracking & cutting
		}
		--score[teamA][1]; --score[teamB][1];
	}

}
