package study.date0317;

public class DP연습_피보나치 {

	public static int[] memo = new int[10000];
	public static void main(String[] args) {
//		System.out.println(fibo(45));
		System.out.println(fiboMemo(45));
	}

	public static int fibo(int num) {
		if (num < 2) return num;
		return fibo(num-1) + fibo(num-2);
	}
	
	public static int fiboMemo(int num) {
		if (num < 2) return num;
		if (memo[num] > 0) return memo[num];
		return memo[num] = fiboMemo(num-1) + fiboMemo(num-2);
	}
}
