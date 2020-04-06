package study.date0406;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B17144_미세먼지안녕 {

	static int N, M, T;
	static int[][] map;		
	static int upY, downY;	// 공기 청정기의 y 좌표 (x좌표는 0으로 동일)
	static List<Pos> things = new ArrayList<Pos>();	// 미세먼지가 있는 칸의 좌표들을 담는 리스트
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		for (int i = 0; i < N; i++) {
			if (map[i][0] == -1) {
				upY = i;
				downY = i + 1;
				break;
			}
		} // 공청 위치 파악
		
		// T초 동안 반복 (미세먼지 확산 -> 공기청정기 작동)
		while (T-- > 0) {
			// 1. 미세먼지 확산
			startSpread();
			// 2. 공기청정기 작동
			startOperate();
		}
		
		// 남아 있는 미세먼지 양 구하기
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					answer += map[i][j];
				}
			}
		}
		System.out.println(answer);
	}
	
	/** 공기 청정기가 가동되면서 바람 불기 시작 */
	private static void startOperate() {
		// 현재 공청의 위치를 기준으로 한칸씩 시계 혹은 반시계 방향으로 밀어준다.
		// (가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.)
		// upper
		for (int i = upY - 2; i >= 0; i--) {
			map[i+1][0] = map[i][0];
		}
		for (int j = 1; j < M; j++) {
			map[0][j-1] = map[0][j];
		}
		for (int i = 1; i <= upY; i++) {
			map[i-1][M-1] = map[i][M-1];
		}
		for (int j = M - 2; j > 0; j--) {
			map[upY][j+1] = map[upY][j];
		}
		map[upY][1] = 0;
		// lower
		for (int i = downY + 2; i < N; i++) {
			map[i-1][0] = map[i][0];
		}
		for (int j = 1; j <= M - 1; j++) {
			map[N-1][j-1] = map[N-1][j];
		}
		for (int i = N - 2; i >= downY; i--) {
			map[i+1][M-1] = map[i][M-1];
		}
		for (int j = M - 2; j > 0; j--) {
			map[downY][j+1] = map[downY][j];
		}
		map[downY][1] = 0;
	}
				//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	/** 미세먼지 확산 시작 메소드 */
	private static void startSpread() {
		// 먼저 미세먼지가 있는 칸들을 조사해서 위치를 리스트에 담기
		things.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					things.add(new Pos(i, j, map[i][j]));
				}
			}
		}
		// 리스트를 순회하면서 4방으로 확산 시작
		int cnt = 0;
		int spreadAmount = 0;
		for (Pos thing : things) {
			spreadAmount = thing.amount / 5; // 4방에 확산될 미세먼지의 양
			cnt = 0;
			for (int dir = 0; dir < 4; dir++) {
				int ny = thing.y + dy4[dir];
				int nx = thing.x + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue; // 범위 체크
				if (map[ny][nx] > -1) { // 공청이 있는 칸이면 Skip
					map[ny][nx] += spreadAmount; // 확산
					++cnt;	// 확산된 방향의 수 카운트
				}
			}
			map[thing.y][thing.x] -= spreadAmount * cnt; // 현재 위치에 남은 미세먼지의 양 
		}
	}

	static class Pos {
		int y, x, amount;

		public Pos(int y, int x, int amount) {
			this.y = y;
			this.x = x;
			this.amount = amount;
		}
	}
}
