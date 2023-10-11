//Har Har Mahadev
import java.io.*;
public class Main
{
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    String inp=buf.readLine();
    long r1=Long.parseLong(inp);
    if(r1>=-9&&r1<0)
    {
      System.out.println("-1");
      return;
    }
    else if(r1<=9)
    {
      System.out.println(0);
      return;
    }      
    if(inp.charAt(inp.length()-1)=='0'||inp.charAt(0)!='-')
    {
      System.out.println(inp.substring(0,inp.length()-1));
      return;
    }
    long r=Long.parseLong(inp);
    r/=10;
    r--;
    System.out.println(r);
  }
}