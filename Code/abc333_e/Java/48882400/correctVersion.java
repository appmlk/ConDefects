import java.util.*;
public class Main {
  public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int t = 1;
      while(t-->0)
      {
        int n = scn.nextInt();
        int[][] arr = new int[n][2];
        int[] co = new int[n+1];
        for(int i=0;i<n;i++)
        {
          arr[i][0] = scn.nextInt();
          arr[i][1] = scn.nextInt();
        }
        boolean[] fl = new boolean[n];
        for(int i=n-1;i>=0;i--)
        {
          if(arr[i][0]==2)
          {
            co[arr[i][1]]++;
          }
          else
          {
            if(co[arr[i][1]] > 0)
            {
              fl[i] = true;
              co[arr[i][1]]--;
            }
            else
            fl[i] = false;
          }
        }
        // for(boolean ff : fl)
        // System.out.print(ff+" ");
        // System.out.println();
        boolean f = true;
        for(int val : co)
        {
          if(val > 0)
          {
            f = false;
            break;
          }
        }
        if(f == false)
        {
          System.out.println("-1");
          continue;
        }
        int ans = 0,max = 0;
        for(int i=0;i<n;i++)
        {
          if(arr[i][0] == 2)
          max--;
          else
          {
            if(arr[i][0] == 1 && fl[i]==true)
            max++;
          }
          ans = Math.max(ans,max);
        }
        System.out.println(ans);
        for(int i=0;i<n;i++)
        {
          if(arr[i][0] == 2)
          continue;
          if(fl[i]==false)
          System.out.print("0 ");
          else
          System.out.print("1 ");
        }
        System.out.println();
      }
  }
}