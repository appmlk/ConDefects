import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
            if ( (a>b && a-b <=3 ) || ( a<b && b-a <= 2)) System.out.println("Yes");
            else System.out.println("No");
    }
}
