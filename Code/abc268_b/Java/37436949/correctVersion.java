import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String T = sc.next();
        if(T.indexOf(S) == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
