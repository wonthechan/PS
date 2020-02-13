package study.im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1493_수의새로운연산 {
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_s1493.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int answer = 0;
			
			st = new StringTokenizer(br.readLine());
			
			int[] p1 = getPos(Integer.parseInt(st.nextToken()));
			int[] p2 = getPos(Integer.parseInt(st.nextToken()));
			
			answer = getValue(p1[0]+p2[0], p1[1]+p2[1]);
			System.out.println("#" + tc + " " + answer);
		}
		
	}

	public static int[] getPos(int k) {
		int[] ret = new int[2];
		int value = 0;
		
	L1:	for (int n = 2; n <= 10000; n++) {
			for (int i = 1; i < n; i++) {
				int curI = i;
				int curJ = n - i;
				
				if (++value == k) {
					ret[0] = curI;
					ret[1] = curJ;
					break L1;
				}
				
			}
			
		}
		return ret;
	}
	public static int getValue(int x, int y) {
//		int ret = 0;
//		ret = x * (x+1) / 2; // 1 부터  x까지의 합
//		
//		// x + y - 2 까지의 합에서 x-1까지의 합을 뺀다.
//		for (int i = 0; i < y - 1; i++) {
//			ret += x + i;
//		}
		
		// x + y - 2 까지의 합
		int k = x + y - 2;
		int ret = k * (k+1) / 2 + x;
		
		// (x+y-2) * (x+y-1) / 2 + x
		// k = x + y
		// (k-2) * (k-1) / 2 + x
		// (k2 - 3k +2) / 2 + x
		// x2/2 + y2/2 + xy - 3x/2 - 3y + x
		return ret;
		
	}

}
