import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] arg) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nq = br.readLine();
        String s  = br.readLine();
        int[] nqarray = splitIntoInt(nq, 2);
        int n = nqarray[0];
        int q = nqarray[1];

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                set.add(i);
            }
        }
        set.add(n + 1);  // 番兵

        for (int qi = 0; qi < q; qi++) {
            String row = br.readLine();
            int[] rowarray = splitIntoInt(row, 3);
            int cmd = rowarray[0];
            int l   = rowarray[1];
            int r   = rowarray[2];
            if (cmd == 1) {
                xor(set, l - 1);
                xor(set, r);
            } else {
                boolean yes = r <= set.higher(l);
                System.out.println(yes ? "Yes" : "No");
            }
        }
    }

    static void xor(Set<Integer> set, int x) {
        if (set.contains(x)) {
            set.remove(x);
        } else {
            set.add(x);
        }
    }

    static int[] buf = new int[3];

    static int[] splitIntoInt(String s, int n) {
        int i = 0;
        int acc = 0;
        for (int pos = 0; pos < s.length(); pos++) {
            if ('0' <= s.charAt(pos) && s.charAt(pos) <= '9') {
                acc = acc * 10 + (s.charAt(pos) - '0');
            } else {
                buf[i++] = acc;
                acc = 0;
            }
        }
        buf[i] = acc;
        return buf;
    }
}
