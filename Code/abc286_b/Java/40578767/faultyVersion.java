import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // System.out.println(N);
        String S = sc.nextLine();
        System.out.println(S.replace("na", "nya"));
    }
}