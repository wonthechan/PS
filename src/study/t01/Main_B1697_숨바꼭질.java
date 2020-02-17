package study.t01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1697_숨바꼭질 {

	static int N, K;
	static int MIN;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// visit 배열 생성
		visit = new boolean[10001];
		
		MIN = Integer.MAX_VALUE;
		dfs(N, 0);
		
		System.out.println(MIN);
	}
	private static void dfs(int cur, int level) {
		if (cur > K) return;
		else if (cur == K) {
			MIN = Math.min(MIN, level);
			return;
		}
		
		if (!visit[cur]) {
			visit[cur] = true;
		}
		dfs(N * 2, level + 1);
		dfs(N + 1, level + 1);
		dfs(N - 1, level + 1);
	}

}
