
/*
@supplement-auto-stop
@supplement-println-stop
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main{

public static void main(String[] args){
  int t = in.nextInt();
  lp : for(int i = 0; i < t; i++){
    long n = in.nextLong();
    String str = (n + "");
    int len = str.length();
    long l = 0;
    lx : for(int x = 2; x < len; x++){if(len % x == 0){
      String s = str.substring(0, len / x); boolean b = true;
      long alt = Long.parseLong(s.repeat(x));
      if(alt > n){s = "" + (Long.parseLong(s)-1); alt = Long.parseLong(s.repeat(x)); b = false;};
      if(alt > l){l = alt;};
      if(b){break lx;};
    };};
      String s = str.substring(0, 1);
      long alt = Long.parseLong(s.repeat(len));
      if(alt > n){int z = Integer.parseInt(s); if(z == 1){alt = Long.parseLong("9".repeat(len-1));}else{alt = Long.parseLong(((z-1) + "").repeat(len));};};
      if(alt > l){l = alt;};
    out.println(l);
  };
}


public static boolean calc(String str, int l, long n){
  String rep = str.substring(0, str.length() / l);
  if(Long.parseLong(rep.repeat(l)) > n){return false;};
  return true;
}

public static String val(String str, int l, long n){
  String rep = str.substring(0, str.length() / l);
  if(Long.parseLong(rep.repeat(l)) > n){rep = "" + (Integer.parseInt(rep) - 1);};
  return rep.repeat(l);
}

public static String val(String str, int l, long n, boolean b){
  String rep = str.substring(0, str.length() / l);
  if(! b){rep = "" + (Integer.parseInt(rep) - 1);};
  return rep.repeat(l);
}


}//main




class in{
  private static Scanner sc = new Scanner(System.in);
  public static int[] nextInt(int len){
    int[] ret = new int[len];
    for(int i = 0; i < len; i++){ret[i] = nextInt();};
    return ret;
  }
  public static int[][] nextInt(int x, int y){
    int[][] ret = new int[x][y];
    for(int i = 0; i < x; i++){for(int j = 0; j < y; j++){ret[i][j] = nextInt();};};
    return ret;
  }
  public static int nextInt(){return Integer.parseInt(sc.next());}
  public static long[] nextLong(int len){
    long[] ret = new long[len];
    for(int i = 0; i < len; i++){ret[i] = nextLong();};
    return ret;
  }
  public static long[][] nextLong(int x, int y){
    long[][] ret = new long[x][y];
    for(int i = 0; i < x; i++){for(int j = 0; j < y; j++){ret[i][j] = nextInt();};};
    return ret;
  }
  public static long nextLong(){return Long.parseLong(sc.next());}
  public static String next(){return sc.next();}
  public static String[] nextString(int len){
    String[] ret = new String[len];
    for(int i = 0; i < len; i++){ret[i] = next();};
    return ret;
  }
}//input


class out{
  public static void println(int... list){
    StringBuilder sb = new StringBuilder();
    sb.append(list[0]);
    for(int i = 1; i < list.length; i++){sb.append(" ").append(list[i]);};
    System.out.println(sb.toString());
  }
  public static void println(long... list){
    StringBuilder sb = new StringBuilder();
    sb.append(list[0]);
    for(int i = 1; i < list.length; i++){sb.append(" ").append(list[i]);};
    System.out.println(sb.toString());
  }
  public static void println(String text){System.out.println(text);}
  public static void println(int i){System.out.println(i);}
  public static void println(long i){System.out.println(i);}
}//output


