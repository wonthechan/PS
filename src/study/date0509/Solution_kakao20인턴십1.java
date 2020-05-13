package study.date0509;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class Solution_kakao20인턴십1 {

	public static void main(String[] args) {
		String expression = "50+6+3+2";
		System.out.println(solution(expression));
	}
	
	/**
	 * 연산자의 갯수만큼 순열을 구하기 때문에 가능한 모든 경우의 수는 N!
	 * 연산자는 총 3개이므로 최대 3!의 우선순위 경우가 존재함.
	 */
	static public long solution(String expression) {
        long answer = 0;
        
        // 등장한 모든 연산자를 파악하고 배열로 구성한다.
        boolean[] usedOp = new boolean[3]; // 등장한 연산자의 경우 true (0: -, 1: +, 2: *)
        char[] opArr = {'-', '+', '*'};
        
        // 연산자, 피연산자 리스트 초기화
        List<Integer> operands = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        // 파싱
        StringBuilder sb = new StringBuilder();
        for (char ch : expression.toCharArray()) {
        	switch (ch) {
			case '-': // 연산자 -
				usedOp[0] = true;
				operands.add(Integer.parseInt(sb.toString()));
				operators.add(ch);
				sb = new StringBuilder();
			break;
			case '+': // 연산자 +
				usedOp[1] = true;
				operands.add(Integer.parseInt(sb.toString()));
				operators.add(ch);
				sb = new StringBuilder();
			break;
			case '*': // 연산자 *
				usedOp[2] = true;
				operands.add(Integer.parseInt(sb.toString()));
				operators.add(ch);
				sb = new StringBuilder();
			break;
			default:	// 피연산자
				sb.append(ch);
				break;
			}
        }
        operands.add(Integer.parseInt(sb.toString())); // 마지막 피연산자 포함
        
        System.out.println(Arrays.toString(operands.toArray()));
        System.out.println(Arrays.toString(operators.toArray()));
        
        // 총 등장 연산자 리스트 구성
        List<Character> op = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
        	if (usedOp[i]) op.add(opArr[i]);
        }
        
        Deque<Integer> operandDeq = new ArrayDeque<>();
        Deque<Character> operatorDeq = new ArrayDeque<>();
        // 연산자가 한개인 경우 (그냥 처음부터 차례대로 계산)
        if (op.size() == 1) {
        	answer += operands.get(0);
        	switch (op.get(0)) {
			case '-':
				for (int i = 1; i < operands.size(); i++) {
	        		answer -= operands.get(i);
	        	}
			break;
			case '+':
				for (int i = 1; i < operands.size(); i++) {
					answer += operands.get(i);
				}
				break;
			case '*':
				for (int i = 1; i < operands.size(); i++) {
					answer *= operands.get(i);
				}
			break;
			}
        }
        else if (op.size() == 2) {
        	// 연산자가 두개인 경우
        	int[][] order = {
        			{0, 1},
        			{1, 0}
        	};
        	for (int[] ord : order) {
        		for (int turn : ord) {
        			
        		}
        	}
        } else if (op.size() == 3) {
        	// 연산자가 세개인 경우
        	int[][] order = {
        			{0, 1, 2},
        			{0, 2, 1},
        			{1, 0, 2},
        			{1, 2, 0},
        			{2, 0, 1},
        			{2, 1, 0},
        	};
        	for (int[] ord : order) {
        		operandDeq.clear();
        		operatorDeq.clear();
        		for (int turn : ord) {
        		}
        	}
        }
        
        return answer;
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
