package study.date0505;

// 
public class Solution_kakao겨울인턴십5_징검다리건너기 {

	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		System.out.println(solution(stones, k));
	}

	/* 해설 로직 (이분 탐색)
	 * 답이 될 수 있는 최솟값과 최댓값의 중간값으로 M값을 계속 변경해 주면 효율적으로 탐색 범위를 줄여나갈 수 있습니다.
	 * 시간 복잡도는 O(nlogm)이 되며, n은 디딤돌의 개수, m은 디딤돌에 적힌 숫자의 최댓값
	 * 이분탐색하는 시간: log(m) * 연속된 0의 개수가 k개 나오는지 확인하는 시간: log(n)
	 */
	static public int solution(int[] stones, int k) {
        int answer = 0;
        
        // 1. 디딤돌에 적혀 있는 숫자중 MAX 값과 MIN 값을 찾는다. (이분탐색 준비)
        int max = -1;
        int min = 0;
        for (int stone : stones) {
        	max = Math.max(max, stone);
        }
        
        // 2. 이분 탐색 시작
        while (min <= max) {
        	int mid = (min + max) / 2; // 중간 값
        	if (findZeroSeq(stones, mid, k)) {
        		// 0이 연속으로 K개가 나오는 구간이 있는 경우 : M번째 친구는 징검다리를 건널 수 없습니다.
        		// 따라서 찾아야 하는 답은 0 이상 M – 1 이하인 정수 중 하나입니다.
        		max = mid - 1;
        	} else {
        		// 0이 연속으로 K개가 나오는 구간이 없는 경우 : M번째 친구는 징검다리를 건널 수 있습니다.
        		// 따라서 찾아야 하는 답은 M 이상 MAX값 이하인 정수 중 하나
        		answer = mid;
        		min = mid + 1;
        	}
        }
        
        return answer;
    }
	
	// mid 번째 친구가 건널 때 0이 연속으로 K개가 나오면 true 리턴
	static public boolean findZeroSeq(int[] stones, int mid, int k) {
		int cnt = 0;
		for (int stone : stones) {
			if (stone < mid) {
				if (++cnt == k) return true;
			} else {
				cnt = 0;
			}
		}
		return false;
	}
}
