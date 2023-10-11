//Har Har Mahadev
import java.io.*;
import java.util.*;
public class Main
{
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    String[]inp=buf.readLine().split(" ");
    int n=Integer.parseInt(inp[0]);
    int k=Integer.parseInt(inp[1]);
    PriorityQueue<String>pq=new PriorityQueue<>((a,b)->b.compareTo(a));
    while(n-->0)
    {
      String s=buf.readLine();
      pq.add(s);
      if(pq.size()>k)pq.poll();
    }
    ArrayList<String>al=new ArrayList<>();
    while(!pq.isEmpty())al.add(0,pq.poll());
    for(int i=0;i<al.size();i++)System.out.println(al.get(i));
  }
}