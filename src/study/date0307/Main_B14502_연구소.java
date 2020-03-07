package study.date0307;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B14502_연구소 {
	
	static int N, M, cntZero=0, answer=0;
	static int[][] map;
	static boolean[][] visit;
	static Pos[] spaces = new Pos[64];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		List<Pos> vList = new ArrayList<>();
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				switch (map[i][j]) {
				case 0:
					spaces[cntZero++] = new Pos(i, j);	// 빈칸의 위치를 배열로 저장해둔다.
					break;
				case 2:
					vList.add(new Pos(i, j));	// 바이러스의 위치를 리스트로 저장해둔다.
					break;
				}
			}
		}
		
		// 빈칸의 갯수를 세고 배열에 넣는다. (조합 만들 준비=>넥퍼활용)
		int[] order = new int[cntZero];
		order[cntZero-1] = order[cntZero-2] = order[cntZero-3] = 1;	// n C 3
		do {
			for (boolean[] a : visit) Arrays.fill(a, false);	// visit 배열 초기화
			for(int i = 0; i < cntZero; i++) {	// 새로 벽을 세운 위치를 visit 처리하여 벽이 세워진 효과를 낸다. (map을 수정하지 말자)				
				if (order[i] == 1) visit[spaces[i].i][spaces[i].j] = true;
			}
			// updated => 바이러스를 퍼트려 보자
			answer = Math.max(answer, getLeftSpace_bfs(vList));
		} while(np(order));
		
		System.out.println(answer);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static int getLeftSpace_bfs(List<Pos> vList) {
		int cntLeftZero = cntZero - 3;	// 벽을 3개 세웠기때문에 3을 줄여준다.
		// 초기 바이러스의 위치를 큐에 모두 집어 넣자.
		Queue<Pos> queue = new LinkedList<>();
		Iterator<Pos> itr = vList.iterator();
		while(itr.hasNext()) {
			Pos pos = itr.next();
			queue.add(new Pos(pos.i, pos.j));
		}
		// BFS 탐색 시작
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (!visit[ny][nx] && map[ny][nx] == 0) {
					--cntLeftZero;	// 바이러스가 퍼졌으니까 빈공간의 갯수 1 감소
					visit[ny][nx] = true;
					queue.offer(new Pos(ny, nx));
				}
			}
		}
		return cntLeftZero;
	}

	private static boolean np(int[] arr) {
		int N = cntZero;
		//
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}

	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
