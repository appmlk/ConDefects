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
        int k = sc.nextInt();
        int[] p = new int[n];
        p[0] = -1;
        int[] sub = new int[n];
        for(int i=1; i<n; i++) {
            p[i] = sc.nextInt()-1;
            sub[p[i]]++;
        }
        int[] num = new int[n];
        int[] sp = new int[n];
        Arrays.setAll(num, i -> Math.min(sc.nextInt(), k+1));
        boolean[][] mark = new boolean[n][k+2];
        for(int i=0; i<n; i++) {
            if(num[i]!=-1) {
                mark[i][num[i]] = true;
            } else {
                sp[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if(sub[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()) {
            int pos = q.poll();
            //out.println("pos = " + pos);
            int cnt = 0;
            for(int i=0; i<k; i++) {
                if(mark[pos][i]) {
                    cnt++;
                }
            }
            if(!mark[pos][k] && (sp[pos] <= 1 && cnt == k || sp[pos] == 1 && cnt == k-1)) {
                out.println("Alice");
                return;
            }
            if(p[pos] == -1) {
                continue;
            }
            for(int i=0; i<k+2; i++) {
                mark[p[pos]][i] |= mark[pos][i];
            }
            sp[p[pos]] += sp[pos];
            sub[p[pos]]--;
            if(sub[p[pos]] == 0) {
                q.add(p[pos]);
            }
        }

        out.println("Bob");
    }
}
