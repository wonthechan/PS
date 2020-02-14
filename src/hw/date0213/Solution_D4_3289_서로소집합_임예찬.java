package hw.date0213;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3289_서로소집합_임예찬 {

	static int N, M;
	static int[] parents;
	
	public static void makeSet() {
		Arrays.fill(parents, -1);
	}
	
	public static int findSet(int a) {	// 대표자를 찾는다
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);	// path compression
	}
	
	public static void union(int a, int b) {
		// 서로의 대표자를 먼저 찾는다 (findSet 이용)
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		// 서로의 대표자가 같은지 확인
		if (aRoot != bRoot) {	// 다르다면 합칠 수 있다.
			parents[bRoot] = aRoot;
		}
	}
	
	public static boolean canUnion(int a, int b) {
		// 서로의 대표자를 먼저 찾는다 (findSet 이용)
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		// 서로의 대표자가 같은지 확인
		if (aRoot != bRoot) {	// 다르다면 합칠 수 있다.
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_s3289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N + 1];
			
			makeSet();	// 단위 집합 만드는 작업
			
			while (M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int unionOrFind = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch (unionOrFind) {
				case 0:	// Union
					union(a, b);
					break;
				case 1:	// Union 가능 여부
					sb.append(canUnion(a,b)? 0 : 1);
					break;
				}
			}
			System.out.println("#" + tc + " " + sb.toString());
		}
	}

}
