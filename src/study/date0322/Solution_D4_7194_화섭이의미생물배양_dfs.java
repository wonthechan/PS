package study.date0322;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DFS (백트래킹:리모콘예제) */
// 아이디어 1: 거꾸로 생각해보면 s에서 t로 가는 것보다 t를 나누거나 빼서 s를 찾는 것이 경우의 수를 줄일 수 있음.
// 아이디어 2: dfs 사용 (가지치기)
public class Solution_D4_7194_화섭이의미생물배양_dfs {

	static int S, T, A, B, answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s7194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			answer = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken()); // 입력 끝

			// b가 1인 경우는 곱을 하지 않는다.
			if (B == 1) {
				if ((T-S) % A == 0) {
					answer = (T-S) / A; // 합으로만 t에 도달할 수 있는 경우
				}
			} else {
				dfs(T, 0);
			}
			if (answer == Integer.MAX_VALUE) {
				System.out.println("#" + tc + " -1");
			} else {
				
				System.out.println("#" + tc + " " + answer);
			}
		}
	}
	
	private static void dfs(int cur, int cnt) {
		if (cur == S) { // 시작점 s에 도달한 경우: 카운트 최솟값 갱신
			answer = Math.min(answer, cnt);
			return;
		}
		
		if (cur < S) {	// 현재 구한 값이 시작점 s보다 작아지는 경우 가지치기
			return;
		}
		
		if (cur % B == 0) {
			dfs(cur / B, cnt + 1);
			dfs(cur - A, cnt + 1);
		} else {
			dfs(cur - A, cnt + 1);
		}
	}

}
