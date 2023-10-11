//Har Har Mahadev
import java.io.*;
public class Main
{
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    long l=Long.parseLong(buf.readLine());
    int mod=998244353;
    l=l%mod;
    if(l<0)
    l+=mod;
    System.out.println(l);
  }
}