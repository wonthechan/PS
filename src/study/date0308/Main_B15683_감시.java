package study.date0308;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B15683_감시 {

	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static Cam[] cams = new Cam[8];	// cctv는 최대 8개 설치 가능
	static int camLen= 0;			// cctv 갯수
					//	상 우 하 좌 (시계방향)
	static int[] dy4 = {-1, 0, 1, 0};
	static int[] dx4 = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b15683.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} //입력 끝
		
		// cctv 위치,정보 확인
		int ny, nx;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				switch (map[i][j]) {
				case 1:
					cams[camLen++] = new Cam(i, j, 1);
					break;
				case 2:
					cams[camLen++] = new Cam(i, j, 2);
					break;
				case 3:
					cams[camLen++] = new Cam(i, j, 3);
					break;
				case 4:
					cams[camLen++] = new Cam(i, j, 4);
					break;
				case 5: // 5번 cctv인 경우 방향이 항상 고정되므로 map에 바로 표시한다.
					for (int dir = 0; dir < 4; dir++) {
						ny = i;
						nx = j;
						while (true) {
							ny += dy4[dir];
							nx += dx4[dir];
							if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6) break;
							if (map[ny][nx] == 0) map[ny][nx] = 7;
						}
					}
					break;
				}
			}
		}
		
		// dfs
		dfs(map, 0);	// 첫번째 cctv부터 dfs 탐색 시작
		
		System.out.println(answer);
	}

	/* 새로운 맵 배열을 생성하여 마킹하고 그 배열을 리턴한다. */
	private static int[][] markArea(int[][] arr, Cam cam, int dir) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = arr[i][j];
			}
		}
		int ny, nx;
		switch (cam.k) {
		case 1:
			ny = cam.i;
			nx = cam.j;
			while(true) {
				ny += dy4[dir];
				nx += dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M || newMap[ny][nx] == 6) break;
				if (newMap[ny][nx] == 0) newMap[ny][nx] = 7;
			}
			break;
		case 2:
			for (int k = dir; k < 4; k += 2) {
				ny = cam.i;
				nx = cam.j;
				while(true) {
					ny += dy4[k];
					nx += dx4[k];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M || newMap[ny][nx] == 6) break;
					if (newMap[ny][nx] == 0) newMap[ny][nx] = 7;
				}
			}
			break;
		case 3:
			for (int k = dir, end = k + 2; k < end; k++) {
				ny = cam.i;
				nx = cam.j;
				while(true) {
					ny += dy4[k%4];
					nx += dx4[k%4];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M || newMap[ny][nx] == 6) break;
					if (newMap[ny][nx] == 0) newMap[ny][nx] = 7;
				}
			}
			break;
		case 4:
			for (int k = dir, end = k + 3; k < end; k++) {
				ny = cam.i;
				nx = cam.j;
				while(true) {
					ny += dy4[k%4];
					nx += dx4[k%4];
					if (ny < 0 || nx < 0 || ny >= N || nx >= M || newMap[ny][nx] == 6) break;
					if (newMap[ny][nx] == 0) newMap[ny][nx] = 7;
				}
			}
			break;
		}
		return newMap;
	}
	
	private static void dfs(int[][] arr, int step) {
		if (step == camLen) {
			// 사각지역 갯수 세기
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0) ++cnt;
				}
			}
			answer = Math.min(answer, cnt);
			return;
		}
		Cam cam = cams[step];
		switch (cam.k) {
		case 1:	// 1번 카메라인 경우
			for (int dir = 0; dir < 4; dir++) {
				dfs(markArea(arr, cam, dir), step + 1);
			}
			break;
		case 2:
			for (int dir = 0; dir < 2; dir++) {
				dfs(markArea(arr, cam, dir), step + 1);
			}
			break;
		case 3:
			for (int dir = 0; dir < 4; dir++) {
				dfs(markArea(arr, cam, dir), step + 1);
			}
			break;
		case 4:
			for (int dir = 0; dir < 4; dir++) {
				dfs(markArea(arr, cam, dir), step + 1);
			}
			break;
		}
	}

	static class Cam {
		int i, j, k;	// y, x 좌표와 카메라 종류 k
		public Cam (int i, int j, int k) {
			this.i = i;
			this.j = j;
			this.k = k;
		}
	}
}
