package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B7569_토마토 {

	static int M, N, H;
	static int[][][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b7569.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		Queue<Pos> queue = new LinkedList<Pos>();
		int cntZero = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[h][i][j] = num;
					switch (num) {
					case 0:
						++cntZero;
						break;
					case 1:
						queue.offer(new Pos(h, i, j));
						break;
					}
				}
			}
		}

		int days = 0;
		while(true) {
			int curSize = queue.size();
			if (curSize == 0) { 
				--days;
				break;	// 더이상 익을 수 있는 토마토가 없는 경우
			}
			++days;
			while(curSize-- > 0) {
				Pos out = (Pos) queue.poll();
				for(int dir = 0; dir < 6; dir++) {	// 4방 탐색 (위 아래 까지)
					int ny = out.i + dy[dir];
					int nx = out.j + dx[dir];
					int nh = out.h + dh[dir];
					if (nh >= 0 && ny >= 0 && nx >= 0 && nh < H && ny < N && nx < M &&  
							map[nh][ny][nx] == 0) {
						--cntZero;
						map[nh][ny][nx] = 1;
						queue.offer(new Pos(nh, ny, nx));
					}
				}
			}
		}
		System.out.println(cntZero != 0? -1 : days);
	}
	
	static int[] dy = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, -1, 1};

	static class Pos {
		int h, i, j;
		public Pos(int h, int i, int j) {
			this.h = h;
			this.i = i;
			this.j = j;
		}
	}
}
