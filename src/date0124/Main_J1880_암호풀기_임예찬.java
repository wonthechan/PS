package date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1880_암호풀기_임예찬 {
	// 복호화 키를 담을 배열 (26개의 소문자)
	static char[] keyArr;
	// 암호화 된 문자열 (최대 80문자)
	static String rawStr;
	// 복호화 된 문자열
	static String answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		keyArr = new char[26];
		answer = "";
		char alpha;
		
		// 복호화 키를 한글자씩 26번 입력 받아 배열에 순서대로 저장
		for (int i = 0; i < 26; i++) {
			keyArr[i] = (char) br.read();
		}
		br.readLine();
		
		// 암호화된 문자 입력
		rawStr = br.readLine();
		
		// 복호화
		for (int i = 0; i < rawStr.length(); i++) {
			alpha = rawStr.charAt(i);
			if (alpha >= 'a' && alpha <= 'z') {
				// 소문자인 경우
				answer += keyArr[alpha - 'a'];
			} else if (alpha >= 'A' && alpha <= 'Z') {
				// 대문자인 경우
				answer += Character.toUpperCase(keyArr[alpha - 'A']);
			} else {
				// 공백 등 이외 문자인 경우 그대로 이어붙임
				answer += alpha;
			}
		}
		
		// 원문 출력
		System.out.println(answer);
	}
	
}
