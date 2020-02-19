package hw.date0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1244_최대상금_임예찬 {

	static int[] numbers;
	static int N, R;
	static int maxScore;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_s1244.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			maxScore = 0;
			
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			R = Integer.parseInt(st.nextToken());
			N = str.length();
			numbers = new int[N];
			visit = new boolean[R][1000000];	// R번째 교환 시 방문한 적이 있는지
			for (int i = 0; i < N; i++) {
				numbers[i] = str.charAt(i) - '0';
			}
			
			dfs(0);
			System.out.println("#" + tc + " " + maxScore);
		}
	}
	
	private static void dfs(int cnt) {
		int curBonus = computeBonus();		// 현재 보너스 금액을 계산
		if (cnt == R) {						// 교환이 모두 끝난 경우
			maxScore = Math.max(maxScore, curBonus);
			return;
		}
		if (visit[cnt][curBonus]) return;	// 이미 구한적이 있다면 pass
		visit[cnt][curBonus] = true;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				swap(i, j);
				dfs(cnt + 1);
				swap(i, j);
			}
		}
	}
	
	private static int computeBonus() {
		int bonus = 0;
		int mul = 1;
		for (int i = N - 1; i >= 0; i--) {
			bonus += numbers[i] * mul;
			mul *= 10;
		}
		return bonus;
	}
	
	private static void swap(int a, int b) {	// 인자는 인덱스를 받는다.
		int temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}

}