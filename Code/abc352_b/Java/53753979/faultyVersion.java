import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    String t = sc.nextLine();
    sc.close();

    List<Integer> out = new ArrayList<>();
    int i = 0;
    for (int j=0; j < t.length(); j++) {
      System.out.println("比較: " + s.charAt(i) + " と " + t.charAt(j));
      if (s.charAt(i) == t.charAt(j)) {
        System.out.println(" マッチ！: " + s.charAt(i) + " と " + t.charAt(j)+ "at i=" + i + " j=" + j);
        i++;
        out.add(j+1);
        if (i == s.length()) break;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int k : out) {
      sb.append(String.valueOf(k));
      sb.append(" ");
    }
    System.out.println(sb);
  }
}