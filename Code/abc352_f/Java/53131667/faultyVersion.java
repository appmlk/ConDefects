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
    List<G> gs;
    int d;
    int md;
    int[] res;
    public void solve() {
        n = sc.nextInt();
        int m = sc.nextInt();
        List<E>[] list = new List[n];
        UFSize uf = new UFSize(n);
        Arrays.setAll(list, i -> new ArrayList<>());
        for(int i=0; i<m; i++) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            int c = sc.nextInt();
            list[a].add(new E(a, b, -c));
            list[b].add(new E(b, a, c));
            uf.join(a, b);
        }
        boolean[] v = new boolean[n];
        int[] l = new int[n];
        gs = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(uf.root(i) != i) continue;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            v[i] = true;
            LinkedList<Integer> q = new LinkedList<>();
            q.offer(i);
            while(!q.isEmpty()) {
                int u = q.poll();
                for(E e : list[u]) {
                    if(v[e.v]) continue;
                    v[e.v] = true;
                    l[e.v] = l[u] + e.c;
                    q.offer(e.v);
                    set.add(e.v);
                }
            }
            int min = l[i];
            int mini = i;
            for(int j : set) {
                if(l[j] < min) {
                    min = l[j];
                    mini = j;
                }
            }
            int max = 0;
            int mask = 0;
            for(int j : set) {
                l[j] -= min;
                max = Math.max(max, l[j]);
                mask |= 1<<l[j];
            }
            gs.add(new G(mini, max, mask, set.size()));
        }
        d = gs.size();
        gs.sort(Comparator.comparingInt(a -> a.cnt));
        int sn  = 0;
        res = new int[n];
        Arrays.fill(res, -1);
        for(int i=0; i<d; i++) {
            G g = gs.get(i);
            if(g.cnt == 1) {
                sn++;
            }
        }
        md = d;
        if(sn >= 2) {
            for(int i=0; i<d; i++) {
                G g = gs.get(i);
                if(g.cnt == 1) {
                    res[g.root] = -1;
                }
            }
            md -= sn;
        }
        dfs(md-1, 0, new int[md]);
        for(int i=0; i<md; i++) {
            G g = gs.get(i);
            if(g.oset.size() != 1) {
                continue;
            }
            int offset = g.oset.iterator().next();
            res[g.root] = offset;
            for(int j=0; j<n; j++) {
                if(uf.root(j) == uf.root(g.root)) {
                    res[j] = l[j] + offset;
                }
            }

        }
        
        for(int i=0; i<res.length; i++) {
            if(i>0) out.print(" ");
            if(res[i] >= 0) {
                res[i]++;
            }
            out.print(res[i]);
         }
         out.println();
    }

    void dfs(int pos, int mask, int[] offset) {
        if(pos == -1) {
            for(int i=0; i<md; i++) {
                gs.get(i).oset.add(offset[i]);
            }
            return;
        }
        G g = gs.get(pos);
        for(int i=0; i+g.max < n; i++) {
            if((mask & (g.mask<<i)) != 0) continue;
            offset[pos] = i;
            dfs(pos-1, mask | (g.mask<<i), offset);
        }
    }

    static class G {
        int root;
        int max;
        int mask;
        int cnt;
        Set<Integer> oset;
        G(int root, int max, int mask, int cnt) {
            this.root = root;
            this.max = max;
            this.mask = mask;
            this.cnt = cnt;
            oset = new HashSet<>();
        }
    }

    static class E {
        int u;
        int v;
        int c;
        E(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }

    static class UF {
        int[] p;
        int n;
        UF(int n) {
            this.n = n;
            p = new int[n];
            for(int i=0; i<n; i++){
                p[i] = i;
            }
        }
        int root(int a) {
            if(p[a] == a) {
                return a;
            }
            int res =  root(p[a]);
            p[a] = res;
            return res;
        }
        void join(int a ,int b) {
            p[root(a)] = p[root(b)];
        }
    }

    static class UFSize extends UF {

        int[] size;
        UFSize(int n) {
            super(n);
            size = new int[n];
            for(int i=0; i<n; i++) {
                size[i] = 1;
            }
        }
        @Override
        void join(int a, int b) {
            if(root(a) != root(b)) {
                size[root(b)] += size[root(a)];
                p[root(a)] = p[root(b)];
            }
        }

    }


}
