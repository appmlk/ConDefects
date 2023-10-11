import java.util.*;
public class Main {
  public static void main(String[] args){
    Scanner abc = new Scanner(System.in);
    int red = abc.nextInt();
    if(red>=42){
      System.out.println("AGC0"+red);
    }else{
      if(red>=9){
        System.out.println("AGC0"+red);
      }else{
        System.out.println("AGC00"+red);
      }
    }
  }
}