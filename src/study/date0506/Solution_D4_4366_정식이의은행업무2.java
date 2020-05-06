package study.date0506;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_D4_4366_정식이의은행업무2 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/s4366.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
	L1:	for (int tc = 1; tc <= T; tc++) {
			String jinsu2Str = br.readLine();
			String jinsu3Str = br.readLine();
			
			long jinsu2 = Integer.parseInt(jinsu2Str, 2);	// 2진수는 비트 연산을 위해 long형으로 변환해둠.
			
			// 제한 조건이 (3 ≤ 2진수, 3진수의 자릿수 < 40) 이므로 Long 형으로 관리 가능
			List<Long> list = new ArrayList<>();
			for (int k = 0; k < jinsu3Str.length(); k++) {
				list.add(Long.parseLong(jinsu3Str.substring(0, k) + ((jinsu3Str.charAt(k) - '0' + 1) % 3) + jinsu3Str.substring(k+1), 3));
				list.add(Long.parseLong(jinsu3Str.substring(0, k) + ((jinsu3Str.charAt(k) - '0' + 2) % 3) + jinsu3Str.substring(k+1), 3));
			}
			
			Collections.sort(list);	// 이분탐색하기 위해 오름차순으로 정렬.
			
			// 완탐 (XOR)
			for (int i = 0; i < jinsu2Str.length(); i++) {
				long temp = jinsu2 ^ (1 << i);
				// 3진수 후보 리스트에 있는지 확인 (contains를 쓰지 않고 별도로 이분탐색을 통해 시간복잡도를 줄일 수 도 있음)
				// Collections.binarySearch() API 활용
				if (Collections.binarySearch(list, temp) > -1) {	
					sb.append("#").append(tc).append(" ").append(temp).append("\n");
					continue L1;
				}
			}
		}
		System.out.print(sb.toString());
	}
}
