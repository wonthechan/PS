package study.date0524;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B17825_주사위윷놀이_hardcoding {

	static final int N = 10;
	static final int[][] next = { // next[x][y] = x에서 y만큼 이동했을 때의 인덱스
			{1, 2, 3, 4, 5},
			{2, 3, 4, 5, 6},
			{3, 4, 5, 6, 7},
			{4, 5, 6, 7, 8},
			{5, 6, 7, 8, 9},
			{21, 22, 23, 24, 25}, // 분기점
			{7, 8, 9, 10, 11},
			{8, 9, 10, 11, 12},
			{9, 10, 11, 12, 13},
			{10, 11, 12, 13, 14},
			{27, 28, 24, 25, 26},// 분기점
			{12, 13, 14, 15, 16},
			{13, 14, 15, 16, 17},
			{14, 15, 16, 17, 18},
			{15, 16, 17, 18, 19},
			{29, 30, 31, 24, 25},// 분기점
			{17, 18, 19, 20, 32},
			{18, 19, 20, 32, 32},
			{19, 20, 32, 32, 32},
			{20, 32, 32, 32, 32},
			{32, 32, 32, 32, 32},
			{22, 23, 24, 25, 26}, // 21에서 이동
			{23, 24, 25, 26, 20},
			{24, 25, 26, 20, 32},
			{25, 26, 20, 32, 32},
			{26, 20, 32, 32, 32},
			{20, 32, 32, 32, 32},
			{28, 24, 25, 26, 20}, // 27에서 이동
			{24, 25, 26, 20, 32},
			{30, 31, 24, 25, 26},
			{31, 24, 25, 26, 20},
			{24, 25, 26, 20, 32},
			{32, 32, 32, 32, 32}	// 이미 도착한 경우에서 움직이려고 하는 경우
	}; // x행 5열
	static final int[] scores = {
			0,
			2, 4, 6, 8, 10,
			12, 14, 16, 18, 20,
			22, 24, 26, 28, 30,
			32, 34, 36, 38, 40,
			13, 16, 19, 25, 30,
			35, 22, 24, 28, 27,
			26,
			0 // 도착 지점
	};
	
	static int[] players = new int[4];		// 말 배열
	static int[] dices = new int[10];				// 주어진 주사위의 눈
	static int answer = -987654321;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17825.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		System.out.println(answer);
	}

	private static void dfs(int turn, int score) {
		// 턴이 다 안 끝나도 모든 말이 전부 도착하는 경우가 존재하는가? => 매번 5가 나와도 불가능
		if (turn == 10) {
			answer = Math.max(answer, score); // 최대 점수 갱신
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (players[i] == 32) continue;	// 이미 도착한 말은 제외
			int nextIdx = next[players[i]][dices[turn]-1];
			if (isOccupied(i, nextIdx)) continue;		// 이미 다른 말이 있는 경우
			
			int curIdx = players[i];
			players[i] = nextIdx;
			dfs(turn + 1, score + scores[nextIdx]);		// 다음턴으로
			players[i] = curIdx;
		}
	}

	private static boolean isOccupied(int curPlayer, int nextIdx) {
		for (int i = 0; i < 4; i++) {
			if (i == curPlayer || players[i] == 32) continue;
			if (nextIdx == players[i]) return true;
		}
		return false;
	}
}
