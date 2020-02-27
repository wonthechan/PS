package study.date0227;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// 참고: https://hoho325.tistory.com/74
public class Main_B3954_인터프리터 {

	static int[] mem;
	static int memLen, codeLen, inputLen, p, inputPointer;
	static int cntLoop;
	static String code, input;
	static Stack<Integer> stack = new Stack<>();
	static Pair[] pairs;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b3954.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			memLen = Integer.parseInt(st.nextToken());
			codeLen = Integer.parseInt(st.nextToken());
			inputLen = Integer.parseInt(st.nextToken());
			code = br.readLine();
			input = br.readLine();
			inputPointer = 0;
			
			mem = new int[memLen];	// 배열 초기화
			p = 0;					// 포인터 인덱스 초기화
			pairs = new Pair[codeLen];
			cntLoop = 0;
			
			checkBracket();
			
			// 코드 해석
			int i = 0;
			int latestBracket = 0;
		 	while (true) {
				char ch = code.charAt(i);
				switch (ch) {
				case '-':
					--mem[p];
					if (mem[p] < 0) mem[p] = 255;
					break;
				case '+':
					++mem[p];
					if (mem[p] == 256) mem[p] = 0;
					break;
				case '<':
					--p;
					if (p < 0) p = memLen - 1;
					break;
				case '>':
					++p;
					if (p == memLen) p = 0;
					break;
				case '[':
					if (mem[p] == 0) {
						// 다음 짝이 맞는 괄호를 찾아야 한다.
						++cntLoop;
						i = pairs[i].right; // Jump to ]
					}
					break;
				case ']':
					if (mem[p] != 0) {
						++cntLoop;
						i = pairs[i].left; // Jump to [
					}
					break;
				case '.':
//					System.out.println(mem[p]);
					break;
				case ',':
					if (inputPointer == inputLen) {
						mem[p] = 255;
						break;
					}
					mem[p] = (int) input.charAt(inputPointer++);
					break;
				}
				
				++i;
				++cntLoop;
				if (i > latestBracket) latestBracket = i;
				if (i == codeLen) {
					sb.append("Terminates").append('\n');
					break;
				}
				
				if (cntLoop >= 50000000) {
					sb.append("Loops ").append(pairs[latestBracket].left).append(" ")
					.append(pairs[latestBracket].right).append('\n');
					break;
				}
				
			}
		}
		System.out.println(sb.toString());
	}
	
	private static void checkBracket() {
		stack.clear();
		int i = 0;
		while (i < codeLen) {
			char ch = code.charAt(i);
			switch (ch) {
			case '[':
				stack.push(i);
				pairs[i] = new Pair(i, -1);
				break;
			case ']':
				int tmp = stack.pop();	// 짝이 맞는 여는 괄호의 인덱스
				pairs[i] = new Pair(tmp, i);
				pairs[tmp].right = i;
				break;
			}
			++i;
		}
	}
	
	static class Pair {
		int left, right;
		public Pair (int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

}
