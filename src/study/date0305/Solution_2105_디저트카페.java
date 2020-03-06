package study.date0305;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페 {

	static int N, answer, sy, sx;
	static int[][] map;
	static boolean[] visit = new boolean[101];	// 디저트 종류는 1이상 100이하의 정수
	//	좌하, 우하, 우상, 좌상 (왼쪽방향으로 한바퀴)
	static int[] dy4 = {1, 1, -1, -1};
	static int[] dx4 = {-1, 1, 1, -1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s2105.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer  = 0;
//			Arrays.fill(visit, false);
			
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][N+2];	// 경계를 0으로 감싸기
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 1; i < N-1; i++) {
				for (int j = 2; j < N; j++) {
					// 현재 위치에서 사각형을 만들 수 있는 경우에만 dfs 시작
					sy = i; sx = j;
					dfs(i + dy4[0], j + dx4[0], 1, 0);	// 좌하 방향으로 한칸 가고 dfs 탐색 시작
				}
			}
			answer = answer == 0 ? -1 : answer;
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static void dfs(int ny, int nx, int cnt, int dir) {
		visit[map[ny][nx]] = true;					// 들어오자마자 자신을 visit 처리
		for (int k = dir; k <= dir + 1 && k < 4; k++) {
			int nextY = ny+dy4[k];
			int nextX = nx+dx4[k];
			if (map[nextY][nextX] == 0) continue;	// 범위 체크
			if (visit[map[nextY][nextX]]) continue;	// visit 체크
			if (nextY == sy && nextX == sx) {		// 기저조건을 안에 넣어줌 (제자리로 돌아오면 끝) => 아예 재귀를 타지 않게 한다.
				answer = Math.max(answer, cnt + 1);
				visit[map[ny][nx]] = false;			// 끝내기 전에 unvisit 처리
				return;
			}
			dfs(nextY, nextX, cnt + 1, k);
		}
		visit[map[ny][nx]] = false;					// 나가면서 unvisit 처리
		return;
	}
	

}