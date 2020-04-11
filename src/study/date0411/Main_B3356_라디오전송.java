package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B3356_라디오전송 {

	static int L;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b3356.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		L = Integer.parseInt(br.readLine());
		String source = br.readLine();
		int[] pi = getPi(source);
//		System.out.println(Arrays.toString(pi));
		System.out.println(L - pi[L - 1]);
	}

	/** pi table of KMP */
	private static int[] getPi(String source) {
		int[] pi = new int[L];
		for (int i = 1, j = 0; i < L; i++) {
			while (j > 0 && source.charAt(j) != source.charAt(i)) {
				j = pi[j - 1]; 	// back!!
			}
			if (source.charAt(j) == source.charAt(i)) {
				pi[i] = ++j;	// update!!
			}
		}
		return pi;
	}

}
