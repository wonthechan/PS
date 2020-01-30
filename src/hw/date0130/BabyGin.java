package hw.date0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 *  0 ~ 9 사이의 숫자 카드에서 임의의 카드 6장을 뽑았을 때, 
 *  3장의 카드가 연속적인 번호를 갖는 경우를 run이라 하고,
 *  3장의 카드가 동일한 번호를 갖는 경우를 triplet이라고 한다.
 *  그리고, 6장의 카드가 run과 triplet로만 구성된 경우를  baby-gin으로 부른다
 *  6자리의 숫자를 입력 받아 baby-gin 여부를 판단하는 프로그램을 작성하라.
 */
public class BabyGin {

	static final int N = 6;
	static int[] numbers;
	static int[] result;
	static boolean[] selected;
	static boolean isBabyGin;
	
	public static void main(String[] args) throws IOException {
		// 기본적으로 입력 받은 6자리 수의 모든 순열을 구해본 후 Baby-gin 에 해당하는 경우를 구해본다. 그다음 절반을 나누어 체크
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		numbers = new int[N];
		result = new int[N];
		selected = new boolean[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(str.charAt(i) + "");
		}
		
		isBabyGin = false;
		perm(0);
		
		if(isBabyGin) {
			System.out.println("Baby-gin !!!");
		} else {
			System.out.println("Not a Baby-gin...");
		}
	}

	private static void perm(int index) {
		if (isBabyGin) return;	// BabyGin이 성립되는 경우를 찾았다면 더 이상 진행하지 않음
		
		if (index == N) {	// 기저조건
			// 절반으로 쪼개서 Baby-gin인 경우가 있는지 체크
			if (checkBabyGin(result.clone()))	isBabyGin = true;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			result[index] = numbers[i];
			selected[i] = true;
			perm(index + 1);
			selected[i] = false;
		}
	}

	private static boolean checkBabyGin(int[] arr) {
		
		String first = "";
		String second = "";
		
		for (int i = 0; i < 3; i++)	first += arr[i];
		for (int i = 3; i < N; i++)	second += arr[i];

		if ((checkRun(first) && checkTriplet(second)) || (checkRun(second) && checkTriplet(first)) ||
				(checkRun(first) && checkRun(second)) || (checkTriplet(first) && checkTriplet(second))) {
			return true;
		}
		
		return false;
	}

	private static boolean checkTriplet(String str) {
		if (str.charAt(0) == str.charAt(1) && str.charAt(1) == str.charAt(2)) return true;
		return false;
	}

	private static boolean checkRun(String str) {
		if (str.charAt(2) - str.charAt(1) == 1 && str.charAt(1) - str.charAt(0) == 1) return true;
		if (str.charAt(2) - str.charAt(1) == -1 && str.charAt(1) - str.charAt(0) == -1) return true;
		return false;
	}
	
	

}
