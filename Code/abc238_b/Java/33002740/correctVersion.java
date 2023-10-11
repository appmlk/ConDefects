import java.util.Scanner;
import java.util.Arrays;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    Integer[] A = new Integer[N];
    A[0] = sc.nextInt();
    for(int i=1;i<N;i++){
      A[i] = (sc.nextInt()+A[i-1])%360;
    }
    Arrays.sort(A);
    int answer = A[0];
    for(int i=1;i<N;i++){
      if(A[i]-A[i-1]>answer)
        answer = A[i]-A[i-1];
    }
    if(360-A[N-1]>answer)
      System.out.println(360-A[N-1]);
    else
      System.out.println(answer);
  }
}
