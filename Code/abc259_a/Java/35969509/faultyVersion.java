import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int t = sc.nextInt();
        int d = sc.nextInt();

        if ((m < n) && (m > x))
            System.out.println(t);
            

        if (m < x) {
            System.out.println(t - (d * (x - m)));
        }

    }
}