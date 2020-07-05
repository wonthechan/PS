package study.date0705;

import java.util.Arrays;

public class Solution_숫자야구 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {
			{123, 1, 1}, 
			{356, 1, 0}, 
			{327, 2, 0}, 
			{489, 0, 1}
		}));
	}

	public static int solution(int[][] baseball) {
        int answer = 0;
        int[] nums = new int[3];
        for (int i = 1; i < 10; i++) {
        	nums[0] = i;
        	for (int j = 1; j < 10; j++) {
        		if (j == i) continue;
        		nums[1] = j;
        		for (int k = 1; k < 10; k++) {
        			if (k == i || k == j) continue;
        			nums[2] = k;
        			boolean flag = true;
        			for (int[] turn : baseball) {
        				if (!isValid(nums, turn)) flag = false;
        			}
        			if (flag) ++answer;
        		}
        	}
        }
        return answer;
    }

	private static boolean isValid(int[] nums, int[] turn) {
		int cntStrike = 0;
		int cntBall = 0;
		String tempStr = turn[0] + "";
		for (int i = 0; i < 3; i++) {
			int k = getIndex(nums, tempStr.charAt(i) - '0');
			if (k == i) { // 스트라이크
				++cntStrike;
			} else if (k > -1) { // 스트라이크는 아니지만 볼
				++cntBall;
			}
		}
		if (cntStrike == turn[1] && cntBall == turn[2]) {
			return true;
		}
		return false;
	}
	
	private static int getIndex(int[] arr, int k) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == k) return i;
		}
		return -1;
	}
}
