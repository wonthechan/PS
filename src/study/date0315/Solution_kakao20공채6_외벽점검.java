package study.date0315;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 완탐(순열생성)으로 풀 수 있는 문제.
 * 하지만 약간의 아이디어가 필요하다.
 */
// 참고: https://saintbeller96.tistory.com/12
public class Solution_kakao20공채6_외벽점검 {

	static int N;
	public static void main(String[] args) throws Exception {
		int n = 12;
		int[] weak = {1, 5, 6, 10};
		int[] weak2 = {1, 3, 4, 9, 10};
		int[] dist = {1, 2, 3, 4};
		int[] dist2 = {3, 5, 7};
		System.out.println(solution(n, weak2, dist2));
	}
	
    public static int solution(int n, int[] weak, int[] dist) {
        int answer = -1;
        N = n;
        
        // 1. weak 배열을 한칸씩 왼쪽으로 땡기면서 만들 수 있는 모든 배열을 만들어준다.
        // (weak 배열의 모든 원소가 한번씩 시작점이 될 수 있도록)
        List<int[]> wList = new ArrayList<>();
        wList.add(weak.clone());
        int[] temp = weak.clone();
        for (int i = 0; i < weak.length - 1; i++) {
        	int first = temp[0] + n;
        	for (int j = 0; j < weak.length - 1; j++) {
        		temp[j] = temp[j + 1];
        	}
        	temp[weak.length - 1] = first;
        	wList.add(temp.clone());
        }
//        for(int[] arr : wList) System.out.println(Arrays.toString(arr));
        // 2. 1명에서 dist.length명을 뽑는 순열 생성하면서 그 때마다 확인
        for (int r = 1; r <= dist.length; r++) {
        	if (generatePerm(dist, wList, new int[r], r, 0)) {
        		return r;
        	}
        }
        return answer;
    }

    // bit-masking 을 이용한 순열 생성
	private static boolean generatePerm(int[] dist, List<int[]> wList, int[] res, int r, int visit) {
		if (r == 0) {
			// 앞서 만든 모든 weak 배열들을 돌면서 전부 체크할 수 있는지 확인한다.
			for (int[] weak : wList) {
				int cur = weak[0];
				int i = 1;
				for (int d : res) {
					int p = cur + d;
					for (int j = i; j < weak.length; j++) {
						if (weak[j] <= p) {
							++i;
						} else {
							cur = weak[j];
							break;
						}
					}
					if (i >= weak.length) {
//						System.out.println(Arrays.toString(weak));
//						System.out.println(Arrays.toString(res));
						return true;
					}
				}
			}
			return false;
		}
		for (int i = 0; i < dist.length; i++) {
			if ((visit & 1 << i) > 0) continue;	// 전에 이미 뽑았다면 넘어가자~
			res[r-1] = dist[i];
			if (generatePerm(dist, wList, res, r-1, visit | 1 << i)) return true;
		}
		return false;
	}

}
