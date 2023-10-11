import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    String fm = s.next();
    
    if(fm.indexOf("FF")==-1&&fm.indexOf("MM")==-1){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }
}