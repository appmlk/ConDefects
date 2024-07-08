import java.util.*;
 
public class Main {
  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    
    String S = sc.next();
    String judge = "ABC";
    
    int result = S.indexOf(judge);
    int len = S.length();
    
    if (len > N){
      S = S.substring(0, N);
    }

    if (result >= 0){
      result = result + 1;
    }
    System.out.println(result);
  } 
}