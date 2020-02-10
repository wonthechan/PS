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
	static int[] arr;
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
		
		int minTemp = tempMin[0];
		for (int t : tempMin) minTemp = Math.min(minTemp, t);
		
		int maxTemp = tempMax[0];
		for (int t : tempMin) maxTemp = Math.min(maxTemp, t);
		
		// 선택정렬 (최저온드 정렬)
		for (int i = 0; i < N-1; i++) {
			int minIdx = i;
			int minValue = tempMin[i];
			for (int j = i+1; j < N; j++) {
				if (tempMin[j] < minValue) {
					minIdx = j;
					minValue = tempMin[j];
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

		
		System.out.println(Arrays.toString(tempMin));
		System.out.println(Arrays.toString(tempMax));
		
		int cnt = 1;
		
		int curMax = tempMax[0];
		for (int i = 1; i < N; i++) {
			
			int nextMin = tempMin[i];
			int nextMax = tempMax[i];
			
			if (nextMin > curMax) { // 범위가 겹치지 않는 경우 냉장고가 하나 더 필요함
				System.out.printf("(%d ~ %d)\n", nextMin, nextMax);
				cnt++;
				curMax = nextMax;
			}
		}
		
		System.out.println(cnt);
		
//		int size = maxTemp - minTemp;
//		
//		arr = new int[size];
//		
//		for (int i = 0; i < N; i++) {
//			int tempSize = tempMax[i] - tempMin[i];
//			for (int j = 0; j <= tempSize; j++) {
//				
//			}
//		}
		
	}

}
