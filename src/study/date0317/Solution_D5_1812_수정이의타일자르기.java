package study.date0317;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D5_1812_수정이의타일자르기 {

	static int N, M, answer;
	static int[] sizes;
	static PriorityQueue<Rectangle> tiles;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s1812.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sizes = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				sizes[i] = Integer.parseInt(st.nextToken());
			}
			
			// 사전 작업
			pre();
			
			// 자르기 시작
			cut();
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void pre() {
		// 필요한 타일들을 변의 길이를 기준으로 오름차순으로 정렬
		Arrays.sort(sizes);
		// 남아 있는 타일들을 담아둘 우선순위큐를 생성한다.
		tiles = new PriorityQueue<>();
		// 일단 처음에 기본적으로 주어지는 M size 타일 하나 큐에 넣기
		tiles.offer(new Rectangle(M, M));
		++answer;
	}

	private static void cut() {
		// 필요한 타일 중 큰거부터 구해보기
		for (int i = N - 1; i >= 0; i--) {
			go(1<<sizes[i]); // 2의 sizes[i]승이 필요한 타일의 한변의 길이
		}
	}

	private static void go(int size) {
		// 큐에서 하나 꺼내기 : 현재 가지고 있는 타일 중에서 min 값이 최대인 타일
		// 꺼낸 직사각형의 최소변의 길이가 size보다 같거나 크면 : 원하는 크기의 정사각형을 만들 수 있음.
		Rectangle rec = tiles.poll();
		if (rec.min >= size) {
			tiles.offer(new Rectangle(rec.min - size, size)); // 잘라내고 남은 직사각형 큐에 넣기
			tiles.offer(new Rectangle(rec.min, rec.max - size)); // 잘라내고 남은 직사각형 큐에 넣기
		} else { // 아니면 : 원하는 크기의 정사각형을 만들 수 없음.
			// 새 타일을 사야함. (큐에 남아 있는 걸로는 못구함)
			tiles.offer(rec); // 꺼낸거 다시 회수
			// 새 타일에서 잘라낸 거 넣어 주기
			tiles.offer(new Rectangle(M - size, size)); // 잘라내고 남은 직사각형 큐에 넣기
			tiles.offer(new Rectangle(M, M - size)); // 잘라내고 남은 직사각형 큐에 넣기
			++answer; // 새 타일 샀으니까 카운트 1 증가
		}	
	}

	static class Rectangle implements Comparable<Rectangle>{
		int min, max;	// 최소변과 최대변만 관리
		public Rectangle (int width, int height) {
			if (width < height) {
				this.min = width;
				this.max = height;
			} else {
				this.min = height;
				this.max = width;
			}
		}
		@Override
		public int compareTo(Rectangle o) {
			// 항상 min 값이 큰 것이 먼저오도록 큐의 상태를 유지한다.
			return o.min - this.min;
		}
	}

}
