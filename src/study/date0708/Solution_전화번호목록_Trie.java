package study.date0708;

// 트라이 자료구조 활용
public class Solution_전화번호목록_Trie {

	public static void main(String[] args) {
		System.out.println(solution(new String[] {"123", "124", "1234"}));
	}
	
	static public boolean solution(String[] phone_book) {
		// insert
		TrieNode root = new TrieNode();
		for (String num : phone_book) {
			insert(root, num);
		}
		
		// check
		for (String num : phone_book) {
			if (!isConsistent(root, num)) return false;
		}
		
        return true;
    }
	
	public static boolean isConsistent(TrieNode root, String num) {
		TrieNode curNode = root;
		for (char ch : num.toCharArray()) {
			int digit = ch - '0';
			if (curNode.isTerm) return false;
			curNode = curNode.children[digit];
		}
		return true;
	}
	
	public static void insert(TrieNode root, String num) {
		TrieNode curNode = root;
		for (char ch : num.toCharArray()) {
			int digit = ch - '0';
			curNode = curNode.children[digit] == null ? curNode.children[digit] = new TrieNode() : curNode.children[digit]; 
		}
		curNode.isTerm = true;
	}
	
	static class TrieNode {
		TrieNode[] children;
		boolean isTerm;
		public TrieNode() {
			children = new TrieNode[10]; // 0 ~ 9
			isTerm = false;
		}
	}
}
