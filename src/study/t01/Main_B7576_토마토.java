package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B7576_토마토 {

	static int M, N;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b7576.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		Queue<Pos> queue = new LinkedList<Pos>();
		int cntZero = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				switch (num) {
				case 0:
					++cntZero;					// 0을 발견하는 경우 카운트 증가
					break;
				case 1:
					queue.offer(new Pos(i, j));	// 1을 발견하는 경우 (익은 토마토) 위치정보를 큐에 삽입
					break;
				}
			}
		}

		int days = 1;							// 모든 토마토가 익기까지 걸리는 날
		while(true) {
			int curSize = queue.size();			// 현재 익은 토마토의 개수
			if (curSize == 0) {
				--days;
				break;							// 더이상 익을 수 있는 토마토가 없는 경우 반복문 종료
			}
			
			if (cntZero == 0) {
				break;
			}
			++days;
			while(curSize-- > 0) {				// 현재 익은 토마토의 개수 만큼 반복
				Pos out = (Pos) queue.poll();
				for(int dir = 0; dir < 4; dir++) {	// 4방 탐색
					int ny = out.i + dy4[dir];
					int nx = out.j + dx4[dir];
					if (ny >= 0 && nx >= 0 && ny < N && nx < M && 
							map[ny][nx] == 0) {
						--cntZero;				// 주변에 안익은 토마토가 있는 경우 1로 바꾸고 카운트 감소
						map[ny][nx] = 1;
						queue.offer(new Pos(ny, nx));
					}
				}
			}
		}
		
		System.out.println(cntZero != 0? -1 : days);
	}
	
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};

	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
