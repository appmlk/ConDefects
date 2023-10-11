import java.util.*;

class Main {
    public static void main(String[] ktr) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        System.out.println(Math.abs(a - b) == 1 || Math.abs(a - b) == 9 ? "Yes" : "No");
    }
}