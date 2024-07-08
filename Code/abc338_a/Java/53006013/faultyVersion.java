import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String s = sc.next();
            if (!s.substring(0, 1).matches("[A-Z]")) {
                System.out.println("No");
                return;
            }
            for (int i = 1; i < s.length() - 1; i++) {
                if (!s.substring(i, i + 1).matches("[a-z]")) {
                    System.out.println("No");
                    return;
                }
            }
            System.out.println("Yes");
        }
    }
}