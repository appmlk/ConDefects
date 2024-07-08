
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String s = sc.next();
        String t = sc.next();

        int firstA = n;
        int lastB = -1;
        for(int i = 0; i < n; i++) {
            char c = t.charAt(i);

            if(c == 'A') {
                firstA = Math.min(firstA, i);
            }
            else {
                lastB = Math.max(lastB, i);
            }
        }

        Deque<Integer> queA = new ArrayDeque<Integer>();
        Deque<Integer> queB = new ArrayDeque<Integer>();
        int stockA = 0;
        int stockB = 0;
        int resultA = 0;
        int resultB = 0;
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'B' && t.charAt(i) == 'A') {
                if(i >= lastB) {
//                    System.out.println("i = " + i);
                    System.out.println(-1);
                    return;
                }
                else {
                    if(stockA > 0) {
                        stockA--;
                    }
                    else {
                        result++;
                        stockB++;
                    }
//                    resultA++;
//                    stockB++;
                }
            }
            if(s.charAt(i) == 'A' && t.charAt(i) == 'B') {
                if(i <= firstA) {
                    System.out.println(-1);
                    return;
                }
                else {
                    if(stockB > 0) {
                        stockB--;
                    }
                    else {
                        result++;
//                        stockA++;
                    }
//                    resultB++;
                }
            }
        }

        System.out.println(result);
    }
}
