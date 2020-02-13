package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_2805_농작물수확하기 {
	
	static int N;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			int answer = 0;
			
			arr = new int[N][N];
			
			// 배열 입력
			String str = "";
			for (int i = 0; i < N; i++) {
				str = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = str.charAt(j) - '0';
				}
			}
			
			int startJ = N/2;
			int endJ = N/2;
			for (int i = 0; i < N/2; i++) {
				for (int j = startJ; j <= endJ; j++) {
//					System.out.print(arr[i][j] + " ");
					answer += arr[i][j];
				}
				startJ--;
				endJ++;
			}
			for (int i = N/2; i < N; i++) {
				for (int j = startJ; j <= endJ; j++) {
//					System.out.print(arr[i][j] + " ");
					answer += arr[i][j];
				}
				startJ++;
				endJ--;
			}
			
			sb.append(answer);
			System.out.println(sb.toString());
		}
	}
}
