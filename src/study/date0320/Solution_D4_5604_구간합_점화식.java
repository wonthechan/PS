package study.date0320;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
f(n)=f(n-1-n%v)+g(n)
g(n)=n/v*(n%v+1)+f(n%v)
유도 
f(9)=1*f(9)=45
f(99)=20*f(9)
f(999)=300*f(9)
f(9999)=4000*f(9)
... 유도
 */
// 수열의 규칙을 찾아 점화식으로 답 구하기
public class Solution_D4_5604_구간합_점화식 {

	static long A, B, answer;
	static HashMap<Long, Long> map = new HashMap<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s5604.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i < 17; i++) {
			long v = pow10(0L + i);
			map.put((v-1L), h(v-1L));
		} //9 99 999 9999 99999 ... (Base case 미리 구해서 저장)
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			
			answer = cal(A, B);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static long cal (long A, long B) {
		if (A <= 1) {
			return f(B);
		}
		return f(B) - f(A - 1);
	}
	
	// 점화식: f(n)=f(n-1-n%v)+g(n)
	private static long f(long n) {
		// memo 확인
		if (map.containsKey(n)) {
			return map.get(n);
		} else if (n <= 9) {
			return e(n);
		}
		// n % v 는 n의 일의자리 수
		long v = pow10(len(n));
		map.put(n, f(n - 1L - n % v) + g(n));
		return map.get(n);
	}

	// 점화식: g(n)=n/v*(n%v+1)+f(n%v)
	private static long g(long n) {
		if (n <= 9) return e(n);
		long v = pow10(len(n));
		return (n / v) * (n % v + 1L) + f(n % v);
	}

	// n 까지의 합 구하기 (공식적용)
	private static long e(long n) {
		return n * (n+1L) / 2L;
	}

	/*
	 * 	f(9)=1*f(9)=45
		f(99)=20*f(9)
		f(999)=300*f(9)
		f(9999)=4000*f(9)
	 */
	private static long h(long n) {
		long len=len(n)+1L;
		return ((45L)*(len)*(1L+n))/(10L);
	}
	
	// n의 자리수 구하기
	public static long len(long n){
		return 0L+(n+"").length()-1;
	}
	public static long pow10(long n){
		return (long)Math.pow(10, n);
		
	}
}
