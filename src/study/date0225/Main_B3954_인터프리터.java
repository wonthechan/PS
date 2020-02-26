package study.date0225;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B3954_인터프리터 {

	static int[] mem, loop;
	static int memLen, codeLen, inputLen, p, inputPointer;
	static int openPos, closePos;
	static String code, input;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b3954.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
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
			loop = new int[codeLen];
			
			// 코드 해석
			int i = 0;
			char temp;
			int pCache = 0;
			int vChache = 0;
			boolean flag = false;
		L1:	while (i < codeLen) {
				char ch = code.charAt(i);
				switch (ch) {
				case '-':
					--mem[p];
					break;
				case '+':
					++mem[p];
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
						// Jump to ]
						// 다음 짝이 맞는 괄호를 찾아야 한다.
						int j = i;
						int cnt = 1;
						while (j++ < codeLen) {
							temp = code.charAt(j);
							switch (temp) {
							case '[': ++cnt; break;
							case ']': --cnt; break;
							}
							if (cnt == 0) {
								i = j; break;// jump
							}
						}
					}
					break;
				case ']':
					if (mem[p] != 0) {
						if (loop[i] == 0) { //루프가 처음 도는 경우
							loop[i] = mem[p];
						} else {
							if ((loop[i] > 0 && mem[p] > loop[i]) || (loop[i] < 0 && mem[p] < loop[i]) || (mem[p] == loop[i])) {
								closePos = i;
								flag = true; // 무한 루프
							}
						}
						// Jump to [
						int j = i;
						int cnt = 1;
						while (j-- >= 0) {
							temp = code.charAt(j);
							switch (temp) {
							case ']': ++cnt; break;
							case '[': --cnt; break;
							}
							if (cnt == 0) {
								if (flag) {
									openPos = j; break L1;
								}
								i = j; break;// jump
							}
						}
					}
					break;
				case '.':
//					System.out.println(mem[p]);
					break;
				case ',':
					mem[p] = input.charAt(inputPointer++);
					break;
				}
				++i;
			}
			
			if (flag) {
				System.out.println("Loops " + openPos + " " + closePos);
			} else {
				System.out.println("Terminates");
			}
		}
	}

}
