package study.date0705;

import java.util.Arrays;

public class Solution_카펫 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(24, 24)));
	}

	/*
	 * brown = width * 2 + 2 * ( height - 2 )
	 * yellow = ( width - 2 ) * ( height - 2 )
	 * => width * height = brown + yellow
	 */
	static public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int bPlusY = brown + yellow;
        // width >= height 이어야 하므로
        int end = (int) Math.sqrt(bPlusY);
        for (int height = 3; height <= end; height++) { // height는 최소 3부터 시작
        	if (bPlusY % height == 0) {
        		int width = bPlusY / height;
        		if (width * 2 + 2 * ( height - 2 ) == brown && ( width - 2 ) * ( height - 2 ) == yellow) {
        			answer[0] = width;
        			answer[1] = height;
        			break;
        		}
        	}
        }
        return answer;
    }

}
