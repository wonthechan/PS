package study.date0313;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * 기본적인 문자열 사용 문제 : 문자열의 길이가 길지 않으므로 완탐 가능
 */
public class Solution_kakao20공채_문자열압축 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_kakao20#1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			System.out.println(solution(br.readLine()));
		}
	}

	public static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		// 문자열 s의 길이는 1 <= len <= 1000
		// 압축단위는 중간에 바꿀 수 없고 한가지만 가능 (정해진길이만 가능)
		// 모든 압축단위를 탐색? 1..2.. ~ len/2 까지
		// 모든 압축단위로 자른 것을 저장
		int len = s.length();
		
		// 문자열의 길이가 1인 경우를 고려
        if (len == 1) return 1;
        
		ArrayList<String>[] win = new ArrayList[len/2+1];
		for (int i = 0, end = len/2+1; i < end; i++) win[i] = new ArrayList<>(); // 초기화
		
		for (int wLen = 1; wLen < len/2+1; wLen++) {
			// 윈도우 사이즈 1씩 증가해서 len/2 까지 시도
			for (int si = 0; si < len; si += wLen) {
				if (si + wLen > len) win[wLen].add(s.substring(si));
				else win[wLen].add(s.substring(si, si+wLen));
			}
		}
		
		// count
		String common = "";
		String temp;
		Iterator<String> itr;
		int cnt = 0;
		int comCnt = 0;
		for (int wLen = 1; wLen < len/2+1; wLen++) {
			cnt = 0;
			comCnt = 0;
			itr = win[wLen].iterator();
			while (itr.hasNext()) {
				temp = itr.next();
				if (!common.equals(temp)) {
					if (comCnt > 1) {
						// 숫자 덧붙히기
						cnt += (comCnt + "").length();
					}
					common = temp;
					cnt += common.length(); 
					comCnt = 1;
				} else {
					++comCnt;
				}
			}
			if (comCnt > 1) cnt += (comCnt + "").length();
			answer = Math.min(answer, cnt);
		}
		
		return answer;
	}
	
}
