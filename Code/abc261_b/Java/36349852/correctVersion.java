import java.util.Scanner;
 
public class Main {
    public static void main(String [] args) {
        try(Scanner scan = new Scanner(System.in)){

          int N = Integer.parseInt(scan.next()); 

          String [][] fig = new String [N][N];
          String line;
          String[] marks;
          
          for(int i = 0; i < N; i++){
            line = scan.next();
            marks = line.split("");
            for(int l = 0; l < marks.length; l++){
              fig[i][l] = marks[l];
            }
          }

          for(int i = 0; i < N; i++){
            for(int l = 0; l < N; l++){
              if(i == l){
                continue;
              }
              if("W".equals(fig[i][l]) && !("L".equals(fig[l][i]))){
                System.out.println("incorrect");
                return;
              }
              if("L".equals(fig[i][l]) && !("W".equals(fig[l][i]))){
                System.out.println("incorrect");
                return;
              }
              if("D".equals(fig[i][l]) && !("D".equals(fig[l][i]))){
                System.out.println("incorrect");
                return;
              }
              if("D".equals(fig[l][i]) && !("D".equals(fig[i][l]))){
                System.out.println("incorrect");
                return;
              }
            }
          }        
          System.out.println("correct");
        }
    }
}