import java.util.*;
public class Main {
  public static void main( String[] args ){
    Scanner scan = new Scanner(System.in);
    
    int n = scan.nextInt();
    int x = scan.nextInt();
    int y = scan.nextInt();
    int z = scan.nextInt();
    
    
      if((x <= z && z <= y) || (y <= z && z <= x)){
        System.out.println("Yes");
      }else{
        System.out.println("NO");
      }
  }
}