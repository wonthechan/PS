package study.date0316;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 참고: https://jksk0115.tistory.com/76#
public class Solution_D4_1868_파핑파핑지뢰찾기 {

	static int N;
	static char[][] map;
	static boolean[][] isZero;
	static List<Pos> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1868.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			int answer = 0;
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			isZero = new boolean[N][N];
			list.clear();
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			// 1. 배열을 돌면서 지뢰가 없는'.' 인 곳은 8방 탐색을 해서 주위에 지뢰가
			// 있는지 없는지 여부를 확인한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						if (!hasMineAround(i, j)) {
							isZero[i][j] = true;
							list.add(new Pos(i, j));
						}
					}
				}
			}
			
			// 2. isZero가 true인 지점 (연쇄반응 지점)을 차례로 방문한다.
			for (Pos zero : list) {
				if (map[zero.i][zero.j] == '.') {
					++answer;
					dfs(zero.i, zero.j);
				}
			}
			
			// 3. 이제 나머지 남은 곳들(연쇄반응x) 을 차례로 방문하여 클릭 수 증가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') ++answer;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static boolean hasMineAround(int i, int j) {
		for (int dir = 0; dir < 8; ++dir) {
			int ny = i + dy8[dir];
			int nx = j + dx8[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if (map[ny][nx] == '*') return true;
		}
		return false;
	}

	// 상방향부터 시계방향
	static int[] dy8 = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx8 = {0, 1, 1, 1, 0, -1, -1, -1};
	private static void dfs(int i, int j) {
		map[i][j] = 'v'; // 주위에 지뢰가 몇개인지는 상관 없음
		// 주위에 지뢰가 있는 경우는 어차피 연쇄 반응이 일어나지 않기 때문에
		// 재귀를 타지 않음.
		// 따라서 주위에 지뢰가 없는 경우만 생각.
		if (!isZero[i][j]) return;
		for (int dir = 0; dir < 8; ++dir) {
			int ny = i + dy8[dir];
			int nx = j + dx8[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if (map[ny][nx] == '.') dfs(ny, nx);
		}
	}
	
	static class Pos {
		int i, j;
		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
