//Har Har Mahadev
//Om Namah Shivay 
import java.util.*;
import java.io.*;
public class Main
{
  static boolean dfs(HashMap<Integer,Set<Integer>>hm,int i,boolean[]vis,int parent)
  {
    if(vis[i])return false;
    vis[i]=true;
    boolean ans=true;
    if(hm.containsKey(i))
    for(int i1:hm.get(i))
    {
      if(parent==i1)continue;
      ans&=dfs(hm,i1,vis,i);
    }
    return ans;    
  }
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    String[]inp=buf.readLine().split(" ");
    int n=Integer.parseInt(inp[0]);
    int m=Integer.parseInt(inp[1]);
    HashMap<Integer,Set<Integer>>hm=new HashMap<>();
    while(m-->0)
    {
      String[]in=buf.readLine().split(" ");
      int a=Integer.parseInt(in[0]);
      int b=Integer.parseInt(in[1]);
      hm.putIfAbsent(a,new HashSet<>());
      hm.putIfAbsent(b,new HashSet<>());
      hm.get(a).add(b);
      hm.get(b).add(a);
      if(hm.get(a).size()>1||hm.get(b).size()>1)
      {
        System.out.println("No");
        return;
      }
    }
    boolean[]vis=new boolean[n+1];
    if(!dfs(hm,1,vis,-1))
    {
      System.out.println("No");
      return;
    }
    //System.out.println(Arrays.toString(vis));
    for(int i=1;i<=n;i++)
    {
      if(!vis[i])
      {
        System.out.println("No");
        return;
      }
    }
    System.out.println("Yes");
  }
}