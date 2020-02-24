package supplement.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D2_2001_파리퇴치 {
	
	static int M, N;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];			// 배열 생성
			
			for (int i = 0; i < N; i++) {	// 배열 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 최대합 구하기
			int max = 0;
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) { // 시작점
					int curSum = 0;
					for (int y = i; y < i + M; y++) {
						for (int x = j; x < j + M; x++) {
							curSum += arr[y][x];
						}
					}
					max = Math.max(max, curSum);
				}
			}

			sb.append(max);
			System.out.println(sb.toString());
		}
	}
}
