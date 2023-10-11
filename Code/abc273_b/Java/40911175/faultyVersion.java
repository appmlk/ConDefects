import java.util.*;
import java.io.*;
public class Main{
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long X = in.nextLong();
    long K = in.nextLong();

    long tmpNum = 0; 
    String tmp;
    for(int i = 0 ; i < K ; i++ ){
      tmp = String.valueOf(X);
      tmpNum = Long.valueOf(tmp.charAt(tmp.length() - i - 1) - 48);
      if(tmpNum > 4){
        X += Math.pow(10, i + 1) - tmpNum * Math.pow(10, i);
      } else {
        X -=  tmpNum * Math.pow(10, i);
      }
      if(X < Math.pow(10, K)){
        System.out.println(0);
        return;
      }
    }
    System.out.println(X);
  }
}