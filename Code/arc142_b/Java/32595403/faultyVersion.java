import java.util.*;
import java.io.*;
import java.text.*;

public class Main{
    //SOLUTION BEGIN
    void pre() throws Exception{}
    void solve(int TC) throws Exception {
        int N = ni();
        int[][] mat = new int[N][N];
        int val = 1;
        for(int s = 0; s <= N+N-2; s++) {
//            List<int[]> list = new ArrayList<>();
            int cnt = 0;
            for(int i = 0; i<= s; i++){
                int r = i, c = s-i;
                if(Math.min(r, c) >= 0 && Math.max(r, c) < N)cnt++;//list.add()
            }
            List<Integer> list = new ArrayList<>();
            int lo = val+1, hi = val+cnt;
            val += cnt;
            for(int i = 0; i< cnt; i++){
                if(i%2 == 0)list.add(lo++);
                else list.add(hi--);
            }

            for(int i = 0, j = 0; i<= s; i++){
                int r = i, c = s-i;
                if(Math.min(r, c) >= 0 && Math.max(r, c) < N)mat[r][c] = list.get(j++);
            }
        }

        for(int i = 0; i< N; i++){
            for(int j = 0; j< N; j++)p(mat[i][j]);
            pn("");
        }

    }
    boolean valid(int[][] mat){
        for(int i = 0; i< mat.length; i++){
            for(int j = 0; j< mat[i].length; j++){

                int sm = 0, lg = 0;
                for(int ii = Math.max(0, i-1); ii <= Math.min(mat.length-1, i+1); ii++)
                    for(int jj = Math.max(0, j-1); jj <= Math.min(mat[i].length-1, j+1); jj++)
                        if(mat[ii][jj] < mat[i][j])sm++;
                        else if(mat[ii][jj] > mat[i][j])lg++;
                if(sm == lg){
                    dbg("Chk", i, j);
                }
                if(sm == lg)return false;
            }
        }
        return true;
    }
    //SOLUTION  END
    void hold(boolean b)throws Exception{if(!b)throw new Exception("Hold right there, Sparky!");}
    static void dbg(Object... o){System.err.println(Arrays.deepToString(o));}
    void exit(boolean b){if(!b)System.exit(0);}
    final long IINF = Long.MAX_VALUE/2;
    final int INF = Integer.MAX_VALUE/2;
    DecimalFormat df = new DecimalFormat("0.000000000");
    double PI = 3.141592653589793238462643383279502884197169399, eps = 1e-9;
    static boolean multipleTC = false, memory = false, fileIO = false;
    FastReader in;PrintWriter out;
    void run() throws Exception{
        long ct = System.currentTimeMillis();
        if (fileIO) {
            in = new FastReader("");
            out = new PrintWriter("");
        } else {
            in = new FastReader();
            out = new PrintWriter(System.out);
        }
        //Solution Credits: Taranpreet Singh
        int T = multipleTC? ni():1;
        pre();
        for (int t = 1; t <= T; t++) solve(t);
        out.flush();
        out.close();
        System.err.println("Runtime: " + (System.currentTimeMillis() - ct));
    }
    public static void main(String[] args) throws Exception{
        if(memory)new Thread(null, new Runnable() {public void run(){try{new Main().run();}catch(Exception e){e.printStackTrace();System.exit(1);}}}, "1", 1 << 26).start();
        else new Main().run();
    }
    int[][] make(int n, int e, int[] from, int[] to, boolean f){
        int[][] g = new int[n][];int[]cnt = new int[n];
        for(int i = 0; i< e; i++){
            cnt[from[i]]++;
            if(f)cnt[to[i]]++;
        }
        for(int i = 0; i< n; i++)g[i] = new int[cnt[i]];
        for(int i = 0; i< e; i++){
            g[from[i]][--cnt[from[i]]] = to[i];
            if(f)g[to[i]][--cnt[to[i]]] = from[i];
        }
        return g;
    }
    int[][][] makeS(int n, int e, int[] from, int[] to, boolean f){
        int[][][] g = new int[n][][];int[]cnt = new int[n];
        for(int i = 0; i< e; i++){
            cnt[from[i]]++;
            if(f)cnt[to[i]]++;
        }
        for(int i = 0; i< n; i++)g[i] = new int[cnt[i]][];
        for(int i = 0; i< e; i++){
            g[from[i]][--cnt[from[i]]] = new int[]{to[i], i, 0};
            if(f)g[to[i]][--cnt[to[i]]] = new int[]{from[i], i, 1};
        }
        return g;
    }
    int[][] make(int n, int[] par, boolean f){
        int[][] g = new int[n][];
        int[] cnt = new int[n];
        for(int x:par)cnt[x]++;
        if(f)for(int i = 1; i< n; i++)cnt[i]++;
        for(int i = 0; i< n; i++)g[i] = new int[cnt[i]];
        for(int i = 1; i< n-1; i++){
            g[par[i]][--cnt[par[i]]] = i;
            if(f)g[i][--cnt[i]] = par[i];
        }
        return g;
    }
    int find(int[] set, int u){return set[u] = (set[u] == u?u:find(set, set[u]));}
    int digit(long s){int brute = 0;while(s>0){s/=10;brute++;}return brute;}
    long gcd(long a, long b){return (b==0)?a:gcd(b,a%b);}
    int gcd(int a, int b){return (b==0)?a:gcd(b,a%b);}
    int bit(long n){return (n==0)?0:(1+bit(n&(n-1)));}
    void p(Object... o){for(Object oo:o)out.print(oo+" ");}
    void pn(Object... o){
        if(o.length == 0)out.println("");
        for(int i = 0; i< o.length; i++){
            out.print(o[i]);
            out.print((i+1 == o.length?"\n":" "));
        }
    }
    void pni(Object... o){for(Object oo:o)out.print(oo+" ");out.println();out.flush();}
    String n()throws Exception{return in.next();}
    String nln()throws Exception{return in.nextLine();}
    int ni()throws Exception{return Integer.parseInt(in.next());}
    long nl()throws Exception{return Long.parseLong(in.next());}
    double nd()throws Exception{return Double.parseDouble(in.next());}
    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws Exception{
            br = new BufferedReader(new FileReader(s));
        }

        String next() throws Exception{
            while (st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException  e){
                    throw new Exception(e.toString());
                }
            }
            return st.nextToken();
        }

        String nextLine() throws Exception{
            String str;
            try{
                str = br.readLine();
            }catch (IOException e){
                throw new Exception(e.toString());
            }
            return str;
        }
    }
}
