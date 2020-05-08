package study.date0503;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// 2h
public class Solution_kakao겨울인턴십4_호텔방배정 {

	static Map<Long, Long> parents = new HashMap<Long, Long>();	// makeSet 과정 (공간복잡도 때문에 Map 사용)
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = {1,3,4,1,3,1};
		System.out.println(Arrays.toString(solution(k, room_number)));
	}

	static public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        int idx = 0;
        for (long num : room_number) {
        	answer[idx++] = assign(num);
        }
        return answer;
    }
	
	static long findSet(long a) {
		if (!parents.containsKey(a)) return a;
		long ret = findSet(parents.get(a));
		parents.put(a, ret);			// path compression.
		return ret;
	}
	
	static long assign(long a) {
		long rootA = findSet(a);		// a가 가리키는 비어 있는 방 번호 rootA
		parents.put(rootA, rootA + 1);
		return rootA;
	}
}
