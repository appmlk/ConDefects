import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.*;

public class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  private static PrintWriter out;

  public static void main(String[] args) {
    LOGGER.setUseParentHandlers(false);
    ConsoleHandler handler = new ConsoleHandler();
    handler.setFormatter(new SingleLineFormatter());
    LOGGER.addHandler(handler);

    Main main = new Main();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    try {
      main.run(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
    out.close();
  }

  private void run(String[] arguments) throws Exception {
    MyScanner sc = new MyScanner();
    int N = sc.nextInt();
    char[][] grid = new char[N][N];
    for (int i = 0; i < grid.length; i++) {
      grid[i] = sc.next().toCharArray();
    }

    Resolver resolver = new Resolver(grid);
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (resolver.execute(i, j)){
          out.println("Yes");
          return;
        }
      }
    }
    out.println("No");
    

  }

  class Resolver {
    char[][] grid;
    int TARGET_COUNT = 6;
    char GET_NG = 'x';

    char BLACK = '#';

    public Resolver(char[][] grid) {
      this.grid = grid;
    }

    private char getValueSafety(int x, int y){
      try {
        return grid[x][y];
      }catch (Exception e){
        return GET_NG;
      }
    }

    public boolean execute(int x , int y){
      return findHorizontal(x, y) || findVertical(x, y) || findDownwardRight(x, y) || findDownwardLeft(x, y);
    }

    private boolean findVertical(int x, int y){

      int count = 0;
      for (int i = 0; i < TARGET_COUNT; i++) {
        char check = getValueSafety(x+i, y);
        if (check == GET_NG) {
          return false;
        } else if (check == BLACK) {
          count++;
        }
      }
      return (count >= 4);
    }

    private boolean findHorizontal(int x, int y){
      int count = 0;
      for (int i = 0; i < TARGET_COUNT; i++) {
        char check = getValueSafety(x, y+i);
        if (check == GET_NG) {
          return false;
        } else if (check == BLACK) {
          count++;
        }
      }
      return (count >= 4);
    }

    private boolean findDownwardRight(int x, int y){
      int count = 0;
      for (int i = 0; i < TARGET_COUNT; i++) {
        char check = getValueSafety(x+i, y+i);
        if (check == GET_NG) {
          return false;
        } else if (check == BLACK) {
          count++;
        }
      }
      return (count >= 4);
    }

    private boolean findDownwardLeft(int x, int y){
      int count = 0;
      for (int i = 0; i < TARGET_COUNT; i++) {
        char check = getValueSafety(x+i, y-i);
        if (check == GET_NG) {
          return false;
        } else if (check == BLACK) {
          count++;
        }
      }
      return (count >= 4);
    }

  }

  static class SingleLineFormatter extends Formatter {

    private static final String format = "[%1$tF %1$tT] %2$s %n";

    @Override
    public String format(LogRecord record) {
      return String.format(format,
          new java.util.Date(record.getMillis()),
          record.getMessage()
      );
    }
  }

  /*
   * Form: http://codeforces.com/blog/entry/7018
   */
  private class MyScanner {

    BufferedReader br;
    StringTokenizer st;

    MyScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
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
  }
}
