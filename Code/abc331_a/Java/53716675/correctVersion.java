import java.util.*;

public class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int month = sc.nextInt();
    int day = sc.nextInt();
    int y = sc.nextInt();
    int m = sc.nextInt();
    int d = sc.nextInt();
    
    if(day == d){
      if(month == m){
        System.out.println((y + 1) + " " + "1 " + "1");
      }else{
        System.out.println(y +" " + (m + 1) +" " + "1" );
      }
    }else{
        System.out.println(y + " " + m + " " + (d + 1));
    }
  }
}