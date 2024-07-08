import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int s = Integer.parseInt(sc.next().substring(3));
    if(s > 349 || s == 316 || s == 000) System.out.print("No");
    else System.out.print("Yes");
  }
}