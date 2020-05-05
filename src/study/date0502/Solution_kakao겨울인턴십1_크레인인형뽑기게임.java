package study.date0502;

import java.util.Stack;
// 20 mins
public class Solution_kakao겨울인턴십1_크레인인형뽑기게임 {

	public static void main(String[] args) {
		int[][] board = {
				{0,0,0,0,0},
				{0,0,1,0,3},
				{0,2,5,0,1},
				{4,2,4,4,2},
				{3,5,1,3,1}
		};
		int[] moves = {1,5,3,5,1,2,1,4};
		System.out.println(solution(board, moves));
	}

    static public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int N = board.length;
         // 바구니는 스택으로 관리하자.
        Stack<Integer> stack = new Stack<>();
        
        // move 시작 (pos 열 위치에 있는 인형 뽑기, 인덱스는 1부터 시작)
   L1:  for (int move : moves) {
	   		int pos = move - 1;
        	for (int i = 0; i < N; i++) {
        		// 가장 위에 있는 인형부터 뽑자
        		if (board[i][pos] > 0) {
        			// 인형이 터지는지 확인
        			if (!stack.isEmpty()) {
        				if (stack.peek() == board[i][pos]) {
        					// 이미 같은게 있으면 터진다.
        					stack.pop();
        					answer += 2;
        				} else {
        					stack.add(board[i][pos]);	// 뽑아서 바구니에 넣기
        				}
        			} else {	// 바구니가 비어있으면 상관 없음
        				stack.add(board[i][pos]);	// 뽑아서 바구니에 넣기
        			}
        			board[i][pos] = 0;			// 뽑았으니 빈칸으로 만들기
        			continue L1;				// 다음 턴으로!
        		}
        	}
        }
        return answer;
    }
}
