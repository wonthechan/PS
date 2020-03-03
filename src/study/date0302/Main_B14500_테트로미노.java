package study.date0302;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 16:55
public class Main_B14500_테트로미노 {

	static int N, M;
	static int[][] map;
	static int[][] memoRow;
	static int[][] memoCol;
	static int answer = -987654321;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14500.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		memoRow = new int[N][M];
		memoCol = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		makeMemo();	// 미리 부분합 구해 놓기
		
//		for (int[] a : map) System.out.println(Arrays.toString(a));
//		System.out.println();
//		for (int[] a : memoRow) System.out.println(Arrays.toString(a));
//		System.out.println();
//		for (int[] a : memoCol) System.out.println(Arrays.toString(a));
		
		check1();	// 막대기 모양 체크
		
		check2();	// 2x2 정사각형 모양 체크
		
		System.out.println(answer);
	}
	
	private static void check2() {
		int subSum;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				subSum = 3;
				subSum = (j == 0)? memoRow[i][3] : memoRow[i][j+3] - memoRow[i][j-1];
				answer = Math.max(answer, subSum);
			}
		}
	}


	private static void check1() {
		int subSum;
		for (int i = 0; i < N; i++) { // Row Check
			for (int j = 0; j < M - 3; j++) {
				subSum = (j == 0)? memoRow[i][3] : memoRow[i][j+3] - memoRow[i][j-1];
				answer = Math.max(answer, subSum);
			}
		}
		for (int j = 0; j < M; j++) { // Column Check
			for (int i = 0; i < N - 3; i++) {
				subSum = (i == 0)? memoCol[3][j] : memoCol[i+3][j] - memoCol[i-1][j];
				answer = Math.max(answer, subSum);
			}
		}
	}

	private static void makeMemo() {
		for (int i = 0; i < N; i++) {
			memoRow[i][0] = map[i][0];
			for (int j = 1; j < M; j++) {
				memoRow[i][j] = memoRow[i][j-1] + map[i][j];
			}
		}
		for (int j = 0; j < M; j++) {
			memoCol[0][j] = map[0][j];
			for (int i = 1; i < N; i++) {
				memoCol[i][j] = memoCol[i-1][j] + map[i][j];
			}
		}
	}
}
