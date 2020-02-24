package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B3780_네트워크연결 {

	static int N;
	static int[] parents;
	static int[] dists;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b3780.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			
			makeSet();
			
			boolean flag = true;
			while (flag) {
				st = new StringTokenizer(br.readLine(), " ");
				char cmd = st.nextToken().charAt(0);
				switch (cmd) {
				case 'E':
					// 기업 I와 현재 I의 센터까지의 거리를 출력한다.
					int corpI = Integer.parseInt(st.nextToken());
					findSet(corpI);
					sb.append(dists[corpI]).append('\n');
					break;
				case 'I':
					// 센터 I를 기업 J에 연결한다.
					int centerI = Integer.parseInt(st.nextToken());
					int corpJ = Integer.parseInt(st.nextToken());
					union(centerI, corpJ);
					break;
				default:			// 입력 종료
					flag = false;
					break;
				}
			}
			System.out.print(sb.toString());
		}
	}

	private static void union(int I, int J) {
		parents[I] = J;
		dists[I] = (Math.abs(I - J)) % 1000;
	}
	
	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		int rootA = findSet(parents[a]);
		dists[a] += dists[parents[a]];
		return parents[a] = rootA;
	}
	private static void makeSet() {
		parents = new int[N + 1];
		dists = new int[N + 1];
		Arrays.fill(parents, -1);
	}
}
