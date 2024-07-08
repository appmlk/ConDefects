import java.util.*;
public class Main{
  public static void main(String[]args){
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int g=sc.nextInt();
    int m=sc.nextInt();
    int gc=0;
    int mc=0;
    for(int i=0; i<n; i++){
      if(gc==g){
        gc=0;
      }else if(mc==0){
        mc=m;
      }else{
        if(g-gc>=mc){
          gc=gc+mc;
          mc=0;
        }else{
          mc=mc-(g-gc);
          gc=g;
        }
      }
    }
    System.out.println(gc+" "+mc);
  }
}