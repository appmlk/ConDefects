import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) {
    FastScanner sc = new FastScanner();
    long a = sc.nextLong();
    long b = sc.nextLong();
    long c = sc.nextLong();
    long d = sc.nextLong();
    a = (a + b) % 2L == 0L ? a: a -1L;
    c = (c + d) % 2L == 0L ? c: c -1L;
    c = Math.abs(c - a);
    a = 0L;
    d = Math.abs(d - b);
    b = 0L;
    long ans = 0L;
    
    if(d%2L == 1L && c%2L == 0L) c = Math.abs(c - 1L);
    if(d%2L == 0L && c%2L == 1L) c = Math.abs(c - 1L);
    if(d > c) {
      ans = d;
    
    } else {
      ans = d;
      ans += (c - d) / 2L;
    }
    System.out.println(ans);
    
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