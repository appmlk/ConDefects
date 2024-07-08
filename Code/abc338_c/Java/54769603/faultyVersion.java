import java.util.*;

class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    
    final int N = sc.nextInt();
    int[] Qn = new int[N];
    int[] An = new int[N];
    int[] Bn = new int[N];
    int loopCnt = 1000000;
    
    for(int i = 0; i < N; i++){
      Qn[i] = sc.nextInt();
    }
    for(int i = 0; i < N; i++){
      An[i] = sc.nextInt();
    }
    for(int i = 0; i < N; i++){
      Bn[i] = sc.nextInt();
    }
    for(int i = 0; i < N; i++){
      if(An[i] == 0) continue;
      loopCnt = Qn[i]/An[i] < loopCnt ? Qn[i]/An[i] : loopCnt;
    }
    
    int max = 0;
    for(int i = 0; i < loopCnt; i++){
      int b_min = 1000000;
      for(int j = 0; j < N; j++){
        if(Bn[j] == 0) continue;
        b_min = (Qn[j] - i * An[j])/Bn[j] < b_min ? (Qn[j] - i * An[j])/Bn[j] : b_min;
      }
      max = (i + b_min) > max ? (i + b_min) : max;
    }
    
    System.out.println(max);
  }
}