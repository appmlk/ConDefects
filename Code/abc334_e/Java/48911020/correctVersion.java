import java.io.*;
import java.util.*;
public class Main implements Runnable{
    private static final FastReader fr = new FastReader();
    private static final long MOD = 998244353L;
    private static final int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    private static final int rows = fr.nextInt(), cols = fr.nextInt();
    private static final char[][] M = new char[rows][cols];
    private static final int[][] G = new int[rows][cols];
    public static void main(String[] args) throws IOException {
        new Thread(null, new Main(), "cp", 1<<26).start();
    }
    @Override
    public void run() {
        PrintWriter out = new PrintWriter(System.out);
//        int testCases = fr.nextInt();
//        for(int testCase=1;testCase<=testCases;testCase++) {
//
//        }
        for(int i=0;i<rows;i++) M[i] = fr.next().toCharArray();
        int reds = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(M[i][j] == '#') G[i][j]--;
                else reds++;
            }
        }
        int num = 1;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(G[i][j] == -1) {
                    dfs(i,j,num);
                    num++;
                }
            }
        }
        int cc = num - 1;
 //       System.out.println("reds= " + reds);
        long inv = mod_inverse(reds);
  //      System.out.println("inv= " + inv + " cc="+cc);
        long res = 0;
  //      for(int[] g: G) System.out.println(Arrays.toString(g));
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(M[i][j] == '.'){
                    HashSet<Integer> set = new HashSet<>();
                    for(int[] dir: directions){
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if(isValid(nr,nc) && M[nr][nc] == '#') set.add(G[nr][nc]);
                    }
                    if(set.size() == 0) res += (cc + 1);
                    else if(set.size()==1) res += cc;
                    else res += (cc - set.size() + 1);
            //        System.out.println("res= "  + res);
                }
            }
        }
        out.println(res%MOD*inv%MOD);
        out.flush();
    }
    private static boolean isValid(int r, int c){
        return r>=0 && c>=0 && r<rows && c<cols;
    }
    private static void dfs(int r, int c, int num){
        G[r][c] = num;
        for(int[] dir: directions){
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(isValid(nr,nc) && G[nr][nc] == -1) dfs(nr,nc,num);
        }
    }
    private static long mod_inverse(long num){
        return powMODRec(num%MOD,MOD-2);
    }
    private static long powMODRec(long x, long n){
        if(n == 0) return 1L;
        long t = powMODRec(x,n/2);
        if(n%2 == 0) return t*t%MOD;
        return t*t%MOD*x%MOD;
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }

    long nextLong() { return Long.parseLong(next()); }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}