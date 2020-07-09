package study.date0710;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B16916_부분문자열 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b16916.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String pattern = br.readLine();
		
		System.out.println(KMP(str, pattern));
	}

	private static int KMP(String str, String pattern) {
		int[] pi = getPi(pattern);
		
		int j = 0;
		for (int i = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if (str.charAt(i) == pattern.charAt(j)) {
				if (j == pattern.length() - 1) {
					return 1;
				}
				++j;
			}
		}
		return 0;
	}

	private static int[] getPi(String pattern) {
		char[] chars = pattern.toCharArray();
		int[] pi = new int[chars.length];
		
		int j = 0;
		for (int i = 1; i < chars.length; i++) {
			while (j > 0 && chars[i] != chars[j]) {
				j = pi[j-1];
			}
			if (chars[i] == chars[j]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

}
