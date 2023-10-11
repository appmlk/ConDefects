//Har Har Mahadev
import java.io.*;
public class Main
{
  public static void main(String[]args)throws IOException
  {
    BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
    int t=Integer.parseInt(buf.readLine());
    char[][]ch=new char[t][t];
    for(int i=0;i<t;i++)
    {
      String s=buf.readLine();
      for(int j=0;j<t;j++)
        ch[i][j]=s.charAt(j);
    }
    for(int i=0;i<t;i++)
    {
      for(int j=0;j<t;j++)
      {
        if(i==j)continue;
        else if((ch[i][j]=='W'&&ch[j][i]!='L')||(ch[i][j]=='L'&&ch[j][i]!='W')||(ch[i][j]=='D'&&ch[j][i]!='D'))
        {
          System.out.println("incorrect");
          return;
        }  
      }
    }
      System.out.println("correct");
  }
}