import java.util.*;

class Main{
  public static void main(String[] args){
    String ex = "atcoder";
    
    int a, b;
    Scanner s = new Scanner(System.in);
    a = s.nextInt();
    b = s.nextInt();
    
    String ans = ex.substring(a, b);
    
    System.out.println(ans);
  }
}