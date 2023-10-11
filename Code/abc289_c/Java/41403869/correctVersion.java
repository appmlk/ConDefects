import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

  private static PrintWriter out;

  public static void main(String[] args) {
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
    int M = sc.nextInt();

    // M個の集合
    ArrayList<Integer[]> unions = new ArrayList<>();

    for (int i = 0; i < M; i++) {
      int C = sc.nextInt();
      Integer[] S = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
      unions.add(S);
    }

    ArrayDeque<HashSet<Integer>> allCombinations = new ArrayDeque<>();
    for (int i = 0; i < Math.pow(2, M); i++) {
      int addCount = 0;
      HashSet<Integer> uniqueNumbers = new HashSet<>();
      int[] debug = new int[M];

      for (int j = 0; j < M; j++) {
        if ((1&i>>j) == 1){
          addCount++;
          Integer[] union = unions.get(j);
          for (Integer integer : union) {
            uniqueNumbers.add(integer);
          }
        }
      }

      if (addCount >= 1) {
        allCombinations.add(uniqueNumbers);
      }

    }

    int ans = 0;
    while (!allCombinations.isEmpty()){
      HashSet<Integer> currentUnion = allCombinations.pop();
      boolean isOk = true;
      // 1 ~ N
      for (int i = 1; i <= N; i++) {
        if (!currentUnion.contains(i)){
          isOk = false;
          break;
        }
      }
      if (isOk){
        ans++;
      }
    }

    out.println(ans);


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
