package study.date0411;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B15686_치킨배달 {

	static int N, M; // 도시의 크기와 골라야 할 치킨집 개수
	static List<Pos> chickens = new ArrayList<Pos>(); 	// 치킨 집의 좌표 리스트
	static List<Pos> houses = new ArrayList<Pos>();		// 집의 좌표 리스트
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 1) { // 집인 경우
					houses.add(new Pos(i, j));
				} else if (a == 2) {
					chickens.add(new Pos(i, j));
				}
			}
		} // 좌표 입력 끝
		
		int answer = Integer.MAX_VALUE;
		// 이제 치킨 집을 선택하는 조합을 만들자. (넥퍼로 구현)
		int cLen = chickens.size(); // 모든 치킨집의 수
		int[] arr = new int[cLen];	// 넥퍼에 사용할 배열
		for (int i = cLen - 1; i >= cLen - M; i--) arr[i] = 1; 
		do {
			// 구해진 조합으로 도시 치킨 거리 계산하고 최솟값 갱신하기.
			int chickenDist = 0;
			for (Pos house : houses) {
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] == 1) {
						Pos chicken = chickens.get(i);
						int dist = Math.abs(house.y - chicken.y) + Math.abs(house.x - chicken.x);
						min = Math.min(min, dist);
					}
				}
				chickenDist += min;
			}
			answer = Math.min(answer, chickenDist);
		} while (np(arr));
		
		System.out.println(answer);
	}
	
	private static boolean np(int[] arr) {
		int N = arr.length;
		// 꼭대기 i 구하기
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		// swap 할 j 구하기
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		// swap
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		// re-order
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}

	static class Pos {
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
