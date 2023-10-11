import java.util.*;

class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();
    int y = sc.nextInt();
    int z = sc.nextInt();
    
    if((x > y && y < z && y >0) || (x < y && y > z && y < 0) ){
      System.out.print(-1);
    }else if((x < y && y > 0) || (x > y && y < 0) || (y > z && z > 0) || (y < z && z < 0)){
      System.out.print(Math.abs(x));
    }else{
      System.out.print(Math.abs(x) + Math.abs(z)*2);
    }
    
    
    
  }
}