package study.date0404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 페르마의 소정리를 이용한 풀이
// SWEA 5607. 조합 문제와 비슷
// 조합 공식: N! / K!(N-K)!
// 참고: https://cru6548.tistory.com/23
public class Main_B11401_이항계수3 {

	static final int P = 1000000007;
	static int N, K;
	static long[] fact;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if (N == K || K == 0) {
			System.out.println(1);
			return;
		}
		
		// 1. N!을 구한다. (N까지의 모든 팩토리얼을 저장해둔다)
		fact = new long[N+1];
		fact[1] = 1;
		for (int i = 2; i <= N; i++) {
			fact[i] = (fact[i-1] * i) % P;
		}
		
		// 2. R!과 (N-R)! 을 구한다. (앞서 저장해둔 걸로 한번에 구하기.)
		long rFact = fact[K];
		long nrFact = fact[N-K];
		
		// 3. R!과 (N-R)! 을 곱하고 P-2승을 구한다.
		long rnrFact = (rFact * nrFact) % P;
		
		// 4. N!과 R!(N-R)!의 P-2승을 곱한 최종 결과 값을 구한다. (mod P 필수)
		long answer = (fact[N] * pow(rnrFact, P-2)) % P;
		
		System.out.println(answer);
	}

	static long pow (long a, long n) {
		long res = 1L;
		while (n > 0) {
			if (n % 2 == 1) {
				res *= a;
				res %= P;
			}
			a *= a;
			a %= P;
			n /= 2;
		}
		return res;
	}
}
