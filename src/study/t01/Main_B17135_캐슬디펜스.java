package study.t01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 조합을 만들고 그 조합을 이용하는 경우 조합을 전부 만들고 어딘가에 저장해서 사용하는 것보다는
 * 조합을 만들 때 마다 그 조합을 가지고 바로 사용하는 것이 낫다.
 */
public class Main_B17135_캐슬디펜스 {

	static int N, M, D;
	static int[][] map;
	
	static boolean[] selected; // 조합
	static List<Enemy> initEnemies;// 적 객체를 담아둘 리스트
	static PriorityQueue<Enemy> queue; // 사정권내에 있는 적들을 관리하는 큐 (우선순위 큐)
	
	static int MaxCntDead = 0; // 가장 많이 제거될 수 있는 적의 수
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17135.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		// map 배열 생성
		map = new int[N][M];
		
		// 적 객체를 담아둘 리스트와 관리를 위한 큐 생성
		initEnemies = new ArrayList<>();
		queue = new PriorityQueue<>();
		
		// map 입력 (입력과 동시에 적 객체 생성)
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					initEnemies.add(new Enemy(i, j));
				}
			}
		}
		
		// 조합을 구하기 위한 selected 배열 생성
		selected = new boolean[M];
		
		// 궁수의 위치에 따라 제거할 수 있는 적의 수가 달라진다.
		generateCombo(0, 0);
		
		System.out.println(MaxCntDead);
	}

	private static void generateCombo(int idx, int level) {
		/* 궁수의 범위는 (N+1, 0~M-1) */
		if (level == 3) { // 기저조건
			// 생성된 궁수 위치의 조합(selected)을 가지고 게임 시작
			int[] archeryX = new int[3];
			int k = 0;
			for (int i = 0; i < M; i++) {
				if (selected[i] == true) {
					archeryX[k++] = i;
				}
			}
			MaxCntDead = Math.max(MaxCntDead, startGame(archeryX));
			return;
		}
		for (int i = idx; i < M; i++) {
			selected[i] = true;
			generateCombo(i + 1, level+1);
			selected[i] = false;
		}
	}
	
	private static int startGame(int[] archeryX) {
//		System.out.println(Arrays.toString(archeryX));
		// 초기 리스트 복사해서 쓰자
		List<Enemy> enemies = new ArrayList<>();
		for (int i = 0; i < initEnemies.size(); i++){
			enemies.add(new Enemy(initEnemies.get(i).y, initEnemies.get(i).x));
		}
		// archeryX 배열을 보면서 궁수 위치 조합을 파악한다.
		
		int dist;
		int cntDead = 0; // 궁수가 제거한 적의 수
		// 게임 시작 (적이 한칸씩 내려옴) : 모든 적이 제거되면 게임 종료
		for(int archeryY = N; archeryY >= 0; archeryY--) {
			// 모든 적이 제거되었다면 게임을 종료한다.
			if (enemies.isEmpty()) {
				break;
			}
			// 범위를 이탈한 적이 있다면 제거한다.
			for (Iterator<Enemy> iter = enemies.iterator(); iter.hasNext();) {
				if (iter.next().y >= archeryY) {
					iter.remove();
				}
			}

			for (int i = 0; i < 3; i++) {
				queue.clear();
				// 궁수들은 차례대로 돌면서 일단 사정권내에 있는 적들을 모두 우선순위 큐에 넣는다.
				for (int j = 0; j < enemies.size(); j++) {
					Enemy enemy = enemies.get(j);
					dist = Math.abs(archeryY - enemy.y) + Math.abs(archeryX[i] - enemy.x);
					if (dist <= D) {
						enemy.d = dist;
						queue.offer(enemy);
					}
				}
				// 큐가 비어 있지 않다면 (적어도 사정권내에 하나 이상의 적이 있는 경우) 가장 우선순위가 높은 적을 뽑는다.
				if (!queue.isEmpty()) {
					Enemy target = queue.poll();
//					System.out.println(target + " poll");
					target.isDead = true;	// 제거되었음을 표시한다. (실제로 삭제하진 않는다)
				}
			}
			
			// 적 리스트를 돌면서 제거 표시가 된 적들을 최종적으로 제거한다.
			for (Iterator<Enemy> iter = enemies.iterator(); iter.hasNext();) {
				if (iter.next().isDead == true) {
					iter.remove();
					++cntDead; // 카운트 1 증가
				}
			}
			
			
		}
		return cntDead;
	}

	static class Enemy implements Comparable<Enemy> {
		Integer y;
		Integer x;
		Integer d;
		boolean isDead; // 한번 제거 된적이 있는지 확인
		
		public Enemy(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Enemy other) {
			if (this.d == other.d) {				// 거리가 같으면 왼쪽에 위치할 수록 우선순위가 높음
				return this.x.compareTo(other.x);
			} else {
				return this.d.compareTo(other.d);	// 1차적으로 거리가 가까울수록 우선순위가 높음
			}
		}
		
		public String toString() {
			return "(y=" + this.y + ", x=" + this.x + ", d=" + this.d + ")";
			
		}
	}
}
