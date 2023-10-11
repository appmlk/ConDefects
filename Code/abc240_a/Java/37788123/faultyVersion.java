import java.util.*;

class Main {
    public static void main(String[] ktr) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        System.out.println((a % 2 + b % 2) % 2 == 1 ? "Yes" : "No");
    }
}