import java.util.Scanner;

class Main{
  public static void main(String[] args){
    Scanner def = new Scanner(System.in);
    int N = def.nextInt();
    for(int i = 1; i <= N ; i++){
      System.out.println(i % 3 == 0 ? "x" : "o");
    }
  }
}