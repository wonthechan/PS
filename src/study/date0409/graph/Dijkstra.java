package study.date0409.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 우선순위를 안쓰는 버전은 간선이 매우 많을 때 유리!
// 일반적인 간선 빈도에서는 간선리스트 + 우선순위큐 조합이 권장됨.
public class Dijkstra {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_dijkstra.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); 	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		int[][] adj = new int[V][V];	// 프림 때 와 마찬가지로 인접 행렬과의 궁합이 좋다. (둘다 간선이 많을때 유리)
		int[] D = new int[V];			// 도달하기 까지 걸리는 거리 저장 배열 
		Arrays.fill(D, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {	// 유향 그래프임을 잊지 말자. (단방향)
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from][to] = Integer.parseInt(st.nextToken());
		} // 간선 정보 입력
		
		// Dijkstra 시작점이 a 정점이라면 D[a] = 0 으로 셋팅
		D[0] = 0;
		boolean[] check = new boolean[V];	// 처리했는지 안했는지 여부를 저장하는 배열
		
		for (int i = 0; i < V - 1; i++) {
			int min = Integer.MAX_VALUE; 	// 가장 작은 값을 기억할 변수
			int idx = -1;					// 그 위치를 기억할 변수
			for (int j = 0; j < V; j++) {
				// 아직 처리하지 않았으면서, 가장 짧은 거리라면 (가장 처음 원소로 시작점이 들어감, D[a] = 0 이니까)
				if (!check[j] && D[j] < min) {
					min = D[j];
					idx = j;
				}
			}
			
			// 연결이 없는 경우 끝.
			if (idx == -1) break;
			
			// 새로운 친구로부터 갈 수 있는 경로들을 업데이트
			for (int j = 0; j < V; j++) {
				// 아직 처리하지 않았으면서, 경로가 존재하고, 
				// 새로운 경로로 가는게 지금 까지 온 거리보다 짧은 경우 (프림과 차이가 나는 부분)
				if (!check[j] && adj[idx][j] > 0 && D[idx] + adj[idx][j] < D[j]) {
					D[j] = D[idx] + adj[idx][j];
				}
			}
			
			// 처리되었음으로 체크
			check[idx] = true;
		}
		
		// A에서 각 정점으로 도달 할 수 있는 최소 거리를 출력
		System.out.println(Arrays.toString(D));
		// [0, 9, 5, 6, 14, 19, 18]
	}

}
