

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          int t[] = new int [24];
          for(int i = 0; i < 24;i++){
            t[i] = 0;
          }
          int N =sc.nextInt();

          for(int i = 0 ;i < N;i++){
            int w = sc.nextInt();
            int x = sc.nextInt();
            int t9 = 9 - x;
            int t17 = 17-x;
            for(int j = t9; j < t17+1 ;j++){
                if (j < 0){
                    t[j+24] =  t[j+24] + w;
                }else{
                    t[j] = t[j] + w ;
                }
            }

          }
          int max = 0;

          for(int i = 0;i < 24;i++){
            if(max < t[i]){
                max = t[i];
               
            }
          }
          System.out.println(max);
          sc.close();
}
}
