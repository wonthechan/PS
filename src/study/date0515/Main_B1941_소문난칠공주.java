package study.date0515;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B1941_소문난칠공주 {

	static final int N = 5;
	
	static Student[][] map = new Student[N][N]; // 맵 크기는 5*5 로 고정
	static boolean[][] visited = new boolean[N][N]; // 맵 크기는 5*5 로 고정
	static int cnt = 0;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1941.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = new Student(line[j], false);
			}
		}
		
		makeComboDFS(0, 0, 0, 0);
		
		System.out.println(answer);
	}
	
	// DFS를 이용해서 25명 중 7명을 뽑는 조합을 만들어내며
	// 중간에 칠공주가 되기 위한 조건들을 만족하는지 확인한다. 
	private static void makeComboDFS(int idx, int level, int cntS, int cntY) {
		if (cntY == 4) return;	// 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
		
		if (level == 7) {	// 모든 조건을 만족하여 일곱명을 모은 경우
			// check 배열을 이용하여 연결성 확인
			for (boolean[] b : visited) Arrays.fill(b, false);
			cnt = 0;
			dfs((idx-1) / N, (idx-1) % N);	// 가장 마지막에 선택된 인덱스를 기준으로 DFS를 타본다.
			if (cnt == 7) ++answer;
			return;
		}
		
		for (int i = idx, end = N * N; i < end; i++) {
			int ny = i / N;
			int nx = i % N;
			map[ny][nx].selected = true;
			makeComboDFS(i + 1, level + 1, map[ny][nx].team == 'S' ? cntS + 1 : cntS, map[ny][nx].team == 'Y' ? cntY + 1 : cntY);
			map[ny][nx].selected = false;	// backtracking
		}
	}
					// 상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	static private void dfs(int y, int x) {
		++cnt;
		visited[y][x] = true;
		for (int dir = 0; dir < 4; dir++) {
			int ny = y + dy4[dir];
			int nx = x + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if (visited[ny][nx]) continue;
			if (!map[ny][nx].selected) continue;
			dfs(ny, nx);
		}
	}
	
	static class Student {
		char team;			// S 또는 Y
		boolean selected;
		public Student(char team, boolean selected) {
			this.team = team;
			this.selected = selected;
		}
	}
}
