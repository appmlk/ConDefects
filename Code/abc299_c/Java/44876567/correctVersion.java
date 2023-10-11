import static java.lang.System.out;
import java.util.*;
import java.util.regex.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Comparator.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        out.println(solve());
    }
    
    private static Object solve() {
        sc.next();
        var s = sc.next();
        boolean hasM = false;
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == '-') {
            hasM = true;
            break;
        }
        if (!hasM) return -1;
        int ans = 0, cur = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'o') ans = max(ans, ++cur);
            else cur = 0;
        }
        return ans == 0 ? -1 : ans;
    }
}
