import java.io.*;
import java.util.*;
import java.util.function.*;

class ModCalculator {
  private final long mod;
  private final ModInverseCache modInverseCache;
  private final ModCombinationCache modCombinationCache;

  ModCalculator(long mod) {
    this.mod = mod;
    this.modInverseCache = new ModInverseCache();
    this.modCombinationCache = new ModCombinationCache();
  }

  public long norm(long v) {
    long nogmalized = v % mod;
    if (nogmalized < 0) {
      nogmalized += mod;
    }
    return nogmalized;
  }

  public long add(long a, long b) { return norm(a + b); }

  public long sub(long a, long b) { return norm(a - b + mod); }

  public long mul(long a, long b) { return norm(a * b); }

  public long pow(long a, long b) {
    if (b == 0) {
      return 1;
    }
    long v = pow(mul(a, a), b / 2);
    if (b % 2 == 1) {
      return mul(v, a);
    } else {
      return v;
    }
  }

  public long inverse(long a) { return pow(a, mod - 2); }

  public long inverseFromCache(int a) { return modInverseCache.get(a); }

  public long div(long a, long b) { return mul(a, inverse(b)); }

  // Verify ARC 042 D
  // https://atcoder.jp/contests/arc042/tasks/arc042_d
  // a^x mod p === b
  // return -1 there is no such positive x
  public long log(long a, long b) {
    Map<Long, Long> map = new HashMap<>();
    long powA = 1;
    long rootP = 0;
    while (true) {
      if (powA == b && rootP != 0) {
        return rootP;
      }
      if (map.containsKey(powA)) {
        return -1;
      }
      map.put(powA, rootP);
      powA = mul(powA, a);
      rootP++;
      if (rootP * rootP > mod) {
        break;
      }
    }
    long inversePowA = inverse(powA);
    for (int i = 1; i <= rootP; i++) {
      b = mul(b, inversePowA);
      Long value = map.get(b);
      if (value != null && value + rootP * i > 0) {
        return value + rootP * i;
      }
    }
    return -1;
  }

  public long getF(int n) { return modCombinationCache.getF(n); }

  public long getP(int n, int r) { return modCombinationCache.getP(n, r); }

  public long getC(int n, int k) { return modCombinationCache.getC(n, k); }

  // Verify ttpc2019 J
  // https://atcoder.jp/contests/ttpc2019/tasks/ttpc2019_j
  class PrimitiveLongList {
    long[] values;
    int size;

    public PrimitiveLongList() { values = new long[10]; }

    private void resize() {
      long[] newValues = new long[values.length * 2];
      System.arraycopy(values, 0, newValues, 0, values.length);
      values = newValues;
    }

    public void add(long value) {
      if (size >= values.length) {
        resize();
      }
      values[size] = value;
      size++;
    }

    private void validateIndex(int index) {
      if (index < 0 || size <= index) {
        throw new IndexOutOfBoundsException(
            String.format("size: %d, index: %d", size, index));
      }
    }

    public long get(int index) {
      validateIndex(index);
      return values[index];
    }

    public void set(int index, long value) {
      validateIndex(index);
      values[index] = value;
    }

    public int size() { return size; }
  }

  // Verify AGC 040 C
  // https://atcoder.jp/contests/agc040/tasks/agc040_c
  class ModInverseCache {
    private final PrimitiveLongList inverseCache;

    public ModInverseCache() {
      inverseCache = new PrimitiveLongList();
      inverseCache.add(0L);
      inverseCache.add(1L);
    }

    private void resize(int n) {
      for (int i = inverseCache.size(); i <= n; i++) {
        long k = mod / i;
        int r = (int)(mod % i);
        long inverse = mul(-k, inverseCache.get(r));
        inverseCache.add(inverse);
      }
    }

    long get(int n) {
      resize(n);
      return inverseCache.get(n);
    }
  }

  class ModCombinationCache {
    private final PrimitiveLongList factorialCache;
    private final PrimitiveLongList factorialInverseCache;

    public ModCombinationCache() {
      factorialCache = new PrimitiveLongList();
      factorialCache.add(1L);
      factorialInverseCache = new PrimitiveLongList();
      factorialInverseCache.add(1L);
    }

    private void resize(int n) {
      for (int i = factorialCache.size() - 1; i < n; i++) {
        factorialCache.add(mul(factorialCache.get(i), i + 1));
        factorialInverseCache.add(
            mul(factorialInverseCache.get(i), modInverseCache.get(i + 1)));
      }
    }

