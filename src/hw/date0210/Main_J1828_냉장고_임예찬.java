package hw.date0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1828_냉장고_임예찬 {
	
	static int[] tempMin;
	static int[] tempMax;
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
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
		int minIdx = 0;
		for (int i = 0; i < N; i++) {
			
		}
		
		
		int size = maxTemp - minTemp;
		
		arr = new int[size];
		
		for (int i = 0; i < N; i++) {
			int tempSize = tempMax[i] - tempMin[i];
			for (int j = 0; j <= tempSize; j++) {
				
			}
		}
		
	}

}
