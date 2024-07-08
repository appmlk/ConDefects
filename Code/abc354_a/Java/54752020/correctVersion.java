import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int h = sc.nextInt();
    int i, t = 0;
    int k = 1;
    for(i=0; t<=h; i++) {
      if(i > 0) k *= 2;
      t += k;
    }
    System.out.println(i);
  }
}