package study.date0315;

import java.util.Arrays;

// 구현하기 너무 까다로웠던 시뮬레이션 문제
// 참고: https://hooongs.tistory.com/30
public class Solution_kakao20공채5_기둥과보설치 {

	static int[][] mapKi, mapBo;
	static int mapSize, cnt;
	public static void main(String[] args) throws Exception {
		int n = 5;
		int[][] build_frame = {
				{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},
				{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}
		};
		int[][] build_frame2 = {
				{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},
				{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0}
				,{1,1,1,0}
				,{2,2,0,1}
		};
		for (int[] a : solution(n, build_frame2)) System.out.println(Arrays.toString(a));
	}
	
	public static int[][] solution(int n, int[][] build_frame) {
        int[][] answer = null;
        mapSize = n;
        cnt = 0;
        mapKi = new int[n+1][n+1];	// 기둥 배열
        mapBo = new int[n+1][n+1];	// 보 배열
        // 명령어를 하나씩 읽는다.
        for (int[] cmd : build_frame) {
        	if (cmd[3] == 1) {
        		if (cmd[2] == 0) {		// 기둥 설치 명령
        			buildKi(cmd[1], cmd[0]);
        		} else {				// 보 설치 명령
        			buildBo(cmd[1], cmd[0]);
        		}
        	} else {
        		if (cmd[2] == 0) {		// 기둥 삭제 명령
        			removeKi(cmd[1], cmd[0]);
        		} else {				// 보 삭제 명령
        			removeBo(cmd[1], cmd[0]);
        		}
        	}
        }
        answer = getResult();
        return answer;
    }
	
	/** 주어진 좌표에 실재로 기둥이 존재하는지 여부를 반환 */
	private static boolean isExistKi(int y, int x) {
		// 경계 및 실제 존재 여부 체크
		if (y < 0 || x < 0 || y > mapSize || x > mapSize || mapKi[y][x] < 1) return false;
		return true;
	}
	
	/** 주어진 좌표에 실재로 보가 존재하는지 여부를 반환 */
	private static boolean isExistBo(int y, int x) {
		// 경계 및 실제 존재 여부 체크
		if (y < 0 || x < 0 || y > mapSize || x > mapSize || mapBo[y][x] < 1) return false;
		return true;
	}
	
	/* 해당 위치에 있는 기둥이 유효한지 검사  */
	private static boolean isValidKi(int y, int x) {
		// (조건은 설치 시 적용된 조건과 동일)
		if (y == 0 || isExistKi(y-1, x) || isExistBo(y, x-1) || isExistBo(y, x)) {
			return true;
		}
		return false;
	}

	/* 해당 위치에 있는 보가 유효한지 검사 */
	private static boolean isValidBo(int y, int x) {
		// (조건은 설치 시 적용된 조건과 동일)
		if (isExistKi(y-1, x) || isExistKi(y-1, x+1) || (isExistBo(y, x-1) && isExistBo(y, x+1))) {
			return true;
		}
		return false;
	}

	/** 기둥 설치 메소드 */
	private static void buildKi(int y, int x) {
		// (기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나(!!!),
		// 또는 다른 기둥 위에 있어야 합니다.) => 교차점만 체크하자
		if (y == 0 || isExistKi(y-1, x) || isExistBo(y, x-1) || isExistBo(y, x)) {
			mapKi[y][x] = 1;
			++cnt;
		}
	}
	
	/** 보 설치 메소드 */
	private static void buildBo(int y, int x) {
		// 보는 한쪽 끝 부분이 기둥 위에 있거나, 
		// 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
		if (isExistKi(y-1, x) || isExistKi(y-1, x+1) || (isExistBo(y, x-1) && isExistBo(y, x+1))) {
			mapBo[y][x] = 1;
			++cnt;
		}
	}
	
	/** 기둥 삭제 메소드 */
	private static void removeKi(int y, int x) {
		// 일단 해당 기둥을 삭제해보고 기둥 주변 요소가 여전히 유효한지 확인한다.
		// 내 위에 기둥이 있을 수도 있고 양쪽으로 나한테 기대던 보가 있을 수 있음 => 모두 확인
		// 확인해서 하나라도 유효하지 않게 되면 삭제 명령 무효
		mapKi[y][x] = 0;
		if (isExistKi(y+1, x) && !isValidKi(y+1, x)) {
			mapKi[y][x] = 1;	// 롤백
			return;
		}
		if (isExistBo(y+1, x-1) && !isValidBo(y+1, x-1)) {
			mapKi[y][x] = 1;	// 롤백
			return;
		}
		if (isExistBo(y+1, x) && !isValidBo(y+1, x)) {
			mapKi[y][x] = 1;	// 롤백
			return;
		}
		--cnt;
	}
	
	/** 보 삭제 메소드 */
	private static void removeBo(int y, int x) {
		// 일단 해당 보를 삭제해보고 주변 요소가 여전히 유효한지 확인한다.
		// 내 양쪽으로 다리가 올라갔을 수 있고 다른 보가 연결되어 있었을 수도 있다.
		// 확인해서 하나라도 유효하지 않게 되면 삭제 명령 무효
		mapBo[y][x] = 0;
		if (isExistKi(y, x) && !isValidKi(y, x)) {
			mapBo[y][x] = 1;
			return;
		}
		if (isExistKi(y, x+1) && !isValidKi(y, x+1)) {
			mapBo[y][x] = 1;
			return;
		}
		if (isExistBo(y, x-1) && !isValidBo(y, x-1)) {
			mapBo[y][x] = 1;
			return;
		}
		if (isExistBo(y, x+1) && !isValidBo(y, x+1)) {
			mapBo[y][x] = 1;
			return;
		}
		--cnt;
	}

	/** 현재 살아 있는 기둥과 보를 리스트에 순서대로 저장하고 answer 배열에 옳겨 담는다. */
	private static int[][] getResult() {
		int idx = 0;
		int[][] answer = new int[cnt][3];
		for (int j = 0; j <= mapSize; j++) {
			for (int i = 0; i <= mapSize; i++) {
				if (mapKi[i][j] > 0) {
					answer[idx++] = new int[] {j, i, 0};
				}
				if (mapBo[i][j] > 0) {
					answer[idx++] = new int[] {j, i, 1};
				}
			}
		}
		return answer;
	}
}
