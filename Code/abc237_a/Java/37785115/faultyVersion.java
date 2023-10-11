import java.util.*;

class Main {
    public static void main(String[] ktr) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println(Integer.MIN_VALUE + 1 <= n && n < Integer.MAX_VALUE ? "Yes" : "No");

        sc.close();
    }
}