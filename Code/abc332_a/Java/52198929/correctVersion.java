import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        int N = scanner.nextInt();
        int S = scanner.nextInt();
        int K = scanner.nextInt();

        int sum = 0;

        for (int i = 0; i < N; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            sum += a * b;
        }
        
        if (sum < S){
            sum += K;
        }

        System.out.print(sum);

        // Scannerを閉じる
        scanner.close();
    }
}
