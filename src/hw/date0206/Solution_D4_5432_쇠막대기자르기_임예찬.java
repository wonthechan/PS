package hw.date0206;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_D4_5432_쇠막대기자르기_임예찬 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			int len = str.length();
			
			int cntBar = 0;
			int answer = 0;
			
			for (int i = 0; i < len-1; i++) {
				int cur = str.charAt(i);
				int next = str.charAt(i+1);
				
				if (cur == '(') {
					if (next == ')') {
						// 레이저
						answer += cntBar;
						i++;
					} else {
						cntBar++;	// 막대기 증가
					}
				} else {
					answer++;
					cntBar--;		// 막대기 감소
				}
				
			}
			answer++;	// 마지막으로 쪼개진 막대기 추가
			System.out.printf("#%d \n %d", tc, answer);
		}
	}

}
