import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main{
  static int[] order = new int[26];
  public static void main(String[] args)throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);
    String S = sc.nextLine();
    int len = S.length();
    String[] arr = new String[len];
    for(int i = 0; i < len; i++){
      arr[i] = S.substring(i, len) + S.substring(0, i);
    }
    Arrays.sort(arr);
    System.out.println(arr[0]);
    System.out.println(arr[len-1]);
  }
}
