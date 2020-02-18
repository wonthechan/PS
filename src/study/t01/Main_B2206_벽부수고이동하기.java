package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2206_벽부수고이동하기 {

	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b2206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
	}
	
	static class Pos {
		int x, y;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
