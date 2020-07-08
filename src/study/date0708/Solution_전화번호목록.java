package study.date0708;

import java.util.Arrays;

// 정렬과 비교로만 해결
public class Solution_전화번호목록 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] {"123", "456", "789"}));
	}
	
	static public boolean solution(String[] phone_book) {
		String[] nums = phone_book.clone();
		Arrays.sort(nums);

		for (int i = 1; i < nums.length; i++) {
			if (nums[i].startsWith(nums[i-1])) return false;
		}
		
        return true;
    }
}
