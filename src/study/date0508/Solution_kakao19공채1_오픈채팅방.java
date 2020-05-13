package study.date0508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_kakao19공채1_오픈채팅방 {

	public static void main(String[] args) {
		String[] record = {
				"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"
		};
		System.out.println(Arrays.toString(solution(record)));
//		System.out.println(solution(record));
	}

	static public String[] solution(String[] record) {
        String[] answer = null;
        
        // 어차피 결과에는 들어오고 나간 기록만 있다.
        // 결국 유저 아이디에 매칭되는 닉네임의 변경 히스토리를 바탕으로
        // 현재 닉네임을 알아내고 그것을 적용시켜 결과 배열을 구성한다.
        
        // userRecord 리스트에는 입장/퇴장 코드와 유저아이디로 구성된 key가 순서대로 들어있음
        List<String> userRecordList = new ArrayList<String>();
        // 해쉬맵으로 유저아이디와 닉네임쌍을 관리한다.
        Map<String, String> userMap = new HashMap<String, String>();
        
        StringTokenizer st = null;
        String userId = null;
        String nickname = null;
        // 주어진 record 배열을 하나씩 읽으면서 출입기록을 관리하고 닉네임을 관리한다.
        for (String rec : record) {
        	st = new StringTokenizer(rec, " ");
        	switch (st.nextToken()) { // 기록 코드 (Enter, Leave, Change)
			case "Enter":	// 입장 기록
				userId = st.nextToken();
				userRecordList.add("1" + userId); 	// 입장하는 경우 1을 앞에 붙여준다.
				nickname = st.nextToken();			
				userMap.put(userId, nickname);		// 입장하는 경우 항상 닉네임이 갱신 된다.
				break;
			case "Leave":	// 퇴장 기록
				userId = st.nextToken();
				userRecordList.add("0" + userId); // 입장하는 경우 0을 앞에 붙여준다.
				break;
			case "Change":	// 닉네임 변경 기록
				userId = st.nextToken();
				nickname = st.nextToken();			
				userMap.put(userId, nickname);		// 닉네임 갱신
				break;
        	}
        }
        
        // 결과 배열 구성
        answer = new String[userRecordList.size()];
        for (int i = 0; i < answer.length; i++) {
        	answer[i] = userMap.get(userRecordList.get(i).substring(1)) + (userRecordList.get(i).charAt(0) == '1'? "님이 들어왔습니다." : "님이 나갔습니다.");
        }
        return answer;
    }
}
