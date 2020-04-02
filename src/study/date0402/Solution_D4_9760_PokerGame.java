package study.date0402;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_9760_PokerGame {

	static char[] sDeck = new char[5]; // suit와 rank를 나눠서 관리
	static int[] rDeck = new int[5];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_s9760.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 5; i++) {
				String card = st.nextToken();
				sDeck[i] = card.charAt(0);
				switch (card.charAt(1)) {
				case 'T':
					rDeck[i] = 10;
					break;
				case 'J':
					rDeck[i] = 11;
					break;
				case 'Q':
					rDeck[i] = 12;
					break;
				case 'K':
					rDeck[i] = 13;
					break;
				case 'A':
					rDeck[i] = 1;
					break;
				default:
					rDeck[i] = card.charAt(1) - '0';
					break;
				}
			}
			
			// 오름차순 정렬
			Arrays.sort(rDeck);
			
			// 검사
			boolean isFlush = checkFlush();
			boolean isStraight = checkStraight();
			
			int cnt = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (rDeck[i] == rDeck[j]) cnt += 1;
				}
			}
			
			// 1. 스트레이트 플러쉬 Check (로열 스트레이트 플러쉬 포함)
			if (isFlush && isStraight) {
				sb.append("Straight Flush\n");
				continue;
			}
			
			if (cnt == 17) {
				sb.append("Four of a Kind\n");
				continue;
			}
			
			if (cnt == 13) {
				sb.append("Full House\n");
				continue;
			}
			
			if (isFlush) {
				sb.append("Flush\n");
				continue;
			}
			
			if (isStraight) {
				sb.append("Straight\n");
				continue;
			}
			
			if (cnt == 11) {
				sb.append("Three of a kind\n");
				continue;
			}
			
			if (cnt == 9) {
				sb.append("Two pair\n");
				continue;
			}
			
			if (cnt == 7) {
				sb.append("One pair\n");
				continue;
			}
			
			sb.append("High card\n");
		}
		System.out.print(sb.toString());
	}

	static boolean checkFlush() {
		return sDeck[0] == sDeck[1] && sDeck[0] == sDeck[2]
				&& sDeck[0] == sDeck[3] && sDeck[0] == sDeck[4]? true : false;
	}
	
	static boolean checkStraight() {
		// 로얄 스트레이트 플러쉬 처리
        if (rDeck[0] == 1 && rDeck[1] == 10 && rDeck[2] == 11 && rDeck[3] == 12 && rDeck[4] == 13) return true;
        
		for (int i = 0; i < 4; i++) {
			if (rDeck[i+1] - rDeck[i] != 1) return false;
		}
		return true;
	}
}