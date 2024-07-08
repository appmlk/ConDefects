import java.util.*;

public class Main{
  public static void main(String[] args)throws Exception{
    Scanner sc = new Scanner(System.in);
    
  int h = sc.nextInt();
  int count = 0;
  int p = 1;
  
  while(p<h){
    count++;
    p*=2;
  }
  
  System.out.println(count);
  }
}