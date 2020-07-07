package study.date0707;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution_여행경로2 {

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
	static public String[] solution(String[][] tickets) {
		answer = new String[tickets.length];
        // 인접 리스트 생성
        for (String[] ticket : tickets) {
        	if (!adj.containsKey(ticket[0])) {
        		adj.put(ticket[0], new ArrayList<String>());
        	}
        	adj.get(ticket[0]).add(ticket[1]);
        }
        // sort
        for (String key : adj.keySet()) {
        	adj.get(key).sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
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
	
	private static boolean dfs(int level, int r, String start, Stack<String> result) {
		if (level == r) {
			answer = result.toArray(answer);
			return true;	// 정답을 찾았다면 바로 return
		}
		
		if (!adj.containsKey(start)) return false;
		
		for (int i = 0; i < adj.get(start).size(); i++) {
			if (visited.get(start)[i]) continue;
			String dest = adj.get(start).get(i);
			visited.get(start)[i] = true;
			result.push(dest);
			if (dfs(level + 1, r, dest, result)) {
				return true;
			}
			visited.get(start)[i] = false;
			result.pop();
		}
		return false;
	}
}