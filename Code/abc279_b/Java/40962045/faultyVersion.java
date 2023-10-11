import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next(), t = sc.next();
        sc.close();

        if (s.equals(t)) {
            System.out.println("Yes");
            System.exit(0);
        }

        for (int i = 0; i < s.length()-t.length(); i++) {
            String tmp = s.substring(i, i+t.length());
            if (tmp.equals(t)) {
                System.out.println("Yes");
                System.exit(0);
            } else {
                continue;
            }
        }
        System.out.println("No");
    }
}