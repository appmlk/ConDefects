import java.util.*;
import java.lang.Math.*;
public class Main { //クラス名はMain
    public static void main(String[] args) {
        //コード
      Scanner sc = new Scanner(System.in);
	  int n = Integer.parseInt(sc.next());
      int[] p = new int[n];
      int[] ans = new int[n];
		for(int i=0;i<n;i++){
		   p[i] = Integer.parseInt(sc.next());
           ans[i]=999999;
		}
      for(int i=0;i<n;i++){
        for(int j=1;j<=ans[i];j++){
          if(i-j>=0){
            ans[i]=Math.min(ans[i],j+Math.abs(p[i]-p[i-j]));
          }
          if(i+j<n){
            ans[i]=Math.min(ans[i],j+Math.abs(p[i]-p[i+j]));
          }
          
          
        }
      }
      
      for(int i=0;i<n;i++){
        if(i!=0){
          System.out.print(" ");
        }
        System.out.print(ans[i]);
      }
      
    }
}