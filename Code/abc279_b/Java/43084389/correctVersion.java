import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String s1 = s.next();
        String s2 = s.next();

        int n = s1.length(), m = s2.length();

        if(n < m) {
            System.out.println("No");
            return;
        }

        for(int i = 0; i < n ; i++) {
            if(i + m > n) continue;
            String cmp = s1.substring(i, i + m);
            if(cmp.equals(s2)) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}