package study.date0314;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_B5052_전화번호목록 {

	static int t, n;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b5052.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		t = Integer.parseInt(br.readLine());
	L1:	for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			String[] nums = new String[n];
			TrieNode rootNode = new TrieNode();
			for (int i = 0; i < n; i++) {
				// 모든 전화번호를 트라이에 추가
				nums[i] = br.readLine();
				insert(rootNode, nums[i]);
			}
			// 한개씩 검색해본다.
			for (int i = 0; i < n; i++) {
				if(!isConsistent(rootNode, nums[i], 0)) {
					sb.append("NO").append("\n");
					continue L1;
				}
			}
			sb.append("YES").append("\n");
		}
		System.out.print(sb.toString());
	}

	public static boolean isConsistent(TrieNode cur, String num, int level) {
		if (level == num.length()) {	// 끝까지 도달했다면 종료
			return true;
		}
		
		int next = num.charAt(level) - '0';
		// 어차피 직전에 추가했기 때문에 children[next]은 항상 null이 아님
		if (cur.isTerm) return false;	// 지금 단계에서 종결된 번호가 있는 경우 (종료)
		return isConsistent(cur.children[next], num, level + 1);
	}
	public static void insert(TrieNode root, String num) {
		TrieNode cur = root;
		for (char ch : num.toCharArray()) {
			int next = ch - '0';
			cur.children[next] = cur.children[next] == null ? cur.children[next] = new TrieNode() : cur.children[next];
			cur = cur.children[next];
		}
		cur.isTerm = true;
	}
	
	static class TrieNode {
		TrieNode[] children;
		boolean isTerm;
		public TrieNode() {
			this.children = new TrieNode[10]; // 범위는 0부터 9까지
			this.isTerm = false;
		}
	}
}
