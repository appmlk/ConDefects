import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long L = sc.nextLong();
        long R = sc.nextLong();
        List<List<Long>> ans = new ArrayList<>();

        while (L < R) {
            long i = 0;
            while (L % (1L << (i + 1)) == 0 && L + (1L << (i + 1)) <= R) {
                i++;
            }
            List<Long> range = new ArrayList<>();
            range.add(L);
            range.add(L + (1L << i) - 1);
            ans.add(range);
            L += (1L << i);
        }

        System.out.println(ans.size());

        for (List<Long> range : ans) {
            System.out.println(range.get(0) + " " + range.get(1));
        }

    }
}
