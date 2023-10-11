import java.util.Scanner;
import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    String i = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
    Scanner scan = new Scanner(System.in);
    int p = scan.nextInt();
    
    String s = i.substring(0, p+2);
    System.out.println(s);
    }
}