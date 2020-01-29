package hw.date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_J2857_세로읽기_임예찬 {
	// 다섯 개의 단어를 담는 배열 (단어는 최소 1개, 최대 15개의 문자로 구성)
	static char[][] words;
	static final int lenWords = 5;
	static final int lenMaxWord = 15;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		words = new char[lenWords][lenMaxWord];
		String word = "";
		String answer = "";
		String temp;
		
		// 배열의 기본값을 공백으로 초기화 한다.
		for (int i = 0; i < lenWords; i++) {
			Arrays.fill(words[i], ' ');
		}
		
		// 5개의 단어를 라인 단위로 차례로 입력 받는다.
		for (int i = 0; i < lenWords; i++) {
			word = br.readLine();
			// 단어를 문자 단위로 쪼개서 배열에 저장한다.
			for (int j = 0; j < word.length(); j++) {
				words[i][j] = word.charAt(j);
			}
		}
		
		// 세로로 읽은 결과를 answer에 저장
		for (int i = 0; i < lenMaxWord; i++) {
			temp = "";
			for (int j = 0; j < lenWords; j++) {
				if (words[j][i] != ' ') {
					// 공백은 포함하지 않는다.
					temp += words[j][i];
				}
			}
			if (temp.equals("")) {
				// 더이상 단어가 없는 경우 루프문을 빠져나온다.
				break;
			} else {
				answer += temp;
			}
		}
		
		System.out.println(answer);
	}
}
