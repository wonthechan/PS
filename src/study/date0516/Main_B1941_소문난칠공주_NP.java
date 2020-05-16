package study.date0516;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/** DFS를 쓰지 않고 넥퍼를 통해 아예 조합을 다 만들어 놓고 조건체크 해보는 방식 */
public class Main_B1941_소문난칠공주_NP {

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
		
		// 25명 중 7명을 뽑는 조합
		int[] arr = new int[N*N];
		for (int i = 0; i < 7; i++) {
			arr[N*N-1-i] = 1;
		}
		do {
			// selected 정보 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j].selected = false;
				}
			}
			// 만들어진 조합에 대해서 나온 인덱스의 selected 설정
			int cntS = 0;
			int selectedIdx = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == 1) {
					selectedIdx = i;
					int y = selectedIdx / N;
					int x = selectedIdx % N;
					map[y][x].selected = true;
					if (map[y][x].team == 'S') ++cntS;
				}
			}
			
			for (boolean[] b : visited) Arrays.fill(b, false);
			cnt = 0;
			dfs(selectedIdx / N, selectedIdx % N);	// 가장 마지막에 선택된 인덱스를 기준으로 DFS를 타본다.
			// 조건 확인 (연결성과 비율)
			if (cnt == 7 && cntS >= 4) { // 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
				++answer;
			}
		} while (np(arr));
		
		System.out.println(answer);
	}
	
	// 넥퍼
	static private boolean np(int[] arr) {
		int N = arr.length;
		// 꼭대기 i 찾기
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		// i-1 와 swap 할 j 찾기
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		// swap
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		// re-order
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
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
