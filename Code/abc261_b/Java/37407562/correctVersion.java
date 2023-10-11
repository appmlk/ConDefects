import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;

public class Main{
  long st = System.currentTimeMillis();

  long elapsed(){ return System.currentTimeMillis() -st; }

  final MyReader in = new MyReader(System.in);
  final MyWriter out = new MyWriter(System.out);
  final MyWriter log = new MyWriter(System.err){
    @Override
    void ln(){
      super.ln();
      flush();
    };
  };

  public static void main(final String[] args){ new Main().exe(); }

  void exe(){
//    solve();
        out.println(solve());
    out.flush();
    log.println(elapsed());
  }

//  int N = in.it();
//  int Q = in.it();
 
String solve() {
    int N = in.it();
    char[][] A = in.ch(N);
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            if (A[i][j] == 'W' && A[j][i] != 'L')
                return "incorrect";
            else if (A[i][j] == 'L' && A[j][i] != 'W')
                return "incorrect";
    return "correct";
    //      SegmentTree st = new SegmentTree(N);
//
//      while(Q-->0) {
//          int q = in.it() ;
//          if(q == 0) {
//              st.upd(in.it(), in.it()+1, in.it());
//          }else
//              out.println(st.get(in.it(), in.it()+1));
//          
//      }
      
  }

  static class SegmentTree{
    long[] arr;
    Long[] lazy;
    int [] powK ;
    int n;
    long e = Integer.MAX_VALUE;

    long upd(final long a,final long b){ return b; }

    long opr(final long a,final long b){ return Long.min(a,b); }

    SegmentTree(final int n){
      this.n = n;
      arr = new long[n<<1];
      lazy = new Long[n<<1];
      powK = new int[n<<1];
      for (int i = n<<1 ;  --i>0; ) {
          powK[i] = i < n ? powK[i<<1]<<1 : 1;
      }
      Arrays.fill(arr,e);
    }

    void eval(int i) {
        if (i == 0)
            return;
        if (lazy[i] != null) {
            arr[i]= upd(arr[i],pow(lazy[i],i));
            if (i < n) {
                lazy[i << 1] =  lazy[i];
                lazy[i << 1|1] =  lazy[i];
            }

            lazy[i] = null;
        }
    }
    
    long pow (long v,int i ) {
        return powK[i]==1? v:pow(opr(v,v), i<<1);
    }

    void upd(int l,int r,final long v){
        l += n;
        r += n;
        down(l/(l&-l));
        down(r/(r&-r));

        setLazy(l, r, v);
        recalc(l/(l&-l));
        recalc(r/(r&-r));

    }


    void recalc(int i) {
        if(i==0)
            return;
        arr[i >> 1] = opr(arr[i & -1], arr[i]);
        recalc(i >> 1);
    }

    void down(int i) {
         if(i==0)
             return ;
         down(i>>1);
         eval(i);
    }

    void setLazy(int l, int r, long v) {
        if (l == r)
            return;

        if ((l & 1) == 1) {
            lazy[l] = v;
            eval(l);
            l++;
        }
        if ((r & 1) == 1) {
            --r;
            lazy[r] = v;
            eval(r);
        }
        setLazy(l>>1, r>>1, v);
    }

    long get(int l,int r){
        l+=n;
        r+=n;
        down(l/(l&-l));
        down(r/(r&-r));
        recalc(l/(l&-l));
        recalc(r/(r&-r));
        return agg(l,r);
    }

    long agg(int l, int r) {
        if (l == r)
            return e;
                
        long vl = (l & 1) == 1 ? arr[l++] : e;
        long vr = (r & 1) == 1 ? arr[--r] : e;
        return opr(opr(vl, agg(l >> 1, r >> 1)), vr);
    }
    
  }

  /* 定数 */
  final static int infI = (int) 1e9;
  final static long infL = (long) 1e18;
  //  final static long mod = (int) 1e9 +7;
  final static long mod = 998244353;
  final static String yes = "Yes";
  final static String no = "No";

