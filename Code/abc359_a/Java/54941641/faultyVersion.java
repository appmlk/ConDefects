import java.util.Scanner;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    String [] str = new String[a];
    int count = 0;
    for(int i = 0 ; i<a;i++){
      str[i] = sc.nextLine();
      if(str[i].equals("Takahashi")){
       count++;  
      }
    
    
    }
    System.out.println(count);
    
    
  }
}