import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
    double a = sc.nextDouble();
    double b = sc.nextDouble();
    
    System.out.println(Math.round(b/a*1000)/1000.0);
  }
}