package study.date0521;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class hw_algo0521_서울_7반_임예찬 {

	static final int LEFT_ALPHA = 21;
	
	static int N, K;	// 단어의 개수 N과 K. N은 50보다 작거나 같은 자연수, K는 26보다 작거나 같은 자연수 또는 0
	static int[] wordBMs; // 단어에서 출현한 알파벳 비트 마스킹
	static int answer = -1;
	// 기본적으로 가지고 있는 알파벳을 제외한 나머지들
	static char[] leftAlpha = {'b', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'o', 'p', 'q', 'r', 's', 'u', 'v', 'w', 'x', 'y', 'z'};
	static int selectedBM = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b1062.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		wordBMs = new int[N];
		for (int i = 0; i < N; i++) {
			for (char ch : br.readLine().toCharArray()) {
				wordBMs[i] |= 1 << (ch - 'a');
			}
		} // 입력 끝
		
		if (K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		} else {
			
			int[] arr = new int[LEFT_ALPHA];
			for (int i = 0; i < K -5; i++) {
				arr[LEFT_ALPHA - 1 -i] = 1;
			}
			
			// NP
			do {
				initSelected();
				
				for (int i = 0; i < LEFT_ALPHA; i++) {
					if (arr[i] == 1) {
						selectedBM |= 1 << (leftAlpha[i] - 'a');
					}
				}
				int cnt = 0;
				for (int wordBM : wordBMs) {
					if ((wordBM & selectedBM) == wordBM) {
						++cnt;
					}
				}
				answer = Math.max(answer, cnt);
			} while (NP(arr));
		}
		System.out.println(answer);
	}

	static void initSelected() {
		selectedBM = 0;
		selectedBM |= 1 << ('a' - 'a');
		selectedBM |= 1 << ('n' - 'a');
		selectedBM |= 1 << ('t' - 'a');
		selectedBM |= 1 << ('i' - 'a');
		selectedBM |= 1 << ('c' - 'a');
	}
	
	static boolean NP(int[] arr) {
		int N = arr.length;
		//
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
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}
}
