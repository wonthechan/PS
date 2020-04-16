package study.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** 그리디로 접근 
 *  소금 봉지의 종류가 더 많아지는 경우에는 짜기 어려움.
 * */
public class 소금배달_Backtracking {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int M = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		
		while (M > 0 && M % 5 != 0) { // 5로 나누어 떨어지지 않는다면
			M -= 3;
			cnt++;
		}
		
		if (M < 0) {
			System.out.println(-1);
		} else {
			cnt += M / 5;
			System.out.println(cnt);
		}
	}

}
