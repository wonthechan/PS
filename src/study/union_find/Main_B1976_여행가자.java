package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 분류는 유니온파인드로 되어 있지만, 그래프의 연결성 확인 문제인만큼 DFS나 BFS를 통해서도 풀이가 가능했다. */
public class Main_B1976_여행가자 {

	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b1976.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		makeSet();
		
		// 인접행렬 입력
		for (int i = 1; i <= N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// i와 j의 연결 상태 => union
				if (Integer.parseInt(st.nextToken()) > 0 && i < j) {
					union(i, j);
				}
			}
		}
		// M 개의 여행 계획을 입력 받으면서 M개의 도시가 모두 하나의 집합인지 확인
		// 모두의 대표자가 일치해야 한다.
		st = new StringTokenizer(br.readLine());
		int curRoot = findSet(Integer.parseInt(st.nextToken()));
		for (int i = 0; i < M-1; i++) {
			if (curRoot != findSet(Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
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
		parents = new int[N + 1];
		Arrays.fill(parents, -1);
	}

}
