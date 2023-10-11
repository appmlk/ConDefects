import java.util.*;
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    double x = sc.nextDouble();
    if(x >= Math.pow(-2,31) && x < Math.pow(2,31)){
      System.out.println("Yes");
    }else{
      System.out.println("No");
    }
  }
}