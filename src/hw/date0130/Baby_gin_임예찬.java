package hw.date0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  0 ~ 9 사이의 숫자 카드에서 임의의 카드 6장을 뽑았을 때, 
 *  3장의 카드가 연속적인 번호를 갖는 경우를 run이라 하고,
 *  3장의 카드가 동일한 번호를 갖는 경우를 triplet이라고 한다.
 *  그리고, 6장의 카드가 run과 triplet로만 구성된 경우를  baby-gin으로 부른다
 *  6자리의 숫자를 입력 받아 baby-gin 여부를 판단하는 프로그램을 작성하라.
 */
public class Baby_gin_임예찬 {

	static final int N = 6;		// 6장의 숫자 카드를 뽑을 수 있음
	static int[] numbers;		// 뽑은 6개의 숫자가 저장된 배열
	static int[] result;		// 앞서 뽑은 6개의 숫자로 만들 수 있는 순열이 저장된 배열
	static boolean[] selected;	// 순열을 만들기 위해 사용되는 방문 확인 배열
	static boolean isBabyGin;	// (순열 생성을 중단할 flag 역할)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		numbers = new int[N];
		result = new int[N];
		selected = new boolean[N];
		for (int i = 0; i < N; i++) {	// 입력 받은 6개의 숫자를 배열로 저장한다.
			numbers[i] = Integer.parseInt(str.charAt(i) + "");
		}
		
//		isBabyGin = false;				// 초기 상태는 false로 지정한다. 
//		generatePerm(0);				// 순열을 만들어내는 메소드를 실행
		
		if(isBabygin(0,0)) {
			System.out.println("Baby-gin 입니다!!");
		} else {
			System.out.println("Baby-gin 이 아닙니다..");
		}
	}

	// boolean형을 리턴하는 버전 (비트마스킹 활용)
	private static boolean isBabygin(int index, int selected) {
		if (index == N) {	// 기저조건
			// 해당 순열이  BabyGin인지 확인하는 메소드 실행 (배열을 문자열로 변환하여 매개변수로 전달)
			String toStr = "";
			for (int i = 0; i < N; i++) toStr += result[i];
			return checkBabyGin(toStr);
		}
		for (int i = 0; i < N; i++) {
			if ((selected & 1<<i) != 0) continue;	// if (selected[i]) continue;
			result[index] = numbers[i];
			if(isBabygin(index + 1, selected | 1<<i)) return true;
		}
		return false;
	}
	
	/* 입력받은 6자리의 수로 만들 수 있는 모든 순열을 구한다 */
	private static void generatePerm(int index) {
		if (index == N) {	// 기저조건
			// 해당 순열이  BabyGin인지 확인하는 메소드 실행 (배열을 문자열로 변환하여 매개변수로 전달)
			String toStr = "";
			for (int i = 0; i < N; i++) toStr += result[i];
//			System.out.println(toStr);
			if (checkBabyGin(toStr))	isBabyGin = true;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (selected[i]) continue;
			result[index] = numbers[i];
			selected[i] = true;
			generatePerm(index + 1);
			if (isBabyGin) return; // BabyGin이 성립되는 경우를 찾았다면 더 이상 진행하지 않음 (중요)
			selected[i] = false;
		}
	}

	/* 6개의 숫자를 3개씩 쪼개어서 두 개 모두 Run 또는 Triplet에 해당되는지 확인하여
	 * Baby-gin 여부를 리턴한다.
	 */
	private static boolean checkBabyGin(String str) {
		String first = str.substring(0, 3);
		String second = str.substring(3);
		// Baby-gin이 되는 경우를 조사한다.
		if ((checkRun(first) && checkTriplet(second)) || (checkRun(second) && checkTriplet(first)) ||
				(checkRun(first) && checkRun(second)) || (checkTriplet(first) && checkTriplet(second))) {
			return true;
		}
		return false;
	}

	/* 처음 숫자와 두번째 숫자와 같고 두번째 숫자가 마지막 숫자와 같다면 Triplet으로 판단 */
	private static boolean checkTriplet(String str) {
		if (str.charAt(0) == str.charAt(1) && str.charAt(1) == str.charAt(2)) return true;
		return false;
	}

	/* 연속으로 감소하거나 증가하는 경우 Run으로 판단 */
	private static boolean checkRun(String str) {
		if (str.charAt(2) - str.charAt(1) == 1 && str.charAt(1) - str.charAt(0) == 1) return true;
		if (str.charAt(2) - str.charAt(1) == -1 && str.charAt(1) - str.charAt(0) == -1) return true;
		return false;
	}
	
}
