package hw.date0210;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_J1828_냉장고_임예찬 {
	
	static int[] tempMin;
	static int[] tempMax;
	static int N;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_j1828.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		tempMin = new int[N];
		tempMax = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			tempMin[i] = Integer.parseInt(st.nextToken());
			tempMax[i] = Integer.parseInt(st.nextToken());
		}
		
		// 선택정렬 (최고온드를 기준으로 오름차순 정렬)
		for (int i = 0; i < N-1; i++) {
			int minIdx = i;
			int minValue = tempMax[i];
			for (int j = i+1; j < N; j++) {
				if (tempMax[j] < minValue) {
					minIdx = j;
					minValue = tempMax[j];
				}
			}
			if (minIdx == i) continue; // 자신보다 더 작은 원소를 발견하지 못한 경우
			// swap
			int minX = tempMin[minIdx];
			int minY = tempMax[minIdx];
			tempMin[minIdx] = tempMin[i];
			tempMax[minIdx] = tempMax[i];
			tempMin[i] = minX;
			tempMax[i] = minY;
		}
		
//		for (int t : tempMin) System.out.printf("%4d ", t);	// debug print
//		System.out.println();
//		for (int t : tempMax) System.out.printf("%4d ", t);
//		System.out.println();
		
		int cnt = 1;					// 냉장고 갯수
		int prevMax = tempMax[0];		// 초기값 설정
		for (int i = 1; i < N; i++) {
			if (tempMin[i] > prevMax) { // 범위가 겹치지 않는 경우 냉장고가 하나 더 필요함
				cnt++;
				prevMax = tempMax[i];
			}
		}
		System.out.println(cnt);
	}
}
