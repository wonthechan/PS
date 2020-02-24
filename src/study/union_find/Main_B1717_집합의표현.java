package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B1717_집합의표현 {

	static int N, M;
	static int[] parents;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b1717.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		// 배열 생성 및 초기화 작업.
		makeSet();
	
		// 명령어 입력
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case 0:	// union 연산
				union(a, b);
				break;
			case 1:	// 같은 집합에 포함되어 있는지 확인
				sb.append(sameParent(a, b)).append('\n');
				break;
			}
		}
		
		System.out.println(sb.toString());
	}

	private static String sameParent(int a, int b) {
		if (findSet(a) != findSet(b)) return "NO";	// 대표자가 다르면 각 각 다른 집합에 소속되어 있음
		return "YES";
	}
	private static void union(int a, int b) { // 합집합 메소드
		int rootA = findSet(a); // 대표자 rootA
		int rootB = findSet(b);
		// 대표자가 서로 같은 경우 합칠 수 없다.
		// 대표자가 서로 다르면 유니온할 수 있다. (대표자 통일하기)
		if (rootA < rootB) {	// 숫자가 더 작은 쪽으로 몰아주기
			parents[rootB] = rootA;
		} else if (rootA > rootB) {
			parents[rootA] = rootB;
		}
	}
	
	private static int findSet(int i) {	// 대표자를 찾는 메소드
		if (parents[i] < 0) return i; 
		return parents[i] = findSet(parents[i]);
	}
	
	private static void makeSet() {
		parents = new int[N + 1];
		Arrays.fill(parents, -1);
	}

}
