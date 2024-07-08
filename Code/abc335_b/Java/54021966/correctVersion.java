import java.util.Scanner;
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    for(int x=0;x<=N;x++){
      for(int y=0;y<=N;y++){
        for(int z=0;z<=N;z++){
          if(x+y+z<=N)System.out.println(x+" "+y+" "+z);
        }
      }
    }
  }
}