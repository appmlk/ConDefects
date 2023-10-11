import java.util.Scanner;
      public class Main{
            public static void main(String []args) {
               Scanner sc = new Scanner(System.in);
              int a = sc.nextInt();
              int b = sc.nextInt();
              int n = a - b;
              if (a > b) {
               System.out.println((int)Math.pow(32, n)); 
              }
              else if (a == b) {
               System.out.println(1); 
              }
            
 }
}