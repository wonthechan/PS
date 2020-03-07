package study.date0307;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B14888_연산자끼워넣기 {

	static final int opSize = 4;
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] nums;
	static int[] opCnt = new int[opSize];	// 0: 덧셈, 1: 뺄셈, 2: 곱셈, 3: 나눗셈
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14888.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < opSize; i++) opCnt[i] = Integer.parseInt(st.nextToken());
		
		// 순열을 만들기 위한 배열 생성 (연산자의 순서) => 넥퍼 사용 : 중복 순열은 생략 되는 효과
		int[] orders = new int[N-1];	// 연산자의 갯수 == ( 연산자의 갯수 - 1)
		int idx = 0;
		for (int i = 0; i < opSize; i++) {
			for (int j = 0; j < opCnt[i]; j++) orders[idx++] = i;
		}
		
		do {
			int res = nums[0];
			for (int i = 0; i < N-1; i++) {
				switch (orders[i]) {	// 연산자 하나 뽑고
				case 0:	// 덧셈
					res += nums[i+1];
					break;
				case 1:	// 뺄셈
					res -= nums[i+1];
					break;
				case 2:	// 곱셈
					res *= nums[i+1];
					break;
				case 3:	// 나눗셈
					res = res < 0 ? - (Math.abs(res) / nums[i+1]) : res / nums[i+1];
					break;
				}
			}
			max = Math.max(max, res);
			min = Math.min(min, res);
		} while(np(orders));
		System.out.println(max);
		System.out.println(min);
	}
	
	private static boolean np(int[] arr) {
		int n = N - 1;
		//
		int i = n - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = n - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
		j = n - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j;
		}
		return true;
	}
}
