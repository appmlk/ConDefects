import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int inputCount = sc.nextInt();
    int count = 0;
    for(int i = 0; i < inputCount; i++) {
      String name = sc.next();
      if(name.equals("Takahashi")) {
        count++;
      }
    }
    System.out.println(count);
  }
}