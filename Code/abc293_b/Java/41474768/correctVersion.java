import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            int N = scanner.nextInt();
            boolean[] array = new boolean[N + 1];

            for (int i = 1; i < N + 1; i++) {
                int current = scanner.nextInt();

                if (!array[i]) {
                    array[current] = true;
                }
            }

            List<Integer> result = new ArrayList<Integer>();
            for (int i = 1; i < N + 1; i++) {
                if (!array[i])
                    result.add(i);
            }
            System.out.println(result.size());
            result.forEach(item -> System.out.print(item + " "));
        }
    }
}