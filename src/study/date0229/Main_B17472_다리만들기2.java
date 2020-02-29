package study.date0229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B17472_다리만들기2 {
	
	static int N, M, cntIsland;
	static char[][] map;
	static int[] parents;
	static Pos[] startPoints;
	static int answer = 987654321;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17472.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		// bfs로 탐색하면서 섬 구분하기 (이름 붙이기)
		char tag = 'A';
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '1') {
					bfs(i, j, tag);
					++tag;
				}
			}
		}
		cntIsland = tag - 'A';	// 섬의 갯수
		
		startPoints = new Pos[cntIsland];
		tag = 'A';
		// 시작지점 저장해두기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == tag) {
					startPoints[tag - 'A'] = new Pos(i, j);
					++tag;
				}
			}
		}
//		System.out.println(Arrays.toString(startPoints));
		
		makeSet();
		
//		// 다리를 놓아 보면서 대표자가 다른 경우 union하는 과정
//		for (int i = 0; i < cntIsland; i++) {
//			dfs(startPoints[i].i, startPoints[i].j, i);
//		}
		
		dfs2(startPoints[0].i, startPoints[0].j, 0, 0);
		
		for (char[] c : map) System.out.println(Arrays.toString(c));
		System.out.println(answer);
	}
	
	private static boolean allConnected() {
		for (int i = 0; i < cntIsland - 1; i++) {
			if (findSet(i) != findSet(i+1)) return false;
		}
		return true;
	}
	private static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if (rootA < rootB) {
			parents[rootB] = rootA;
			return true;
		} else if (rootA > rootB) {
			parents[rootA] = rootB;
			return true;
		}
		return false;
	}
	
	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static void makeSet() {
		parents = new int[cntIsland];
		Arrays.fill(parents , -1);
	}
					//	상 우 하 좌
	static int[] dy4 = {-1, 0, 1, 0};
	static int[] dx4 = {0, 1, 0, -1};
	
	
	private static void dfs2(int i, int j, int from, int dist) {
		if (dist >= answer) return;
		// 모두 연결되어 있는 경우 종료
		if (allConnected()) {
			System.out.println("fuck");
			System.out.println(dist);
			answer = Math.min(answer, dist);
			Arrays.fill(parents, -1);
			return;
		}
		
		int ny, nx;
		// 사방으로 다리 놓을 수 있는 위치 인지 확인
		for (int dir = 0; dir < 4; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == '0') {
				// 다리를 놓아보자
				int[] res = makeBridge(ny, nx, dir); // res[0] : 섬 tag 인덱스, res[1] : 다리 길이
				if (res[1] > 1) {
					// union (from, to)
					if (union(from, res[0])) {
						int to = res[0];
						System.out.println((char) ('A'+from) + " => " + (char) ('A'+res[0]));
						System.out.println("* " + dist);
						dfs2(startPoints[to].i, startPoints[to].j, to, dist + res[1]);
					}
				}
			}
		}
		// 오른쪽, 아래 방향으로만 탐색
		for (int dir = 1; dir <= 2; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == 'A' + from) {
				dfs2(ny, nx, from, dist);
			}
		}
	}
	
	
	private static int[] makeBridge(int i, int j, int dir) {
		int dist = 1;
		int to = 0;
		int ny = i;
		int nx = j;
		boolean flag = false;
		while (true) {
			ny += dy4[dir];
			nx += dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) break;
			if (map[ny][nx] == '0') ++dist;
			else {
				flag = true;
				to = map[ny][nx] - 'A';
				break;	// 다른 섬을 만난 경우
			}
		}
		
		if (flag) return new int[] {to, dist};
		else return new int[] {-1, 1};
	}
	
	private static void dfs(int i, int j, int from) {
//		System.out.println("from : " + from);
		int ny, nx;
		// 사방으로 다리 놓을 수 있는 위치 인지 확인
		for (int dir = 0; dir < 4; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
//			System.out.println(map[ny][nx]);
			if (map[ny][nx] == '0') {
//				System.out.println("in");
				// 다리를 놓아보자
				int[] res = makeBridge(ny, nx, dir); // res[0] : 섬 tag 인덱스, res[1] : 다리 길이
				if (res[1] > 1) {
					// union (from, to)
//					System.out.println("to : " + res[0]);
					if (union(from, res[0])) {
						System.out.println((char) ('A'+from) + " => " + (char) ('A'+res[0]));
						answer += res[1];
						
					}
				}
			}
		}
		// 오른쪽, 아래 방향으로만 탐색
		for (int dir = 1; dir <= 2; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == 'A' + from) {
				dfs(ny, nx, from);
			}
		}
	}
	
	static private void bfs(int initI, int initJ, char tag) {
		Queue<Pos> queue = new LinkedList<>();
		map[initI][initJ] = tag;
		queue.add(new Pos(initI, initJ));
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (map[ny][nx] == '1') {
					map[ny][nx] = tag;
					queue.offer(new Pos(ny, nx));
				}
			}
		}
		
	}
	
	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
		public String toString() {
			return "(" + i + ", " + j + ")";
		}
	}
}
