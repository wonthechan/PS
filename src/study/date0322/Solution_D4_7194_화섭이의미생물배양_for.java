package study.date0322;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 반복문으로만 풀어보기 */
// 참고: https://sxngho.github.io/ps/2019/12/17/SWEA-7194/
public class Solution_D4_7194_화섭이의미생물배양_for {

	static int S, T, A, B, answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s7194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken()); // 입력 끝

			// b가 1인 경우는 곱을 하지 않는다.
			if (B == 1) {
				if ((T-S) % A == 0) {
					answer = (T-S) / A; // 합으로만 t에 도달할 수 있는 경우
				} else {
					answer = -1;		// 합으로만 t에 도달할 수 없음 (-1 출력)
				}
			} else {
				answer = solve();
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	// 곱셈(나눗셈) 연산에 우선 순위를 두고 계산해 나간다.
	// 곱셈이 안되는 경우 차선으로 덧셈(뺄셈)연산을 해서 s에 도달할 수 있는지 확인
	private static int solve() {
		int time = 0;
		int cur = T;
		while (S != cur) {
			if (cur % B == 0) { 	// 현재에서 나눌 수 있는 경우
				++time;
				if (cur / B < S) {	// 현재에서 나눴는데 S보다 작아지는 경우
					cur -= A;		// 어쩔 수 없이 뺄 수 밖에 없음.
				} else {
					cur /= B;
				}
			} else {				// 나눌 수 없어서 뺄 수 밖에 없는 경우
				++time;
				cur -= A;
			}
			if (cur < S) {			// S에 도달하지 못한 경우 (break)
				time = -1;
				break;
			}
		}
		return time;
	}

}
