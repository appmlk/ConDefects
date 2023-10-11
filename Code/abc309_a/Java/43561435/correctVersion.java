import java.util.*;
 
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int a1 = sc.nextInt();
    int a2 = sc.nextInt();
    if(a1 + 1 == a2 && a2 != 4 && a2 != 7){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }
}