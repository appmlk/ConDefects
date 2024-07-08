import java.io.*;
import java.util.*;
 
public class Main extends PrintWriter {
 
    class Point{
        long x;
        long y;
        
        public Point(long x_, long y_) {
            x = x_;
            y = y_;
        }
        
        long cross(Point other) {
            return x * other.y - y * other.x;
        }
        
        Point minus(Point other) {
            return new Point(x - other.x, y - other.y);
        }
        
        long triangle(Point b, Point c) {
            return (b.minus(this)).cross(c.minus(this));
        }
    }
    
    
    private void solve()  {
        N = sc.nextInt();
        P = new Point[N];
        for(int i = 0; i < N; i++) {
            P[i] = new Point(sc.nextLong(), sc.nextLong());
        }
        int Q = sc.nextInt();
        for(int i = 1; i <= Q; i++) {
            Point w = new Point(sc.nextLong(), sc.nextLong());
            long res = doit(w);
            if(res < 0) println("OUT");
            else if(res == 0) println("ON");
            else println("IN");
        }
    }
    
    int N;
    Point[] P;
    
    long doit(Point w) {
        int l = 0;
        int r = N;
        while(l < r-1) {
            int mid = (l+r) / 2;
            if(P[0].triangle(P[mid], w) > 0L) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if(r == N) return -1L;
        if(r == 1 && P[0].triangle(P[1], w) == 0) { // verify 012 (can only be at side 01)
            if(P[1].triangle(P[2], w) >= 0 && P[N-1].triangle(P[0], w) >= 0) return 0;
            else return -1;
        }
        if(r == N-1 && P[N-1].triangle(P[0], w) == 0) { // verify 0(N-2)(N-1) (can possibly be at side 0(N-1))
            if(P[N-2].triangle(P[N-1], w) >= 0 && P[0].triangle(P[N-1], w) >= 0) return 0;
            else return -1;
        }
        return P[l].triangle(P[r], w);
    }
    
//  Main() throws FileNotFoundException { super(new File("output.txt")); }
//  InputReader sc = new InputReader(new FileInputStream("test_input.txt"));
  Main() { super(System.out); }
  InputReader sc = new InputReader(System.in);
  static class InputReader {
      InputReader(InputStream in) { this.in = in; } InputStream in;
      
      private byte[] buf = new byte[16384];
      private int    curChar;
      private int    numChars;
      
 
      public int read() {
          if (numChars == -1)
              throw new InputMismatchException();
          if (curChar >= numChars) {
              curChar = 0;
              try {
                  numChars = in.read(buf);
              } catch (IOException e) {
                  throw new InputMismatchException();
              }
              if (numChars <= 0)
                  return -1;
          }
          return buf[curChar++];
      }
 
      public String nextLine() {
          int c = read();
          while (isSpaceChar(c))
              c = read();
          StringBuilder res = new StringBuilder();
          do {
              res.appendCodePoint(c);
              c = read();
          } while (!isEndOfLine(c));
          return res.toString();
      }
 
      public String nextString() {
          int c = read();
          while (isSpaceChar(c))
              c = read();
          StringBuilder res = new StringBuilder();
          do {
              res.appendCodePoint(c);
              c = read();
          } while (!isSpaceChar(c));
          return res.toString();
      }
 
      public long nextLong() {
          int c = read();
          while (isSpaceChar(c))
              c = read();
          int sgn = 1;
          if (c == '-') {
              sgn = -1;
              c = read();
          }
          long res = 0;
          do {
              if (c < '0' || c > '9')
                  throw new InputMismatchException();
              res *= 10;
              res += c - '0';
              c = read();
          } while (!isSpaceChar(c));
          return res * sgn;
      }
 
      public int nextInt() {
          int c = read();
          while (isSpaceChar(c))
              c = read();
          int sgn = 1;
          if (c == '-') {
              sgn = -1;
              c = read();
          }
          int res = 0;
          do {
              if (c < '0' || c > '9')
                  throw new InputMismatchException();
              res *= 10;
              res += c - '0';
              c = read();
          } while (!isSpaceChar(c));
          return res * sgn;
      }
 
      private boolean isSpaceChar(int c) {
          return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
      }
 
      private boolean isEndOfLine(int c) {
          return c == '\n' || c == '\r' || c == -1;
      }
  }
 
    public static void main(String[] $) {
        new Thread(null, new Runnable() {
            public void run() {
                long start = System.nanoTime();
                try {Main solution = new Main(); solution.solve(); solution.flush();}
//                try {Main solution = new Main(); solution.gen(); solution.flush();} 
                catch (Exception e) {e.printStackTrace(); System.exit(1);}
                System.err.println((System.nanoTime()-start)/1E9);
            }
        }, "1", 1 << 27).start();
 
    }
    
    
    
}