package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B17140_이차원배열과연산 {

	static int R, C, K; // A[R][C]에 들어있는 값이 K가 되기 위한 최소 시간을 구해보자
	static int[][] map = new int[100][100];
	static int N, M;	// 현재 배열의 행, 열 크기
	static int[] freq = new int[101];	// 빈도 수 배열
	static List<Elem> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17140.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = M = 3;	// 초기 크기는 3부터 시작
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 초기 상태 입력 끝
		
		int sec = 0;
		while (true) {
//			System.out.println(N + ", " + M);
			// 목표 확인
			if (map[R][C] == K) break;
			
			if (N < M) {
				calC(); // 행의 개수 < 열의 개수인 경우
			} else {
				calR(); // 행의 개수 ≥ 열의 개수인 경우
			}
			if (++sec > 100) { // 100을 넘어가는 경우에는 -1을 출력
				sec = -1;
				break;
			}
		}
		
		System.out.println(sec);
	}
	
	/** 배열 A의 모든 행에 대해서 정렬을 수행 */
	private static void calR() {
		int maxSize = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			Arrays.fill(freq, 0); // 빈도 배열 초기화
			list.clear();
			for (int j = 0; j < M; j++) {
				++freq[map[i][j]];	// 빈도 수 누적
			}
			// 빈도 수가 1이상인 것만 리스트에 넣기
			for (int k = 1; k < 101; k++) {
				if (freq[k] > 0) {
					list.add(new Elem(k, freq[k]));
				}
			}
			// 리스트 정렬
			Collections.sort(list);

			// 행 크기 미리 늘려주기
			if (list.size() > 50) { // 행렬의 크기가 100보다 커질 수 없다.
				maxSize = 100;
				// 배열 반영
				Arrays.fill(map[i], 0);		// 먼저 0으로 쫙 깔아주기
				for (int j = 0, end = 50; j < end; j++) {
					Elem out = list.get(j);
					map[i][j * 2] = out.value;
					map[i][j * 2 +1] = out.freq;
				}
			} else {
				maxSize = Math.max(maxSize, list.size() * 2);
				// 배열 반영
				Arrays.fill(map[i], 0); 	// 먼저 0으로 쫙 깔아주기
				for (int j = 0, end = list.size(); j < end; j++) {
					Elem out = list.get(j);
					map[i][j * 2] = out.value;
					map[i][j * 2 +1] = out.freq;
				}
			}
		}
		M = maxSize;
	}
	
	/** 배열 A의 모든 열에 대해서 정렬을 수행 */
	private static void calC() {
		int maxSize = Integer.MIN_VALUE;
		for (int j = 0; j < M; j++) {
			Arrays.fill(freq, 0); // 빈도 배열 초기화
			list.clear();
			for (int i = 0; i < N; i++) {
				++freq[map[i][j]];	// 빈도 수 누적
			}
			// 빈도 수가 1이상인 것만 리스트에 넣기
			for (int k = 1; k < 101; k++) {
				if (freq[k] > 0) {
					list.add(new Elem(k, freq[k]));
				}
			}
			// 리스트 정렬
			Collections.sort(list);

			// 행 크기 미리 늘려주기
			if (list.size() > 50) { // 행렬의 크기가 100보다 커질 수 없다.
				System.out.println("hola");
				maxSize = 100;
				// 배열 반영
				for (int i = 0; i < 100; i++) {
					map[i][j] = 0;
				}
				
				for (int i = 0, end = 50; i < end; i++) {
					Elem out = list.get(i);
					map[i * 2][j] = out.value;
					map[i * 2 + 1][j] = out.freq;
				}
			} else {
				maxSize = Math.max(maxSize, list.size() * 2);
				// 배열 반영
				for (int i = 0; i < 100; i++) {
					map[i][j] = 0;
				}
				
				for (int i = 0, end = list.size(); i < end; i++) {
					Elem out = list.get(i);
					map[i * 2][j] = out.value;
					map[i * 2 + 1][j] = out.freq;
				}
			}
		}
		N = maxSize;
	}

	static class Elem implements Comparable<Elem>{
		int value, freq; // 값과 빈도 수
		public Elem(int value, int freq) {
			this.value = value;
			this.freq = freq;
		}
		@Override
		public int compareTo(Elem o) {
			// 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬
			return this.freq == o.freq ? this.value - o.value : this.freq - o.freq;
		}
	}
}
