import java.util.*;
class Main{
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    int N=sc.nextInt();
    int[] point=new int[N];
    boolean judge=true;
    for(int i=0;i<N;i++){
      point[i]=sc.nextInt();
      if(point[0]!=point[i]){
        judge=false;
        break;
      }
    }
    if(judge==true){
      System.out.print("Yes");
    }else{
      System.out.println("No");
    }
    sc.close();
    }
  }