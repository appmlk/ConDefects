import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> str = List.of("ACE", "BDF", "CEG", "DFA", "FAC", "GBD");
        String s = sc.nextLine();
        String ans = "No";
        if (str.contains(s)) {
            ans = "Yes";
        }
        System.out.println(ans);
    }
}
