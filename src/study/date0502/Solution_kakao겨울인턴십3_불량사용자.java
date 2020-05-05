package study.date0502;

import java.util.HashSet;
import java.util.Set;

// 1.5h
public class Solution_kakao겨울인턴십3_불량사용자 {

	static int answer = 0;
	static String[] user;
	static String[] banned;
	static Set<Integer> set;
	public static void main(String[] args) {
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		System.out.println(solution(user_id, banned_id));
	}

    static public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        // 해당 패턴에 여러개의 문자열이 매칭이 되는 경우를 고려..
        // 범위가 작으니까 재귀..?
        user = user_id;
        banned = banned_id;
        set = new HashSet<>();
        dfs(0, 0);
        answer = set.size();
        return answer;
    }
    
    static public void dfs(int idx, int visit) {
    	if (idx == banned.length) {
    		set.add(visit);
    		return;
    	}
    	
    	for (int i = 0; i < user.length; i++) {
    		if ((visit & (1 << i)) == 0 && isMatched(user[i], banned[idx])) {
    			dfs(idx + 1, visit | (1 << i));
    		}
    	}
    }
    
    static public boolean isMatched(String str, String pattern) {
    	if (pattern.length() != str.length()) return false;
    	for (int i = 0; i < pattern.length(); i++) {
    		if (pattern.charAt(i) != '*' && pattern.charAt(i) != str.charAt(i)) return false;
    	}
    	return true;
    }
}
