package study.dp;

import java.util.Arrays;

public class 동전거스름돈_DP {

	public static void main(String[] args) {
		// 1차원 배열에 업데이트 해서 동전 거스름돈 개수를 저장해보자.
		int N = 8;
		int[] c = new int[N + 1];
		
		// 1원 짜리로만 거스르는 경우
		for (int i = 0; i < c.length; i++) {
			c[i] = i; // 1원짜리 필요한 개수
		}
		System.out.println(Arrays.toString(c));
		
		// 1, 4 원 짜리 동전을 이용해서 거스르는 경우
		for (int i = 4; i < c.length; i++) { // 최소 4원 이상은 되어야 4원 짜리 동전을 사용하여 거스를 수 있다.
			// 현재 c[i]에는 1원짜리 동전만 고려한 최소 개수가 들어가 있음
			c[i] = Math.min(c[i], c[i-4] + 1); // c[i-4] + 1 은 4원짜리 동전까지 고려한 최소 개수
		}
		System.out.println(Arrays.toString(c));
		
		for (int i = 6; i < c.length; i++) {
			// 현재 c[i]에는 1, 4원짜리 동전만 고려한 최소 개수가 들어가 있음
			c[i] = Math.min(c[i], c[i-6] + 1); // c[i-6] + 1 은 6원짜리 동전까지 고려한 최소 개수
		}
		System.out.println(Arrays.toString(c));
		
		System.out.println(c[8]);
	}

}
