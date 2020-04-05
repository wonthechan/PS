package study.date0405;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_line20공채1 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_line20#1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(solution(br.readLine()));
	}

	public static int solution(String inputString) {
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		char openC;
		boolean isValid = true;		// 괄호 체크 유효 여부 (기본값은 true)
		// 괄호를 한개 씩 확인하면서 여는 괄호면 스택에 넣자
	L:	for (char ch : inputString.toCharArray()) {
			switch (ch) {
			case '(': case '{': case '[': case '<':
				stack.push(ch);
				break;
			case ')': case '}': case ']': case '>': // 닫는 괄호인 경우
				if (stack.empty()) {
					// 짝이 맞는 여는 괄호가 없는 경우
					isValid = false;
					break L;
				}
				openC = stack.pop(); // 남은게 있으면 하나 꺼내기
				if (openC == '(' && ch != ')' || openC == '{' && ch != '}' || openC == '[' && ch != ']' || openC == '<' && ch != '>') {
					// 괄호식이 잘못된 경우 (매칭이 안되는 경우) => 그냥 빠져나온다.
					isValid = false;
					break L;		// L레이블에 해당되는 반복문을 빠져나온다.
				}
				++answer;
				break;
			}
		}
		// 괄호 체크를 전부 한 경우와 체크 도중 괄호식이 잘못되어  break로 빠져나온 경우를 구분한다.
		// 스택이 비어 있지 않은 경우는 여는 괄호가 더 많았던 경우
		if (isValid && stack.isEmpty()) {
			return answer;
		}
		return -1;
    }
}
