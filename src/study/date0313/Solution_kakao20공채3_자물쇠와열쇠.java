package study.date0313;

public class Solution_kakao20공채3_자물쇠와열쇠 {
	
	static int N, M, T;
	public static void main(String[] args) throws Exception {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		System.out.println(solution(key, lock));
	}

	public static boolean solution(int[][] key, int[][] lock) {
//        for(int[] a : key) System.out.println(Arrays.toString(a));
//        for(int[] a : lock) System.out.println(Arrays.toString(a));
        N = lock.length;
        M = key.length;
        // key 맵을 TxT 맵으로 확장
        T = N + 2 * (M-1);	// 새로운 맵의 사이즈 T 계산 
        int[][] tMap = new int[T][T];
        
        for (int r = 0; r < 4; r++) {
        	// lock맵을 시계방향으로 90도 회전
        	int[][] rotated = new int[M][M];
        	for (int i = 0; i < M; i++) {
        		for (int j = 0; j < M; j++) {
        			rotated[j][M-1-i] = key[i][j];
        		}
        	}
        	key = rotated;
        	for (int i = 0; i <= T - M; i++) {
        		for (int j = 0; j <= T - M; j++) {
        			// key 모양 옳기기
        			for (int ki = 0; ki < M; ki++) {
        				for (int kj = 0; kj < M; kj++) {
        					tMap[i+ki][j+kj] = key[ki][kj];
        				}
        			}
        			// 자물쇠와 비교
        			if(compare(tMap, lock)) return true;
        			// 원상태로 복원
        			for (int ki = 0; ki < M; ki++) {
        				for (int kj = 0; kj < M; kj++) {
        					tMap[i+ki][j+kj] = 0;
        				}
        			}
        		}
        	}
        }
        return false;
    }

	// 자물쇠와 열쇠가 맞는지 확인
	private static boolean compare(int[][] tMap, int[][] lock) {
		int gap = M - 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == tMap[i+gap][j+gap]) return false;
			}
		}
		return true;
	}
}
