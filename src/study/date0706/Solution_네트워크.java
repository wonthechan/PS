package study.date0706;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_네트워크 {

	public static void main(String[] args) {
		System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
	}

	static boolean[] visited;
	static public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        // computers 배열은 인접행렬
        for (int i = 0; i < n; i++) {
        	if (visited[i]) continue;
        	bfs(i, computers); // i번째 정점과 연결되어 있는 네트워크를 찾는다.
        	++answer;
        }
        return answer;
    }
	
	private static void bfs(int startVertex, int[][] adj) {
		Queue<Integer> queue = new LinkedList<>();
		visited[startVertex] = true;
		queue.offer(startVertex);
		
		while (!queue.isEmpty()) {
			int out = queue.poll();
			for (int nextVertex = 0; nextVertex < adj.length; nextVertex++) {
				if (adj[out][nextVertex] == 1 && !visited[nextVertex]) { // 연결되어 있고 방문하지 않은 정점이라면
					visited[nextVertex] = true;
					queue.offer(nextVertex);
				}
			}
		}
		
	}
}
