package study.date0705;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_소수찾기 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1,3,2,4,2})));
	}

	/*
	 * 	1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
		2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
		3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
	 */
	public static int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        
        int[][] patterns = {
        		{1, 2, 3, 4, 5},	
        		{2, 1, 2, 3, 2, 4, 2, 5},	
        		{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        
        int[] scores = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
        	for (int j = 0; j < 3; j++) {
        		if (answers[i] == patterns[j][i % patterns[j].length]) {
        			++scores[j];
        		}
        	}
        }
        
        int maxScore = Arrays.stream(scores).max().getAsInt();
        
        for (int i = 0; i < 3; i++) {
        	if (scores[i] == maxScore) answer.add(i);
        }
        
        return answer.stream().mapToInt(i -> i + 1).toArray();
    }
}
