import java.util.*;

public class Main {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long L = sc.nextLong();
    int N1 = sc.nextInt();
    int N2 = sc.nextInt();
    Pair[] x1 = new Pair[N1];
    Pair[] x2 = new Pair[N2];
    for(int i = 0; i < N1; i++) {
      x1[i] = new Pair(sc.nextLong(), sc.nextLong());
    }
    for(int i = 0; i < N2; i++) {
      x2[i] = new Pair(sc.nextLong(), sc.nextLong());
    }
    long ans = 0;
    int i1 = 0;
    int i2 = 0;
    Pair c1 = x1[i1];
    Pair c2 = x2[i2];
    boolean update = true;
    while(update) {
      update = false;
      if ( c1.v.equals(c2.v) ) {
        long cnt = Math.min(c1.l, c2.l);
        if ( cnt > 0 ) {
          ans += cnt;
          c1 = c1.add(-cnt);
          c2 = c2.add(-cnt);
          update = true;
        }
      }
      else {
        long shift = Math.min(c1.l, c2.l);
        if ( shift > 0 ) {
          c1 = c1.add(-shift);
          c2 = c2.add(-shift);
          update = true;
        }
      }
      if ( c1.isZero() && (i1 + 1 < N1) ) {
        c1 = x1[++i1];
        update = true;
      }
      if ( c2.isZero() && (i2 + 1 < N2) ) {
        c2 = x2[++i2];
        update = true;
      }
    }
    System.out.println(ans);
  }
  
  private static class Pair {
    
    public final Long v;
    public final Long l;
    
    public Pair(Long v, Long l) {
      this.v = v;
      this.l = l;
    }
    
    public Pair add(long shift) {
      return new Pair(this.v, this.l + shift);
    }
    
    public boolean isZero() {
      return this.l.equals(0);
    }
  }
}