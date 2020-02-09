package study.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B2567_색종이2 {

	static char[][] map;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new char[100][100];
		N = Integer.parseInt(br.readLine());
		
		for (char[] c: map) {
			Arrays.fill(c, '0');
		}
		
		for (int k = 0; k < N; k++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			// 모서리 체크
			for (int i = startX; i <= startX + 10; i++) {
				
//				map[startY][i] = (map[startY][i] == '0')? '1' : '*';	// 위 모서리
//				map[startY+10][i] = (map[startY+10][i] == '0')? '1' : '*';// 아래 모서리
				map[startY][i] = (map[startY][i] == '0')? '1' : map[startY][i];	// 위 모서리
				map[startY+10][i] = (map[startY+10][i] == '0')? '1' : map[startY+10][i];// 아래 모서리
				
			}
			for (int i = startY + 1; i < startY + 10; i++) {
//				map[i][startX] = (map[i][startX] == '0')? '1' : '*';		// 왼쪽 모서리
//				map[i][startX+10] = (map[i][startX+10] == '0')? '1' : '*';	// 오른쪽 모서리
				map[i][startX] = (map[i][startX] == '0')? '1' : map[i][startX];		// 왼쪽 모서리
				map[i][startX+10] = (map[i][startX+10] == '0')? '1' : map[i][startX+10];	// 오른쪽 모서리
				
			}
			// 사각형 안 채우기
			for (int i = startY + 1; i < startY + 10; i++) {
				for (int j = startX + 1; j < startX + 10; j++) {
					map[i][j] = '*';
				}
			}
		}
		
		for (char[] c: map) {
			for (char ch: c) {
				System.out.print(ch + " ");
			}
			System.out.println();
		}
		int cnt = 0;
		for (char[] c: map) {
			for (char ch: c) {
				if (ch == '1') cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
