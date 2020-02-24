package study.dfs_bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B10828_스택 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input_10828.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		Stack<Integer> stack = new Stack<Integer>();

		int commandCase = Integer.parseInt(br.readLine());

		for (int i = 0; i < commandCase; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("push")) {
				stack.push(Integer.parseInt(st.nextToken()));
			} else if (command.equals("pop")) {
				if (stack.empty())
					sb.append("-1");
				else
					sb.append(stack.pop());
				sb.append("\n");
			} else if (command.equals("size")) {
				sb.append(stack.size()+ "\n");
			} else if (command.equals("empty")) {
				if (stack.empty())
					sb.append("1");
				else
					sb.append("0");
				sb.append("\n");	
			} else if (command.equals("top")) {
				if (stack.empty())
					sb.append("-1");
				else
					sb.append(stack.peek());
				sb.append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}
