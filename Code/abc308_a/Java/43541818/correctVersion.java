import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] wordArray = sc.nextLine().split(" ");
    
    for (int i=0; i < 8; i++) {
      if (i == 0) {
        continue;
      }
      
      int num = Integer.parseInt(wordArray[i]);
      int num2 = Integer.parseInt(wordArray[i - 1]);
      int diff = num - num2;
      
      if (!(0 <= diff && 100 <= num && num <= 675 && num % 25 == 0)) {
        System.out.print("No");
        return;
      }
    }
    
    System.out.print("Yes");
  }
}