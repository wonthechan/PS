package study.date0505;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 
public class Solution_kakao겨울인턴십5_징검다리건너기 {

	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		System.out.println(solution(stones, k));
	}

	static public int solution(int[] stones, int k) {
        int answer = 0;
        // 무식하게 루프 돌려서 푸는 문제가 아님..
        // 연속된 0이 k를 초과하는 구간을 찾아야함.
        // 부분 증가 수열.?
        
        IntStream stream = Arrays.stream(stones);
        IntStream streamNew = stream.distinct().sorted();
        
        int[] temp = stones.clone();
        
        for (int min : streamNew.toArray()) {
//        	System.out.println(Arrays.toString(temp));
        	if (findZeroSeq(temp) >= k) break;
        	if (min == 0) continue;
        	for (int i = 0; i < stones.length; i++) {
        		temp[i] = stones[i] - min;
        	}
        	answer = min;
        }
        
        return answer;
    }
	
	// 연속된 0의 최대 갯수를 찾는다.
	static public int findZeroSeq(int[] stones) {
		int maxCnt = -1;
		int cnt = 0;
		for (int stone : stones) {
			if (stone < 1) {
				++cnt;
				maxCnt = Math.max(maxCnt, cnt);
			} else {
				cnt = 0;
			}
		}
		return maxCnt;
	}
}
