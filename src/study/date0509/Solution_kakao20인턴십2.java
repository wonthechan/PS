package study.date0509;

import java.util.HashMap;
import java.util.Map;

public class Solution_kakao20인턴십2 {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		String hand = "right";
		System.out.println(solution(numbers, hand));
	}
	
	
	static public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        // 왼쪽 열이나 오른쪽 열의 숫자는 항상 왼손이나 오른손을 써야 하고
        // 다만 가운데 열은 현재 왼손 엄지와 오른손 엄지의 거리를 각각 계산하여 짧은 손을 선택한다
        // 두 손의 거리가 같은 경우에는 왼/오른잡이 손 여부로 판가름한다.
        boolean isRightHanded = hand.equals("right") ? true : false;
        
        // 현재 왼손/오른손 엄지의 위치를 기억해야 한다.
        Pos leftHand = new Pos(3, 0);
        Pos rightHand = new Pos(3, 2);
        
        // 숫자별 좌표를 미리 지정해둘까. (맵으로)
        Map<Character, Pos> posMap = new HashMap<>();
        posMap.put('1', new Pos(0,0));
        posMap.put('2', new Pos(0,1));
        posMap.put('3', new Pos(0,2));
        posMap.put('4', new Pos(1,0));
        posMap.put('5', new Pos(1,1));
        posMap.put('6', new Pos(1,2));
        posMap.put('7', new Pos(2,0));
        posMap.put('8', new Pos(2,1));
        posMap.put('9', new Pos(2,2));
        posMap.put('*', new Pos(3,0));
        posMap.put('0', new Pos(3,1));
        posMap.put('#', new Pos(3,2));
        
        // 숫자 차례로 입력 받으면서 시뮬 시작
        for (int num : numbers) {
        	switch (num) {
			case 1: case 4: case 7: // 항상 왼손으로 누르는 경우
				leftHand = posMap.get((num + "").charAt(0));
				sb.append("L");
			break;
			case 3: case 6: case 9: // 항상 오른손으로 누르는 경우
				rightHand = posMap.get((num + "").charAt(0));
				sb.append("R");
			break;
			default:				// 가운데 열 숫자를 누르는 경우 (거리 계산 필요)
				Pos pos = posMap.get((num + "").charAt(0));
				int rDist = Math.abs(rightHand.y - pos.y) + Math.abs(rightHand.x - pos.x);
				int lDist = Math.abs(leftHand.y - pos.y) + Math.abs(leftHand.x - pos.x);
				if (rDist < lDist) { // 오른손이 더 가까운 경우
					rightHand = pos;
					sb.append("R");
				} else if (lDist < rDist){
					leftHand = pos;
					sb.append("L");
				} else {			// 거리가 같은 경우
					if (isRightHanded) {
						rightHand = pos;
						sb.append("R");
					} else {
						leftHand = pos;
						sb.append("L");
					}
				}
				break;
			}
        }
        return sb.toString();
    }

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
