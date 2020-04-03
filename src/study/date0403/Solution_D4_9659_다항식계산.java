package study.date0403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_9659_다항식계산 {
	
	static final int DIV = 998244353;
	static int N, M;
	static long[] memo = new long[51];		// x값 대입 시 i번째 다항식의 결과값 저장
	static int[][] polys = new int[51][3];	// i번째 다항식의 t, a, b 값을 저장
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_s9659.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc);
			N = Integer.parseInt(br.readLine());
			for (int i = 2; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				polys[i][0] = Integer.parseInt(st.nextToken());
				polys[i][1] = Integer.parseInt(st.nextToken());
				polys[i][2] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int x = Integer.parseInt(st.nextToken());
				memo[0] = 1;
				memo[1] = x;
				// 계산 시작
				for (int j = 2; j <= N; j++) {
					memo[j] = cal(polys[j][0], polys[j][1], polys[j][2]);
				}
				sb.append(" ").append(memo[N]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static long cal(int t, int a, int b) {
		long res = 0;
		switch (t) {
		case 1:
			res = (memo[a] + memo[b]) % DIV;
			break;
		case 2:
			res = (a * memo[b]) % DIV;
			break;
		case 3:
			res = (memo[a] * memo[b]) % DIV;
			break;
		}
		return res;
	}
}
