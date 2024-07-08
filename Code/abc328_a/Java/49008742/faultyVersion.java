import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int X = sc.nextInt();
    int ans = 0;
    for(int i = 0; i < N; i++){
      int H = sc.nextInt();
      if(X >= H){
        ans += 1;
      }
    }
    System.out.print(ans);
  }
}