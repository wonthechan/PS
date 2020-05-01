package study.date0501;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class hw_algo0501_서울_7반_임예찬 {
	
	static int jinsu2, jinsu3;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/s4366.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
	L1:	for (int tc = 1; tc <= T; tc++) {
			String jinsu2Str = br.readLine();
			String jinsu3Str = br.readLine();
			
			jinsu2 = Integer.parseInt(jinsu2Str, 2);
			jinsu3 = Integer.parseInt(jinsu3Str, 3);
			
			List<Integer> list = new ArrayList<>();
			for (int k = 0; k < jinsu3Str.length(); k++) {
				list.add(Integer.parseInt(jinsu3Str.substring(0, k) + ((jinsu3Str.charAt(k) - '0' + 1) % 3) + jinsu3Str.substring(k+1), 3));
				list.add(Integer.parseInt(jinsu3Str.substring(0, k) + ((jinsu3Str.charAt(k) - '0' + 2) % 3) + jinsu3Str.substring(k+1), 3));
			}
			
			// 완탐 (XOR)
			for (int i = 0; i < jinsu2Str.length(); i++) {
				int temp = jinsu2 ^ (1 << i);
				for (int k : list) {
					if (k == temp) {
						sb.append("#").append(tc).append(" ").append(k).append("\n");
						continue L1;
					}
				}
			}
		}
		System.out.print(sb.toString());
	}
}
