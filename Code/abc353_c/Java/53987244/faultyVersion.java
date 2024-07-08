import java.util.*;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    
    final int N = sc.nextInt();
    int[] An = new int[N];
    for(int i = 0; i < N; i++){
      An[i] = sc.nextInt();
    }
    
    Arrays.sort(An);
    
    long sum = 0;
    for(int a: An){
      sum += a;
    }
    sum *= (N-1);
    
    int cnt = 0;
    int right_index = N-1;
    for(int i = 0; i < N-1; i++){
      right_index = Math.max(right_index, i);
      
      while(right_index > i && An[i] + An[right_index] >= 100000000){
        right_index--;
      }
      cnt += (N-1) -right_index;
    }
    
    sum -= (cnt * 100000000);
    
    System.out.println(sum);
  }
}