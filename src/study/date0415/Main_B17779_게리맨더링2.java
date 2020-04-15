package study.date0415;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B17779_게리맨더링2 {

	static int N;
	static int y, x; // 기준점 x, y
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17779.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int totalSum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				totalSum += map[i][j];
			}
		} // 입력 끝
		
		int answer = Integer.MAX_VALUE;
		// 완탐 시도
		// 모든 점을 기준점으로 생각해본다.
		for (int si = 0; si < N - 2; si++) {
			for (int sj = 1; sj < N - 1; sj++) { // 최소한의 선거구를 만들 수 있는 범위내에서만
				y = sj;
				x = si;
				// 가능한 경계 길이 확인
				// 경계 길이 d1, d2
				for (int d1 = 1; y - d1 >= 0; d1++) {
					for (int d2 = 1; y + d2 < N; d2++) {
						// d1, d2 길이 유효성 체크!!
						if (x + d1 + d2 >= N) continue;
//						System.out.println("d1: " + d1 + ", d2: " + d2);
						int[][] tMap = markFiveArea(d1, d2);
//						for (int[] arr : tMap) System.out.println(Arrays.toString(arr));
//						System.out.println();
						
						// 1번 선거구 인구수 계산
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						int total = 0;
						int subTotal = 0;
					L1:	for (int i = 0, end = x + d1; i < end; i++) {
							for (int j = 0; j <= y; j++) {
								if (tMap[i][j] == 5) continue L1;
								total += map[i][j];
							}
						}
						subTotal += total;
						max = Math.max(max, total);
						min = Math.min(min, total);
						total = 0;
						// 2번 선거구 인구수 계산
					L1:	for (int i = 0, end = x + d2; i <= end; i++) {
							for (int j = N - 1; j > y; j--) {
								if (tMap[i][j] == 5) continue L1;
								total += map[i][j];
							}
						}
						subTotal += total;
						max = Math.max(max, total);
						min = Math.min(min, total);
						total = 0;
						// 3번 선거구 인구수 계산
					L1:	for (int i = x + d1; i < N; i++) {
							for (int j = 0, end = y - d1 + d2; j < end; j++) {
								if (tMap[i][j] == 5) continue L1;
								total += map[i][j];
							}
						}
						subTotal += total;
						max = Math.max(max, total);
						min = Math.min(min, total);
						total = 0;
						// 4번 선거구 인구수 계산
					L1:	for (int i = x + d2 + 1; i < N; i++) {
							for (int j = N-1, endj = y - d1 + d2; j >= endj; j--) {
								if (tMap[i][j] == 5) continue L1;
								total += map[i][j];
							}
						}
						subTotal += total;
						max = Math.max(max, total);
						min = Math.min(min, total);
						
						// 5번 선거구 인구수 계산
						total = totalSum - subTotal;
						max = Math.max(max, total);
						min = Math.min(min, total);
						
						answer = Math.min(answer, max - min);
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static int[][] markFiveArea(int d1, int d2) {
		int[][] tMap = new int[N][N];
		
		for (int i = x, j = y, endi = x + d1, endj = y - d1; i <= endi && j >= endj; i++,j--) {
			tMap[i][j] = 5;
		}
		for (int i = x, j = y, endi = x + d2, endj = y + d2; i <= endi && j <= endj; i++,j++) {
			tMap[i][j] = 5;
		}
		for (int i = x + d1, j = y - d1, endi = x + d1 + d2, endj = y - d1 + d2; i <= endi && j <= endj; i++,j++) {
			tMap[i][j] = 5;
		}
		for (int i = x + d2, j = y + d2, endi = x + d2 + d1, endj = y + d2 - d1; i <= endi && j >= endj; i++,j--) {
			tMap[i][j] = 5;
		}
		return tMap;
	}
}
