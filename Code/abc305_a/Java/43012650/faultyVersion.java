import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int point = sc.nextInt();

    if (point % 5 >= 3) {
      System.out.println((point - 1) + point % 5);
    } else {
      System.out.println(point - point % 5);
    }
    

    sc.close();
  }
}