import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read input
        int N = scanner.nextInt();
        
        // Initialize result string
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i <= N; i++) {
            boolean found = false;
            for (int j = 1; j <= 9; j++) {
                if (N % j == 0 && i % (N / j) == 0) {
                    result.append(j);
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.append("-");
            }
        }
        
        // Print the result
        System.out.println(result.toString());
    }
}