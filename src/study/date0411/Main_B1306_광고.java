package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B1306_광고 {

	static int L;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b1305.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		L = Integer.parseInt(br.readLine());
		String source = br.readLine();
		
		int[] pi = getPi(source);
//		System.out.println(Arrays.toString(pi));
//		System.out.println(source.substring(0, L - pi[L - 1]));
		System.out.println(L - pi[L - 1]);// 개수를 출력!!!
	}
	
	private static int[] getPi(String source) {
		int[] pi = new int[source.length()];
		int j = 0;
		for (int i = 1; i < source.length(); i++) {
			while (j > 0 && source.charAt(i) != source.charAt(j)) {
				j = pi[j - 1]; //back!
			}
			if (source.charAt(i) == source.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
}
