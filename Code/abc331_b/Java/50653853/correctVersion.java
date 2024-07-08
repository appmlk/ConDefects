import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        int M = scanner.nextInt();
        int L = scanner.nextInt();
        int MinCost = Integer.MAX_VALUE;

        for (int Scount = 0; Scount <= (N + 5) / 6; Scount++) {
            for (int Mcount = 0; Mcount <= (N - 6 * Scount + 7) / 8; Mcount++) {
                for (int Lcount = 0; Lcount <= (N - 6 * Scount - 8 * Mcount + 11) / 12; Lcount++){
                    if (6 * Scount + 8 * Mcount + 12 * Lcount >= N) {
                        int Cost = S * Scount + M * Mcount + L * Lcount;
                        if (Cost < MinCost) {
                            MinCost = Cost;
                        }
                    }
                }
            }
        }
        System.out.println(MinCost);
    }
}