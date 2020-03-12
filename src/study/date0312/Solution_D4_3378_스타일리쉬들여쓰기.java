package study.date0312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 중복순열 + 구현
// 참고 : https://yabmoons.tistory.com/209
public class Solution_D4_3378_스타일리쉬들여쓰기 {

	static int p, q, tabCnt, sBrCnt, mBrCnt, lBrCnt;		// 들여쓰기 개수 와 현재 열려 있는 소괄호, 중괄호, 대괄호의 개수
	static boolean[][][] perms = new boolean[21][21][21]; 	// 20 x 20 x 20 중복순열 관리
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s3378.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" 0 "); // 첫 라인은 들여쓰기 개수가 항상 0이다.
			for (boolean[][] a : perms) {
				for (boolean[] b : a)	Arrays.fill(b, true);
			}	// 배열 초기화 (모든 중복순열이 가능한 후보군에 있음)
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			
			// 마스터한 사람의 코드를 보면서 (R, C, S)를 알아내고
			// 내코드에도 동일한 규칙을 적용한다.
			
			sBrCnt = mBrCnt = lBrCnt = 0; // sBrCnt: a-b, mBrCnt: c-d, lBrCnt: e-f
			// 일단은 마스터의 코드 라인 하나씩 읽자.
			for (int k = 0; k < p; k++) {
				tabCnt = 0;
				// 먼저 들여쓰기 개수 먼저 세자
				char[] str = br.readLine().toCharArray();
				int len = str.length;
				int i = 0;
				while (i < len && str[i] == '.') {
					++tabCnt;
					++i;
				}
				// R, C, S 결과에 따라 가능한 중복 순열 찾기 (후보군 추리기)
				if (k > 0) findRCSCandidates();
				
				// 그 다음 괄호 개수 세자
				while (i < len) {
					switch (str[i]) {
					case '(':
						++sBrCnt;
						break;
					case ')':
						--sBrCnt;
						break;
					case '{':
						++mBrCnt;
						break;
					case '}':
						--mBrCnt;
						break;
					case '[':
						++lBrCnt;
						break;
					case ']':
						--lBrCnt;
						break;
					}
					++i;
				}
			}
			
			// 마스터가 제멋대로 들여쓰기를 한 경우 RCS 후보가 하나도 없을 수 있다.
			// 내 코드에서는 어떤 경우도 들여쓰기를 할 수가 없다.
			if (!hasCandidates()) {
				br.readLine();
				for (int k = 1; k < q; k++) {
					br.readLine();
					sb.append(-1).append(" ");
				}
				sb.append("\n");
				continue;	// 다음 테케로
			}
			
			sBrCnt = mBrCnt = lBrCnt = 0; // sBrCnt: a-b, mBrCnt: c-d, lBrCnt: e-f
			// 이제 내 코드 라인을 하나씩 읽으면서 판단
			for (int k = 0; k < q; k++) {
				// R, C, S 결과에 따라 가능한 중복 순열 찾기 (후보군 추리기)
				if (k > 0) sb.append(getTabCnt()).append(" ");
				
				// 괄호 개수 세자
				char[] str = br.readLine().toCharArray();
				int len = str.length;
				int i = 0;
				while (i < len) {
					switch (str[i]) {
					case '(':
						++sBrCnt;
						break;
					case ')':
						--sBrCnt;
						break;
					case '{':
						++mBrCnt;
						break;
					case '}':
						--mBrCnt;
						break;
					case '[':
						++lBrCnt;
						break;
					case ']':
						--lBrCnt;
						break;
					}
					++i;
				}
				
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	/** RCS 후보가 하나라도 있는 경우 true 바환 */
	private static boolean hasCandidates() {
		for (int r = 1; r < 21; r++) {
			for (int c = 1; c < 21; c++) {
				for (int s = 1; s < 21; s++) {
					if (perms[r][c][s]) return true;
				}
			}
		}
		return false;
	}
	
	/** 들여쓰기 개수 계산 결과값이 모든 RCS 후보에 대해 동일하다면 계산 결과 값 출력
	 * 	그렇지 않다면 -1 출력
	 */
	private static int getTabCnt() {
		int sum = -1;
		for (int r = 1; r < 21; r++) {
			for (int c = 1; c < 21; c++) {
				for (int s = 1; s < 21; s++) {
					if (!perms[r][c][s]) continue;
					if (sum < 0) {
						sum = r * sBrCnt + c * mBrCnt + s * lBrCnt;
					} else {
						if (sum != r * sBrCnt + c * mBrCnt + s * lBrCnt) return -1;
					}
				}
			}
		}
		return sum;
	}
	private static void findRCSCandidates() {
		// 중복 순열을 구하는 과정이므로 재귀를 써도 되지만
		// 어차피 depth(깊이)가 3으로 정해져 있기 때문에 반복문을 쓰는 것이 더 유리하다.
		int sum = 0;
		for (int r = 1; r < 21; r++) {
			for (int c = 1; c < 21; c++) {
				for (int s = 1; s < 21; s++) {
					if (!perms[r][c][s]) continue;
					sum = r * sBrCnt + c * mBrCnt + s * lBrCnt;
					if (sum != tabCnt) { // 후보군에 있는데 새로운 기준에 맞지 않는다면 후보군에서 제외
						perms[r][c][s] = false;
					}
				}
			}
		}
	}

}
