package study.date0302;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 주사위눈을 어떻게 처리해야할지 한참을 고민한 문제.
 * 그리고 범위 체크 시 N과 M을 헷갈려써서 런타임에러 수렁에 빠지기도 했음.
 * 항상 오타 조심하자!
 */
public class Main_B14499_주사위굴리기 {

	static int N, M, K, cy, cx;	// 지도의 세로 가로 크기, 명령의 갯수, 주사위 시작 좌표
	static int[][] map;
	static int[] cmds;
	static int[] dice = new int[7];
					//	동 서 북 남 (1부터 인덱스 시작)
	static int[] dy4 = {0, 0, 0, -1, 1};
	static int[] dx4 = {0, 1, -1, 0, 0};
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input_b14499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cy = Integer.parseInt(st.nextToken());
		cx = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cmds = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) cmds[i] = Integer.parseInt(st.nextToken());
		
		// 주사위를 움직이자
		int dir;
		for (int i = 0; i < K; i++) {
			dir = cmds[i];
			cy += dy4[dir];
			cx += dx4[dir];	// 현재 위치를 다음 위치로 움직인다.
			
			// 지도의 범위 밖으로 나가는 경우 해당 명령어 Skip
			if (cy < 0 || cx < 0 || cy >= N || cx >= M) {
				cy -= dy4[dir];	// 이동하기 전, 원래대로 되돌림.
				cx -= dx4[dir];
				continue;
			}
			
			// 가장 먼저 주사위 상태 업데이트
			updateStateDice(dir);
			
			// 이동한 칸에 쓰여 있는 수가 0인 경우 (주사위의 바닥면에 있는 수로 복사됨)
			if (map[cy][cx] == 0) {
				map[cy][cx] = dice[6];
			} else {
				// 0이 아닌 경우 (칸에 쓰여 있는 수를 주사위의 바닥면으로 복사하고 칸은 0으로 초기화)
				dice[6] = map[cy][cx];
				map[cy][cx] = 0;
			}
			
			// 윗면 출력
			sb.append(dice[1]).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static private void updateStateDice(int dir) {
		// 방향 dir에 따라 새로운 주사위 상태를 만들어준다. (각 주사위 면에 해당하는 숫자가 바뀌게 됨)
		// dir[1] 은 항상 바닥면이고 dir[6]은 항상 윗면이다.
		int[] temp = dice.clone();
		switch (dir) {
		case 1:	// 동쪽
			dice[1] = temp[4];
			dice[3] = temp[1];
			dice[4] = temp[6];
			dice[6] = temp[3];
			break;
		case 2: // 서쪽
			dice[1] = temp[3];
			dice[3] = temp[6];
			dice[4] = temp[1];
			dice[6] = temp[4];
			break;
		case 3:	// 북쪽
			dice[1] = temp[5];
			dice[2] = temp[1];
			dice[5] = temp[6];
			dice[6] = temp[2];
			break;
		case 4:	// 남쪽
			dice[1] = temp[2];
			dice[2] = temp[6];
			dice[5] = temp[1];
			dice[6] = temp[5];
			break;
		}
	}

}
