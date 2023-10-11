import java.util.Scanner;

class Main {

  public static void main(String[] args) {
    // Inputs are N, M, P
    // N: Max number of days
    // M: First day of full Moon
    // P: Multiple
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int p = scanner.nextInt();
    int counter = 0;

    for (int i = m; i <= n; i += p) {
      counter = counter + 1;
    }
    System.out.println(counter);
    scanner.close();
  }
}
