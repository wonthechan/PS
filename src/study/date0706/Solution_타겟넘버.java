package study.date0706;

import java.util.Arrays;

public class Solution_타겟넘버 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1, 1, 1, 1, 1}, 3));
	}

	static int answer = 0;
	static int[] nums;
	static int targetNum;
	static boolean[] result;
	static public int solution(int[] numbers, int target) {
		nums = numbers;
		targetNum = target;
		result = new boolean[numbers.length];
		
		dfs(0, numbers.length);
        return answer;
    }
	private static void dfs(int level, int r) {
		if (level == r) {
			if (cal() == targetNum) ++answer;
			return;
		}
		
		result[level] = true;
		dfs(level + 1, r);
		result[level] = false;
		dfs(level + 1, r);
	}
	
	private static int cal() {
		int res = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i]) {	// 더하기
				res += nums[i];
			} 
			else {				// 빼기
				res -= nums[i];
			}
		}
		return res;
	}
}
