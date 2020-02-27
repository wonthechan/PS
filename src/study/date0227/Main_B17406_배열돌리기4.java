package study.date0227;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17406_배열돌리기4 {

	static int N, M, K;
	static int[][] initMap;
	static int[][] map;
	static int[][] rotates;
	static int answer = 987654321;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17406.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		initMap = new int[N + 1][M + 1];
		map = new int[N + 1][M + 1];
		rotates = new int[K][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				initMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r, c, s;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			rotates[i] = new int[] {r, c, s};
		}
		
		// 넥퍼로 순열 생성
		int[] order = new int[K];
		for (int i = 0; i < K; i++) order[i] = i;
		do {
			initMap();
			// 만들어진 순서에 따라
			for (int i = 0; i < K; i++) {
				rotate(rotates[order[i]][0], rotates[order[i]][1], rotates[order[i]][2]);
			}
			answer = Math.min(answer, getMinRowSum());
		} while(np(order));
		
		System.out.println(answer);
		
	}
	
	private static void initMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = initMap[i][j];
			}
		}
	}

	private static boolean np(int[] arr) {
		int N = arr.length;
		//
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int tmp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = tmp;
		//
		j = N - 1;
		while (i < j) {
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			++i;--j;
		}
		return true;
	}

	static void printMap() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void rotateOne(int r, int c, int s, int[][] copied) {
		for (int j = c - s; j < c + s; j++) {
			map[r - s][j + 1] = copied[r - s][j];
		}
		for (int i = r - s; i < r + s; i++) {
			map[i + 1][c + s] = copied[i][c + s];
		}
		for (int j = c + s; j > c - s; j--) {
			map[r + s][j - 1] = copied[r + s][j];
		}
		for (int i = r + s; i > r - s; i--) {
			map[i - 1][c - s] = copied[i][c - s];
		}
	}
	
	static void rotate(int r, int c, int s) {
		int[][] copied = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++){
			for (int j = 1; j <= M; j++) copied[i][j] = map[i][j];
		}
		
		for (int k = 1; k <= s; k++) {
			rotateOne(r, c, k, copied);
		}
	}
	
	static int getMinRowSum() {
		int ret = 987654321;
		for (int i = 1; i <= N; i++) {
			int rowSum = 0;
			for (int j = 1; j <= M; j++) rowSum += map[i][j];
			ret = Math.min(ret, rowSum);
		}
		return ret;
	}

}
