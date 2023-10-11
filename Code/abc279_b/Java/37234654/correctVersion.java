import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
      Scanner sc = new Scanner(System.in);
      String s = sc.next();
      String t = sc.next();
      if(0<=s.indexOf(t)){
          System.out.print("Yes");
      }else{
          System.out.print("No");
      }
    }
}