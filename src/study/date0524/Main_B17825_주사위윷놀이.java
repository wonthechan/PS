package study.date0524;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B17825_주사위윷놀이 {

	static final int N = 10;
	
	static Player[] players = new Player[4];		// 말 배열
	static int[] dices = new int[10];				// 주어진 주사위의 눈
	
	// 경로별 점수 유형 (0,1,2,3)
	static int[][] scores = {
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			{0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40},
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40},
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40},
	};
	
	static int answer = -987654321;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/b17825.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < 4; i++) players[i] = new Player(); // 초기 말 셋팅
		
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
			if (players[i].isFinished) continue;	// 이미 도착한 말은 제외
			
			int curPath = players[i].pathKind;
			int curPosIdx = players[i].posIdx;
			boolean curStatus = players[i].isFinished;
			
			if (canGoNext(i, dices[turn])) {
				players[i].stack.push(scores[players[i].pathKind][players[i].posIdx]);
				dfs(turn + 1, score + scores[players[i].pathKind][players[i].posIdx]);		// 다음턴으로
				players[i].pathKind = curPath; // backtracking
				players[i].posIdx = curPosIdx;
				players[i].isFinished = curStatus;
				players[i].stack.pop();
			}
		}
	}

	private static boolean isOccupied(int curPlayerIdx) {
		int curScore = scores[players[curPlayerIdx].pathKind][players[curPlayerIdx].posIdx];
		for (int i = 0; i < 4; i++) {
			if (i == curPlayerIdx || players[i].isFinished) continue;
			int otherScore = scores[players[i].pathKind][players[i].posIdx];
			if (curScore == otherScore) {	// 다음 위치에 이미 다른 말이 있는 경우
				switch (curScore) {
				case 16: case 22: case 24: case 26: case 28: case 30: // 중복된 숫자판
					if (players[i].pathKind == players[curPlayerIdx].pathKind) {
						return true;
					}
					break;
				default:
					return true;
				}
			}
		}
		return false;
	}

	/** 다음 위치를 계산하고 갈 수 있는지 여부를 확인하여 리턴 */
	private static boolean canGoNext(int curPlayerIdx, int move) {
		Player player = players[curPlayerIdx];
		
		int curPath = player.pathKind;
		int curPosIdx = player.posIdx;
		
		player.posIdx = curPosIdx + move; // 다음위치로 현위치 갱신
		
		if (player.posIdx >= scores[player.pathKind].length) {
			player.posIdx = 0;			// 도착한 경우는 인덱스를 0으로 설정
			player.isFinished = true;	// 도착 처리
		} else {											// 도착하지 않은 경우
			if (player.pathKind == 0) {							// 분기점은 한번만 걸릴 수 있음
				if (scores[curPath][curPosIdx] == 10) {			// 다음 위치가 분기점에 있는 경우 경로 유형 바꿔주기
					player.pathKind = 1;
				} else if (scores[curPath][curPosIdx] == 20) {
					player.pathKind = 2;
				} else if (scores[curPath][curPosIdx] == 30) {
					player.pathKind = 3;
				}
			}
			if (isOccupied(curPlayerIdx)) {			// 갈 수 없으면 원상 복구
				player.pathKind = curPath;
				player.posIdx = curPosIdx;
				return false;
			}
		}
		return true;
	}

	static class Player {
		int posIdx;
		boolean isFinished;
		int pathKind; // 초기 경로는 0
		Stack<Integer> stack;
		public Player() {
			stack = new Stack<>();
		}
		public Player(int posIdx, boolean isFinished, int pathKind) {
			this.posIdx = posIdx;
			this.isFinished = isFinished;
			this.pathKind = pathKind;
		}
		@Override
		public String toString() {
			return "Player [posIdx=" + posIdx + ", isFinished=" + isFinished + ", pathKind=" + pathKind + "]";
		}
	}
}
