import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PrintWriter out = new PrintWriter(System.out, false);
    private static final Set<String> stopLineName = new HashSet<>();

    public static void main(String[] args) {
        sc.nextLine();
        String[] lineName = sc.nextLine().split(" ");
        stopLineName.addAll(Arrays.asList(sc.nextLine().split(" ")));
        for (String line: lineName) {
            out.println(stopLineName.contains(line) ? "Yes" : "No");
        }
        out.flush();
    }
}