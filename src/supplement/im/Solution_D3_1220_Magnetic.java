package supplement.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 기본적인 2차원 배열 탐색 */
public class Solution_D3_1220_Magnetic {
	
	static int[][] arr;
	static final int N = 100;
					//	상 하
	static int[] dy2 = {-1, 1};
	static int[] dx2 = {0, 0};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1220.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		arr = new int[N][N];
		
		for (int tc = 1; tc <= 10; tc++) {
			int answer = 0;
			br.readLine(); // 테케 번호 입력
			
			// 배열 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 배열을 탐색
			boolean flag = false;
			for (int j = 0; j < N; j++) {
				flag = false;
				for (int i = 0; i < N; i++) {
					int cur = arr[i][j];
					switch (cur) {
					case 1:
						flag = true;
						break;
					case 2:
						if (flag) {
							answer++;
							flag = false;
						}
						break;
					}
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

}
