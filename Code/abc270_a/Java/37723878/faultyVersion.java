import java.util.*;

// A - 1-2-4 Test
public class Main {
 	public static void main(String[] args) {
      
      Scanner sc = new Scanner(System.in);
      int s = 0;
      
      String a = String.format("%3s", Integer.toBinaryString(sc.nextInt())).replace(" ", "0");
      String b = String.format("%3s", Integer.toBinaryString(sc.nextInt())).replace(" ", "0");
      
      if(a.indexOf("1") == -1 && b.indexOf("1") == -1) {
        System.out.println(0);
      }
      
      if(a.startsWith("1") || b.startsWith("1")) {
        s += 4;
      }
      if(a.indexOf("1", 1) == 1 || b.indexOf("1", 1) == 1) {
        s += 2;
      }
      if(a.endsWith("1") || b.endsWith("1")) {
        s += 1;
      }
      
      System.out.println(s);
    }
}