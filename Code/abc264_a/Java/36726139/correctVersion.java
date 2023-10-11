import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    String word = "atcoder";
    int input1 = console.nextInt();

    int input2 = console.nextInt();
    String output = word.substring(input1-1, input2);
    System.out.println(output);
  }
}