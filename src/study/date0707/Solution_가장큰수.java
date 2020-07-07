package study.date0707;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution_가장큰수 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {0, 0, 0}));
	}

	static public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        List<String> list = new ArrayList<>();
        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
        	list.add(numbers[i] + "");
        	sum += numbers[i];
        }
        
        if (sum == 0) return "0";
        
        list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return - (Integer.parseInt(o1 + o2) - Integer.parseInt(o2 + o1)) ;
			}
		});
        
        for (int i = 0; i < numbers.length; i++) {
        	answer.append(list.get(i));
        }
        
        return answer.toString();
    }
}