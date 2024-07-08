import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int L = scanner.nextInt();
        int gokaku = 0;

        for (int i = 0; i<N; i++) {
            int A = scanner.nextInt();
            if(A >= 60){
                gokaku++;
            }
        }

        System.out.println(gokaku);
    }
}
