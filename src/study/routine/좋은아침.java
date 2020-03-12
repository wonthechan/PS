package study.routine;

import java.util.Arrays;

public class 좋은아침 {
	static char[] chars = {'a', 'b', 'c', 'd'};
	static boolean[] visited;
	static char[] result;
	static int R;
	public static void main(String[] args) {
		// chars로 만들 수 있는 부분집합을 구해보세요. 
		// 1) 재귀 (포함한다,안한다) / 2) 비트마스킹 이용 - 반복문 / 3) ~ n개까지의 모든 조합을 구한다.(0개를 고르는 조합 + 1개를 고르는 조합 + ... + n개를 고르는 조합)
		
		// chars에서 3개를 고르는 순열을 작성해보세요. 
		// 1)visited배열이용-재귀 / 2)비트마스킹 이용-재귀 / 3)넥퍼=>nPn만 가능
		R = 2;
//		makePerm(R, new char[R], new boolean[chars.length]);
//		makePerm2(R, new char[R]);
		
		// chars에서 3개를 고르는 조합을 작성해보세요. 
		// 1) 재귀 2) 넥퍼 (n개만큼 1로 셋팅)
//		visited = new boolean[chars.length];
//		makeComboUsingVisited(0, r);	// visit 배열을 활용하여 조합을 만듬
		
//		result = new char[R];
//		makeCombo(0, 0);				// 조합의 결과 배열에 바로 저장함.
//		makeCombo2(0,0, new char[R]);	// 결과 배열을 달고 다님 (전역변수 사용하지 않음)
		makeDupCombo(0, 0, new char[R]);	// 중복 조합 구하기
	}
	
	private static void makeDupCombo(int idx, int level, char[] res	) {
		if (level == R) {
			System.out.println(Arrays.toString(res));
			return;
		}
		for (int i = idx, end = chars.length; i < end; i++) {
			res[level] = chars[i];
			makeDupCombo(i, level+1, res);
		}
	}

	private static void makeCombo2(int idx, int level, char[] res) {
		if (level == R) {
			System.out.println(Arrays.toString(res));
			return;
		}
		for (int i = idx, end = chars.length; i < end; i++) {
			res[level] = chars[i];
			makeCombo2(i+1, level+1, res);
		}
	}
	
	private static void makeCombo(int idx, int level) {
		if (level == R) {
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = idx, end = chars.length; i < end; i++) {
			result[level] = chars[i];
			makeCombo(i+1, level+1);
		}
	}

	private static void makeComboUsingVisited(int idx, int r) {
		if (r == 0) {
			System.out.println(Arrays.toString(visited));
			return;
		}
		
		for (int i = idx, end = chars.length; i < end; i++) {
			visited[i] = true;
			makeComboUsingVisited(i+1, r-1);
			visited[i] = false;
		}
	}

	/**
	 * 완성 배열 temp와 방문 배열을 새로 만들어서 주소를 갖고 다닌다. => 전역변수 사용하지 않음
	 * @param r : 뽑아야 할 숫자
	 * @param temp : 뽑힌 녀석들
	 * @param visited : 중복 방지용
	 */
	static void makePerm(int r, char[] temp, boolean[] visited) {
		// base rule
		if (r==0) {
			System.out.println(Arrays.toString(temp));
			return;
		}
		// inductive rule
		for (int i = 0, end = chars.length; i < end; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			temp[r-1] = chars[i];
			makePerm(r-1, temp, visited);
			visited[i] = false;
		}
	}
	// 중복 순열 만들기 (방문체크를 하지 않으면 됨)
	static void makePerm2(int r, char[] temp) {
		// base rule
		if (r==0) {
			System.out.println(Arrays.toString(temp));
			return;
		}
		// inductive rule
		for (int i = 0, end = chars.length; i < end; i++) {
			temp[r-1] = chars[i];
			makePerm2(r-1, temp);
		}
	}

}
