import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int correntHeight = 0;
  
        for (int i = 0; i < n; i++){
          int height = sc.nextInt();

          if (correntHeight < height){
            correntHeight = height;
          }
        }
      
         System.out.println(correntHeight);
    }
}