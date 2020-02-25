package study.date0224;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 참고 : https://sophia2730.tistory.com/entry/Algorithm-%EB%B0%B1%EC%A4%8017136-%EC%83%89%EC%A2%85%EC%9D%B4-%EB%B6%99%EC%9D%B4%EA%B8%B0
 * 단순한 브루트포스 문제..그리디같은건 없었다.
 * 다만 가지치키를 통해 시간 단축을 해야함.
 */
public class Main_B17136_색종이붙이기 {

	static char[][] map = new char[10][10]; // 크기는 항상 동일
	static int oneCnt = 0;
	static int minCnt = 987654321;
	static int[] left = {0, 5, 5, 5, 5, 5};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17136.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 현재 상태 입력
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < 10; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == '1') ++oneCnt;
			}
		}
		
		dfs(0, 0, 0, 0);
		System.out.println(minCnt == 987654321? "-1" : minCnt);
	}
	
	public static void dfs(int y, int x, int pCnt, int sum) {
//		System.out.println(y + " , " + x + " : " + pCnt + " :: " + sum);
		if (pCnt >= minCnt) return;	// 현재 최솟값보다 더 색종이를 많이 쓰는 경우는 더 이상 볼 필요가 없다.
		
		if (sum == oneCnt) {	// 1을 다 덮은 경우
//			System.out.println(y + " , " + x + " : " + pCnt + " :: " + sum);
			minCnt = Math.min(minCnt, pCnt);
			return;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == '1') {
					boolean flag = false; // canCover를 반복하지 않기 위함
					// 큰 색종이 부터 넣을 수 있는 지 확인 (큰 종이가 맞다면 작은 종이도 포함할 수 있도록 한다.
				L1:	for (int n = 5; n > 0; n--) {
						// 색종이가 남아 있는지 부터 확인 (없으면 다음 색종이 보기)
						if (left[n] < 1) continue;
						
						// flag가 false인 경우는 직전 색종이가 cover에 실패한 경우 이므로 다시 확인해봐야 한다.
						if (!flag) {
							flag = canCover(i, j, n);
						}
						
						// flag가 true이면 직전 색종이가 cover했으므로 현재 색종이는 크기가 작으므로 당연히 확인할 필요 없이 cover 가능하다.
						if (flag) {
							--left[n];				// 색종이 하나 사용했으니까 1 감소
							switchMap(i, j, n); 	// visit 처리
							dfs(i, x, pCnt + 1, sum + n * n);
							++left[n];				// 색종이 수 원래대로 돌려놓기
							switchMap(i, j, n); 	// unvisit 처리
						}
					}
					// 한장도 덮지 못한 경우에는 더 이상 탐색해볼 필요가 없다. (가지치기 1)
					if (!flag) {
						return;
					}
				}
				// 현재 좌표를 덮지 못한 경우 색종이가 부족한 경우일 수 있다. (가지치기 2) ?? 
				if (map[i][j] == '1') {
					return;
				}
			}
		}
	}
	
	public static boolean canCover(int y, int x, int n) {
		if (y + n > 10 || x + n > 10) return false;	// 범위 check
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (map[i][j] != '1') {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void switchMap(int y, int x, int n) {
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				map[i][j] = map[i][j] == '1'? '0' : '1';
			}
		}
	}
}
