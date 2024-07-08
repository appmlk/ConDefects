import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                StringBuffer outLine = new StringBuffer();
                for (int j = 1; j <= n; j++) {                    
                    outLine.append((sc.nextInt() == 1) ? j + " " : "");
                }
                System.err.println(outLine);
            }
        }
    }
}