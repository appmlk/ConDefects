import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), h = sc.nextInt(), x = sc.nextInt(), i = 0;
    n = h;
    while(h > x) {
      h = n;
      h += sc.nextInt();
      i++;
    }
    System.out.print(i);
  }
}