import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        try (Scanner input = new Scanner(System.in)) {

            int N = input.nextInt();

            if (N % 100 == 0)

                System.out.println("00");

            else if (N % 100 >= 1 && N % 100 < 10)

                System.out.println("0" + N % 100);

            else

                System.out.println(N % 100); // 2 last degits

        }

    }

}
