import java.util.*;

public class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    int snum = s.length();
    String [] ss = new String[snum];
    String sum = "";
    for(int i = 0;i < snum;i ++){
      ss[i] = s.substring(i,i + 1);
      sum += ss[i] + " ";
    }
    System.out.println(sum);
    
  }
}