package supplement.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B8958_OX퀴즈 {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			int total = 0;
			int score = 0;
			int len = str.length();
			for (int j = 0; j < len; j++) {
				switch (str.charAt(j)) {
				case 'O':
					score++;
					total += score;
					break;
				case 'X':
					score = 0;
					break;
				}
			}
			System.out.println(total);
		}
	}
}
