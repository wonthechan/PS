package supplement.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B2567_색종이2 {

	final static int maxSize = 101;
	static boolean[][] map;
	static int N;
					//	상 하 좌 우
	static int[] dy4= {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new boolean[maxSize][maxSize];
		N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		for (int k = 0; k < N; k++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			// 사각형 표시하기
			for (int i = startY; i < startY + 10; i++) {
				for (int j = startX; j < startX + 10; j++) {
					map[i][j] = true;
				}
			}
			
		}
		
		// 표시된 사각형 테두리 변의 길이 카운트 (4방 탐색)
		for (int i = 0; i < maxSize; i++) {
			for (int j = 0; j < maxSize; j++) {
				if (map[i][j] == true) {
					for (int dir = 0; dir < 4; dir++) {
						int ny = i + dy4[dir];
						int nx = j + dx4[dir];
						if (nx >= 0 && ny >= 0 && nx < maxSize && ny < maxSize && map[ny][nx] == false) cnt++;
					}
				}
			}
		}
		
		System.out.println(cnt);
		
	}

}
