package study.date0227;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취_임예찬 {

	static int N, M, C;
	static int[][] map;
	static Pos[] subSum;
	static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = -987654321	;
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			int subSumSize = (N - M + 1) * N;
			subSum = new Pos[subSumSize];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 나올 수 있는 모든 구간의 부분합 최대값을 구해서 따로 배열에 저장해둔다.
			int idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N-M; j++) {
					subSum[idx++] = new Pos(i, j, j + (M-1), getMaxSubSum(i, j, M));
				}
			}
			
			// 앞서 저장한 배열에서 2개씩 뽑는 조합으로 합이 가장 큰 경우를 찾는다. (범위가 서로 겹치는 경우는 continue)
			for (int i = 0; i < subSumSize; i++) {
				for (int j = i + 1; j < subSumSize; j++) {
					if (subSum[j].si == subSum[i].si && subSum[j].sj <= subSum[i].ej) continue;
					answer = Math.max(subSum[i].sum + subSum[j].sum, answer);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	private static int getMaxSubSum(int i, int j, int n) {
		int ret = -987654321;
		int tmp = (int) Math.pow(2, n);
		for (int k = 1; k < tmp; k++) {	// 반드시 한개는 선택해야 하므로 1부터 시작
			int sum = 0;
			int squareSum = 0;
			for (int b = 0; b < n; b++) { // 비트마스킹을 통해 모든 부분집합을 구함
				if ((k & 1 << b) > 0) {
					sum += map[i][j+b];
					squareSum += Math.pow(map[i][j+b], 2);
				}
			}
			if (sum <= C) {
				ret = Math.max(ret, squareSum);
			}
		}
		return ret;
	}

	static class Pos {
		int si, sj, ej, sum;
		public Pos (int si, int sj, int ej, int sum) {
			this.si = si;
			this.sj = sj;
			this.ej = ej;
			this.sum = sum;
		}
	}
}
