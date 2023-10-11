import java.util.*;
public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int M = in.nextInt();
    int[] C = new int[M]; 
    int[] a = new int[M];

    for(int i=0;i<M;i++){
      C[i] = in.nextInt();
      for(int j=0;j<C[i];j++){
        int num = in.nextInt();
        num--;
        a[i] |= (1<<num);
      }
    }

    int count =0;
    for(int combination=0; combination < (1<<M); combination++){
      int sumUnion = 0;//和集合
      for(int i=0; i<M; i++){
        if((combination>>i & 1) == 1 ){
          sumUnion |= a[i];
        }
      }
      if(sumUnion == (1<<N) - 1){
        count++;
      }
    }

    //結果の出力
    System.out.print(count);
  }
}