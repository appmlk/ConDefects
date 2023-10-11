import java.util.*;
public class Main{
  public static void main(String[]args){
    Scanner s=new Scanner(System.in);
    int n=s.nextInt();
    StringBuilder sb=new StringBuilder();
    for(int i=1;i<=n;i++){
      boolean find=false;
      for(int j=1;j<=9;j++ ){
        if(n%j==0 && i%(n/j)==0){
          sb.append(j);
          find=true;
          break;
        }
      }
      if(!find)
      sb.append("-");
    }
    System.out.println(sb.toString());
  }
}