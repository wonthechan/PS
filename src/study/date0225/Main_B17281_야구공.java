package study.date0225;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 모든 경우의 타순을 정하기 위해 순열을 생성해야 할 것 같음.
 * 1번선수는 항상 4번타자로 고정되어 있으므로 이를 제외한 8명의 순열을 구성해보고
 * 넥퍼로 한개씩 땡기면서 그 때 모든 이닝을 돌리면서 점수를 계산해보고 이의 최댓값을 구해보자.
 * 순서는 유지가 되지만 각 이닝별로 얻을 수 있는 점수가 다르므로 차등해서 적용한다.
 */
public class Main_B17281_야구공 {

	static int N;
	static int[][] scorePerInit;
	static boolean[] roo = new boolean[3];
	static int answer = -987654321;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_b17281.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());

		// 이닝 별 선수들의 성적 입력 (인덱스는 1부터 시작)
		scorePerInit = new int[N][10];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				scorePerInit[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] hitOrder = {2, 3, 4, 5, 6, 7, 8, 9}; // 1번타자를 제외한 8명의 타순 배열 
		
		do {
			// 1번 선수를 포함한 타순 구성
			int[] finalOrder = new int[9];
			for (int i = 0; i < 3; i++) finalOrder[i] = hitOrder[i];
			finalOrder[3] = 1;
			for (int i = 3; i < 8; i++)	finalOrder[i+1] = hitOrder[i];
			
			// 점수 초기화
			int gameScore = 0;
			
			// 타순 인덱스
			int strikeIdx = 0;
			
			// 게임 시작 => 이닝 순회 시작
			for (int init = 0; init < N; init++) {
				int initScore = 0;	// 이번 이닝에 낸 점수
				// 매 이닝 마다 주자의 진루 상황 초기화
				Arrays.fill(roo, false);
				// 매 이닝 마다 아웃 카운트 초기화
				int outCnt = 0;
				// 3아웃까지는 계속해서 이닝 진행
				while (outCnt < 3) {
					// 이번 차례 타자 등판과 이번 이닝 성적 확인
					int striker = finalOrder[strikeIdx++];
					strikeIdx %= 9;
					int score = scorePerInit[init][striker];
					
					// 성적별로 분기
					switch (score) {
					case 0:
						++outCnt;
						break;
					case 1:	// 안타(1루타)
						if (roo[2]) ++initScore; roo[2] = false;	// 3루 주자는 홈인
						if (roo[1]) roo[2] = true; roo[1] = false;	// 2루 주자는 3루로 이동
						if (roo[0]) roo[1] = true; roo[0] = false;	// 1루 주자는 2루로 이동
						roo[0] = true;	// 타자는 1루로 이동
						break;
					case 2:	// 2루타
						// 2루나 3루주자는 무조건 홈인한다.
						if (roo[2]) ++initScore; roo[2] = false;
						if (roo[1]) ++initScore; roo[1] = false;
						if (roo[0]) roo[2] = true; roo[0] = false;	// 1루 주자는 3루로 이동
						roo[1] = true;	// 타자도 2루로 이동
						break;
					case 3:
						// 주자는 모두 득점한다.
						if (roo[2]) ++initScore; roo[2] = false;
						if (roo[1]) ++initScore; roo[1] = false;
						if (roo[0]) ++initScore; roo[0] = false;
						roo[2] = true;	// 타자도 3루로 이동
						break;
					case 4:	// 홈런
						if (roo[2]) ++initScore; roo[2] = false;
						if (roo[1]) ++initScore; roo[1] = false;
						if (roo[0]) ++initScore; roo[0] = false;
						++initScore; // 타자도 홈인
						break;
					}
				}
				gameScore += initScore;
			}
			// 게임이 끝나면 최고점 업데이트
			answer = Math.max(answer, gameScore);
		} while(np(hitOrder));
		
		System.out.println(answer);
	}
	
	private static boolean np(int[] arr) {
		int N = arr.length;
		//
		int i = N - 1;
		while (i > 0 && arr[i-1] >= arr[i]) --i;
		if (i == 0) return false;
		//
		int j = N - 1;
		while (arr[i-1] >= arr[j]) --j;
		//
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		//
		j = N - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			++i;--j; // 이조건 빠트리지 않기!!
		}
		return true;
	}
}
