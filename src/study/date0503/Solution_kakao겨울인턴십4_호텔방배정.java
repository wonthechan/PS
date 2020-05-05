package study.date0503;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// 2h
public class Solution_kakao겨울인턴십4_호텔방배정 {

	static Map<Long, Long> parents;
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = {1,3,4,1,3,1};
		System.out.println(Arrays.toString(solution(k, room_number)));
	}

	static public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        makeSet();
        
        int idx = 0;
        for (long num : room_number) {
        	answer[idx++] = assign(num);
        }
        return answer;
    }
	
	static void makeSet() {
		parents = new HashMap<Long, Long>();
	}
	
	static long findSet(long a) {
		if (!parents.containsKey(a)) return a;
		long ret = findSet(parents.get(a));
		parents.put(a, ret);
		return ret;
	}
	
	static long assign(long a) {
		long rootA = findSet(a);
		if (rootA == a) { 	// a번 방이 비어있는 경우
			parents.put(a, a + 1);
		} else {			// a번 방이 이미 차있는 경우
			parents.put(rootA, rootA + 1);
		}
		return rootA;
	}
}
