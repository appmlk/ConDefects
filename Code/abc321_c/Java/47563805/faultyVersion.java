import java.util.Arrays;
import java.util.Scanner;

public final class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int K = scanner.nextInt() + 1;

        scanner.close();

        int[][] numberOf321LikeNumber = new int[10][10];
        Arrays.fill(numberOf321LikeNumber[0], 1);
        Arrays.setAll(numberOf321LikeNumber[1], index -> index);
        for (int digits = 2; digits < 10; ++digits) {
            int sum = 0;
            for (int number = 0; number < 10; ++number) {
                numberOf321LikeNumber[digits][number] = sum;
                sum += numberOf321LikeNumber[digits - 1][number];
            }
        }

        int digitsMax = 0;
        int numberMax = 0;
        int[] answerArray = new int[10];
        Arrays.fill(answerArray, 0);
        int cumulativeNumberOf321LikeNumber = 0;
        for (int digits = 0; digits < 10; ++digits) {
            for (int number = digits; number < 10; ++number) {
                cumulativeNumberOf321LikeNumber += numberOf321LikeNumber[digits][number];
                if (cumulativeNumberOf321LikeNumber >= K) {
                    digitsMax = digits;
                    numberMax = number;
                    break;
                }
            }
            if (cumulativeNumberOf321LikeNumber >= K) {
                break;
            }
        }
        answerArray[digitsMax] = numberMax;
        cumulativeNumberOf321LikeNumber -= numberOf321LikeNumber[digitsMax][numberMax];

        for (int digits = digitsMax - 1; digits >= 0; --digits) {
            for (int number = digits; number < 10; ++number) {
                cumulativeNumberOf321LikeNumber += numberOf321LikeNumber[digits][number];
                if (cumulativeNumberOf321LikeNumber >= K) {
                    answerArray[digits] = number;
                    cumulativeNumberOf321LikeNumber -= numberOf321LikeNumber[digits][number];
                    break;
                }
            }
        }

        int answer = 0;
        for (int digits = digitsMax; digits >= 0; --digits) {
            answer *= 10;
            answer += answerArray[digits];
        }

        System.out.println(answer);
    }
}
