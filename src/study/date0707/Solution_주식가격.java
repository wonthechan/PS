package study.date0707;

public class Solution_주식가격 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {1, 2, 3, 2, 3}));
	}

	static public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
        	if (prices[i] == 1) {
        		answer[i] = prices.length - 1 - i;
        	} else {
        		answer[i] = prices.length - 1 - i - findSameElement(prices[i], prices);
        	}
        	
        }
        return answer;
    }
	
	static public int findSameElement(int element, int[] prices) {
		return 0;
	}
}