package study.date0303;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_7699_수지의수지맞는여행_BM {

	static int R, C;
	static char[][] map;
	static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s7699.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		String str;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			answer = -987654321;
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R+1][C+1];
			
			for (int i = 1; i <= R; i++) {
				str = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = str.charAt(j-1);
				}
			}
			dfs(1, 1, 1, 0 | (1 << (map[1][1]-'A')));
			
			sb.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	static private void dfs(int i, int j, int cnt, int visit) {
		answer = Math.max(answer, cnt);
		if (cnt == 26) return;
		for (int dir = 0; dir < 4; dir++) {
			int ny = i + dy4[dir];
			int nx = j + dx4[dir];
			if (ny < 1 || nx < 1 || ny > R || nx > C) continue;
			int visitIdx = map[ny][nx] - 'A';
			if ((visit & 1 << visitIdx) > 0) continue;
			dfs(ny, nx, cnt + 1, visit | 1 << visitIdx);
		}
	}

}
