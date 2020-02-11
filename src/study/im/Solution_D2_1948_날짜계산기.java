package study.im;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_D2_1948_날짜계산기 {
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
         
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
 
            st = new StringTokenizer(br.readLine());
            int firstM = Integer.parseInt(st.nextToken());
            int firstD = Integer.parseInt(st.nextToken());
            int secondM = Integer.parseInt(st.nextToken());
            int secondD = Integer.parseInt(st.nextToken());
             
            if (firstM == secondM) answer = (secondD - firstD + 1);
            else {
                answer += getDate(firstM) - firstD + 1;
                for (int i = firstM + 1; i < secondM; i++) {
                    answer += getDate(i);
                }
                answer += secondD;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
     
    public static int getDate(int month) {
        switch (month) {
        case 4: case 6: case 9: case 11:
            return 30;
        case 2:
            return 28;
        default:
            return 31; 
        }
    }
}