package study.t01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B1697_숨바꼭질 {

	static int N, K;
	static int MIN;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b1697.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// visit 배열 생성
		visit = new boolean[100001];
		
		MIN = 0;
		
		if (K < N) {
			MIN = N - K;
		} else {
			bfs(N);
		}
		
		System.out.println(MIN);
	}
	
	private static void bfs(int cur) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(cur);
		
		int size, out;
		while(!queue.isEmpty()) {
			size = queue.size();
			while(size-- > 0) {
				out = queue.poll();
				if (out == K) {
					return;
				}
				if (out >= 0 && out <= 100000 && visit[out] == false) {
					visit[out] = true;
					queue.offer(out * 2);
					queue.offer(out + 1);
					queue.offer(out - 1);
				}
			}
			++MIN; // 연산 횟수 증가
		}
	}
}
