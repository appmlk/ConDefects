import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, i=1;
        String s;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        s = sc.next();
        StringBuilder sb = new StringBuilder(s);

        while (i < sb.length()) {
            if (sb.charAt(i) == 'A' && sb.charAt(i-1) == 'B') {
                sb = sb.replace(i-1, i+1, "AB");
            }

            if (sb.charAt(i) == 'B' && sb.charAt(i-1) == 'B') {
                sb = sb.replace(i-1, i+1, "A");
            }
            i += 1;
        }

        System.out.print(sb);
    }
}