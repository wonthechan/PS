package hw.date0205;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D3_1225_암호생성기_임예찬 {

	static Queue<Integer> queue;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		queue = new LinkedList<Integer>();	// Integer 타입 큐 생성
		
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			br.readLine();
			
			// 큐에 데이터 enqueue
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) queue.offer(Integer.parseInt(st.nextToken()));
			
			int num = 0;
			int dec = 1;
			
			while((num = queue.poll().intValue() - dec) > 0) {	// 감소시킨 숫자가 0이하 인 경우 빠져나온다.
				queue.offer(new Integer(num)); // Auto-boxing
				if (++dec > 5) dec = 1; // 사이클 초기화
			}
			queue.offer(0);				// 마지막으로 0 넣어 주기
			
			System.out.printf("#%d ", tc);
			while(!queue.isEmpty()) {System.out.print(queue.poll()+ " ");}	 // 결과 출력
			System.out.println();
		}
	}
}
