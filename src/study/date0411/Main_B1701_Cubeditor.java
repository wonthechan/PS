package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B1701_Cubeditor {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1701.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String pattern = br.readLine();
		System.out.println(getPi(pattern));
	}

	/** 해당 패턴의 파이테이블을 만들자! */
	public static int getPi(String pattern) {
		int len = pattern.length();
		int max = 0;
		// 주어진 문자열에서 한 문자씩 줄여가면서 파이 구하기
		for (int s = 0; s < len; s++) {
			String target = pattern.substring(s, len);
			int[] pi = new int[target.length()];
			int j = 0; // 오락가락하는 j 초기화
			// i는 무조건 한칸씩 앞으로 (무조건 i는 1부터 시작!!!)
			for (int i = 1, end = target.length(); i < end; i++) {
				// 일치하지 않는 경우 j위치 조정
				while (j > 0 && target.charAt(j) != target.charAt(i)) {
					j = pi[j - 1];
				}
				
				// 일치하는 경우 파이테이블 갱신 및 j 증가
				if (target.charAt(j) == target.charAt(i)) {
					pi[i] = ++j;
				}
			}
			for (int i = 0, end = pi.length; i < end; i++) {
				max = Math.max(max, pi[i]);
			}
		}
		return max;
	}
}
