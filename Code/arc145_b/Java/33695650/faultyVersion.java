import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Long N = input.nextLong();
        Long A = input.nextLong();
        Long B = input.nextLong();

        Long result = Long.valueOf(0);

        result = countSet(A, B, N) - countSet(A, B, A-1);

        System.out.println(result);
    }
    public static Long countSet(Long A, Long B, Long X) {
        return (X / A) * Math.min(A, B) + Math.min(X % A, B - 1);
    }
}
