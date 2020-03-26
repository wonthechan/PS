package study.date0326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 별도의 맵을 만들어서 거기에 연합국을 표시하는 방식이 아니라
 * 연합국을 리스트에 넣어서 관리한다.
 */
// 참고: https://mygumi.tistory.com/338
public class Main_B16234_인구이동_mygumi {

	static int N, L, R, answer = 0;
	static int[][] map;
	static boolean[][] visit;
	static List<Pos> track = new ArrayList<>(); // mark 배열을 대신하여 연합국의 위치를 담아둘 리스트
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		boolean flag = true;
		while (flag) {
			for (boolean[] arr : visit) Arrays.fill(arr, false); // visit 초기화
			flag = false;
			++answer; // 인구 이동 횟수 1 증가
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j] && bfs(i, j)) {
						flag = true;
					}
				}
			}
		}
		
		System.out.println(answer - 1);
	}
					//	상 하 좌 우
	static int[] dy4 = {-1, 1, 0, 0};
	static int[] dx4 = {0, 0, -1, 1};
	private static boolean bfs(int i, int j) {
		Queue<Pos> queue = new LinkedList<>();
		visit[i][j] = true;
		track.clear();
		track.add(new Pos(i, j));
		queue.offer(new Pos(i, j));
		int cnt = 1;			// 연합국의 개수
		int sum = map[i][j];	// 연합국의 인구 수 누적
		while (!queue.isEmpty()) {
			Pos out = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int ny = out.i + dy4[dir];
				int nx = out.j + dx4[dir];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;
				int diff = Math.abs(map[out.i][out.j] - map[ny][nx]);
				if (diff >= L && diff <= R) {
					++cnt;
					sum += map[ny][nx];
					visit[ny][nx] = true;
					track.add(new Pos(ny, nx));
					queue.offer(new Pos(ny, nx));
				}
			}
		}
		
		// 여기서 그냥 업데이트까지 해버리기
		if (cnt > 1) { // 연합국이 두개 이상인 경우 (인구 이동이 가능한 경우) => 업데이트 진행
			int midValue = sum / cnt;
			for (Pos pos : track) {
				map[pos.i][pos.j] = midValue;
			}
			return true;	// 인구 이동이 일어 났음
		}
		return false;
	}

	static class Pos {
		int i, j;
		public Pos (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
