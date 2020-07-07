package study.date0707;

public class Solution_HIndex {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {2}));
	}

	static int[] freq = new int[10001];
	static public int solution(int[] citations) {
        int answer = 0;
        
        for (int i = 0; i < 10001; i++) {
        	int cnt = 0;
        	for (int citation : citations) {
        		if (citation >= i) ++cnt;
        	}
        	
        	if (cnt < i) {
        		answer = i - 1;
        		break;
        	}
        }
        
        return answer;
    }
}