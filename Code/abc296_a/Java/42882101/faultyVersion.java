import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    if(n == 1){
      System.out.println("No");
      return;
    }
    String[] s = sc.next().split("");
    
    for(int i = 0; i < n - 1; i++){
      if(s[i].equals(s[i+1])){
        System.out.println("No");
        return;
      }
    }
    System.out.println("Yes");
  }
}