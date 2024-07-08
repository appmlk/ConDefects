import java.util.*;

class Abc {
  int n;
  String s;
  
  Abc(int n, String s) {
    this.n = n;
    this.s = s;
  }
  
  int find() {
    for(int i=0; i<=n-3; i++) {
      String sub = s.substring(i, i+3);
      if (sub.equalsIgnoreCase("ABC")) {
        return i+1;
      }
    }
    return -1;
  }
  
}

class Main {
  public static void main(String args[]) {
    
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();
    String s = scanner.nextLine();
    Abc problem = new Abc(n, s);
    
    System.out.println(problem.find());
  }  
}