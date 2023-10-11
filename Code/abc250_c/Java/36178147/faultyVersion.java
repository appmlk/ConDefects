import java.util.*;
public class Main{
  public static void main(String[] args){
    Scanner sc =new Scanner(System.in);
    int N =sc.nextInt();
    int Q =sc.nextInt();
    int A[] =new int[N];
    int B[] =new int[Q];
    int C[] =new int[N];
    for(int i=0;i<N;i++){
      A[i]=i+1;
      C[i]=i;
    }
    for(int j =0;j<Q;j++){
      B[j]=sc.nextInt();
      if(C[B[j]-1]==N-1){
        int w =A[N-2];
        A[N-2]=A[N-1];
        A[N-1]=w;
        C[w-1]=N-1;
        C[B[j]-1]=0;
      }
      else{
        int l =C[B[j]-1];
        int m =A[l];
        int n =A[l+1];
        A[l] =A[l+1];
        A[l+1]=m;
        C[m-1]=l+1;
        C[n-1]=l;
      }
    }
    for(int k =0;k<N;k++){
      System.out.print(A[k]+" ");
    }
  }
}