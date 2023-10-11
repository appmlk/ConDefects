import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static String solve(String s, int n) {
        if (n == 1) return s;
        if(s.lastIndexOf('A')>s.indexOf('B')) return "A";
        else return "B";
    }

    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var out = new PrintWriter(System.out, false); //true for Interactive
        try {
            for (int i = sc.nextInt(); i > 0; i--) {
                int n = sc.nextInt();
                String s = sc.next();
                out.println(solve(s, n));
            }
            out.flush();
        } catch (Exception e) {
            out.flush();
            throw e;
        }
    }
}
