import java.util.*;
 
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int a1 = sc.nextInt();
    int a2 = sc.nextInt();
    if(a1 + 1 == a2 || a1 - 1 == a2){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }
}