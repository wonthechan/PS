package study.date0229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.event.ListSelectionEvent;

/* 
 * 1) 먼저 BFS 탐색을 통해 섬들의 개수를 알아내고 알파벳으로 마킹했음 (A, B, C ...)
 * 2) 각 섬들의 시작점을 구하여 배열로 관리함.
 * 3) 각 섬들의 시작점부터 DFS 탐색을 통해 다리를 만들어 인접할 수 있는 섬들을 찾아내고
 * 이 때의 인접 정보와 다리의 길이를 인접 행렬로 관리함.
 * 4) 인접 행렬에서 단방향 인접 정보만 뽑아 간선 리스트 (edges)로 만듬.
 * 5) 비트마스킹을 이용해 가능한 모든 간선의 부분 집합을 구하고 union-find를 통해
 * 각각의 경우에서 모든 그래프가 연결되어 있는지 확인한다.
 * 6) 위의  조건을 모두 만족하는 경우 다리의 합을 구하고 이의 최솟값을 구한다.
 */
public class Main_B17472_다리만들기2_완탐 {
	
	static final int MAX = 987654321;
	static int N, M, cntIsland;
	static char[][] map;
	static boolean[][] visited;
	static int[] parents;
	static Pos[] startPoints;
	static int[][] adj;
	static int answer = 987654321;
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
		
		// 인접 행렬에서 간선 리스트 뽑기
		List<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < cntIsland; i++) {
			for (int j = i; j < cntIsland; j++) {
				if (adj[i][j] < 987654321) edges.add(new Edge (i, j, adj[i][j]));
			}
		}

//		for (int i = 0; i < edges.size(); i++) {
//			System.out.println(edges.get(i).from + ", " + edges.get(i).to);
//		}
		makeSet();
		
		int edgeLen = edges.size();
		int bitMax = (int) Math.pow(2, edgeLen);
		int sum;
		boolean flag;
		// 비트마스킹을 이용한 부분집합 구하기 => 놓을 수 있는 다리 조합 (완전 탐색)
		for (int bit = 1; bit < bitMax; bit++) {
			Arrays.fill(parents, -1);
			sum = 0;
			flag = false;
			for (int i = 0; i < edgeLen; i++) {
				if ((bit & 1 << i) > 0) {
					Edge edge = edges.get(i);
					union(edge.from, edge.to);
					if((sum += edge.dist) >= answer) {
						flag = true;
						break;
					}
				}
				
			}
			if (!flag && allConnected()) {
				answer = Math.min(answer, sum);
			}
		}
		

		
//		for (char[] c : map) System.out.println(Arrays.toString(c));
		System.out.println(answer<987654321? answer : -1);
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
//		System.out.println("from : " + from);
		int ny, nx;
		// 사방으로 다리 놓을 수 있는 위치 인지 확인
		for (int dir = 0; dir < 4; dir++) {
			ny = i + dy4[dir];
			nx = j + dx4[dir];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == '0') {
//				System.out.println("from : " + from + "("+i+", "+j+")");
				// 다리를 놓아보자
				int[] to = makeBridge(ny, nx, dir); // to[0] : 섬 tag 인덱스, to[1] : 다리 길이
				if (to[1] > 1) {
//					System.out.println("from : " + from + ", to : " + (char) (to[0]+'A') + ", " + to[1]);
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
	
	static class Edge {
		int from, to, dist;
		public Edge(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
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
