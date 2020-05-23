package study.date0523;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B17822_원판돌리기 {

	static int N, M, T;	// 원판의 개수 N, 원판에 쓰인 숫자 개수 M, 원판 회전 회수 T
	static List<Point>[] circles;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17822.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		circles = new ArrayList[N];
		for (int i = 0; i < N; i++) circles[i] = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				circles[i].add(new Point(Integer.parseInt(st.nextToken()), false, false));
			}
		} // 원판 초기 상태 입력 끝
		
		// 회전 명령 시작
 		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());	// 회전 대상 (x의 배수인 원판)
			int d = Integer.parseInt(st.nextToken());	// 회전 방향
			int k = Integer.parseInt(st.nextToken());	// 회전 횟수
			rotate(x, d, k); // 회전
			
			if (isEmpty()) continue; // 원판이 비어 있다면 인접한 수를 찾지 않는다.
			
			if (!checkAdj()) { // 인접한 수중에 같은게 없다면 보정
				update();
			}
		}
		
		// 최종 결과 출력
		System.out.println(getResult());
	}
	
	private static boolean isEmpty() {
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				if (!circles[k].get(j).isDead) return false;
			}
		}
		return true;
	}
	/** 최종적으로 남은 수의 합을 구한다 */
	private static int getResult() {
		int ret = 0;
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				if (!circles[k].get(j).isDead) {	// 지워진 수는 제외
					ret += circles[k].get(j).num;
				}
			}
		}
		return ret;
	}

	/** 값 보정 */
	private static void update() {
		// 전체 원판에 적힌 수의 평균을 구하기
		int total = 0;
		int leftCnt = 0;
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				if (!circles[k].get(j).isDead) {	// 지워진 수는 제외
					total += circles[k].get(j).num;
					++leftCnt;
				}
			}
		}
		float avg = (float) total / leftCnt;
		// 보정 (평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더함)
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				if (!circles[k].get(j).isDead) {
					if (circles[k].get(j).num > avg) { // 지워진 수는 제외
						--circles[k].get(j).num;
					} else if (circles[k].get(j).num < avg){ // 평균과 같은 수는 그대로 둔다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						++circles[k].get(j).num;
					}
				}
			}
		}
	}

	private static boolean checkAdj() {
		boolean flag = false;
		// 원판내의 인접 숫자 비교
		for (int k = 0; k < N; k++) { // 모든 원판에 대해서
			for (int j = 0; j < M; j++) {
				if (circles[k].get(j).isDead || circles[k].get((j + 1) % M).isDead) continue;
				if (circles[k].get(j).num == circles[k].get((j + 1) % M).num) {
					circles[k].get(j).checked = true;
					circles[k].get((j + 1) % M).checked = true;
					flag = true;
				}
			}
		}
		
		// 원판 간의 인접 숫자 비교
		for (int j = 0; j < M; j++) { // 모든 원판 숫자에 대해서
			for (int k = 0; k < N - 1; k++) {
				if (circles[k].get(j).isDead || circles[k + 1].get(j).isDead) continue;
				if (circles[k].get(j).num == circles[k + 1].get(j).num) {
					circles[k].get(j).checked = true;
					circles[k + 1].get(j).checked = true;
					flag = true;
				}
			}
		}
		
		// 인접 체크된 숫자들 모두 바꿔주기
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				if (!circles[k].get(j).isDead && circles[k].get(j).checked) {
					circles[k].get(j).isDead = true; // 0으로 바꿔주기
				}
			}
		}
		return flag;
	}

	/** x의 배수인 원판에 대해서 적용 */
	private static void rotate(int x, int d, int k) {
		for (int i = 0; i < N; i++){
			if ((i+1) % x != 0) continue;
			List<Point> temp = new ArrayList<>();
			int startIdx;
			if (d == 0) { // 시계 방향
				startIdx = M - (k % M);
			} else {
				startIdx = k % M;
			}
			for (int j = 0; j < M; j++) {
				Point point = circles[i].get((startIdx + j) % M);
				temp.add(new Point(point.num, point.checked, point.isDead));
			}
			circles[i] = temp;
		}
	}

	static class Point {
		int num;
		boolean checked;
		boolean isDead;
		public Point(int num, boolean checked, boolean isDead) {
			this.num = num;
			this.checked = checked;
			this.isDead = isDead;
		}
	}
}
