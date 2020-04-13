package study.date0412;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17779_게리맨더링2 {

	static int N;
	static int y, x, d1, d2; // 기준점 x, y 와 경계 길이 d1, d2
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17779.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		
		// 완탐 시도
		// 모든 점을 기준점으로 생각해본다.
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < N - 2; j++) { // 최소한의 선거구를 만들 수 있는 범위내에서만
				y = j;
				x = i;
				// 가능한 경계 길이 확인
				d1 = d2 = 1;
				while (d1 <= y - 1) {
					while (d2 <= N - y && d1 + d2 <= N - x) {
						// 1번 선거구
						int area1 = cal1();
						
						++d2;
					}
					++d1;
				}
			}
		}
	}
	
	private static int cal1() {
		int cnt = 0;
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j < y; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}

}
