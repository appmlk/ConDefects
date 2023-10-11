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

    int n;
    int[] sub;
    List<Integer>[] next ;

    public void solve() {
        n = sc.nextInt();
        sub = new int[n];
        sub2 = new int[n];
        next = new List[n];
        Arrays.setAll(next, i->new ArrayList<>());
        for(int i=0; i<n-1; i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            next[u].add(v);
            next[v].add(u);
        }
        res = new int[n];
        try {
            dfs1(0, -1);
        } catch (MyException e) {
            for (int i=0; i<n; i++) {
                if (i>0) out.print(" ");
                out.print(res[i]+1);
            }
            out.println();
            return;
        }
        throw new RuntimeException();


    }
    void dfs1(int u, int p) {
        sub[u] = 1;
        int max = 0;
        for(int v : next[u]) {
            if(v!=p) {
                dfs1(v, u);
                max = Math.max(max, sub[v]);
            }
        }
        if(p!=-1) {
            sub[p] += sub[u];
        }
        if(n%2==0 && sub[u]==n/2) {
            solve2(p, u);
            throw new MyException();
        }
        max = Math.max(max, n-sub[u]);
        if(max <= (n-1)/2) {
            solve1(u, max);
            throw new MyException();
        }

    }

    int[] res;
    int[] sub2;

    void solve1(int x, int max) {

        res[x] = x;
        LinkedList<Integer> ll = new LinkedList<>();
        int m = next[x].size();
        int s = 0;
        for(int i=0; i<m; i++) {
            dfs10(next[x].get(i), x);
            if(sub2[next[x].get(i)]==max) {
                s = i;
            }
        }
        for(int i=0; i<m; i++) {
            dfs11(next[x].get((i+s)%m), x, ll);
        }
        for(int i=0; i<m; i++) {
            dfs12(next[x].get((i+1+s)%m), x, ll);
        }
    }

    void dfs10(int u, int p) {
        sub2[u] = 1;
        for(int v: next[u]) {
            if(v==p) continue;
            dfs10(v, u);
        }
        sub2[p] += sub2[u];
    }

    void dfs11(int u, int p, LinkedList<Integer> ll) {
        ll.addLast(u);
        for(int v: next[u]) {
            if(v==p) continue;
            dfs11(v, u, ll);
        }
    }
    void dfs12(int u, int p, LinkedList<Integer> ll) {
        res[u] = ll.removeFirst();
        for(int v: next[u]) {
            if(v==p) continue;
            dfs12(v, u, ll);
        }
    }

    void solve2(int x, int y) {
        LinkedList<Integer> ll = new LinkedList<>();
        dfs11(x, y, ll);
        dfs11(y, x, ll);
        dfs12(y, x, ll);
        dfs12(x, y, ll);
    }

    class MyException extends RuntimeException {
        public MyException() {}
    }


}
