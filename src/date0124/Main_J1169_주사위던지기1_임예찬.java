package date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1169_주사위던지기1_임예찬 {
	// 주사위를 던진 횟수 N (2<=n<=5)
	static int N;
	// 출력 모양 M (1<=M<=3)
	static int M;
	// N번 던진 주사위의 눈을 저장하는 배열
	static int[] diceArr;
	// 뽑힘 여부를 저장하는 배열
	static boolean[] visitArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		diceArr = new int[6];
		visitArr = new boolean[7];
		
		while(true) {
			// 첫 줄에 주사위를 던진 횟수 N(2≤N≤5)과 출력모양 M(1≤M≤3)이 들어온다.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if ((N >= 2 && N <= 5) && (M >= 1 && M <= 3)) {
				// 올바른 범위에 있는 경우
				break;
			} else {
				// 주어진 범위를 벗어날 경우
				System.out.println("INPUT ERROR!");
			}
		}
		
		rollTheDice(N, M);
	}
	private static void rollTheDice(int step, int mode) {
		switch(mode) {
		case 1:	diceDFS1(1);
				break;
		case 2: diceDFS2(1, 1);
				break;
		case 3: diceDFS3(1);
				break;
		}
	}
	private static void diceDFS3(int step) {
		/* 주사위를 N번 던져서 모두 다른 수가 나올 수 있는 모든 경우 */
		// diceDFS1과 유사하지만 방문 여부를 체크하여 한번 나온 수는 다시 나오지 않게 한다.
		if (step > N) {
			printResult();
			return;
		}
		for (int i = 1; i <= 6; i++) {
			if (visitArr[i]) continue;
			visitArr[i] = true;
			diceArr[step] = i;
			diceDFS3(step+1);
			// 끝까지 탐색한 경우 방문 여부를 다시 초기화한다.
			visitArr[i] = false;
		}
	}
	private static void diceDFS2(int step, int start) {
		/* 주사위를 N번 던져서 중복이 되는 경우를 제외하고 나올 수 있는 모든 경우 */
		if (step > N) {
			printResult();
			return;
		}
		for (int i = start; i <= 6; i++) {
			diceArr[step] = i;
			diceDFS2(step+1, i);
		}
	}
	private static void diceDFS1(int step) {
		/* 주사위를 N번 던져서 나올 수 있는 모든 경우 */
		if (step > N) {
			printResult();
			return;
		}
		for (int i = 1; i <= 6; i++) {
			diceArr[step] = i;
			diceDFS1(step+1);
		}
	}
	
	private static void printResult() {
		// N번 던진 주사위 결과를 출력한다.
		for (int i = 1; i <= N; i++) {
			System.out.print(diceArr[i] + " ");
		}
		System.out.println();
	}
}
