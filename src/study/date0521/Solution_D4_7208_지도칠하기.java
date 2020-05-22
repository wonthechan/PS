package study.date0521;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_7208_지도칠하기 {

	static int N; // 나라 수, 3<=N<=8
	static int[] initColors;	// 초기 배정 색상
	static int[] selected;		// 중복 순열 결과 배열
	static int[][] adj;			// 인접 행렬
	static boolean[] visited;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s7208.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			initColors = new int[N];
			adj = new int[N][N];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				initColors[i] = Integer.parseInt(st.nextToken());
			}
			selected = initColors.clone();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝
			
			if (newCheckValid()) { // 초기 배정 색상으로 이미 구분가능하다면 0을 출력하고 다음 테케로 ㄱㄱ
				sb.append(0).append("\n");
				continue;
			}
			
			answer = 987654321;	// 최소 변경 횟수를 저장함.
			// 그렇지 않다면 순열(중복)을 구해서 완탐해야함.
			makeDupPerm(0);
			sb.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void makeDupPerm(int level) {
		if (level == N) {
			if (!newCheckValid()) return;
			// 유효한 색 배정이라면 최소 변경 횟수를 갱신
			answer = Math.min(answer, countChangedColors());
			return;
		}
		
		// 반드시 1부터 4로 마킹해야 함. (0부터 3으로 하면 틀림..)
		for (int i = 1; i <= 4; i++) { // 4개 컬러의 선택지가 있음
			selected[level] = i;
			makeDupPerm(level + 1);
		}
	}

	/** 원래 초기 색 배정에서 바뀐 나라의 수를 카운트 */
	private static int countChangedColors() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (initColors[i] != selected[i]) ++count;
		}
		return count;
	}

	/** 굳이 BFS를 써서 탐색할 필요는 없음. 인접 정점만 확인하면 됨 */
	private static boolean newCheckValid() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (adj[i][j] == 1 && selected[i] == selected[j]) return false;
			}
		}
		return true;
	}
	
//	private static boolean checkValid() {
//		Arrays.fill(visited, false);
//		for (int i = 0; i < N; i++) { // 인접 정점이 없는 정점이 있을 수도 있음
//			if (visited[i]) continue;
//			if (!checkValidBFS(i)) return false;
//		}
//		return true;
//	}
//	
//	/** BFS를 이용해서 나라간의 인접 색이 서로 다른지 확인 */
//	private static boolean checkValidBFS(int start) {
//		Queue<Integer> queue = new LinkedList<Integer>();
//		visited[start] = true;
//		queue.offer(start);
//		
//		while (!queue.isEmpty()) {
//			int out = queue.poll();
//			
//			// 인접 정점 탐색
//			for (int i = 0; i < N; i++) {
//				if (adj[out][i] == 0) continue;
//				if (visited[i]) continue;
//				// 인접 정점과 색이 같은지 확인
//				if (selected[out] == selected[i]) {
//					return false; // 더 이상 볼 필요 없음
//				}
//				visited[i] = true;
//				queue.offer(i);
//			}
//		}
//		return true;
//	}
}
