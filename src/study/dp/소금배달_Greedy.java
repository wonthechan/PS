package study.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 백트래킹(가지치기)으로 풀기 
 * 
 * */
public class 소금배달_Greedy {

	static int min = Integer.MAX_VALUE;	// 최소봉지 개수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		
		
		gogosing(M, 0); // 배달함수
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	/** 재귀함수로 봉지의 개수를 구한다 */
	private static void gogosing(int M, int cnt) { // 현재까지 남은 무게 M과 사용한 봉지의 개수 cnt
		if (M < 0) { // 끝!!
			return;
		} else if (M == 0) {
			// 끝!!
			min = Math.min(min, cnt);
			return;
		}
		gogosing(M - 5, cnt + 1);
		gogosing(M - 3, cnt + 1);
	}

}
