package study.date0502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 50 mins
public class Solution_kakao겨울인턴십2_튜플 {

	public static void main(String[] args) {
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
//		System.out.println(solution(s));
		System.out.println(Arrays.toString(solution(s)));
	}

    static public int[] solution(String s) {
    	// 일단 튜플의 원소 개수부터 파악 (최대 원소 개수 = 500개)
    	// 그냥 파싱해서 리스트에 담아두자.
    	List<List> tuples = new ArrayList<>();
    	char[] str = s.substring(1, s.length() - 1).toCharArray(); // 가장 바깥 괄호는 제거
    	List<Integer> tuple = null;
    	StringBuilder sb = new StringBuilder();
    	for (char ch : str) {
    		switch (ch) {
			case '{':
				tuple = new ArrayList<>();
				break;
			case '}':
				tuple.add(Integer.parseInt(sb.toString()));
				tuples.add(tuple);
				sb = new StringBuilder();
				break;
			case ',': 
				if (sb.length() > 0) {
					tuple.add(Integer.parseInt(sb.toString()));
					sb = new StringBuilder();
				}
				break;
			default:
				sb.append(ch);
				break;
			}
    	}
    	// 튜플의 원소 개수 N
    	int N = tuples.size();

    	// 집합의 원소개수에 따라 정렬 (오름차순)
    	Collections.sort(tuples, new Comparator<List>() {
			@Override
			public int compare(List o1, List o2) {
				return o1.size() - o2.size();
			}
		});
    	
    	List<Integer> list = new ArrayList<>();
    	// 원래 튜플 유추
    	for (List t : tuples) {
    		t.removeAll(list);
    		list.addAll(t);
    	}
    	
    	int[] answer = new int[N];
    	int idx = 0;
    	for (int a : list) answer[idx++] = a;
        return answer;
    }
}
