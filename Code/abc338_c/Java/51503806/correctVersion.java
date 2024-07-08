
import java.util.Scanner;

public class Main {
    static int number;
    static int[] stock, food1, food2;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            number = sc.nextInt();

            stock = new int[number];
            food1 = new int[number];
            food2 = new int[number];

            for (int i = 0; i < number; i++) {
                stock[i] = sc.nextInt();
            }
            for (int i = 0; i < number; i++) {
                food1[i] = sc.nextInt();
            }
            for (int i = 0; i < number; i++) {
                food2[i] = sc.nextInt();
            }

            int max = 0;

            while (checkStock(max, 0)) {
                max++;
            }

            max--;

            int maxA = max;
            int maxB = 0;
            while (maxA > -1) {
                while (checkStock(maxA, maxB)) {
                    if (max < maxA + maxB)
                        max = maxA + maxB;
                    maxB++;
                }
                maxA--;
            }

            System.out.println(max);
        }
    }

    static boolean checkStock(int a, int b) {
        for (int i = 0; i < number; i++) {
            if (a * food1[i] + b * food2[i] > stock[i])
                return false;
        }

        return true;
    }
}
