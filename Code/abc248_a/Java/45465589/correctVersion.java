
// 문제 설명
// 숫자로 구성된 길이가 정확히 9인 문자열 S가 주어진다. 0부터 9까지의 모든 숫자가 S에 정확히 한 번 나타난다.
// S에서 누락된 유일한 숫자를 인쇄하라.

// 제약
// S는 숫자로 구성된 길이 9의 문자열이다.
// S의 모든 문자는 서로 다르다.

// 입력
// 입력은 다음 형식의 표준입력으로 제공된다.

// 출력
// S에서 누락된 유일한 숫자를 인쇄한다.
import java.util.*;

public class Main {
    public static void main (String[]args){
        Scanner scn = new Scanner(System.in);

        String S = scn.nextLine(); // 길이 9
        String checkS = "0123456789"; // 길이 10

        int Array [] = new int [S.length()];
        boolean checkArray [] = new boolean[checkS.length()];

        for ( int x = 0 ; x < S.length() ; x++){
            Array[x] = S.charAt(x);             // Array[]에 char 0 ~9 까지 넣음(하나없음)
        }
        for ( int x = 0 ; x < 9 ; x++){
            checkArray[Array[x]-'0'] = true;
        }
        for ( int x = 0 ; x <= 9 ; x++){
            if ( checkArray[x] == false){
                System.out.println(x);
                break;
            }
        }
        // for ( int x = 0 ; x < checkS.length(); x++){
        //     if ( checkArray[x] = false){
        //         System.out.println(x);
        //     }
        // }

        // for ( int k : Array){
        //     System.out.println(k);
        // }

        // for ( boolean k : checkArray){
        //     System.out.println(k);
        // }
    }
}