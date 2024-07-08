import java.util.*;

public class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    String n = sc.next();
    int count = 0;
    for(int i = 1; i < n.length();i = i + 2){
      if(n.substring(i,i+1).equals("0")){
        count ++;
      }else{
        break;
      }
    }
    if(count==16){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
    
  }
}