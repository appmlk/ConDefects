import java.math.*;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String s = sc.next();
        final long n = s.length();

        Map<Character, Long> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0L) + 1L);
        }

        long sum = n * (n - 1) / 2;

        long dup = 0;
        for (var e : map.entrySet()) {
            if (e.getValue() > 1) {
                dup += e.getValue() * (e.getValue() - 1) / 2;
            }
        }
        if (dup > 0) {
            System.out.println(sum - dup + 1);
        } else {
            System.out.println(sum);
        }
    }
}