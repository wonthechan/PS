package study.date0410;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B17143_낚시왕_최적화 {

	static int N, M, K; 	// 격자의 행렬 크기와 상어의 개수
	static Shark[][] map;	// 격자 map
	static List<Shark> sharks = new ArrayList<Shark>();
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17143.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Shark[N+1][M+1];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			sharks.add(new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
								, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
								, Integer.parseInt(st.nextToken())));
		}
		
		initMap();	// 현재 리스트에 있는 상어를 Map에 옳기는 작업
		
		// 낚시꾼이 오른쪽으로 한칸씩 이동하면서
		for (int j = 1; j <= M; j++) {
			goFishing(j);	// 낚시하자
			moveShark();	// 상어가 이동한다.
			updateMap();	// 리스트에 있는 상어를 다시 Map에 새로 옳기는 작업
		}
		
		// 낚시왕이 잡은 상어 크기의 합을 출력
		System.out.println(answer);
	}

			//	상 하 우 좌
	static int[] dy4 = {0, -1, 1, 0, 0};
	static int[] dx4 = {0, 0, 0, 1, -1};
	/** 모든 상어는 1초 마다 정해진 방향과 속도만큼 움직인다. 
	 * 	시간이 오래걸릴 수 있는 부분  (한칸씩 가면 불리)
	 * */
	private static void moveShark() {
		for (Shark shark : sharks) {
			if (shark.isDead) continue;
			int ny = shark.y + dy4[shark.d] * shark.s;
			int nx = shark.x + dx4[shark.d] * shark.s;
			
			// 한칸씩 이동하지 않고 규칙성을 찾아내서
			// 반복횟수를 최대한 줄여야 한다. (최적화)
			while (ny < 1 || nx < 1 || ny > N|| nx > M) {
				switch (shark.d) {
				case 1: 
					shark.d = 2;
					ny = 1 + dy4[shark.d] * (1 - ny);
					break;
				case 2: 
					shark.d = 1; 
					ny = N + dy4[shark.d] * (ny - N);
					break;
				case 3: 
					shark.d = 4; 
					nx = M + dx4[shark.d] * (nx - M);
					break;
				case 4: 
					shark.d = 3; 
					nx = 1 + dx4[shark.d] * (1 - nx);
					break;
				}
			}
			shark.y = ny;
			shark.x = nx;
		}
	}
	
	/** j열에 서서 낚시를 시작한다 */
	private static void goFishing(int j) {
		for (int i = 1; i <= N; i++) { 		// 위에서부터 찾아 내려간다.
			if (map[i][j] != null && !map[i][j].isDead) {		// 상어 발견!!
				answer += map[i][j].z;
//				sharks.remove(map[i][j]);	// 리스트에서 삭제
				map[i][j].isDead = true;
				return;
			}
		}
	}

	
	/** 상어를 격자에 넣는 작업 : 한 칸에는 두마리 이상 들어갈 수 없음. */
	private static void updateMap() {
		for (Shark[] arr : map) Arrays.fill(arr, null); // map 초기화 작업
		for (Shark shark : sharks) {
			if (shark.isDead) continue;
			Shark out = map[shark.y][shark.x];	// 박힌돌
			if (out != null) {	// 이미 다른 상어가 들어가 있는 경우
				// 크기를 비교
				if (out.z < shark.z) {
					// 박힌돌 빼내기
//					sharks.remove(out);
					out.isDead = true;
					map[shark.y][shark.x] = shark;
				} else {
					// 굴러온돌 내보내기
//					sharks.remove(shark);
					shark.isDead = true;
				}
			} else {
				map[shark.y][shark.x] = shark;
			}
		}
	}

	/** 초기 상어 리스트를 맵으로 옳기는 과정 */
	private static void initMap() {
		for (Shark shark : sharks) {
			map[shark.y][shark.x] = shark;
		}
	}

	static class Shark {
		int y, x, s, d, z;
		boolean isDead;

		public Shark(int y, int x, int s, int d, int z) {
			super();
			this.y = y;
			this.x = x;
			this.s = s;	// 속력
			this.d = d;	// 이동 방
			this.z = z;	// 크기
			this.isDead = false;
		}

		@Override
		public String toString() {
			return "Shark [y=" + y + ", x=" + x + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
}
