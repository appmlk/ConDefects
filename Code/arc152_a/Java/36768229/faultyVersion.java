import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int L = s.nextInt();
        int last2Idx = -1;
        for (int i=1;i<N + 1;++i) {
            int x = s.nextInt();
            if (x == 2) {
                last2Idx = i;
            }
        }

        if (last2Idx != -1 && 2 * last2Idx >= N + 1) System.out.println("No");
        else System.out.println("Yes");
    }
}