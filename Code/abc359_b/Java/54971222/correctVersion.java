import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), a1 = sc.nextInt(), a2 = sc.nextInt(), an, count = 0;
    for(int i=2; i<2*n; i++) {
      an = sc.nextInt();
      if(a1 == an) ++count;
      a1 = a2;
      a2 = an;
    }
    System.out.print(count);
  }
}