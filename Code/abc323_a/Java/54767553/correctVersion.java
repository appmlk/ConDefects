import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        try(Scanner scan = new Scanner(System.in)){
          String S = scan.next();
          String result = "Yes";

          for(int i = 1; i <= 16; i+=2){
            if(S.charAt(i) != '0'){
              result = "No";
            }
          }
          System.out.println(result);
        }
    }
}