import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main{
  /* 定数 */
  static int infI = (int) 1e9;
  static long infL = (long) 1e18;
  // static long mod = (int) 1e9 +7;
  static long mod = 998244353;
  static String yes = "Yes";
  static String no = "No";

  /* 入出力とか */
  long st = System.currentTimeMillis();

  MyReader in = new MyReader(System.in);
  MyWriter out = new MyWriter(System.out);
  MyLogger log = new MyLogger();

  boolean local(){
    //    out.out = new ByteArrayOutputStream();
    return true;
  }

  int T = in.it();

  Long solve(){

    while (T-- > 0) {
      int N = in.it();
      int[] A = in.it(N);
      int[] B = in.it(N);
      out.println(solve(N,A,B));
    }

    return null;
  }

  boolean solve(int N,int[] A,int[] B){
    List<Integer> listB = new ArrayList<>();
    listB.add(B[0]);
    boolean canRotate = B[N -1] == B[0];
    for (int i = 1;i < N;i++)
      if (B[i -1] != B[i])
        listB.add(B[i]);
      else
        canRotate = true;

    A = Arrays.copyOf(A,N <<1);
    for (int i = 0;i < N;i++)
      A[i +N] = A[i];
    for (int s = 0;s < N;s++)
      if (can(N,Arrays.copyOf(A,N <<1),B,s,canRotate))
        return true;

    return false;
  }

  boolean can(int N,int[] A,int[] B,int s,boolean canRotate){
    if (!canRotate) {
      for (int i = 0;i < N;i++)
        if (A[i +s] != B[i])
          return false;

      return true;
    }

    int a = s;

    int t = B[0] == B[N -1] ? 1 : 0;

    for (int i = 0;i < N;i++) {
      while (a < s +N +t && B[i] != A[a])
        a++;

      if (a == s +N +t)
        return false;
    }

    return true;

  }

  /* Util */
  static long mod(long n){ return (n %mod +mod) %mod; }

  static int[][] trans(int[][] M){
    int[][] ret = new int[M[0].length][M.length];

    for (int i = 0;i < M.length;i++)
      for (int j = 0;j < M[0].length;j++)
        ret[j][i] = M[i][j];
    return ret;
  }

  static long[][] trans(long[][] M){
    long[][] ret = new long[M[0].length][M.length];

    for (int i = 0;i < M.length;i++)
      for (int j = 0;j < M[0].length;j++)
        ret[j][i] = M[i][j];
    return ret;
  }

  static int[][] toi(char[][] s){
    int[][] ret = new int[s.length][];
    Arrays.setAll(ret,i -> toi(s[i]));
    return ret;
  }

  static int[] toi(char[] s){
    int[] ret = new int[s.length];
    Arrays.setAll(ret,i -> toi(s[i]));
    return ret;
  }

  static int toi(char c){
    if (c == '.')
      return 0;
    if (c == '#')
      return 1;
    if ('a' <= c && c <= 'z')
      return c -'a';
    if ('A' <= c && c <= 'Z')
      return c -'A';
    if ('0' <= c && c <= '9')
      return c -'0';
    return c;
  }

  /* 実行 */
  public static void main(String[] args){ new Main().exe(); }

  long elapsed(){ return System.currentTimeMillis() -st; }

  void exe(){
    assert local();
    Optional.ofNullable(solve()).ifPresent(out::println);
    out.flush();
    log.println(elapsed());
  }

  /* 入力 */
  static class MyReader{
    byte[] buf = new byte[1 <<16];
    int ptr = 0;
    int tail = 0;
    InputStream in;

    MyReader(InputStream in){ this.in = in; }

    byte read(){
      if (ptr == tail)
        try {
          tail = in.read(buf);
          ptr = 0;
        } catch (IOException e) {}
      return buf[ptr++];
    }

    boolean isPrintable(byte c){ return 32 < c && c < 127; }

    boolean isNum(byte c){ return 47 < c && c < 58; }

    byte nextPrintable(){
      byte ret = read();
      while (!isPrintable(ret))
        ret = read();
      return ret;
    }

    int it(){ return (int) lg(); }

    int[] it(int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> it());
      return a;
    }

    int[][] it(int H,int W){ return arr(new int[H][],i -> it(W)); }

    int idx(){ return it() -1; }

    int[] idx(int N){
      int[] a = new int[N];
      Arrays.setAll(a,i -> idx());
      return a;
    }

    int[][] idx(int H,int W){ return arr(new int[H][],i -> idx(W)); }

    long lg(){
      byte i = nextPrintable();
      boolean negative = i == 45;
      long n = negative ? 0 : i -'0';
      while (isPrintable(i = read()))
        n = 10 *n +i -'0';
      return negative ? -n : n;
    }

    long[] lg(int N){
      long[] a = new long[N];
      Arrays.setAll(a,i -> lg());
      return a;
    }

    long[][] lg(int H,int W){ return arr(new long[H][],i -> lg(W)); }

    double dbl(){ return Double.parseDouble(str()); }

    double[] dbl(int N){
      double[] a = new double[N];
      Arrays.setAll(a,i -> dbl());
      return a;
    }

    double[][] dbl(int H,int W){ return arr(new double[H][],i -> dbl(W)); }

    char[] ch(){ return str().toCharArray(); }

    char[][] ch(int H){ return arr(new char[H][],i -> ch()); }

    String line(){
      StringBuilder sb = new StringBuilder();

      for (byte c;isPrintable(c = read()) || c == ' ';)
        sb.append((char) c);
      return sb.toString();
    }

    String str(){
      StringBuilder sb = new StringBuilder();
      sb.append((char) nextPrintable());

      for (byte c;isPrintable(c = read());)
        sb.append((char) c);
      return sb.toString();
    }

    String[] str(int N){ return arr(new String[N],i -> str()); }

    <T> T[] arr(T[] arr,IntFunction<T> f){
      Arrays.setAll(arr,f);
      return arr;
    }

    int[][] g(int N,int M,boolean d){
      List<List<Integer>> g = new ArrayList<>();
      for (int i = 0;i < N;i++)
        g.add(new ArrayList<>());

      for (int i = 0,u,v;i < M;i++) {
        g.get(u = idx()).add(v = idx());
        if (!d)
          g.get(v).add(u);
      }

      int[][] ret = new int[N][];
      for (int u = 0;u < N;u++) {
        ret[u] = new int[g.get(u).size()];
        for (int i = 0;i < ret[u].length;i++)
          ret[u][i] = g.get(u).get(i);
      }

      return ret;
    }
  }

  /* 出力 */
  static class MyWriter{
    OutputStream out;
    byte[] buf = new byte[1 <<16];
    byte[] ibuf = new byte[20];
    int tail = 0;

    public MyWriter(OutputStream out){ this.out = out; }

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

    void write(byte b){
      buf[tail++] = b;
      if (tail == buf.length)
        flush();
    }

    void write(byte[] b,int off,int len){
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

    void println(boolean b){ println(b ? yes : no); }

    void println(long n){
      write(n);
      ln();
    }

    void println(double d){ println(String.valueOf(d)); }

    void println(String s){ println(s.toCharArray()); }

    void println(char[] s){
      for (char b:s)
        write((byte) b);
      ln();
    }

    void println(int[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

    void println(long[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        write(a[i]);
      }
      ln();
    }

    void println(double[] a){
      for (int i = 0;i < a.length;i++) {
        if (0 < i)
          sp();
        for (char b:String.valueOf(a[i]).toCharArray())
          write((byte) b);
      }
      ln();
    }

  }

  /* デバッグ用 */
  static class MyLogger{
    MyWriter log = new MyWriter(System.err){
      @Override
      void ln(){
        super.ln();
        flush();
      };
    };

    void println(Object obj){ assert write(obj); }

    boolean write(Object obj){
      if (obj instanceof Boolean)
        log.println((boolean) obj);
      else if (obj instanceof char[])
        log.println((char[]) obj);
      else if (obj instanceof int[])
        log.println((int[]) obj);
      else if (obj instanceof long[])
        log.println((long[]) obj);
      else if (obj instanceof double[])
        log.println((double[]) obj);
      else
        log.println(Objects.toString(obj));
      return true;
    }
  }
}