import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    
    List<String> strList = new ArrayList<>(); 
    
    for(int i=1; i<=n; i++){
      if(i%3 == 0){
        strList.add("x");
      }else{
      strList.add("o");
      }
    }
    
    for(String str : strList){
      System.out.print(str);
    }
  }
}