import java.util.*;
public class Main{
  public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
    
    int h = sc.nextInt();
    int syo = 0;
    int day;
    
    for(day=0; syo<h; day++){
      syo += Math.pow(2, day);
    }
    System.out.println(day);
  }
}