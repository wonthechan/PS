package study.date0405;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_line20공채4 {
	public static void main(String[] args) throws Exception {
		String[][] snapshots = {
				{"ACCOUNT1", "100"}, 
				  {"ACCOUNT2", "150"}
		};
		String[][] transactions = {
				{"1", "SAVE", "ACCOUNT2", "100"},
				  {"2", "WITHDRAW", "ACCOUNT1", "50"}, 
				  {"1", "SAVE", "ACCOUNT2", "100"}, 
				  {"4", "SAVE", "ACCOUNT3", "500"}, 
				  {"3", "WITHDRAW", "ACCOUNT2", "30"}
		};
		String[][] answer = solution(snapshots, transactions);
		for (String[] arr : answer) System.out.println(Arrays.toString(arr));
	}

    public static String[][] solution(String[][] snapshots, String[][] transactions) {
        String[][] answer = {};
        
        
        // 트랜젝션에는 있는데 스냅샷에는 없는 계좌가 있을 수 있음 (중간에 추가된 계좌)
        
        // 따라서 스냅샷을 기준으로 DB를 구성하고
        // (계좌 DB를 해쉬맵으로 관리)
        Map<String, Integer> accounts = new HashMap<String, Integer>();
        for (String[] snapshot : snapshots) {
        	accounts.put(snapshot[0], Integer.parseInt(snapshot[1]));
        }
        // 트랜젝션을 하나씩 읽으면서 DB에 있는 계좌 정보를 업데이트하거나 신규 계좌는 새로 추가해준다.
        // 일단 트랜젝션에는 중복되는 ID값이 있을 수 있음 (따라서 제거하거나 한번만 읽어야 함) => HashSet으로 관리?
        Set<String> transactionIDs = new HashSet<String>();
        for (String[] tran : transactions) {
        	// 중복 검사
        	if (transactionIDs.contains(tran[0])) continue;
        	transactionIDs.add(tran[0]);
        	// 내용 해석
        	String account = tran[2];
        	String price = tran[3];
        	switch (tran[1]) {
			case "SAVE":
				if (accounts.containsKey(account)) {
					accounts.put(account, accounts.get(account) + Integer.parseInt(price));
				} else { // 이후 추가된 계좌인 경우
					accounts.put(account, Integer.parseInt(price));
				}
				break;
			case "WITHDRAW":
				// 잔액이 음수가 되는 트랜잭션은 없으므로 출금 대상 계좌는 항상 스냅샷에 존재
				accounts.put(account, accounts.get(account) - Integer.parseInt(price));
				break;
        	}
        }
        
        // 정답 출력 준비
        int lenAccount = accounts.size();	// 현재 파악된 계좌의 수
        answer = new String[lenAccount][];
        int idx = 0;
        for (String key : accounts.keySet()) {
        	answer[idx++] = new String[] {key, accounts.get(key) + ""};
        }
        // 정답 데이터 정렬 (계좌이름을 기준으로 오름차순)
        Arrays.sort(answer, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[0].compareTo(o2[0]);
			}
		});
        
        
        return answer;
    }
}
