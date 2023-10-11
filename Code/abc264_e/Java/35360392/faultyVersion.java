import java.io.*;
import java.util.*;
import java.lang.*;
// import java.math.BigInteger;

public class Main {
    public static FastReader in;
    public static PrintWriter out;
    public static long mod = (long)(998244353);

    public static int[] rank;
    public static int[] parent;
    public static int N;

    public static void init_set(int n,int size){
        N = n;
        rank = new int[size+1];
        parent = new int[size+1];
    } 

    public static void make_set(int vertex,int r){
        parent[vertex] = vertex;
        rank[vertex] = r;
    }

    public static int find_sets(int v){
        if(v==parent[v]) return v;
        return parent[v] = find_sets(parent[v]);
    }

    public static void union_sets(int u,int v){
        int a = find_sets(u);
        int b = find_sets(v);
        int A = Math.min(a,b);
        int B = Math.max(a,b);
        if(A!=B){
            parent[A] = B;
            rank[B] += rank[A];
            if(A>N) rank[A] = 0; 
        }
    }

    public static int mergeAndGetAns(int u,int v){
        int a = find_sets(u);
        int b = find_sets(v);
        if(a==b) return 0;
        if((a<=N&&b<=N )|| (a>N&&b>N) ){
            union_sets(u,v);
            return 0;
        }
        else{
            int A = Math.min(a,b);
            int B = Math.max(a,b);
            parent[A] = B;
            rank[B] += rank[A];
            return rank[A];
        }
    }
    public static void solve(int nTestCase){
        int N = in.nextInt(), M = in.nextInt(), E = in.nextInt();
        int[][] adj = new int[E+1][2];
        for(int i=1;i<=E;i++){
            adj[i][0] = in.nextInt();
            adj[i][1] = in.nextInt();
        }
        int Q = in.nextInt();
        int[] seq = in.readArray(Q);
        init_set(N,N+M);
        for(int i=1;i<=N+M;i++) {
            if(i<=N) make_set(i,1);
            else make_set(i,0);
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i:seq) set.add(i);
        for(int i=1;i<=E;i++){
            if(set.contains(i)) continue;
            else{
                // out.println("fs:"+find_sets(adj[i][0])+" "+find_sets(adj[i][1]));
                union_sets(adj[i][0],adj[i][1]);
                // out.println(adj[i][0]+" "+adj[i][1]);
                // out.println("fs:"+find_sets(adj[i][0])+" "+find_sets(adj[i][1]));
                // out.println(rank[adj[i][0]]+" ranl "+rank[adj[i][1]]);
            }
        }
        int curr = 0;
        for(int i=N+1;i<=N+M;i++) curr+=rank[i];
        ArrayList<Integer> ans = new ArrayList<>();
        // out.println(curr);
        // out.println(rank[4]);
        // out.println(set);
        for(int i=seq.length-1;i>=0;i--){
            ans.add(curr);
            int nEle = mergeAndGetAns(adj[seq[i]][0],adj[seq[i]][1]);
            curr+=nEle;
            out.println("nEle:"+nEle);
        }
        for(int i=ans.size()-1;i>=0;i--) out.println(ans.get(i));
    }  


    public static int gcd(int a,int b){
        if(a==0) return b;
        else return gcd(b%a,a);
    }
    public static void fastSort(int[] a){
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            int next = random.nextInt(a.length);
            int curr = a[next];
            a[next] = a[i];
            a[i] = curr;
        }
        Arrays.sort(a);
    }
    public static void main(String[] args) throws IOException {
        boolean isInputOutputFiles = args!=null&&args.length>0&&args[0].equals("LOCAL_RUN");
        // isInputOutputFiles = false;
        if (isInputOutputFiles) {
            in = new FastReader(new BufferedReader(new FileReader("./input/input.txt")));
            out = new PrintWriter(new BufferedWriter(new FileWriter("./output/output.txt")));
        }
        else{
            in = new FastReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        long startT = 0;
        if (isInputOutputFiles) startT = System.currentTimeMillis();
        int T = 1;
        // T = in.nextInt();
        for (int tt = 1; tt <= T; tt++) {
            solve(tt);
        }
        if (isInputOutputFiles) out.println("Total time: "+(System.currentTimeMillis()-startT)+"ms");
        out.close();
    }


    static class Pair  implements Comparable<Pair> {
        int a;
        int b;
        int c;
        public Pair(int a,int b,int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int compareTo(Pair o) {
            if(this.a==o.a)
                return (int)(o.b-this.b);
            if((this.a - o.a)>0) return 1;
            else if((this.a - o.a)<0) return -1;
            else  return (int) (this.a - o.a);
        }
    }


    static class SegTree{
        int leftmost,rightmost;
        int xorAns;
        SegTree left,right;

        public SegTree(int leftmost,int rightmost,int[] a) {
            this.leftmost = leftmost;
            this.rightmost = rightmost;
            if(leftmost==rightmost) {
                this.xorAns = a[leftmost];
                return;
            }
            int mid = (leftmost+rightmost)/2;
            left = new SegTree(leftmost,mid,a);
            right = new SegTree(mid+1,rightmost,a);
            recalc();
        }
        public void recalc() {
            if(leftmost==rightmost) return;
            xorAns = left.xorAns^right.xorAns;
            return;
        }
        public void pointUpdate(int index,int newVal) {
            if(leftmost==rightmost) {
                this.xorAns = newVal;
                return;
            }
            if(index<=left.rightmost) left.pointUpdate(index, newVal);
            else right.pointUpdate(index, newVal);
            recalc();
        }
        public int rangeQuery(int l, int r){
            if(l>rightmost||r<leftmost) return 0;
            if(l<=leftmost && r>=rightmost) return xorAns;
            return left.rangeQuery(l,r)^right.rangeQuery(l,r);
        }
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(Reader rd) {
            br = new BufferedReader(rd);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }
    }
}


