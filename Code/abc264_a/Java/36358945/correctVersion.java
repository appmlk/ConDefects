import java.util.*;
import java.util.Scanner;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt()-1;
    int b = sc.nextInt();
    String s = "atcoder";
    for(;a<b;a++){
      System.out.print(s.charAt(a));
    }

  }
}