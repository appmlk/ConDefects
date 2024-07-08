

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt(); 
    int M = scanner.nextInt();
    String S = scanner.next();
    String T = scanner.next();
    scanner.close();
    
    if ( T.startsWith(S) && T.endsWith(S)) {
      System.out.println(0);
    } else if (T.startsWith(S) && !T.endsWith(S)) {
      System.out.println(1);
    } else if (!T.startsWith(S) && T.endsWith(S)) {
      System.out.println(2);
    } else if (!T.startsWith(S) && !T.endsWith(S)) {
      System.out.println(4);
    }

  }
}
