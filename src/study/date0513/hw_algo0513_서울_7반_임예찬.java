package study.date0513;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 위상 정렬을 이용한 풀이 */
public class hw_algo0513_서울_7반_임예찬 {

	static int N, M;	// N(1≤N≤32,000), M(1≤M≤100,000)
	static List[] list;
	static int[] inDegree;	// 진입차수 저장 배열
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input/b2252.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		for (int i = 1; i <= N; i++) list[i] = new ArrayList<Integer>();
		inDegree = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			list[prev].add(next);
			++inDegree[next];
		} // 입력 끝
		
		Queue<Integer> queue = new LinkedList<>();
		// 진입 차수가 0인 것들만 먼저 뽑아서 큐에 넣는다.
		// 진입 차수가 0인 것이 없으면 위상정렬 불가
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		// 큐가 빌 때까지 자신이 가리키고 있는 좌표들을 방문하여
		// inDegree값을 -1 해주고, 만약 0이라면 큐에 넣어주고
		// 현재 값을 출력 결과에 넣어준다.
//		List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			int out = queue.poll();
//			result.add(out);
			sb.append(out).append(" ");
			for (int i = 0; i < list[out].size(); i++) {
				int temp = (int) list[out].get(i);
				--inDegree[temp];
				if (inDegree[temp] == 0) {
					queue.offer(temp);
				}
			}
		}
		
		// 결과 출력
//		for (int i : result) sb.append(i).append(" ");
		System.out.println(sb.toString());
	}

}
