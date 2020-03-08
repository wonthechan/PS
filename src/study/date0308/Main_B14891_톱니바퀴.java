package study.date0308;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B14891_톱니바퀴 {

	static ArrayList<Character>[] arr = new ArrayList[4];	// 리스트로 바퀴 관리
	static int answer = 0;
	static int[] isRotate = new int[4];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b14891.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 4; i++) {
			arr[i] = new ArrayList<>();
			for (char c : br.readLine().toCharArray()) {
				arr[i].add(c);
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotate(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
		}
		
		// 점수 계산
		for (int i = 0; i < 4; i++) {
			if (arr[i].get(0) == '1') answer += Math.pow(2, i);
		}
		System.out.println(answer);
	}
	
	private static void rotate(int k, int way) {
		Arrays.fill(isRotate, 0);
		// 일단 k번째 톱니바퀴를 움직이고
		isRotate[k] = way;
		// 초기 상태를 보고 회전할 수 있는지 미리 판단
		int curWay = way;
		for (int i = k-1; i >= 0; i--) {
			if (arr[i].get(2) != arr[i+1].get(6)) {
				curWay *= -1;
				isRotate[i] = curWay;	// 방향도 함께 저장
			} else {
				break;
			}
		}
		curWay = way;
		for (int i = k+1; i < 4; i++) {
			if (arr[i].get(6) != arr[i-1].get(2)) {
				curWay *= -1;
				isRotate[i] = curWay;
			} else {
				break;
			}
		}
		// 앞서 판단한 결과로 나머지 바퀴들을 움직인다.
		for (int i = 0; i < 4; i++) {
			switch (isRotate[i]) {
			case 1:		// 오른쪽으로 회전
				arr[i].add(0, arr[i].remove(7));
				break;
			case -1:	// 왼쪽으로 회전
				arr[i].add(arr[i].remove(0));
				break;
			}
		}
	}
}
