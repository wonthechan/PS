package study.date0709;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B1717_집합의표현 {

	static int N, M; // n(1≤n≤1,000,000), m(1≤m≤100,000)
	static int[] parents;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1717.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeSet();
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())) {
			case 0:	// union
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case 1:	// find
				if (isInSameSet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makeSet() {
		parents = new int[N + 1];
		Arrays.fill(parents, -1);
	}

	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA < rootB) {
			parents[rootB] = rootA;
			return true;
		} else if (rootB < rootA) {
			parents[rootA] = rootB;
			return true;
		}
		return false;
	}
	private static boolean isInSameSet(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB) return true;
		return false;
	}
}
