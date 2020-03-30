package study.date0330;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B16235_나무재테크 {

	static int N, M, K;
	static int[][] A;
	static int[][] map;				// 양분정보를 담고 있는 Map
	static List<Tree>[][] trees;	// 1X1 단위에 들어있는 나무들의 리스트
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b16235.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		trees = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				trees[i][j] = new ArrayList<Tree>();
			}
		}
		map = new int[N][N];
		for (int i = 0; i < N; i++) {	// 가장 처음 양분은 모든 칸에 5만큼
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
			}
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int y, x, year;
		for (int i = 0; i < M; i++) {	// 초기 나무 추가
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;
			year = Integer.parseInt(st.nextToken());
			trees[y][x].add(new Tree(year));
		} // 입력 끝
		
		// 계절 시작
		while (K-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}
		
		// 결과 구하기
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += trees[i][j].size();
			}
		}
		System.out.println(answer);
	}
					//	상 방향부터 시계방향
	static int dy8[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dx8[] = {0, 1, 1, 1, 0, -1, -1, -1};
	
	/** 땅에 양분이 추가되는 단계 */
	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}

	/** 나무가 번식하는 단계 */
	private static void fall() {
		int ny, nx;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Tree tree : trees[i][j]) {
					if (tree.year > 0 && tree.year % 5 == 0) {	// 나이가 5의 배수인 경우
						for (int dir = 0; dir < 8; dir++) {		// 8방으로 나이가 1인 나무 번식
							ny = i + dy8[dir];
							nx = j + dx8[dir];
							if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
							trees[ny][nx].add(new Tree(1));
						}
					}
				}
			}
		}
		
	}

	/** 봄에 죽은 나무가 양분으로 변하는 단계 */
	private static void summer() {
		Iterator<Tree> itr;
		Tree tree;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				itr = trees[i][j].iterator();
				while (itr.hasNext()) {
					tree = itr.next();
					if (tree.isDead) {
						map[i][j] += tree.year / 2;
						itr.remove();
					}
				}
			}
		}
		
	}

	/** 양분 먹고 나이 먹고 죽는 단계 */
	private static void spring() {
		// 자신의 나이만큼 양분을 먹고, 나이가 1 증가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Collections.sort(trees[i][j]);
				for (Tree tree : trees[i][j]) {
					if (map[i][j] < tree.year) {	// 양분이 부족해서 죽는 경우
						tree.isDead = true;
					} else {
						map[i][j] -= tree.year;
						++tree.year;
					}
				}
			}
		}
	}

	static class Tree implements Comparable<Tree>{
		int year;			// 나이
		boolean isDead;		// 죽었는지 flag
		public Tree(int year) {
			this.year = year;
		}
		@Override
		public int compareTo(Tree o) {
			return this.year - o.year;
		}
	}
}
