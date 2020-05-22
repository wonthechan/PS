package study.date0522;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {

	static int K;					// ( 1 ≤ K ≤ 20 )
	static List<Integer>[] magnets = new ArrayList[4];
	static boolean[] visited = new boolean[4];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 4; i++) magnets[i] = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			for (int i = 0; i < 4; i++) magnets[i].clear();
			
			K = Integer.parseInt(br.readLine());
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i].add(Integer.parseInt(st.nextToken()));
				}
			} // 자석 초기 상태 입력 완료
			
			// 회전 시뮬 시작
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int target = Integer.parseInt(st.nextToken());
				int way = Integer.parseInt(st.nextToken());
				Arrays.fill(visited, false);
				rotateDFS(target - 1, way);
			}
			
			// 점수 계산하고 출력
			System.out.println("#" + tc + " " + calScore());
		}
	}
	
	private static int calScore() {
		int total = 0;
		for (int i = 0; i < 4; i++) {
			if (magnets[i].get(0) == 1) total += (int) Math.pow(2, i);
		}
		return total;
	}

	// target 번 자석을 way 방향으로 회전 */
	private static void rotateDFS(int curIdx, int way) {
		if (visited[curIdx]) return; // 이미 회전한 자석이라면 pass!
		visited[curIdx] = true;
		// 회전
		if (way == 1) { // 시계 방향
			// 오른쪽 자석도 회전
			if (curIdx < 3 &&magnets[curIdx].get(2) != magnets[curIdx + 1].get(6)) {
				rotateDFS(curIdx + 1, 0);
			}
			// 왼쪽 자석도 회전
			if (curIdx > 0 && magnets[curIdx - 1].get(2) != magnets[curIdx].get(6)) {
				rotateDFS(curIdx - 1, 0);
			}
			// 현재 자석 회전
			magnets[curIdx].add(0, magnets[curIdx].remove(7)); // 마지막원소를 맨 앞에 끼워 넣는다.
		} else { // 시계 방향
			// 오른쪽 자석도 회전
			if (curIdx < 3 && magnets[curIdx].get(2) != magnets[curIdx + 1].get(6)) {
				rotateDFS(curIdx + 1, 1);
			}
			// 왼쪽 자석도 회전
			if (curIdx > 0 && magnets[curIdx - 1].get(2) != magnets[curIdx].get(6)) {
				rotateDFS(curIdx - 1, 1);
			}
			// 현재 자석 회전
			magnets[curIdx].add(magnets[curIdx].remove(0)); 	// 맨 앞의 원소를 뒤에 이어 붙인다.
		}
	}
}
