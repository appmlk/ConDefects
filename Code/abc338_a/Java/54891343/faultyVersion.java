import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print(sc.next().equals("[A-Z]" + "[a-z]*") ? "Yes" : "No");
  }
}