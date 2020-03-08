package study.date0308;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B14889_스타트와링크 {

	static int N, answer = Integer.MAX_VALUE;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14889.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];	// 인덱스는 1부터 시작
		for (int i = 1; i <= N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for (int j = 1; j <= N; j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			 }
		} // 입력 끝
		
		// 총 N명이 참가하여 N/2 명씩 나누어 두개의 팀을 만드므로 N명에서 N/2명을 뽑는 조합과 같다.
		// 넥퍼를 이용해 조합 생성.
		int[] arr = new int[N];
		int[] teamA = new int[N/2];
		int[] teamB = new int[N/2];
		int idxA, idxB, scoreA, scoreB;
		for (int i = N/2; i < N; i++) arr[i] = 1;
		do {
			// 팀 구성
			idxA = idxB = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i] == 1) teamA[idxA++] = i;
				else teamB[idxB++] = i;
			}
			scoreA = scoreB = 0;
			for (int i = 0; i < N/2; i++) {	// 각 팀의 능력치 계산
				for (int j = 0; j < N/2; j++) {
					scoreA += map[teamA[i]+1][teamA[j]+1];
					scoreB += map[teamB[i]+1][teamB[j]+1];
				}
			}
			answer = Math.min(answer, (int) Math.abs(scoreA - scoreB)); 
		} while(np(arr));
		
		System.out.println(answer);
	}
	private static boolean np(int[] arr) {
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
		j = N -1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}

}
