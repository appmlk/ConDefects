import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    
    int total = 0;

    Scanner scanner = new Scanner(System.in);
    
    int X = scanner.nextInt();
    int Y = scanner.nextInt();
    int N = scanner.nextInt();
    
    if(Y < X * 3){
      int m = N / 3;
      int l = N % 3;
      total = m * Y + l * X;
    }else{
      total = X * N;
    }
    System.out.println(total);
    
  }
}
