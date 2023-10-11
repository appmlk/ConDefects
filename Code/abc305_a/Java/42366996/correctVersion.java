import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int a=n%5;
    if(a<2.5){
      System.out.println(5*(n/5));
    }
    else if(a>2.5){
      System.out.println(5*(n/5+1));
    }
  }
}