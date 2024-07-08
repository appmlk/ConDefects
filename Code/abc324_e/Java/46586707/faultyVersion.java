//package atcoder.abc324;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    // TODO edit this code, this code is for
    // https://atcoder.jp/contests/practice/tasks/practice_1

    // param
    Scanner sc = new Scanner(System.in);
    int N = Integer.parseInt(sc.next());
    String T = sc.next();
    int[] firstLet=new int[N];
    int[] lastLet=new int[N];
    int Tl=T.length();
    for(int i=0;i<N;i++){
      String S=sc.next();
      int L=S.length();
      for(int d=0;d<L;d++){
        if(firstLet[i]<Tl){
          if(S.charAt(d)==(T.charAt(firstLet[i]))){
            firstLet[i]++;
          }
        }
        if(lastLet[i]<Tl){
          if(S.charAt(L-d-1)==T.charAt(Tl-lastLet[i]-1)){
            lastLet[i]++;
          }
        }
      }
        //System.out.println(firstLet[i]);
        
        //System.out.println(lastLet[i]);
    }
    Arrays.sort(firstLet);
    Arrays.sort(lastLet);
    sc.close();
    int count=0;
    int now=0;
    for(int i=N-1;i>=0;i--){
      
      while(now<N&&firstLet[i]+lastLet[now]<Tl){
        now++;
      }
      count=count+N-now;
    }
    System.out.println(count);
    // resolve

    // answer
  }

}