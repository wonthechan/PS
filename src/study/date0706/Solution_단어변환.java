package study.date0706;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_단어변환 {

	public static void main(String[] args) {
		System.out.println(solution("hit", "cog",  new String[] {"hot", "dot", "dog", "lot", "log"}));
	}

	static public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int[][] adj = new int[words.length + 1][words.length + 1];
        String[] words2 = new String[words.length + 1];
        words2[0] = begin;
        for (int i = 1; i < words2.length; i++) words2[i] = words[i - 1];
        completeAdj(adj, words2);
        
        answer = bfs(adj, words2, target);
        return answer;
    }

	private static int bfs(int[][] adj, String[] words, String target) {
		boolean[] visited = new boolean[words.length];
		Queue<Integer> queue = new LinkedList<>();
		visited[0] = true;
		queue.offer(0);
		int dist = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int out = queue.poll();
				if (words[out].equals(target)) {
					return dist;
				}
				for (int nextVertex = 0; nextVertex < adj.length; nextVertex++) {
					if (!visited[nextVertex] && adj[out][nextVertex] == 1) {
						visited[nextVertex] = true;
						queue.offer(nextVertex);
					}
				}
			}
			++dist;
		}
		return 0;
	}

	private static void completeAdj(int[][] adj, String[] words) {
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj.length; j++) {
				if (i == j) {
					adj[i][j] = 1;
				}
				else if (isConnected(words[i], words[j])) {
					adj[i][j] = 1;
				}
			}
		}
	}
	
	private static boolean isConnected(String s1, String s2) {
		int diffCnt = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) ++diffCnt;
			if (diffCnt > 1) return false;
		}
		return true;
	}
}