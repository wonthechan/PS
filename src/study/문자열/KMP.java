package study.문자열;

public class KMP {
	// 모든 경우를 다 비교하지 않아도 부분 문자열을 찾을 수 있는 알고리즘.
	// 접두사와 접미사 개념을 활용해서, '반복되는 연산을 최대한 줄이도록'
	// 지금까지 검사한 문자열의 접두사와 접미사가 일치하는 최대길이만큼 겹침을 이용
	
	// 파이 테이블 : 각 길이별로 접두사 == 접미사의 최대길이가 저장된 배열
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0; // 오락가락하는 j는 미리 준비
		for (int i = 1, end = pattern.length(); i < end; i++) { // i는 무조건 한칸씩 계속 뒤로 (그리고 반드시 1부터 시작!!!!)
			// 맞는날이 올때까지 하나 전칸에서의 공통부분 위치로 이동
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			// 맞는 경우
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// i 길이 문자열의 공통길이는 j의 위치 + 1
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	static void KMP (String origin, String pattern) {
		int[] pi = getPi(pattern);
		int j = 0; // 오락가락하는 j는 미리 준비
		for (int i = 0, end = origin.length(); i < end; i++) {
			while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			// 맞는 경우
			if (origin.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) {
					System.out.println("ㅇㅋ" + (i - pattern.length() + 1));
					j = pi[j];
				}
				// 맞았지만 패턴이 끝나지 않았다면 j를 하나 증가
				else {
					++j;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String origin = "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddcddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
		String pattern = "cdc";
		KMP(origin, pattern);
	}
}
