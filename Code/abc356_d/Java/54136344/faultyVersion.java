public class Main{
  public static void main(String[] args){
    var sc=new java.util.Scanner(System.in);
    long N=sc.nextLong();
    long M=sc.nextLong();
    solve(N,M);
  }
  
  static void solve(long N,long M){
    long t=1;
    long ans=0;
    int div=998244353;
    while(t<N){
      if((M&t)>0){
        ans+=(N/(t*2))*t;
        ans+=Math.max(0,N%(t*2)-t+1);
      }
      ans%=div;
      t*=2;
    }
    System.out.println(ans);
  }
}