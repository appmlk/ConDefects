import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double X = sc.nextDouble();
        int K = sc.nextInt();
        for(int i = 0; i <= K; i++) {
            X = (Math.round(X / Math.pow(10, i))) * Math.pow(10, i);
        }
        System.out.println((long)X);
    }
}
