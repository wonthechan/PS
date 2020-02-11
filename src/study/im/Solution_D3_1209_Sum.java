package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 기본적인 2차원 배열 탐색 */
public class Solution_D3_1209_Sum {
	
	static int[][] arr;
	static final int N = 100;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1209.txt"));
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
			
			// 각 행의 합 구하기
			int rowSum = 0;
			for (int i = 0; i < N; i++) {
				rowSum = 0;
				for (int j = 0; j < N; j++) rowSum += arr[i][j];
				answer = Math.max(answer, rowSum);
			}
			
			// 각 열의 합 구하기
			int colSum = 0;
			for (int j = 0; j < N; j++) {
				colSum = 0;
				for (int i = 0; i < N; i++) colSum += arr[i][j];
				answer = Math.max(answer, colSum);
			}
			
			// 대각선 합 구하기
			int crossSum = 0;
			for (int i = 0; i < N; i++) crossSum += arr[i][i]; // 오른쪽 방향 대각선의 합
			answer = Math.max(answer, crossSum);
			crossSum = 0;
			for (int i = 0; i < N; i++) crossSum += arr[i][N-1-i]; // 왼쪽 방향 대각선의 합
			answer = Math.max(answer, crossSum);
			
			System.out.println("#" + tc + " " + answer);
		}
	}

}
