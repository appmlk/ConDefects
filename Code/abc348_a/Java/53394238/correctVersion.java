import java.util.*;
public class Main
{
  public static void main (String[] args)
  {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    String result = "";
    
    for (int i = 1; i <= N; ++i)
    {
      if (i % 3 != 0)
      {
        result += "o";
      }
      else 
      {
        result += "x";
      }
    }
    System.out.print(result);
  }
}