    long getF(int n) {
      resize(n);
      return factorialCache.get(n);
    }

    long getP(int n, int r) {
      resize(n);
      return mul(factorialCache.get(n), factorialInverseCache.get(n - r));
    }

    long getC(int n, int k) {
      resize(n);
      return mul(factorialCache.get(n), mul(factorialInverseCache.get(k),
                                            factorialInverseCache.get(n - k)));
    }
  }
}

class Solver {
  private final int n;
  private final int[][] abs;

  private List<List<Integer>> graph;
  private ModCalculator mc;

  private long[] mergeHalf(long[] dp1, long[] dp2) {
    int s1 = dp1.length;
    int s2 = dp2.length;
    long[] output = new long[s1 + s2];
    for (int i = 0; i < s1; i++) {
      long dp2sum = 0;
      for (int k = s2 - 1; k >= 0; k--) {
        dp2sum = mc.add(dp2sum, dp2[k]);
        long v = mc.mul(dp1[i], dp2sum);
        v = mc.mul(v, mc.getC(i + k, k));
        v = mc.mul(v, mc.getC(s1 - i - 1 + s2 - k, s2 - k));
        v = mc.add(v, output[i + k]);
        output[i + k] = v;
      }
    }
    return output;
  }

  private long[] merge(long[] dp1, long[] dp2) {
    if (dp1 == null) {
      return dp2;
    }
    if (dp2 == null) {
      return dp1;
    }

    long[] output1 = mergeHalf(dp1, dp2);
    long[] output2 = mergeHalf(dp2, dp1);
    for (int i = 0; i < output1.length; i++) {
      output1[i] = mc.add(output1[i], output2[i]);
    }
    return output1;
  }

  private long[] dfs(int index, int parentIndex) {
    long[] dp = null;
    for (int childIndex : graph.get(index)) {
      if (childIndex == parentIndex) {
        continue;
      }
      dp = merge(dp, dfs(childIndex, index));
    }

    if (dp == null) {
      return new long[]{1};
    }

    long[] outputReverse = new long[dp.length + 1];
    outputReverse[dp.length] = 0;
    for (int i = dp.length - 1; i >= 0; i--) {
      outputReverse[i] = outputReverse[i + 1] + dp[i];
    }
    long[] output = new long[outputReverse.length];
    for (int i = 0; i < outputReverse.length; i++) {
      output[i] = outputReverse[outputReverse.length - 1 - i];
    }
    return output;
  }

  public Solver(int n, int[][] abs) {
    this.n = n;
    this.abs = abs;
  }

  public long solve() {
    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    mc = new ModCalculator(998244353);
    for (int[] ab : abs) {
      int a = ab[0];
      int b = ab[1];
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    long[] dp = dfs(1, -1);
    long answer = 0;
    for (long v : dp) {
      answer = mc.add(answer, v);
    }
    return mc.mul(answer, 2);
  }
};

public class Main {
  private static void execute(ContestReader reader, ContestWriter out) {
    int n = reader.nextInt();
    int[][] abs = reader.nextInt(n - 1, 2);
    out.println(new Solver(n, abs).solve());
  }
  
  public static void main(String[] args) {
    ContestReader reader = new ContestReader(System.in);
    ContestWriter out = new ContestWriter(System.out);
    execute(reader, out);
    out.flush();
  }
}

class ContestWriter extends PrintWriter {
  ContestWriter(PrintStream printStream) {
    super(printStream);
  }

  public void printList(List<? extends Object> list) {
    for (Object object : list) {
      println(object);
    }
  }

  public void printListOneLine(List<? extends Object> list) {
    List<String> stringList = new ArrayList<>();
    for (Object object : list) {
      stringList.add(object.toString());
    }
    println(String.join(" ", stringList));
  }
}

class ContestReader {
  private static final int BUFFER_SIZE = 1024;
  
  private final InputStream stream;
  private final byte[] buffer;
  private int pointer;
  private int bufferLength;
  
  ContestReader(InputStream stream) {
    this.stream = stream;
    this.buffer = new byte[BUFFER_SIZE];
    this.pointer = 0;
    this.bufferLength = 0;
  }
  
