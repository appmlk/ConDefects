import java.util.Scanner;

class Main{
  public static void main(String args[]){
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        int k = scanner.nextInt();
        if(k == 1){
          System.out.print(j+" ");
        }
      }
      System.out.println("");
    }
  }
}