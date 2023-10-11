//Har Har Mahadev
import java.io.*;
public class Main
{
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    String s=buf.readLine();
    String t=buf.readLine();
    int r=(-s.charAt(0)+t.charAt(0)+26)%26;
    //System.out.println(r);
    StringBuilder temp=new StringBuilder();
    for(char ch:s.toCharArray())
    {
      temp.append((char)((ch-'a'+r)%26+'a'))  ;
    }
    //System.out.println(temp);
    System.out.println(temp.toString().compareTo(t)==0?"Yes":"No");
  }
}