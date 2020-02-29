package study.date0229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 크루스칼 알고리즘을 이용한 풀이
 * 참고: https://victorydntmd.tistory.com/101
 */
public class Main_B17472_다리만들기2_크루스칼 {
	
	static final int MAX = 987654321;
	static int N, M, cntIsland;
	static char[][] map;
	static boolean[][] visited;
	static int[] parents;
	static Pos[] startPoints;
	static int[][] adj;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17472.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
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
		
		adj = new int[cntIsland][cntIsland]; // 인접행렬 생성
		for (int[] a : adj ) Arrays.fill(a, MAX);
		
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
		
		// 인접 행렬 만들기
		for (int i = 0; i < cntIsland; i++) {
			dfs_adj(startPoints[i].i, startPoints[i].j, i);
		}
		
		
//		for (char[] c : map) System.out.println(Arrays.toString(c).replace("987654321", "0"));
//		for (int[] c : adj) System.out.println(Arrays.toString(c).replace("987654321", "0"));
		
		// 인접 행렬에서 간선을 뽑아 우선순위 큐에 넣어 정렬상태로 유지하기 (크루스칼을 사용하기 위함)
		PriorityQueue<Edge> pqEdges = new PriorityQueue<>();
		for (int i = 0; i < cntIsland; i++) {
			for (int j = i; j < cntIsland; j++) {
				if (adj[i][j] < 987654321) pqEdges.offer(new Edge (i, j, adj[i][j]));
			}
		}

		makeSet();
		
//		List<Edge> MST = new ArrayList<>();
		int cntEdges = 0;
		// 크루스칼 알고리즘 이용 (사이클이 생기기 전까지 가중치가 적은 간선부터 union해나간다)
		while(!pqEdges.isEmpty()) {
			Edge out = pqEdges.poll();
			if (union(out.from, out.to)) {
				++cntEdges;
				answer += out.dist;
//				MST.add(new Edge(out.from, out.to, out.dist));
			}
		}
//		for (Edge edge : MST) System.out.println(edge);
		// 모두 연결된 상태 : 간선의 갯수 = 전체 정점의 갯수 - 1
		System.out.println(cntEdges == (cntIsland - 1)? answer : -1);
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
	
	private static void dfs_adj(int i, int j, int from) {
		visited[i][j] = true;
		int ny, nx;
		// 사방으로 다리 놓을 수 있는 위치 인지 확인
		for (int dir = 0; dir < 4; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == '0') {
				// 다리를 놓아보자
				int[] to = makeBridge(ny, nx, dir); // to[0] : 섬 tag 인덱스, to[1] : 다리 길이
				if (to[1] > 1) {
					// 행렬 값을 가장 최소 다리 길이로 저장
					adj[from][to[0]] = Math.min(adj[from][to[0]], to[1]);
				}
			}
		}
		// 4방 탐색
		for (int dir = 0; dir < 4; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == 'A' + from && !visited[ny][nx]) {
				dfs_adj(ny, nx, from);
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
	
	static class Edge implements Comparable<Edge>{
		int from, to, dist;
		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge other) {
			return this.dist - other.dist; // 다리 길이에 대해 오름차순으로 정렬
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[from: ").append(this.from).append(", to: ").append(this.to)
				.append("], dist = ").append(this.dist);
			return sb.toString();
		}
	}
	
	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "(" + i + ", " + j + ")";
		}
	}
}
