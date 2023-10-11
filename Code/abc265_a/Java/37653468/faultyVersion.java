import java.util.Scanner;
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();
    int y = sc.nextInt();
    int n = sc.nextInt();
    int min = -1;
    for(int i=0; i<=n; i++){
      int sta = n-3*i;
      if( sta>0 ){
        int c = sta*x+i*y;
        if( min==-1 )
          min = c;
        else
          min = c<min ? c : min;
      }
    }
    System.out.print(min);
  }
}