import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long h = sc.nextLong();
        long w = sc.nextLong();
        int m = sc.nextInt();
        int[] t = new int[m];
        int[] a = new int[m];
        int[] x = new int[m];
        for(int i=0; i<m; ++i) {
            t[i] = sc.nextInt();
            a[i] = sc.nextInt();
            x[i] = sc.nextInt();
        }

        Set<Integer> cols = new TreeSet<>();
        Set<Integer> rows = new TreeSet<>();
        Map<Integer, Long> ans = new TreeMap<>();
        ans.put(0, h * w);
        for(int i=m-1; i>=0; --i) {
            if(t[i] == 1) {
                if(rows.contains(a[i])) continue;

                if(w - cols.size() > 0) {
                    long add = w - cols.size();
                    long base = ((ans.containsKey(x[i])) ? ans.get(x[i]) : 0);
                    ans.put(x[i], base + add);
                    ans.put(0, ans.get(0) - add);
                }

                rows.add(a[i]);
            } else if(t[i] == 2) {
                if(cols.contains(a[i])) continue;

                if(h - rows.size() > 0) {
                    long add = h - rows.size();
                    long base = ((ans.containsKey(x[i])) ? ans.get(x[i]) : 0);
                    ans.put(x[i], base + add);
                    ans.put(0, ans.get(0) - add);
                }

                cols.add(a[i]);
            }
        }

        if(ans.get(0) < 1) {
            ans.remove(0);
        }

        System.out.println(ans.size());
        for(var e : ans.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
