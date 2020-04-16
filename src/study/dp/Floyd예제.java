package study.dp;

import java.util.Arrays;

public class Floyd예제 {

	static final int M = Integer.MAX_VALUE;
	public static void main(String[] args) {
		int[][] D = {
				{0, M, 2, 3},
				{4, 0, 1, 8},
				{2, 5, 0, M},
				{M, 9, 6, 0}
		};
		
		for (int k = 0; k < D.length; k++) {			// 경유 정점
			for (int i = 0; i < D.length; i++) {		// 출발 정점
				if (i == k) continue; // 굳이 쓰지 않아도 결과에는 영향을 미치지 않는다.
				for (int j = 0; j < D.length; j++) {	// 도착 정점
					if (j == k || j == i) continue;
					// 경유하는 경우 무한대 가중치가 있다면 그냥 원래 거리를 선택
					// 무한대 가중치를 int형 범위내에서 충분히 큰 수로 대체 할 수 있다면
					// D[i][k] != M && D[k][j] != M 와 같은 코드가 불필요하다.
					if (D[i][k] != M && D[k][j] != M) {
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
		}
		
		for (int[] arr : D) System.out.println(Arrays.toString(arr));
	}

}
