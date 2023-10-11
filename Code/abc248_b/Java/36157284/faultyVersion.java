import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int a = sc.nextInt();
      int b = sc.nextInt();
      int k = sc.nextInt();
      
      int count = 0;
      while(a < b) {
        a *= k;
        count++;
      };
      
      System.out.println(count);
    }

}