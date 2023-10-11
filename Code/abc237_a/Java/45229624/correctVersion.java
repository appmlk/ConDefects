import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();

        if (input >= (-1 * (long) Math.pow(2, 31)) && input <= ((long) Math.pow(2, 31) - 1)) System.out.println("Yes");
        else
            System.out.println("No");

    }
}