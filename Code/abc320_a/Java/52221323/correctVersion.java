import java.util.Date;
import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        double ans = 0;
        ans += Math.pow(A, B);
        ans += Math.pow(B, A);
        System.out.println((int)ans);
    }
}