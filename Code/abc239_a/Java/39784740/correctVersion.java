import java.util.*;
public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    double a = sc.nextInt();
    if (1 <= a && a <= 100000){
    double b = a * (12800000 + a);
    System.out.println(Math.sqrt(b));
    }
  }
}