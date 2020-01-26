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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		diceArr = new int[6];
		
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
	private static void diceDFS3(int i) {
		
	}
	private static void diceDFS2(int step, int start) {
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
		for (int i = 1; i <= N; i++) {
			System.out.print(diceArr[i] + " ");
		}
		System.out.println();
	}
	
}
