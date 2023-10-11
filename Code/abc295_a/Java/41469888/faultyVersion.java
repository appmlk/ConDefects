import java.util.*;
import java.io.*;
public class Main{
  public static void main(String[] args) throws IOException{
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    boolean flg = false;
    String tmp;
    String[] chkStr = {"and","not","that","you"};
     for (int i = 0; i < N; i++) {
      tmp = in.next();
      for(String str : chkStr){
        if(tmp.equals(str)){
          flg = true;
        }
      }
    }
    if(flg){
      System.out.println("Yes");
    } else{
      System.out.println("No");
    }
  }
}