package study.date0405;

public class Solution_line20공채2 {
	public static void main(String[] args) throws Exception {
		String answer_sheet = "24551";
		String[] sheets = {
				"24553", "24553", "24553", "24553"
		};
		System.out.println(solution(answer_sheet, sheets));
	}

    public static int solution(String answer_sheet, String[] sheets) {
        int answer = -1;
        
        int lenP = answer_sheet.length();	// 문항 개수
        int lenA = sheets.length;			// 응시자 수
        // 완탐(조합)
        char answerA, answerB;
        for (int i = 0; i < lenA - 1; i++) {
        	for (int j = i + 1; j < lenA; j++) {
        		// 의심문항 확인
        		int cntDoubt = 0;
        		int cntContinue = 0;
        		int cntMax = 0;
        		for (int k = 0; k < lenP; k++) {
        			answerA = sheets[i].charAt(k);
        			answerB = sheets[j].charAt(k);
        			// 같은 선택지를 골랐으나 오답인 문항만 체크
        			if (answerA == answerB && answerA != answer_sheet.charAt(k)) {
        				++cntDoubt;	// 의심 문항 수 카운트
        				++cntContinue;
        				cntMax = Math.max(cntMax, cntContinue);
        			} else {
        				cntContinue = 0;
        			}
        		}
        		answer = (int) Math.max(answer, cntDoubt + Math.pow(cntMax, 2));
        	}
        }
        return answer;
    }
}
