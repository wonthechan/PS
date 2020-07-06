package study.date0705;

import java.util.HashSet;
import java.util.Set;

public class Solution_소수찾기 {

	static Set<Integer> answer = new HashSet<>();
	public static void main(String[] args) {
		System.out.println(solution("011"));
	}

	public static int solution(String numbers) {
        // 문자열을 배열로 변환
        int[] nums = numbers.chars().map(i -> i - '0').toArray();
        
        // 순열 생성
        for (int i = 1; i <= nums.length; i++) {
        	makePermDFS(0, i, new boolean[nums.length], new int[i], nums);
        }
        return answer.size();
    }

	private static void makePermDFS(int level, int r, boolean[] visited, int[] result, int[] nums) {
		if (level == r) {
			// 순열 결과 순자로 만들기
			int res = 0;
			int pow = (int) Math.pow(10, r - 1);
			for (int i = 0; i < r; i++) {
				res += result[i] * pow;
				pow /= 10;
			}
			if (answer.contains(res)) return;
			if (isPrime(res)) answer.add(res);
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			result[level] = nums[i];
			makePermDFS(level + 1, r, visited, result, nums);
			visited[i] = false;
		}
	}
	
	private static boolean isPrime(int num) {
		if (num < 2) return false;
		int end = (int) Math.sqrt(num);
		for (int i = 2; i <= end; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
}
