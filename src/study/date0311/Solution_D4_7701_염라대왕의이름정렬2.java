package study.date0311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution_D4_7701_염라대왕의이름정렬2 {

	static int N;	// 이름 개수 (1 <= N <= 20000)
	static PriorityQueue<Str> pq = new PriorityQueue<>();
	static Str out;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s7701.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append("\n");
			
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				pq.offer(new Str(br.readLine()));
			}
			
			Str temp = pq.poll();
			sb.append(temp).append("\n");
			while(!pq.isEmpty()) {
				out = pq.poll();
				if (!temp.equals(out)) {
					temp = out;
					sb.append(temp).append("\n");
				}
			}
		}
		System.out.print(sb.toString());
	}
	
	static class Str implements Comparable<Str>{
		char[] chars;
		int n = 0;
		public Str (String str) {
			chars = new char[50];
			for (char c : str.toCharArray()) this.chars[n++] = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			Str another = (Str) obj;
			int n = this.n;
			if (n == another.n) {
				int i = 0;
				while(n-- > 0) {
					if (this.chars[i] != another.chars[i]) return false;
					++i;
				}
				return true;
			}
			return false;
		}

		@Override
		public int compareTo(Str another) {
			int diff = this.n - another.n;
			if (diff == 0) {
				int n = this.n;
				int i = 0;
				while(n-- > 0) {
					if (this.chars[i] > another.chars[i]) {
						return 1;
					} else if (this.chars[i] < another.chars[i]) {
						return -1;
					}
					++i;
				}
				return 0;
			}
			return diff;	// 문자열의 길이가 짧은 순으로 정렬
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < this.n; i++) sb.append(this.chars[i]);
			return sb.toString();
		}
	}
}
