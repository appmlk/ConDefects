import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    double dn = (double)n;
    dn *= 0.2;
    int ans = (int)Math.round(dn);
    n = (int)(dn*5);
    System.out.println(ans);
  }
}