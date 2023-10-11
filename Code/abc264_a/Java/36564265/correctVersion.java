import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "atcoder";
        int L = sc.nextInt();
        int R = sc.nextInt();
        sc.close();
        String ans = str.substring(L-1, R);
        System.out.println(ans);
    }
}