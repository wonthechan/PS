package study.date0321;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_5987_달리기 {

	static int N, M;
	static long answer;
	static int[] needs;
	static long[] memo;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s5987.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			needs = new int[N];
			memo = new long[1<<N];
			for (int i = 0; i < M; i++) {	// first가 second 보다 먼저 들어왔다.
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken()) - 1;
				int second = Integer.parseInt(st.nextToken()) - 1;
				needs[first] |= (1 << second);
			} // 누가 먼저 들어왔는지에 대한 정보 입력
			
			// M개의 조건을 모두 만족하는 N명의 사람이 들어오는 순서로 가능 모든 경우의 수를 구하라
			
			// 순서니까 기본적으로 순열: N명을 줄세우기.
			// 순열을 하나씩 만들면서 매번 M개의 조건을 만족하는지 확인.?
			// M개의 조건들을 통해 무조건 등수가 정해진 사람도 있을것. => 순열생성시 제외
			
			// 비마로 순열 차례대로 생성
			answer = genPerm(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static long genPerm(int visit) {
		if (visit == (1<<N)-1) {
			return 1L;
		}
		
		if (memo[visit] > 0) return memo[visit];
		
		for (int i = 0; i < N; i++) {
			if ((visit & 1 << i) > 0) continue;
			if ((visit & needs[i]) == needs[i]) {
				memo[visit] += genPerm(visit | 1 << i);
			}
		}
		return memo[visit];
	}
}
