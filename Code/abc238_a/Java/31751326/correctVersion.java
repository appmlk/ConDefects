

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(n > 4  || n == 1 ? "Yes" : "No");
        
    }
}
