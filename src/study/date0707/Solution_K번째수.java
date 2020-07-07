package study.date0707;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_K번째수 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1, 5, 2, 6, 3, 7, 4}, new int[][] {
				{2, 5, 3}, 
				{4, 4, 1}, 
				{1, 7, 3}
			})));
	}

	static List<Integer> list = new ArrayList<>();
	static public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < answer.length; i++) {
        	answer[i] = cal(array, commands[i][0], commands[i][1], commands[i][2]);
        }
        
        return answer;
    }
	
	// 배열 arr의 begin번째 숫자부터 end번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하는 메소드
	public static int cal(int[] arr, int begin, int end, int k) {
		list.clear();
		for (int i = begin - 1; i <= end - 1; i++) {
			list.add(arr[i]);
		}
		Collections.sort(list);
		return list.get(k - 1);
	}
}