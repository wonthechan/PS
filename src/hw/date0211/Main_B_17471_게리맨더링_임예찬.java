package hw.date0211;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17471_게리맨더링_임예찬 {

	static int N;
	static int[] ingusu;
	static boolean[][] adj;
	static boolean[] selected;
	static int minDiff;
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b17471.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());		// 구역 개수 입력
		ingusu = new int[N+1];						// 인구 수 배열 생성
		selected = new boolean[N+1];				// 조합 생성을 위한 배열
		adj = new boolean[N+1][N+1];				// 인접 행렬 배열 생성
		
		st = new StringTokenizer(br.readLine());	// 구역 별 인구 수 입력
		for (int i = 1; i <= N; i++) ingusu[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {				// 인접 정보 입력
			st = new StringTokenizer(br.readLine());
			int adjLen = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjLen; j++) {
				adj[i][Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		minDiff = 987654321;
		// 조합을 만들자 (N개를 두개의 그룹으로 나눔:)
		for (int i = 1; i <= (N/2); i++) {
			comboDFS(1, 0, i);	// i개의 조합을 만들어낸다.
		}
		if (minDiff == 987654321) System.out.println(-1);
		else System.out.println(minDiff);
	}
	
	private static void comboDFS(int idx, int level, int max) {
		if (level == max) {
			int sumA = 0;
			int sumB = 0;
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			boolean[] selA = new boolean[N+1];
			boolean[] selB = new boolean[N+1];
			for (int i = 1; i <= N; i++) {
				if (selected[i]) {
					sb1.append(i).append(" ");
					sumA += ingusu[i];
					selA[i] = true;
				} else {
					sb2.append(i).append(" ");
					sumB += ingusu[i];
					selB[i] = true;
				}
			}
			sb1.append("(").append(sumA).append(")");
			sb2.append("(").append(sumB).append(")");
			// 유효한 선거구인지 확인.
			if (bfs(sb1.charAt(0) - '0', selA) && bfs(sb2.charAt(0) - '0', selB)) {
				
				int tempMin = Math.abs(sumA - sumB);
				if (tempMin < minDiff) {
					minDiff = tempMin;
//					System.out.println(sb1.toString() + " / " + sb2.toString());
				}
			}
			return;
		}
		
		for(int i = idx; i <= N; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			
			comboDFS(i, level+1, max);
			selected[i] = false;
		}
	}

	private static boolean bfs(int i, boolean[] sel) {
		boolean ret = true;
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[i] = true;
		queue.offer(i);
		while(!queue.isEmpty()) {
			int out = queue.poll();
			for (int k = 1; k <= N; k++) {
				if (adj[out][k] == true && visited[k] == false && sel[k] == true) { // sel[k] == true 조건이 중요
					visited[k] = true;
					queue.offer(k);
				}
			}
		}
		for (int k = 1; k <= N; k++) {
			if (sel[k] == true) {
				if (visited[k] == false) ret = false;
			}
		}
		return ret;
	}

}
