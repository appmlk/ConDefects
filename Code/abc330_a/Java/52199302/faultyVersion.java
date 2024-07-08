import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        int N = scanner.nextInt();
        int L = scanner.nextInt();

        int cnt = 0;
        
        for (int i = 0; i < N; i++) {
            int score = scanner.nextInt();
            if (score > L){
                cnt++;
            }
        }

        System.out.println(cnt);

        // Scannerを閉じる
        scanner.close();
    }
}
