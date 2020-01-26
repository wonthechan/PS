package date0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J2817_로또 {
	// 후보 숫자 개수
	static int K;
	// 후보 숫자 배열
	static int[] nums;
	// 뽑은 숫자 배열 (6개)
	static int[] lottos;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		lottos = new int[6];
		
		// 입력
		st = new StringTokenizer(br.readLine());		
		K = Integer.parseInt(st.nextToken());
		nums = new int[K];
		for (int i = 0; i < K; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 가능한 모든 번호 조합을 구하는 재귀 함수 (햄버거 다이어트 문제와 유사)
		comboR(0, 0, 0);
	}

	private static void comboR(int level, int curIdx, int tgtIdx) {
		if (level == 6) {
			// 6개 다 뽑은 경우
			printLottos();
			return;
		}
		
		if (tgtIdx == K) {
			// 모든 후보 숫자들을 탐색한 경우
			return;
		}
		
		lottos[curIdx] = nums[tgtIdx];
		// 뽑는 경우
		comboR(level+1, curIdx+1, tgtIdx+1);
		// 안 뽑는 경우
		comboR(level, curIdx, tgtIdx+1);
	}

	private static void printLottos() {
		for (int i = 0; i < 6; i++) {
			System.out.print(lottos[i] + " ");
		}
		System.out.println();
	}
	
}
