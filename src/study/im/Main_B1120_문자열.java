package study.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1120_문자열 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		String s2 = st.nextToken();
		int len1 = s1.length();
		int len2 = s2.length();
		
		int diff = len2 - len1;
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < diff + 1; i++) {
			int temp = computeDiff(s1, s2.substring(i, i + len1));
			if (temp < min) min = temp;
		}
		System.out.println(min);
	}
	
	private static int computeDiff(String s1, String s2) {
		// s1과 s2의 길이는 같다
		int ret = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) ret++;
		}
		return ret;
	}
}
