import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s = sc.next();

        String ws1 = "";
        String ws2 = "";
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                ws1 = s.substring(i, j);
                sb.append(ws1);
                ws2 = sb.reverse().toString();

                if (ws1.equals(ws2)) {
                    ans = Math.max(ans, ws1.length());
                }

                sb.setLength(0);
            }
        }

        System.out.println(ans);

        sc.close();
    }
}
