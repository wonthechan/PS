package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_4615_재미있는오셀로게임 {
	
	static int N, M;
	static int[][] arr;
					// 	상 하 좌 우 우상 우하 좌하 좌상
	static int[] dy8 = {-1, 1, 0, 0, -1, 1, 1, -1};
	static int[] dx8 = {0, 0, -1, 1, 1, 1, -1, -1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s4615.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {

			int[] cntArr = new int[3];
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N+1][N+1];

			// 보드 초기화 (기본 세팅)
			arr[N/2][N/2] = 2;
			arr[N/2][N/2+1] = 1;
			arr[N/2+1][N/2] = 1;
			arr[N/2+1][N/2+1] = 2;
			
			// 명령 입력 받기
			int curY, curX, me, other;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				curX = Integer.parseInt(st.nextToken());
				curY = Integer.parseInt(st.nextToken());
				me = Integer.parseInt(st.nextToken());
				other = (me == 1)? 2 : 1;
				
				if (arr[curY][curX] != 0) {
					continue; // 이미 돌이 놓여져 있는 경우;
				}
				
				// 8방에 어떤 돌이 있는지 판단
				for (int dir = 0; dir < 8; dir++) {
					int ny = curY;
					int nx = curX;
					boolean meCheck = false;
					boolean otherCheck = false;
					while (true) {		// 같은 돌이 있는 지 확인
						ny += dy8[dir];
						nx += dx8[dir];
						if (ny < 1 || nx < 1 || ny > N || nx > N ) break; // 범위 이탈
						if (arr[ny][nx] == 0) {	// 중간에 0이 있으면 Stop
							break;
						} else if (arr[ny][nx] == me) {
							meCheck = true;
							break;
						} 
					}
					ny = curY;
					nx = curX;
					if (meCheck) {		// 같은 돌이 있는 경우 사이에 다른 돌이 있는지 확인
						while (true) {		// 같은 돌이 있는 지 확인
							ny += dy8[dir];
							nx += dx8[dir];
							
							if (arr[ny][nx] == other) {
								arr[ny][nx] = me;
								otherCheck = true;
							} else if (arr[ny][nx] == me) {
								break;
							} 
						}
					}
					
					if (otherCheck) {	// 사이에 다른 돌이 있었던 경우
						arr[curY][curX] = me;
					}
				}
			}
			
			for (int[] a : arr) {
				for (int b : a) {
					cntArr[b]++;
				}
			}
			
			sb.append(cntArr[1]).append(" ").append(cntArr[2]);
			System.out.println(sb.toString());
		}
	}
}
