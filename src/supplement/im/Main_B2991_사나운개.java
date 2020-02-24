package supplement.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2991_사나운개 {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int attackA, quietA, attackB, quietB, cycleA, cycleB;
		st = new StringTokenizer(br.readLine());
		attackA = Integer.parseInt(st.nextToken());
		quietA = Integer.parseInt(st.nextToken());
		attackB = Integer.parseInt(st.nextToken());
		quietB = Integer.parseInt(st.nextToken());
		cycleA = attackA + quietA; // 개 A의 공격은 cycleA마다 시작된다.
		cycleB = attackB + quietB; // 개 B의 공격은 cycleB마다 시작된다.
		
		st = new StringTokenizer(br.readLine());
		// 우체부, 우유배달원, 신문배달원의 도착시간을 차례대로 입력 받고
		// 두마리의 개에게 공격받는지 여부를 판단한다.
		for (int i = 0; i < 3; i++) {
			int arrive = Integer.parseInt(st.nextToken());
			int cnt = 0;
			// 첫번째 개한테 공격받는지 여부 판단 (공격 범위 안에 있는지 확인)
			if (arrive % cycleA > 0 && arrive % cycleA <= attackA) cnt++;
			// 두번째 개한테 공격받는지 여부 판단
			if (arrive % cycleB > 0 && arrive % cycleB <= attackB) cnt++;
			System.out.println(cnt);
		}
	}
}
