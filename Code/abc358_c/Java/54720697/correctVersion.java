// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N=sc.nextInt();
    int M=sc.nextInt();
    String[] S=new String[N];
    for(int i=0;i<N;i++){
      S[i]=sc.next();
    }
    int ans=N;
    for(int bit=0;bit<(1<<N);bit++){
      boolean[] exist=new boolean[M];
      int cnt=0;
      for(int i=0;i<N;i++){
        if((bit>>i&1)==1){
         cnt++;
          for(int j=0;j<M;j++){
            if(S[i].charAt(j)=='o'){
              exist[j]=true;
            }
          }
        }
      }
      boolean allExist=true;
      for(int j=0;j<M;j++){
        if(!exist[j]){
          allExist=false;
          break;
        }
      }
      if(allExist){
        ans=Math.min(ans,cnt);
      }
    }
    System.out.println(ans);
    // @Test
    // void addition() {
    // assertEquals(2, 1 + 1);
  }
}