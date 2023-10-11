import java.util.*;

class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int A = sc.nextInt();
    int B = sc.nextInt();
    int ans = 7;
    //4点の問題解けたかな？
    if(A < 4 && B < 4){
      ans -= 4;
    }
    //2点の問題は解けたかな？
    if(A % 4 < 2 && B % 4 < 2){
      ans -= 2;
    }
    //1点の問題は解けたかな？
    if(A % 2 < 1 && B % 2 < 1){
      ans--;
    }
    
    
    System.out.print(ans);
    
    
    
  }
}