import java.util.*;

public class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    int [] a = new int[n];
    int count = 0;
    for(int i = 0;i < (n - 1);i ++){
      a[i] = sc.nextInt();
       count = count + a[i];
    }
    
    if(count == 0){
      System.out.println(count);
    }else{
       System.out.println(-count);
    }
  
  }
}