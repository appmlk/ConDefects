import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        String input = sc.next();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            list.add(String.valueOf(c));
        }

        if (list.contains("o") && !list.contains("x")) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}
