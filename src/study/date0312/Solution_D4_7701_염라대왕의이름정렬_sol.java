package study.date0312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Solution_D4_7701_염라대왕의이름정렬_sol {

	static int N;	// 이름 개수 (1 <= N <= 20000)
	static TreeSet<String> set;
	static String out;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s7701.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append("\n");
			set = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					int diff = o1.length() - o2.length();
					if (diff == 0) return o1.compareTo(o2);
					return diff;	// 문자열의 길이가 짧은 순으로 정렬
				}
			});
			
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				set.add(br.readLine());
			}
			
			Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				sb.append(itr.next()).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}
