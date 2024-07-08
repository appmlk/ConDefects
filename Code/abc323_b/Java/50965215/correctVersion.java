import java.util.*;
public class Main{
  public static void main(String[]args){
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    String[] s=new String[n];
    int[] cnt=new int[n];
    for(int i=0; i<n; i++){
      s[i]=sc.next();
    }
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
        if(s[i].charAt(j)=='o'){
          cnt[i]++;
        }
      }
    }
    for(int k=100; k>=0; k--){
      for(int i=0; i<n; i++){
        if(cnt[i]==k){
          System.out.print(i+1+" ");
        }
      }
    }
  }
}