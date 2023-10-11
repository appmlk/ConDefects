
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int number1 = sc.nextInt();
        int number2 = sc.nextInt();
        long result = 500000000L * number2 + number1;
        out.println(result);
        out.flush();
    }
}