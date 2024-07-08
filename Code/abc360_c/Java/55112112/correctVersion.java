import java.util.*;
import java.io.*;
class Main {
//  public static box[];
  public static void main(String[] args) {
    FastScanner sc = new FastScanner();
    int n = Integer.parseInt(sc.next());
    List<Long> a = new ArrayList<>();
    Long ans = 0L;
    Map<Long, Long> w = new HashMap<>();
    for(int j = 0; j < n; j++) {
      a.add(sc.nextLong());
    }
    for(int i = 0; i < n; i++) {
      long temp = sc.nextLong();
      if(!w.containsKey(a.get(i))) {
        w.put(a.get(i), temp);
      } else {
        ans += Math.min(w.get(a.get(i)), temp);
        w.put(a.get(i), Math.max(w.get(a.get(i)), temp));
      }
    }
    System.out.print(ans);
  }
}
class FastScanner {
  private final InputStream in = System.in;
  private final byte[] buffer = new byte[1024];
  private int ptr = 0;
  private int buflen = 0;
  private boolean hasNextByte() {
    if (ptr < buflen) {
      return true;
    }else{
      ptr = 0;
      try {
        buflen = in.read(buffer);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (buflen <= 0) {
        return false;
      }
    }
    return true;
  }
  private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
  private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
  private void skipUnprintable() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
  public boolean hasNext() { skipUnprintable(); return hasNextByte();}
  public String next() {
    if (!hasNext()) throw new NoSuchElementException();
    StringBuilder sb = new StringBuilder();
    int b = readByte();
    while(isPrintableChar(b)) {
      sb.appendCodePoint(b);
      b = readByte();
    }
    return sb.toString();
  }
  public long nextLong() {
    if (!hasNext()) throw new NoSuchElementException();
    long n = 0;
    boolean minus = false;
    int b = readByte();
    if (b == '-') {
      minus = true;
      b = readByte();
    }
    if (b < '0' || '9' < b) {
      throw new NumberFormatException();
    }
    while(true){
      if ('0' <= b && b <= '9') {
        n *= 10;
        n += b - '0';
      }else if(b == -1 || !isPrintableChar(b)){
        return minus ? -n : n;
      }else{
        throw new NumberFormatException();
      }
      b = readByte();
    }
  }
}