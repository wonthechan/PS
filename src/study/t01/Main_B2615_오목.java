package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2615_오목 {

	static int[][] map;
	
	//	 1:상 2:우상 3:우 4:우하 5:하 6:좌하 7:좌 8:좌상
	static int[] dy8 = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx8 = {0,  0,  1, 1, 1, 0,-1,-1, -1};

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_2615.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new int[20][20];
		// 바둑판 상태 배열 입력
		for (int i = 1; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 20; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int whoWin = 0;	// 승자 바둑알
		int winI = 0;
		int winJ = 0;
		// 바둑판 배열 체크
	L1:	for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				// 1이나 2가 있으면 검사 시작
				int num = map[i][j];
				if (num == 1 || num == 2) {
					if(checkWin(i, j, num)) {	// 승리한 경우를 찾았다면 반복문 종료
						whoWin = num;
						winI = i;
						winJ = j;
						break L1;
					}
				}
			}
		}
		
		System.out.println(whoWin);
		if (whoWin != 0) System.out.println(winI + " " + winJ);
	}

	private static boolean checkWin(int i, int j, int color) {
		
		int num = color;
		boolean isWin = true;
		// 왼쪽 위에서부터 차례대로 보기 때문에 하(5), 우하(4), 우(3), 우상(2) 방향만 본다.
		for (int dir = 2; dir <= 5; dir++) {
			isWin = true;
			int curY = i;
			int curX = j;
			
			// 이전 방향에 알이 하나 있으면 자동으로 육목이 되므로 해당 방향으로 더이상 살펴볼 필요가 없음
			int preY = curY + dy8[dir] * -1;
			int preX = curX + dx8[dir] * -1;
			// 6목인 경우 fail
			if (preY <= 19 && preX <= 19 && preY >= 1 && preX >= 1 && map[preY][preX] == num) {
				isWin = false;
				continue;
			}
			
			// 해당 방향으로 바둑알이 연속해서 있는지 확인한다.
			for (int k = 0 ; k < 4; k++) {
				curY += dy8[dir];
				curX += dx8[dir];
				
				// 바둑판 범위를 벗어나는 경우 fail
				if (curY > 19 || curX > 19) {
					isWin = false;
					break;
				}
				
				// 연속되는 같은 바둑알이 없는 경우 실패
				if (map[curY][curX] != num) {
					isWin = false;
					break;
				}
			}
			
			if (isWin) {
				// 6목 체크
				curY += dy8[dir];
				curX += dx8[dir];
				// 6목인 경우 fail
				if (curY <= 19 && curX <= 19 && curY >= 1 && curX >= 1 && map[curY][curX] == num) {
					isWin = false;
				}
			}
			
			
			if (isWin) break;
		}
		
		return isWin;
	}
}
