import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String S = sc.next();
    
    int length = S.length();
    
    Set<String> partString = new HashSet<>();
    
    for(int i=1;i<=length;i++){
      for(int j=0;j<=length-i;j++){
        String temp = S.substring(j,j+i);
        partString.add(temp);
      }
    }
    
    System.out.println(partString.size());
  }
}