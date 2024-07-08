import java.util.*;

public class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String s = sc.next();
    int num = s.indexOf("ABC");
    if(num != -1){
      System.out.println(num);
    }else{
      System.out.println(-1);
    }
  }
}