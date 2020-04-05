package study.date0405;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_line20공채5 {
	static Map<String, Boolean> filter = new HashMap<String, Boolean>();
	public static void main(String[] args) throws Exception {
		String[][] dataSource = {
				{"doc1", "t1", "t2", "t3"},
			    {"doc2", "t0", "t2", "t3"},
			    {"doc3", "t1", "t6", "t7"},
			    {"doc4", "t1", "t2", "t4"},
			    {"doc5", "t6", "t100", "t8"}
		};
		String[] tags = {
				"t1", "t2", "t3"
		};
		String[] answer = solution(dataSource, tags);
		
		System.out.println(Arrays.toString(answer));
	}

    public static String[] solution(String[][] dataSource, String[] tags) {
        String[] answer = {};
        
        // 각 문서별로 일치도(태그 일치 개수)를 계산하여 우선순위를 둔다.
 
        // 검색 태그를 읽고 태그 Map 설정
        filter.clear();
        for (String tag : tags) {
        	filter.put(tag, true);
        }
        
        // 일치도 검사 (문서이름과 일치도를 가지고 있는 클래스 객체 리스트)
        List<Score> scores = new ArrayList<Score>();
        
        int cnt = 0;
        String[] document;
        for (int i = 0; i < dataSource.length; i++) {
        	cnt = 0;
        	document = dataSource[i];
        	if (document.length > 1) { // 태그가 없는 문서인 경우 (리스트에 포함하지 않음)
        		for (int j = 1; j < document.length; j++) {
        			if (filter.get(document[j]) != null) ++cnt;
        		}
        	}
        	if (cnt > 0) {
        		scores.add(new Score(document[0], cnt));
        	}
        }
        
        // 일치 태그의 개수가 많은 순, 음소문자 순 으로 정렬
        Collections.sort(scores);
        int lenScores = scores.size();
        
        if (lenScores > 10) { // 정답 대상 문서의 개수가 10개를 넘는 경우
        	answer = new String[10];
        	int idx = 0;
        	for (int i = 0; i < 10; i++) {
        		answer[idx++] = scores.get(i).title;
        	}
        } else {
        	answer = new String[lenScores];
        	int idx = 0;
        	for (int i = 0; i < lenScores; i++) {
        		answer[idx++] = scores.get(i).title;
        	}
        }
        return answer;
    }
    
    static class Score implements Comparable<Score> {
    	String title;
    	int score;
		public Score(String title, int score) {
			super();
			this.title = title;
			this.score = score;
		}
		@Override
		public int compareTo(Score o) {
			return this.score == o.score? this.title.compareTo(o.title) : o.score - this.score;
		}
    }
}
