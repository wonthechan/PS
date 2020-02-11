package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D2_2005_파스칼의삼각형 {
	
	static int N;
	static int[][] pascal;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s2005.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append("\n");
			N = Integer.parseInt(br.readLine());
			
			pascal = new int[N][N];
			
			if (N >= 1) pascal[0][0] = 1;
			if (N >= 2) pascal[1][0] = 1; pascal[1][1] = 1;
			if (N >= 3) {
				for (int i = 2; i < N; i++) {
					for (int j = 0; j <= i; j++) {
						if (j == 0 || j == i) pascal[i][j] = 1;
						else pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
					}
				}
			}
			
			
			System.out.println(sb.toString());
		}
	}
}
