package study.date0224;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A형 기출 */
public class Main_B16637_괄호추가하기 {
	/*
	 * 연산의 순서에 따라 결과가 달라짐.
	 * 재귀로 푸는게 답이다..현재 위치에서 괄호를 포함하느냐 안하느냐
	 * 참고 : https://lshdv.tistory.com/83?category=831519
	 */
	static int MAX = Integer.MIN_VALUE;
	static int N;
	static String raw;
	static int[] operands = new int[10];
	static char[] operators = new char[9];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b16637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		raw = br.readLine();
		
		if (N == 1) {
			System.out.println(raw);
			return;
		}
		
		// 연산자, 피연산자 배열 생성
		operands = new int[N/2 + 1];
		operators = new char[N/2];
		
		// 연산자, 피연산자 배열 입력
		int k1, k2;
		k1 = k2 = 0;
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {	// 피연산자
				operands[k1++] = raw.charAt(i) - '0';
			} else {			// 연산자
				operators[k2++] = raw.charAt(i);
			}
		}
		
		dfs(0, operands[0]);	// 첫번째 피연산자를 포함한채로 시작
		System.out.println(MAX);
	}
	private static void dfs(int idx, int res) {
		if (idx >= N/2) { // 모든 연산을 끝낸 경우 (기저조건)
			MAX = Math.max(MAX, res);
			return;
		}
		// 현재 괄호를 포함하는 경우 (다음 연산자는 괄호를 포함하지 않음)
		dfs(idx + 1, calculate(res, operators[idx], operands[idx+1]));
		
		// 현재 괄호를 포함하지 않는 경우 (다음 연산자가 괄호를 포함하므로 다음 결과값을 미리 계산)
		if (idx + 1 < N/2) {
			int nextRes = calculate(res, operators[idx], calculate(operands[idx + 1], operators[idx + 1], operands[idx + 2]));
			dfs(idx + 2, nextRes);
		}
	}
	
	private static int calculate(int res, char operator, int nextOperand) {
		int ret = res;
		switch (operator) {
		case '+': ret += nextOperand;break;
		case '-': ret -= nextOperand;break;
		case '*': ret *= nextOperand;break;
		}
		return ret;
	}
}
