package study.date0313;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_kakao20공채2_괄호변환 {
	
	static Stack<Character> stack = new Stack<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_kakao20#2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(solution(line));
		}
	}

	// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
	private static String solution(String p) {
        if (p.isEmpty() || isCorrect(p)) return p;
		// 일단 두개로 쪼개자
		// 쪼개려면 균형잡힌 괄호 문자열을 찾아야함
		int cnt = p.charAt(0) == '(' ? 1 : -1;
		int i, end;
		for (i = 1, end = p.length(); cnt != 0 && i < end; i++) {
			if (p.charAt(i) == '(') {
				++cnt;
			} else {
				--cnt;
			}
		}
		String u = p.substring(0, i);
		String v = "";
		if (i < p.length()) v = p.substring(i);
		// 3. 문자열 u가 "올바른 괄호 문자열"이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
		//   3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
		if (isCorrect(u)) return u + solution(v);
		// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
		/* 
		 *  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
  			4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
  			4-3. ')'를 다시 붙입니다. 
  			4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
  			4-5. 생성된 문자열을 반환합니다.
		 */
		String temp = "(";
		temp += solution(v);
		temp += ")";
		end = u.length() - 1;
		for (int k = 1; k < end; k++) temp += u.charAt(k) == '(' ? ')' : '(';
		return temp;
	}
	
	// 올바른 괄호형 확인
	public static boolean isCorrect(String str) {
		stack.clear();
		// 괄호를 한개 씩 확인하면서 여는 괄호면 스택에 넣자
		for (char ch : str.toCharArray()) {
			switch (ch) {
			case '(':
				stack.push(ch);
				break;
			default: // 닫는 괄호인 경우
				if (stack.empty()) {
					// 짝이 맞는 여는 괄호가 없는 경우
					return false;
				}
				stack.pop();	// 남은게 있으면 하나 꺼내기
				break;
			}
		}
		if (!stack.empty()) return false; // 여는 괄호가 남아 있으면 실패
		return true;
	}
	
}
