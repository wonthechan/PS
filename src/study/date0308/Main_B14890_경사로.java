package study.date0308;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B14890_경사로 {

	static int N, L, answer = 0;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14890.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		// 가로 길 체크
		boolean isValid;
		int curH, cnt;	// 현재 높이와 현재 높이가 연속된 횟수
		for (int i = 0; i < N; i++) {
			isValid = true;
			curH = map[i][0];
			cnt = 1;
			int j;
			for (j = 1; j < N; j++) {
				if (curH == map[i][j]) {
					++cnt;
				} else if (curH - map[i][j] == -1){	// 높이가 1 높아진 경우
					// 높이가 달라진 경우 (경사로를 만들 수 있는 지 확인)
					if (cnt >= L) {	// 경사로를 만들 수 있는 경우 (연속 카운트 1로 초기화)
						cnt = 1;
						curH = map[i][j];
					} else {		// 경사로를 만들 수 없는 경우 (L개가 연속되지 않음)
						isValid = false;
						break;
					}
				} else if (curH - map[i][j] == 1) {	// 높이가 1 낮아진 경우
					curH = map[i][j];
					if (j + L > N) {				// 경사로를 만들 수 없는 경우 (충분한 공간 없음)
						isValid = false;
						break;
					}
					boolean flag = false;
					int end = j + L;
					while (j+1 < end) {
						if (curH != map[i][++j]) flag = true;
					}
					if (flag) {						// 경사로를 만들 수 없는 경우 (뒤로 L개가 연속되지 않음)
						isValid = false;
						break;
					}
					// 만들수 있는 경우
					cnt = 0;
				} else {	// 그 외의 경우는 경사로를 통해 길을 만들 수 없음.
					isValid = false;
					break;
				}
			}
			if (isValid) {
//				System.out.println(i+", "+j);
				++answer;
			}
		}
		
		for (int j = 0; j < N; j++) {
			isValid = true;
			curH = map[0][j];
			cnt = 1;
			int i;
			for (i = 1; i < N; i++) {
				if (curH == map[i][j]) {
					++cnt;
				} else if (curH - map[i][j] == -1){	// 높이가 1 높아진 경우
					// 높이가 달라진 경우 (경사로를 만들 수 있는 지 확인)
					if (cnt >= L) {	// 경사로를 만들 수 있는 경우 (연속 카운트 1로 초기화)
						cnt = 1;
						curH = map[i][j];
					} else {		// 경사로를 만들 수 없는 경우 (L개가 연속되지 않음)
						isValid = false;
						break;
					}
				} else if (curH - map[i][j] == 1) {	// 높이가 1 낮아진 경우
					curH = map[i][j];
					if (i + L > N) {				// 경사로를 만들 수 없는 경우 (충분한 공간 없음)
						isValid = false;
						break;
					}
					boolean flag = false;
					int end = i + L;
					while (i+1 < end) {
						if (curH != map[++i][j]) flag = true;
					}
					if (flag) {						// 경사로를 만들 수 없는 경우 (뒤로 L개가 연속되지 않음)
						isValid = false;
						break;
					}
					// 만들수 있는 경우
					cnt = 0;
				} else {	// 그 외의 경우는 경사로를 통해 길을 만들 수 없음.
					isValid = false;
					break;
				}
			}
			if (isValid) {
//				System.out.println(i+", "+j);
				++answer;
			}
		}
		
		System.out.println(answer);
	}

}
