package study.t02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_B4195_친구네트워크 {

	static Map<String, String> parents;
	static Map<String, Integer> cnt;

	public static String findSet(String a) {	// 대표자 찾기
		if (parents.get(a) == a) return a;
		String res = findSet(parents.get(a));
		parents.put(a, res);
		return res;
	}
	
	public static void union(String a, String b) {
		String aRoot = findSet(a);
		String bRoot = findSet(b);
		
		if (!aRoot.equals(bRoot)) {	// union 할 수 있는 경우
			cnt.put(aRoot, cnt.get(aRoot) + cnt.get(bRoot));
			parents.put(bRoot, aRoot);
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_b4195.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			parents = new HashMap<String, String>();
			cnt = new HashMap<String,Integer>();
			
			int F = Integer.parseInt(br.readLine());
			
			while (F-- > 0) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if (!parents.containsKey(a)) {
					parents.put(a, a);	// make-set
					cnt.put(a, 1);		// 초기 개수는 1개
				}
				if (!parents.containsKey(b)) {
					parents.put(b, b);	// make-set
					cnt.put(b, 1);		// 초기 개수는 1개
				}
				
				// 등록된 경우  union을 계속 한다.
				union(a, b);
//				System.out.println(parents.entrySet());
				System.out.println(cnt.get(findSet(a)));
			}
		}
	}
}
