package study.union_find;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B9938_방청소_sol {
	static int[] parent = new int[300001];
	static boolean[] visited = new boolean[300001];
	static StringBuilder sb = new StringBuilder();

	public static void merge(int u, int v) {
		u = find(u);
		v = find(v);
		parent[u] = v;
		sb.append("LADICA\n");
	}

	public static int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		return parent[u] = find(parent[u]);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b9938.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= l; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (!visited[a]) {
				visited[a] = true;
				merge(a, b);
			} else if (!visited[b]) {
				visited[b] = true;
				merge(b, a);
			} else if (!visited[find(a)]) {
				visited[find(parent[a])] = true;
				merge(a, b);
			} else if (!visited[find(b)]) {
				visited[find(parent[b])] = true;
				merge(b, a);
			} else {
				sb.append("SMECE\n");
			}
		}
		System.out.print(sb.toString());
	}
}