import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        String pi = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
        for (int i = 0; i < N + 2; i++) {
            System.out.print(pi.charAt(i));
        }
    }
}
