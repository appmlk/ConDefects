//Har Har Mahadev
import java.io.*;
public class Main
{
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    long a=Long.parseLong(buf.readLine());
    long b=Long.parseLong(buf.readLine());
    long ans=200000000*b+a;
    System.out.println(ans);
  }
}