  private boolean hasNextByte() {
    if (pointer < bufferLength) {
      return true;
    }
    
    pointer = 0;
    try {
      bufferLength = stream.read(buffer);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return bufferLength > 0;
  }
  
  private int readByte() {
    if (hasNextByte()) {
      return buffer[pointer++];
    } else {
      return -1;
    }
  }
  
  private static boolean isPrintableChar(int c) {
    return 33 <= c && c <= 126;
  }
  
  public boolean hasNext() {
    while (hasNextByte() && !isPrintableChar(buffer[pointer])) {
      pointer++;
    }
    return hasNextByte();
  }
  
  public String next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    StringBuilder sb = new StringBuilder();
    while(true) {
      int b = readByte();
      if (!isPrintableChar(b)) {
        break;
      }
      sb.appendCodePoint(b);
    }
    return sb.toString();
  }
  
  public String nextLine() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    StringBuilder sb = new StringBuilder();
    while(true) {
      int b = readByte();
      if (!isPrintableChar(b) && b != 0x20) {
        break;
      }
      sb.appendCodePoint(b);
    }
    return sb.toString();
  }
  
  public char nextChar() {
    return next().charAt(0);
  }
  
  public int nextInt() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    
    int n = 0;
    boolean minus = false;
    
    {
      int b = readByte();
      if (b == '-') {
        minus = true;
      } else if ('0' <= b && b <= '9') {
        n = b - '0';
      } else {
        throw new NumberFormatException();
      }
    }
    
    while(true){
      int b = readByte();
      if ('0' <= b && b <= '9') {
        n *= 10;
        n += b - '0';
      } else if (b == -1 || !isPrintableChar(b)) {
        return minus ? -n : n;
      } else {
        throw new NumberFormatException();
      }
    }
  }
  
  public long nextLong() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    
    long n = 0;
    boolean minus = false;
    
    {
      int b = readByte();
      if (b == '-') {
        minus = true;
      } else if ('0' <= b && b <= '9') {
        n = b - '0';
      } else {
        throw new NumberFormatException();
      }
    }
    
    while(true){
      int b = readByte();
      if ('0' <= b && b <= '9') {
        n *= 10;
        n += b - '0';
      } else if (b == -1 || !isPrintableChar(b)) {
        return minus ? -n : n;
      } else {
        throw new NumberFormatException();
      }
    }
  }
  
  public double nextDouble() {
    return Double.parseDouble(next());
  }
  
  public String[] next(int n) {
    String[] array = new String[n];
    for (int i = 0; i < n; i++) {
      array[i] = next();
    }
    return array;
  }
  
  public String[] nextLine(int n) {
    String[] array = new String[n];
    for (int i = 0; i < n; i++) {
      array[i] = nextLine();
    }
    return array;
  }
  
  public char[] nextChar(int n) {
    char[] array = new char[n];
    for (int i = 0; i < n; i++) {
      array[i] = nextChar();
    }
    return array;
  }
  
  public int[] nextInt(int n) {
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = nextInt();
    }
    return array;
  }
  
  public long[] nextLong(int n) {
    long[] array = new long[n];
    for (int i = 0; i < n; i++) {
      array[i] = nextLong();
    }
    return array;
  }
  
  public double[] nextDouble(int n) {
    double[] array = new double[n];
    for (int i = 0; i < n; i++) {
      array[i] = nextDouble();
    }
    return array;
  }
  
  public char[] nextCharArray() {
    return next().toCharArray();
  }
  
  public String[][] next(int n, int m) {
    String[][] matrix = new String[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = next();
      }
    }
    return matrix;
  }
  
  public int[][] nextInt(int n, int m) {
    int[][] matrix = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = nextInt();
      }
    }
    return matrix;
  }
  
  public char[][] nextChar(int n, int m) {
    char[][] matrix = new char[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = nextChar();
      }
    }
    return matrix;
  }
  
  public long[][] nextLong(int n, int m) {
    long[][] matrix = new long[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = nextLong();
      }
    }
    return matrix;
  }
  
  public double[][] nextDouble(int n, int m) {
    double[][] matrix = new double[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = nextDouble();
      }
    }
    return matrix;
  }
  
  public char[][] nextCharArray(int n) {
    char[][] matrix = new char[n][];
    for (int i = 0; i < n; i++) {
      matrix[i] = next().toCharArray();
    }
    return matrix;
  }
}

class MyAssert {
  public static void myAssert(boolean flag, String message) {
    if (!flag) {
      throw new RuntimeException(message);
    }
  }
 
  public static void myAssert(boolean flag) {
    myAssert(flag, "");
  }
}
