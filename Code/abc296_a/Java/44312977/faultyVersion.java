import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    int n = s.nextInt();
    String fm = s.next();
    
    if(fm.indexOf("ff")==-1&&fm.indexOf("mm")==-1){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }
}