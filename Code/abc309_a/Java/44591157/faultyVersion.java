import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        if ((!(A == 3 || A == 6) && B == A + 1) || B == A + 3) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}