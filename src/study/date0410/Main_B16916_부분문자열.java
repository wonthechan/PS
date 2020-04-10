package study.date0410;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B16916_부분문자열 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b16916.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String origin = br.readLine();
		String pattern = br.readLine();
		
		System.out.println(isSubstringKMP(origin, pattern));
	}

	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0; // 오락가락하는 j 초기화
		for (int i = 1, end = pattern.length(); i < end; i++) { // i는 1부터 시작
			// 불일치
			while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
				// 맞는 날이 올때까지 BACK!!
				j = pi[j - 1];
			}
			if (pattern.charAt(j) == pattern.charAt(i)) {
				pi[i] = ++j; // 파이 배열의 i 자리에 ++j 저장
			}
		}
		return pi;
	}
	
	static int isSubstringKMP(String origin, String pattern) {
		int[] pi = getPi(pattern);
		int j = 0; // 오락가락하는 j 초기화
		for (int i = 0, end = origin.length(); i < end; i++) {
			while (j > 0 && pattern.charAt(j) != origin.charAt(i)) {
				// 맞는 날이 올때까지 BACK!!
				j = pi[j - 1];
			}
			if (pattern.charAt(j) == origin.charAt(i)) {
				// 일치하는 경우 진행
				if (j == pattern.length() - 1) {
					// 패턴이 전부 일치한 경우 바로 true 리턴
					return 1;
				} else {
					// 일치는 했으나 비교해야할 패턴 문자가 남은 경우
					++j;
				}
			}
		}
		return 0;
	}
}
