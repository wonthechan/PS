package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B10775_공항 {

	static int G, P;
	static boolean[] docked;
	static int[] parents; 	// 게이트의 개수 만큼 대표자를 만든다.
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b10775.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		makeSet();
		
		int cnt = 0;
		for (int i = 0; i < P; i++) {
			int cur = Integer.parseInt(br.readLine());
			int rootCur = findSet(cur);
			if (rootCur == 0) break;	// 0인 경우 더 이상 자리가 없는 경우
			++cnt;
			union(rootCur, rootCur-1);
		}
		
		System.out.println(cnt);
	}
	
	private static void union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA < rootB) {
			parents[rootB] = rootA;
		} else if (rootA > rootB) {
			parents[rootA] = rootB;
		}
	}
	
	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	private static void makeSet() {
		parents = new int[G + 1];	// 0은 dummy 역할
		Arrays.fill(parents, -1);
	}

}
