import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), s = sc.nextInt(), k = sc.nextInt(), total = 0;
    for(int i=0; i<n; i++) total += sc.nextInt() * sc.nextInt();
    System.out.print(total > s ? total : total + k);
  }
}