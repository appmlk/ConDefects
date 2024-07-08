

import java.util.HashMap;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String S = scanner.next();
    String T = scanner.next();
    scanner.close();
    
    int dis1 = distance(S);
//    System.out.println("dis1");
//    System.out.println(dis1);
    int dis2 = distance(T);
//    System.out.println("dis2");
//    System.out.println(dis2);
    
    if (dis1 == dis2) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
    
  }
  
  public static int distance(String s) {
    int[][] disMatrix = { {0, 1, 2, 2, 1}, 
                          {1, 0, 1, 2, 2},
                          {2, 1, 0, 1, 2},
                          {2, 2, 1, 0, 1},
                          {1, 2, 2, 1, 0}};
    
    HashMap<String, Integer> StringNumber = new HashMap<String, Integer>();

    // Add keys and values (String, Number)
    StringNumber.put("A", 0);
    StringNumber.put("B", 1);
    StringNumber.put("C", 2);
    StringNumber.put("D", 3);
    StringNumber.put("E", 4);
    
    String firstS = s.substring(0, 1);
    String secondS = s.substring(1, 2);
    int distance = disMatrix[StringNumber.get(firstS)][StringNumber.get(secondS)];
    
    return distance;
    
  }
}