  /* Util */
  void swap(final int[] arr,final int i,final int j){
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  void swap(final long[] arr,final int i,final int j){
    long t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  long mod(final long n){ return (n %mod +mod) %mod; }

  /* 入力 */
  static class MyReader{
    byte[] buf = new byte[1 <<16];
    int head = 0;
    int tail = 0;
    InputStream in;

    public MyReader(final InputStream in){ this.in = in; }

    byte read(){
      if (head == tail) {
        try {
          tail = in.read(buf);
        } catch (IOException e) {
          e.printStackTrace();
        }
        head = 0;
      }
      return buf[head++];
    }

    boolean isPrintable(final byte c){ return 32 < c && c < 127; }

    boolean isNum(final byte c){ return 47 < c && c < 58; }

    byte nextPrintable(){
      byte ret = read();
      while (!isPrintable(ret))
        ret = read();
      return ret;
    }

    int it(){ return (int) lg(); }

    int[] it(final int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> it());
      return a;
    }

    int[][] it(final int H,final int W){ return arr(new int[H][],i -> it(W)); }

    int idx(){ return it() -1; }

    int[] idx(final int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> idx());
      return a;
    }

    int[][] idx(final int H,final int W){ return arr(new int[H][],i -> idx(W)); }

    int[][] trans(final int[][] mat){
      int[][] ret = new int[mat[0].length][mat.length];

      for (int i = 0;i < mat.length;i++)
        for (int j = 0;j < mat[0].length;j++)
          ret[j][i] = mat[i][j];
      return ret;
    }

    long lg(){
      byte i = nextPrintable();
      boolean negative = i == 45;
      long n = negative ? 0 : i -'0';
      while (isPrintable(i = read()))
        n = 10 *n +i -'0';
      return negative ? -n : n;
    }

    long[] lg(final int N){
      long[] a = new long[N];
      Arrays.setAll(a,i -> lg());
      return a;
    }

    long[][] lg(final int H,final int W){ return arr(new long[H][],i -> lg(W)); }

    long[][] trans(final long[][] mat){
      long[][] ret = new long[mat[0].length][mat.length];

      for (int i = 0;i < mat.length;i++)
        for (int j = 0;j < mat[0].length;j++)
          ret[j][i] = mat[i][j];
      return ret;
    }

    double dbl(){ return Double.parseDouble(str()); }

    double[] dbl(final int N){
      double[] a = new double[N];
      Arrays.setAll(a,i -> dbl());
      return a;
    }

    public double[][] dbl(final int H,final int W){ return arr(new double[H][],i -> dbl(W)); }

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(final int H){ return arr(new char[H][],i -> ch()); }

    char[][] trans(final char[][] mat){
      char[][] ret = new char[mat[0].length][mat.length];

      for (int i = 0;i < mat.length;i++)
        for (int j = 0;j < mat[0].length;j++)
          ret[j][i] = mat[i][j];
      return ret;
    }

    String line(){
      StringBuilder sb = new StringBuilder();

      byte c;
      while (isPrintable(c = read()) || c == ' ')
        sb.append((char) c);
      return sb.toString();
    }

    String str(){
      StringBuilder sb = new StringBuilder();
      sb.append((char) nextPrintable());
      byte c;
      while (isPrintable(c = read()))
        sb.append((char) c);
      return sb.toString();
    }

    String[] str(final int N){ return arr(new String[N],i -> str()); }

    <T> T[] arr(final T[] arr,final IntFunction<T> f){
      Arrays.setAll(arr,f);
      return arr;
    }

    List<List<Integer>> fwd(final int N,final int M){
      List<List<Integer>> fwd = new ArrayList<>();
      for (int i = 0;i < N;i++)
        fwd.add(new LinkedList<>());

      for (int i = 0;i < M;i++)
        fwd.get(idx()).add(idx());

      return fwd;
    }

    List<List<Integer>> g(final int N,final int M){
      List<List<Integer>> fwd = fwd(N,M);
      List<List<Integer>> g = new ArrayList<>();
      for (int i = 0;i < fwd.size();i++)
        g.add(new LinkedList<>());

      for (int u = 0;u < fwd.size();u++) {
        g.get(u).addAll(fwd.get(u));
        for (int v:fwd.get(u))
          g.get(v).add(u);
      }
      return g;
    }

  }

  /* 出力 */
  static class MyWriter{
    OutputStream out;

    byte[] buf = new byte[1 <<16];
    byte[] ibuf = new byte[20];

    int tail = 0;

    public MyWriter(final OutputStream out){ this.out = out; }

    void flush(){
      try {
        out.write(buf,0,tail);
        tail = 0;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    void sp(){ write((byte) ' '); }

    void ln(){ write((byte) '\n'); }

    void write(final byte b){
      buf[tail++] = b;
      if (tail == buf.length)
        flush();
    }

    void write(final byte[] b,final int off,final int len){
      for (int i = off;i < off +len;i++)
        write(b[i]);
    }

    void write(long n){
      if (n < 0) {
        n = -n;
        write((byte) '-');
      }

      int i = ibuf.length;
      do {
        ibuf[--i] = (byte) (n %10 +'0');
        n /= 10;
      } while (n > 0);

      write(ibuf,i,ibuf.length -i);
    }

    void println(final boolean b){ println(b ? yes : no); }

    void println(final long n){
      write(n);
      ln();
    }

    public void println(final double d){ println(String.valueOf(d)); }

    void println(final String s){
      for (byte b:s.getBytes())
        write(b);
      ln();
    }

    public void println(final char[] s){
      for (char b:s)
        write((byte) b);
      ln();
    }

    void println(final int[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

    void println(final long[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

  }
}