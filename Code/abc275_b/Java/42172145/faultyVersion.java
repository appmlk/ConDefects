import java.util.*;
import java.io.*;

class Main {
  public static void main (String[] args) {
    final long MOD = 998244353;
    Scanner sc = new Scanner (System.in);
    List<Long> a = new ArrayList<>();
    long multi1 = 1;
    long multi2 = 1;
    long ans = 0;
    for (int i = 0; i < 6; i++) {
      a.add(Long.parseLong(sc.next()));
      a.set(i, a.get(i) % MOD);
      //System.out.println(a.get(i));
    }
    for (int i = 0; i < 3; i++) {
      multi1 = multi1 * a.get(i) % MOD;
      //System.out.println(multi1);
    }
    for (int i = 3; i < 6; i++) {
      multi2 = multi2 * a.get(i) % MOD;
      //System.out.println(multi2);
    }
    ans = (multi1 - multi2) % MOD;
    PrintWriter output = new PrintWriter(System.out);
    output.println(ans);
    output.flush();
  }
}
      
