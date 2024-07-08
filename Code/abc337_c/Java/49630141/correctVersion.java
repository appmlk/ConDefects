import java.util.* ;
public class Main{
  public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
    int N= sc.nextInt(); int ans[]= new int[N+1];
    Arrays.fill(ans,0); int root=0;
    for(int i=1;i<=N;i++){
      int ps= sc.nextInt();
      if(ps== -1){
       root= i;
       System.out.print(root+" ");
      }
      else{
        ans[ps]= i;
      }
    }
   int i=1;
   while(i<N){
         System.out.print(ans[root]+" ");
         root= ans[root];
         i++ ;
      }
    System.exit(0);
  }
}