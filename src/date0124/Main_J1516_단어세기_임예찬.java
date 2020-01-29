package date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_J1516_단어세기_임예찬 {
	
	// 단어 발생 빈도 수를 담을 HashMap 선언
	static Map<String, Integer> wordFreqMap = null;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		wordFreqMap = new HashMap<>();
		String word = "";
		
		while (true) {
			// Map 초기화
			wordFreqMap.clear();
			
			st = new StringTokenizer(br.readLine());
		
			word = st.nextToken();
			if (word.equals("END")) break; // 입력 종료 조건
			
			// 가장 처음 입력 받은 단어를 Map에 넣는다.
			wordFreqMap.put(word, 1);
			
			// 나머지 단어를 모두 입력 받는다.
			while (st.hasMoreElements()) {
				word = st.nextToken();
				if (wordFreqMap.containsKey(word)) {
					// Map에 해당 word가 있는 경우 빈도수 값을 1 증가시킨다.
					Integer freq = wordFreqMap.get(word);
					wordFreqMap.put(word, ++freq);
				} else {
					// Map에 등록 되지 않은 단어인 경우 새로 추가하고 값을 1로 고정한다.
					wordFreqMap.put(word, 1);
				}
			}
			
			// 결과를 출력한다.
			printResult();
			
		}
	}
	private static void printResult() {
		// 단어를 오름차순으로 정렬하여 빈도수를 출력한다. (문장 단위)
		Object[] keyArr = wordFreqMap.keySet().toArray();
		Arrays.sort(keyArr);
		
		for (Object key : keyArr) {
			System.out.printf("%s : %d\n", key, wordFreqMap.get(key));
		}
	}
}
