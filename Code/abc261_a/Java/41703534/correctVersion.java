import java.util.*;
public class Main{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int L1 = in.nextInt();
    int R1 = in.nextInt();
    int L2 = in.nextInt();
    int R2 = in.nextInt();
    int ans = 0;
    for(int i = 0 ; i <= 100; i++){
      if(L1 <= i && i <= R1){
        if(L2 <= i && i <= R2){
          ans++;
        }
      }
    }
    if(ans > 0){
      ans--; 
    }
    System.out.println(ans);
  }
}
