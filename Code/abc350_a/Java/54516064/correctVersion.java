import java.util.*;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String S = sc.next();
    if(S.substring(3).equals("316")){
      System.out.print("No");
    }else{
      if(Integer.parseInt(S.substring(3))<350 && Integer.parseInt(S.substring(3))>0){
        System.out.print("Yes");  
      }else{
        System.out.print("No");
      }
    }
    sc.close();
  }
}