package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B9938_방청소 {

	static int N, L;	// 술병의 갯수와 서랍의 갯수
	static int[] parents;	// disjoint set 사용
	static boolean[] occupied;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b9938.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		makeSet();
		occupied = new boolean[L + 1];	// 현재 서랍이 쓰이고 있는지 여부가 담기는 배열
		
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			sb.append(union(first, second)? "LADICA" : "SMECE").append("\n");
		}
		System.out.print(sb.toString());
	}

	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if (!occupied[rootA]) { 	// 첫번째 서랍의 차선책 서랍이 비어 있는 경우
			occupied[rootA] = true;
			if (rootA != rootB) parents[rootA] = rootB;
			return true;
		} else if (!occupied[rootB]) {	
			occupied[rootB] = true;
			if (rootA != rootB) parents[rootB] = rootA;
			return true;
		}
		// 차선책 서랍이 다 차 있는 경우 (마셔야 한다)
		return false;
	}
	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	private static void makeSet() {
		// 서랍을 노드로 생각한다. (루트노드는 다음 차선책 -> 비어있는 서랍)
		parents = new int[L + 1];
		Arrays.fill(parents, -1);
	}
}