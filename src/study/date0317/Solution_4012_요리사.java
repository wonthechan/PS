package study.date0317;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 스타트와 링크 문제와 유사한 문제 */
public class Solution_4012_요리사 {

	static int N, answer;
	static int[][] adj;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			adj = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 넥퍼로 조합 생성 (N개에서 N/2개를 뽑는 조합)
			int[] pick = new int[N];
			for (int i = N-1; i >= N/2; i--) {
				pick[i] = 1; // 만들려고 하는 조합의 수만큼 뒤부터 1로 채워주기
			}
			int[] foodA = new int[N/2]; // 음식 A의 식재료 배열
			int[] foodB = new int[N/2]; // 음식 B의 식재료 배열
			int idxA, idxB, sA, sB;
			do {
				idxA = idxB = sA = sB = 0;	// 초기화
				// 음식 A와 B의 식재료 배열 구하기
				for (int i = 0; i < N; i++) {
					if (pick[i] == 1) foodA[idxA++] = i;
					else foodB[idxB++] = i;
				}
				// 음식 A와 B의 시너지 값 구하기
				for (int i = 0, end = N/2; i < end - 1; i++) {
					for (int j = i + 1; j < end; j++) {
						sA += (adj[foodA[i]][foodA[j]] + adj[foodA[j]][foodA[i]]);
						sB += (adj[foodB[i]][foodB[j]] + adj[foodB[j]][foodB[i]]);
					}
				}
				
				answer = Math.min(answer, Math.abs(sA - sB));
			} while (np(pick));
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private static boolean np(int[] arr) {
		int N = arr.length;
		// 꼭대기 i 구하기
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		// 교환지점 j 구하기
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		// swap
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		// reoreder
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		if (arr[0] == 1) return false;	// 절반만 구하기
		return true;
	}
	
	

}
