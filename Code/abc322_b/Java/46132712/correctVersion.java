import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String s = sc.next();
        String t = sc.next();

        System.out.println(solve(s, t));
    }

    private static int solve(String s, String t) {
        boolean isPrefix = t.startsWith(s);
        boolean isSuffix = t.endsWith(s);
        if (isPrefix) {
            return isSuffix ? 0 : 1;
        } else {
            return isSuffix ? 2 : 3;
        }
    }
}
