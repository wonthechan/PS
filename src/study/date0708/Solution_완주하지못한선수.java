package study.date0708;

import java.util.HashMap;
import java.util.Map;

public class Solution_완주하지못한선수 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] {"marina", "josipa", "nikola", "vinko", "filipa", "vinko"}, new String[] {"josipa", "filipa", "marina", "nikola"}));
	}
	
	static public String solution(String[] participant, String[] completion) {
		String answer = "";
		Map<String, Integer> map = new HashMap<String, Integer>();
		
        for (int i = 0; i < participant.length; i++) {
        	if (!map.containsKey(participant[i])) {
        		map.put(participant[i], 1);
        	} else {
        		map.put(participant[i], map.get(participant[i]) + 1);
        	}
        }
        
        for (int i = 0; i < completion.length; i++) {
        	map.put(completion[i], map.get(completion[i]) - 1);
        }
        
        for (String key : map.keySet()) {
        	if (map.get(key) > 0) {
        		answer = key;
        		break;
        	}
        }
        return answer;
    }
}
