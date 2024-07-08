import java.util.*;
public class Main{
  public static void main(String args[])
  {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int k=sc.nextInt();
  
     int nums[]=new int[n];
     for(int i=0;i<n;i++)
     {
       nums[i]=sc.nextInt();
     }
     int idx=0;
     int cnt=1;
     int temp=k;
     while(idx<n)
     {
      if(nums[idx]<=temp)
      {
        temp-=nums[idx];
      }else
      {
        cnt++;
        temp=k;
        temp-=nums[idx];
      }
      idx++;
     }
    System.out.println(cnt);
  }
}