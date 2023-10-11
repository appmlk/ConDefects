import java.util.Scanner;
  public class Main{
        public static void main(String []args) {
             Scanner sc = new Scanner(System.in);
          int n = sc.nextInt();
          int x = sc.nextInt();
          int p = (x + (n - 1)) / n;
                   System.out.println((char) ('A' + (p - 1)));
        }
  }