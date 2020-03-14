package study.date0314;

import java.util.Arrays;

/* 순차적으로 모든 word를 탐색하면서 일치하는 횟수를 세어도 되지만
 * word의 개수가 많아지면 시간복잡도가 급격하게 증가한다.
 * => 따라서 문자열 특화 자료구조인 "트라이" 자료구조를 문제에 맞게 변형하여 사용한다.
 */
// 참고 : https://the-dev.tistory.com/3
public class Solution_kakao20공채4_가사검색 {
	
	static final int ALPHABET = 26;
	public static void main(String[] args) throws Exception {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?", "?????"};
		System.out.println(Arrays.toString(solution(words, queries)));
	}
	
	public static int[] solution(String[] words, String[] queries) {
		int N = words.length;
		int M = queries.length;
        int[] answer = new int[M];
        
        // 단어 하나의 길이가 최대 1만이기 때문에 길이가 1인 문자열을 넣을 트라이부터 
        // 길이가 1만인 문자열을 넣을 트라이(루트노드)까지 생성합니다. (역순 문자열 트라이도 만든다)
        TrieNode[] tries = new TrieNode[10001];
        TrieNode[] triesRev = new TrieNode[10001];
        for (int i = 0; i < 10001; i++) tries[i] = new TrieNode();
        for (int i = 0; i < 10001; i++) triesRev[i] = new TrieNode(); // 역순 트라이
        
        // 이제 각 단어별로 길이에 맞는 트라이에 넣어줍니다. (역순 트라이에는 뒤집어서 insert)
        for (int i = 0; i < N; i++) {
        	insert(tries[words[i].length()], words[i]);
        	insertRev(triesRev[words[i].length()], words[i]);
        }
        
        // 쿼리 길이와 일치하는 트라이를 뒤져서 매칭 cnt 가져오기
        for (int i = 0; i < M; i++) {
        	answer[i] = queries[i].charAt(0) == '?' ? matchRev(triesRev[queries[i].length()], queries[i]) : match(tries[queries[i].length()], queries[i]) ;
        }
        
        return answer;
    }
	
	static class TrieNode {
		TrieNode[] children;	// 각 노드는 모든 알파벳에 대해서 연결성을 가지고 있음
		int childCnt;
		public TrieNode () {
			this.children = new TrieNode[ALPHABET];
			this.childCnt = 0;
		}
	}
	
	// 트라이에 문자열 word를 추가한다.
	public static void insert (TrieNode root, String word) {
		TrieNode cur = root;
		for (char ch : word.toCharArray()) {
			int next = ch - 'a';
			// 해당 자식 노드가 없다면 생성한다.
			cur = cur.children[next] == null ? cur.children[next] = new TrieNode() : cur.children[next];
			// 단어를 넣을 때는 각 문자별로 해당 노드의 count를 1씩 증가시켜 줍니다.
			++cur.childCnt;
		}
		++root.childCnt;	// 현재 자리수에 포함되는 모든 단어의 개수 증가
	}
	
	public static void insertRev (TrieNode root, String word) {
		TrieNode cur = root;
		for (int i = word.length() - 1; i >= 0; --i) {
			int next = word.charAt(i) - 'a';
			// 해당 자식 노드가 없다면 생성한다.
			cur = cur.children[next] == null ? cur.children[next] = new TrieNode() : cur.children[next];
			// 단어를 넣을 때는 각 문자별로 해당 노드의 count를 1씩 증가시켜 줍니다.
			++cur.childCnt;
		}
		++root.childCnt;
	}
	
	// 특정 query와 매칭되는 word가 트라이에 존재하는지 확인
	// query가 전부 ?인 경우도 고려!!
	public static int match (TrieNode root, String query) {
		TrieNode cur = root;	// 루트 노드부터 확인한다.
		// 단어를 검색할 때는 접두사에 해당하는 노드까지 이동한 후 해당 노드의 count를 return 하면 됩니다.
		for (char ch : query.toCharArray()) {
			if (ch == '?') break;
			if (cur.children[ch - 'a'] == null) return 0;
			cur = cur.children[ch - 'a'];
		}
		return cur.childCnt;
	}
	public static int matchRev (TrieNode root, String query) {
		TrieNode cur = root;	// 루트 노드부터 확인한다.
		// 단어를 검색할 때는 접두사에 해당하는 노드까지 이동한 후 해당 노드의 count를 return 하면 됩니다.
		for (int i = query.length() - 1; i >= 0; --i) {
			char ch = query.charAt(i);
			if (ch == '?') break;
			if (cur.children[ch - 'a'] == null) return 0;
			cur = cur.children[ch - 'a'];
		}
		return cur.childCnt;
	}
}
