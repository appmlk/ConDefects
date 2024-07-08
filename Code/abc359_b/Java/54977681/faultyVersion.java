import java.io.*;
import java.util.*;

public class Main{
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    int N = readInt();
    int count = 0;
    Map<Integer, Integer> firstAppearanceIndex = new HashMap<>();
    for(int i = 0; i < 2 * N; i++){
      int input = readInt();
      if(!firstAppearanceIndex.containsKey(input)){
        firstAppearanceIndex.put(input, i);
      }
      else{
        if(i - firstAppearanceIndex.get(input) == 2){
          count++;
        }
      }
      System.out.println(count);
    }
    
  }
  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
      return st.nextToken();
  }
  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }
}