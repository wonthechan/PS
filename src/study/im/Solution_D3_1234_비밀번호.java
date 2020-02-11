package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1234_비밀번호 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		for (int tc = 1; tc <= 10; tc++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			sb = new StringBuilder(st.nextToken());
			
			boolean flag = true;
			while(flag) {
				int len = sb.length();
				flag = false;
				for (int i = 0; i < len-1; i++) {
					if (sb.charAt(i) == sb.charAt(i+1)) {
						sb.setCharAt(i, '*');
						sb.setCharAt(i+1, '*');
						flag = true;
					}
				}
				sb = new StringBuilder(sb.toString().replace("*", ""));
			}
			
			System.out.println("#" + tc + " " + sb.toString());
		}
	}

}
