package study.date0324;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B15685_드래곤커브 {

	static int N, x, y, d, g, answer = 0;
	static List<Integer> list = new ArrayList<>();
	static int[][] map = new int[101][101];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b15685.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			list.clear();
			list.add(d);		// 0세대 (초기)
			if (g == 1) {
				list.add((d+1)%4); 	// 1세대 만들기
			} else if (g > 1) {
				list.add((d+1)%4); 	// 1세대 만들기
				findCurve(1, list);	// 2세대 이상인 경우 메소드 호출
			}
			markMap(y, x);
		}
		
		// 정사각형 찾기
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] + map[i][j+1] + map[i+1][j] + map[i+1][j+1] == 4) ++answer;
			}
		}
		
		System.out.println(answer);
	}

			 //	우 상 좌 하
	static int[] dy4 = {0, -1, 0, 1};
	static int[] dx4 = {1, 0, -1, 0};
	private static void markMap(int y, int x) {
		map[y][x] = 1; // 시작점 마킹
		int dir;
		int ny = y, nx = x;
		for (int i = 0, end = list.size(); i < end; i++) {
			dir = list.get(i);
			ny += dy4[dir];
			nx += dx4[dir];
			map[ny][nx] = 1;
		}
	}

	private static void findCurve(int gen, List<Integer> dirs) {
		if (gen == g) return;
		
		int mid = dirs.size() / 2;
		int end = dirs.size();
		for (int i = 0; i < mid; i++) {
			dirs.add((dirs.get(i) + 2) % 4);
		}
		for (int i = mid; i < end; i++) {
			dirs.add(dirs.get(i));
		}
		
		findCurve(gen + 1, dirs);
	}

}
