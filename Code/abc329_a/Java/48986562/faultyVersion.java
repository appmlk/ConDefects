import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    String S = sc.next();
    
    for(int i = 0; i < S.length(); i++){
      System.out.print(S.charAt(i));
      if(i < S.length()-1){
        System.out.print("　");
      }
    }
  }
}