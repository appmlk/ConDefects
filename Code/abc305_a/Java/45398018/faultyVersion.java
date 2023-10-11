import java.util.*;
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    if(n / 5 == 0){
      System.out.println(n);
    }else {
      int tmp = n % 5;
      if(tmp == 1){
        System.out.println(n - 1);
      }else if(tmp == 2){
        System.out.println(n - 2);
      }else if(tmp == 3){
        System.out.println(n + 2);
      }else if(tmp == 4){
        System.out.println(n + 1);
      }
    }
  }
}