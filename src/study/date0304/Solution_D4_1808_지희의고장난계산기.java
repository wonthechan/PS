package study.date0304;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_1808_지희의고장난계산기 {

	static boolean[] nums = new boolean[10];
	static int[] combo;
	static int X, answer, len;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = 987654321;
			len = 0;
			Arrays.fill(nums, false);
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 10; i++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					nums[i] = true;
					++len;
				}
			}
			X = Integer.parseInt(br.readLine());
			combo = new int[X+1];
			
			if (X == 0 && nums[0]) answer = 3;
			else if (X == 0 && !nums[0]) answer = -1;
			else {
				makeCombo(0, 0);
				
				dfs(X, 1);	// 계산 버튼은 항상 누르니까 1부터 시작
				
				answer = answer == 987654321? -1 : answer;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	private static void makeCombo(int value, int pCnt) {
		if (value > X) {
			return;
		}
		
		if (value == 0 && pCnt > 1) return;
		
		if (pCnt > 0) {
			combo[value] = pCnt;
		}
		for (int i = 0; i < 10; i++) {
			if (nums[i]) {
				makeCombo(value * 10 + i, pCnt + 1);
			}
		}
	}
	private static void dfs(int x, int cnt) {
		// 1) X의 각 자릿수가 주어진 버튼으로 구성할 수 있는지 확인
		boolean flag = false;
		int temp = x;
		int k = 0;
		while(temp > 0) {
			++k;
			int digit = temp % 10;
			if (!nums[digit]) {
				flag = true;
				break;
			}
			temp /= 10;
		}
		
		if (!flag) {
			answer = Math.min(answer, cnt + k);
			return;
		}
		
		// 2) 주어진 버튼으로 만든 조합으로 나눌 수 있는지 확인 (0과 1은 누르지 않는다)
		for (int i = 2; i < X; i++) {
			if (combo[i] > 0 && x % i == 0) {
				dfs(x/i, cnt + combo[i] + 1); // 곱하기 버튼과 나누는 수를 누르는 횟수.
			}
		}
	}
}
