import java.util.*;
import java.math.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = Long.parseLong(sc.next());

        if (Math.pow(-2, 32) <= N && N < Math.pow(2, 32)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}