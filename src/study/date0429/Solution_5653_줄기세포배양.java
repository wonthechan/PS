package study.date0429;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5653_줄기세포배양 {

	static int N, M, K;	// 줄기 세포가 분포된 영역의 넓이는 세로 크기 N, 가로 크기 M, 배양 시간은 K시간 (1≤N≤50, 1≤M≤50, (1≤K≤300)
	static int[][] map;	// 무한한 크기의 맵
	static List<ArrayList<Cell>> list; // 줄기 세포 생명력 (1<=X<=10)별 저장 - 인덱스는 0~9 사용
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s5653.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		list = new ArrayList<>();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 초기화
			list.clear();
			for (int x = 0; x <= 9; x++) {	// 줄기 세포 생명력 1<=X<=10, 리스트 작업시 0~9로 변경
				list.add(new ArrayList<>());// 생명력이 큰 줄기세포부터 번식이 이루어질수 있도록 하기 위함
			}
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N+K][N+K];	// 세로 + 배양시간, 가로 + 배양시간 (가능한 최대 맵 크기 설정)
			for (int i = (K/2); i < N + (K/2); i++) {	// 비열의 중간을 계산 (0,0) -> (K/2, K/2)
				st = new StringTokenizer(br.readLine());
				for (int j = (K/2); j < M + (K/2); j++) {
					map[i][j] = Integer.parseInt(st.nextToken());	// 줄기세포생명력
					if (map[i][j] != 0) {
						int idx = map[i][j] - 1;
						list.get(idx).add(new Cell(i, j, map[i][j], map[i][j], K, 0));
					}
				}
			}
			
			// 처리
			bfs();
			
			// 출력
			int cnt = 0;
			for (int i = 0; i < N + K; i++) {
				for (int j = 0; j < M + K; j++) {
					if (map[i][j] != 0 && map[i][j] != -1) ++cnt;	// (비활성화+활성화) 줄기세포 수 카운트
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	static void bfs() {
		Queue<Cell> queue = new LinkedList<>();
		for (int x = 9; x >= 0; x--) {
			for (int s = 0; s < list.get(x).size(); s++) {
				queue.offer(list.get(x).get(s));
			}
		}
		
		while (!queue.isEmpty()) {
			Cell out = queue.poll();
			if (out.life == 0 && out.flag == 1) {
				map[out.i][out.j] = -1;
				continue;
			}
			if (out.time == 0) continue;
			if (out.life != 0) {
				queue.offer(new Cell(out.i, out.j, out.x, out.life - 1, out.time - 1, out.flag));
				continue;
			}
			// life가 0인 경우 (번식 시작)
			queue.offer(new Cell(out.i, out.j, out.x, out.life, out.time, 1));
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N + K || nx >= M + K) continue;
				if (map[ny][nx] == 0) {
					map[ny][nx] = out.x;
					queue.offer(new Cell(ny, nx, out.x, out.x, out.time - 1, 0));
				}
			}
		}
	}
	
	static class Cell {
		int i, j, x;	// 좌표값과 생명력 (초기입력값 보관용)
		int life;	// 활성화까지 시간 -> 살아있는 시간 (시간이 지나면서 감소됨)
		int time;	// 배양시간(시간이 지나면서 감소됨)
		int flag;	// 활성화상태(0:비활성화,1:활성화)
		public Cell(int i, int j, int x, int life, int time, int flag) {
			super();
			this.i = i;
			this.j = j;
			this.x = x;
			this.life = life;
			this.time = time;
			this.flag = flag;
		}
		@Override
		public String toString() {
			return "Cell [i=" + i + ", j=" + j + ", x=" + x + ", life=" + life + ", time=" + time + ", flag=" + flag
					+ "]";
		}
	}
}
