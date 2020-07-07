package study.date0706;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution_여행경로 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[][] {
				{"ICN", "SFO"}, 
				{"SFO", "ICN"}, 
				{"ICN", "SFO"}, 
				{"SFO", "QRE"}, 
			})));
	}

	static Map<String, List<String>> adj = new HashMap<String, List<String>>();
	static Map<String, boolean[]> visited = new HashMap<String, boolean[]>();
	static String[] answer;
	static String[] temp;
	static public String[] solution(String[][] tickets) {
		temp = new String[tickets.length];
        // 인접 리스트 생성
        for (String[] ticket : tickets) {
        	if (!adj.containsKey(ticket[0])) {
        		adj.put(ticket[0], new ArrayList<String>());
        	}
        	adj.get(ticket[0]).add(ticket[1]);
        }
        // visited 배열 생성
        for (String key : adj.keySet()) {
        	visited.put(key, new boolean[adj.get(key).size()]);
        }
        
        // DFS
        Stack<String> result = new Stack<String>();
        result.push("ICN");
        dfs(0, tickets.length, "ICN", result);
        return answer;
    }
	
	private static void dfs(int level, int r, String start, Stack<String> result) {
		if (level == r) {
			if (answer == null) {
				answer = new String[result.size()];
				answer = result.toArray(answer);
			} else {
				answer = getMinStrArr(answer, result.toArray(temp));
			}
			return;
		}
		
		if (!adj.containsKey(start)) return;
		
		for (int i = 0; i < adj.get(start).size(); i++) {
			if (visited.get(start)[i]) continue;
			String dest = adj.get(start).get(i);
			visited.get(start)[i] = true;
			result.push(dest);
			dfs(level + 1, r, dest, result);
			visited.get(start)[i] = false;
			result.pop();
		}
	}
	
	private static String[] getMinStrArr(String[] strArr1, String[] strArr2) {
		for (int i = 0; i < strArr1.length; i++) {
			int k = strArr1[i].compareTo(strArr2[i]);
			if (k > 0) {
				return strArr2;
			} else if (k < 0) {
				return strArr1;
			}
		}
		return strArr1;
	}
}