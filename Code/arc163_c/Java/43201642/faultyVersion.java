import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    public void solve() {
        int n = sc.nextInt();
        for(int i=0; i<n; i++) solve2();
    }

    void solve2() {
        int n = sc.nextInt();
        if(n == 1) {
            out.println("Yes");
            out.println("1");
            return;
        }
        if(n == 2) {
            out.println("No");
            return;
        }
        if(n == 3) {
            out.println("Yes");
            out.println("2 3 6");
            return;
        }

        out.println("Yes");

        Set <Integer> set = new HashSet<>();
        int l1 = 0;
        int l2 = 0;
        int l3 = 0;
        for(int i=0; i<n-1; i++) {
            int num = (i+1) * (i+2);
            l3 = l2;
            l2 = l1;
            l1 = num;
            set.add(num);
        }
        if(!set.contains(n)) {
            set.add(n);
        } else {
            set.remove(l1);
            set.remove(l2);
            set.remove(l3);
            set.add(l3 * 2);
            set.add(l3 * 3);
            set.add(l3 * 6);
            set.add(n-1);
        }

        int[] res = set.stream().mapToInt(i->i).sorted().toArray();
        double sum = 0;
        for (int i=0; i<res.length; i++) {
            if (i>0) out.print(" ");
            out.print(res[i]);
            sum += 1d / res[i];
        }
        out.println();
        //out.println(sum);

    }



}
