import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
    while(a >= b) {
      System.out.printf("%d ",a);
      a += c;
    }
  }
}