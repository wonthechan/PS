package study.date0310;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기_sol {

	static int N, W, H, answer;
	static int[][] map;
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			int brickCnt = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) ++brickCnt;	// 벽돌 갯수를 세어준다.
				}
			} // 입력 끝
			answer = brickCnt;
			// 구슬을 하나씩 떨어뜨려보자.. => 좌표가 달라져야 한다 => 중복 순열로 좌표 고르자.
			// 벽돌의 갯수도 전달(중간중간에 다깻는지확인하고 가지치기하기위함), map 전달
			dropMarble(N, brickCnt, map);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	private static void dropMarble(int r, int brickCnt, int[][] map) {
		if (r == 0) { // N개의 구슬을 전부 쏜 경우
			answer = Math.min(answer, brickCnt);	// 남아 있는 벽돌 갯수로 answer 갱신
			return;
		}
		
		for(int j = 0; j < W; j++) {
			// 1. map을 복제
			int[][] copiedMap = getCopiedMap(map);
			// 2. 해당 컬럼의 맨 꼭대기 벽돌 가져오기
			Brick topBrick = getTopBrick(j, copiedMap);
			// 2-1. null(해당 칼럼에 깰 벽돌이없는 경우 --> continue
			if (topBrick == null) {
				continue;
			}
			// 3. 구슬을 떨어트려서 벽돌을 깬다. --> 깨진 벽돌 개수??
			int broken = breakBricks(topBrick, copiedMap);
			// 3-1. 이미 다 벽돌이 깨졌다면?? 정답 = 0, 종료
			if (broken >= brickCnt) {
				answer = 0;
				return;
			}
			// 4. 화면 정리
			cleanMap(copiedMap);
			
			// 5. 다음 구슬 발사!!
			dropMarble(r - 1, brickCnt - broken, copiedMap);
		}
	}
	
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int breakBricks(Brick top, int[][] arr) {
		int broken = 0;
		// 얻어 맞은 벽돌 자신 삭제
		arr[top.i][top.j] = 0;
		++broken;
		
		// 주변 벽돌에 영향 주기
		for (int k = 1; k < top.pow; k++) {
			// 사방 탐색
			for (int dir = 0; dir < 4; dir++) {
				int ny = top.i + dy4[dir] * k;
				int nx = top.j + dx4[dir] * k;
				if (ny < 0 || nx < 0 || ny >= H || nx >= W) continue;
				if (arr[ny][nx] > 0) {
					broken += breakBricks(new Brick(ny, nx, arr[ny][nx]), arr);
				}
			}
		}
		return broken;
	}
	private static void cleanMap(int[][] arr) {
		for (int j = 0; j < W; j++) {
			for (int i = H - 1, ni = H - 1; i >= 0; i--) {
				if (arr[i][j] != 0) {
					int temp = arr[i][j];
					arr[i][j] = 0;
					arr[ni--][j] = temp;
					// swap 없이 아래와 같이 짜는 경우 i와 ni가 같은 인덱스일 때 자신을 0으로 만드는 문제 발생
//					arr[ni--][j] = arr[i][j];
//					arr[i][j] = 0;
				}
			}
		}
	}
	
	private static Brick getTopBrick(int j, int[][] arr) { // j열에 벽돌이 남아 있는지? 없으면 -1, 있으면 가장 높이 있는 벽돌의 높이 반환
		int i = 0;
		while (i < H && arr[i][j] == 0) ++i;
		return i == H? null : new Brick(i, j, arr[i][j]);
	}

	private static int[][] getCopiedMap(int[][] arr){
		int[][] temp = new int[H][W];
		for (int i = 0; i < H; i++) {
			temp[i] = arr[i].clone();
		}
		return temp;
	}
	
	static class Brick {
		int i, j, pow;
		public Brick (int i, int j, int pow) {
			this.i = i;
			this.j = j;
			this.pow = pow;
		}
	}
}
