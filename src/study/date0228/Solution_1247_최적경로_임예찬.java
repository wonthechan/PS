package study.date0228;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1247_최적경로_임예찬 {

	static int N;
	static Pos company, home;
	static Pos[] customers;
	static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			answer = 987654321;
			
			N = Integer.parseInt(br.readLine());
			customers = new Pos[N];
			
			st = new StringTokenizer(br.readLine());
			
			company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for (int i = 0; i < N; i++) {
				 customers[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			int[] order = new int[N];
			for (int i = 0; i < N; i++) order[i] = i;
			// 넥퍼로 순열 생성 (가능한 모든 고객 방문 순서 뽑기)
			do {
//				System.out.println(Arrays.toString(order));
				boolean flag = false;
				// 현재 주어진 순서로 총 거리 구하기
				int dist = getDistBetween(company, customers[order[0]]);
//				System.out.println(answer);
				for (int i = 0; i < N - 1; i++) {
					dist += getDistBetween(customers[order[i]], customers[order[i+1]]);
					if (dist >= answer) {
						flag = true;
						break;
					}
				}
				if (flag) continue;
				
				dist += getDistBetween(customers[order[N - 1]], home);
				answer = Math.min(answer, dist);
			} while(np(order));
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	private static int getDistBetween(Pos from, Pos to) {
		return Math.abs(from.i - to.i) + Math.abs(from.j - to.j);
	}
	private static boolean np(int[] arr) {
		int N = arr.length;
		//
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}

	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
