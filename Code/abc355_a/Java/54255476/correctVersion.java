import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            ArrayList<Integer> people = new ArrayList<>(Arrays.asList(1, 2, 3));

            if (A != B) {
                people.remove(Integer.valueOf(A));
                people.remove(Integer.valueOf(B));
                System.out.println(people.get(0));
            } else if (A == B) {
                System.out.println(-1);
            }
        }

    }
}
