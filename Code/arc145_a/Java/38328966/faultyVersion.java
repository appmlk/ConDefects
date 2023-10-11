
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = Integer.valueOf(scan.next());
        String S = scan.next();
        if (N <= 2) {
            if (scan.equals("BA") || scan.equals("AB")) {
                System.out.println("No");

            } else {
                System.out.println("Yes");
            }
            return;
        }
        if (S.charAt(0) == 'A' && S.charAt(N - 1) == 'B') {
            System.out.println("No");
            return;
        }
        System.out.println("Yes");
    }
}