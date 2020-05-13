package study.date0513;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D5_1798_범준이의제주도여행계획 {

	static final int MAX = 987654321;
	static final int MIN = -987654321;
	
	static int N, M;	// 지점 개수 N과 휴가 기간. M (3≤ N ≤35) / (1≤ M ≤5)
	static int[][] adj;	// 지점간의 거리
	
	static Point airport;								// 공항 포인트
	static List<Point> hotels = new ArrayList<Point>();	// 호텔의 포인트 리스트
	static List<Point> tours = new ArrayList<Point>();	// 관광지 포인트 리스트
	
	static int maxSatis;
	static List<Integer> maxStatisPath;	// 만족도가 최대인 경우의 경로
	
	// 탐색에 사용할 임시 경로
	static Stack<Integer> tempPath = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s1798.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			adj = new int[N+1][N+1];
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = i + 1; j <= N; j++) {
					adj[i][j] = adj[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			hotels.clear();
			tours.clear();
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				switch (st.nextToken().charAt(0)) {
				case 'A':	// 공항
					airport = new Point("A", i);
					break;
				case 'P':	// 관광지
					tours.add(new Point("P", i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
					break;
				case 'H':	// 호텔
					hotels.add(new Point("H", i));
					break;
				}
			} // 입력 끝
			
			/* 각 관광지에서 가장 가까운 호텔 정보 설정해주기. */
			for (Point tour : tours) {
				int min = MAX;
				for (Point hotel : hotels) {
					if (adj[tour.idx][hotel.idx] < min) {
						min = adj[tour.idx][hotel.idx];
						tour.nearH = new Point(hotel.type, hotel.idx);
					}
				}
			}
			
			/* 여행 준비 */
			maxSatis = MIN;
			tempPath.clear();
			
			/* solve */
			solve(0, airport, 0, 0, 0);
			
			/* 결과 출력 */
			sb.append("#").append(tc).append(" ");
			if (maxSatis == MIN) {
				sb.append(0);
			} else {
				sb.append(maxSatis).append(" ");
				for (int i : maxStatisPath) {
					sb.append(i).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void solve(int day, Point start, int satis, int time, long visit) {
		// 종료 조건
		if (day == M) {
			if (satis > maxSatis) {
				maxSatis = satis;
				maxStatisPath = new ArrayList<>(tempPath); // 최대 만족도에서의 경로 전달
			}
			return;
		}
		
		// 일반 탐색.
		boolean canGoNext = false;
		for (Point tour : tours) {
			// 갈 수 있는 지점들을 찾아서 계속 돌아다닌다.
			if ((visit & 1 << tour.idx) > 0) continue;	// 방문 체크
			int tempTime = time + adj[start.idx][tour.idx] + tour.playTime;
			if (day == M - 1) { // 마지막 날이라 공항으로 돌아가는 경우
				tempTime += adj[tour.idx][airport.idx];
			} else {			// 여행중이라 호텔로 돌아가는 경우
				tempTime += adj[tour.idx][tour.nearH.idx];
			}
			
			if (tempTime > 540) continue;		// 관광지로 못감
			
			canGoNext = true;
			tempPath.push(tour.idx);
			solve(day, tour, satis + tour.satis, time + adj[start.idx][tour.idx] + tour.playTime, visit | 1 << tour.idx);
			tempPath.pop();
		}
		
		// 어떤 관광지로도 못간다면 날짜에 따라서 공항 또는 호텔로 이동
		if (!canGoNext) {
			if (day == M - 1) { // 마지막 날이라 공항으로 돌아가는 경우
				tempPath.push(airport.idx);
				solve(day + 1, airport, satis, 0, visit);	// 다음날로 넘어가서 시간은 0으로 초기화
				tempPath.pop();
			} else {			// 여행중이라 호텔로 돌아가는 경우
				for (Point hotel : hotels) {	// 갈수 있는 범위의 호텔은 모두 방문해본다.
					if (time + adj[start.idx][hotel.idx] <= 540) {
						tempPath.push(hotel.idx);
						solve(day + 1, hotel, satis, 0, visit);
						tempPath.pop();
					}
				}
			}
		}
	}
	
	static class Point {
		String type;	// A, H, P
		int idx, playTime, satis;
		Point nearH;	// 근접 호텔 포인트
		
		public Point(String type, int idx) {
			this.type = type;
			this.idx = idx;
		}

		public Point(String type, int idx, int playTime, int satis) {
			this.type = type;
			this.idx = idx;
			this.playTime = playTime;
			this.satis = satis;
		}
	}
}
