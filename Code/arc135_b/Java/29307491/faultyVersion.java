import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        Main m = new Main();
        m.run();
    }

    public void run(){
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        long s[] = new long[n];
        for(int i =0 ;i < n;i ++){
            s[i] = sc.nextInt();
        }

        long dum[] = new long[n+2];
        dum[0] = 0;
        dum[1] = 0;
        for(int i = 0 ;i < n ; i++){
            dum[i+2] = s[i] - dum[i+1] - dum[i];
        }
//        System.out.println(Arrays.toString(dum));


        long min[] = new long[3];

        min[0] = dum[0];
        min[1] = dum[1];
        min[2] = dum[2];

        for(int i =0 ;i < n +2 ;i++){
            min[i%3] = Math.min(min[i%3], dum[i]);
        }


        if(min[0] + min[1]+ min[2] < 0){
            System.out.println("No");
            return;
        }

        long[] off = new long[3];
        off[0] = min[0] - min[1] - min[2];
        off[1] = min[1];
        off[2] = min[2];

        System.out.println("Yes");
        for(int i = 0; i < n+1; i++){
            System.out.print(dum[i]-off[i%3]);
            System.out.print(" ");
        }
        System.out.println(dum[n+1]-off[(n+1)%3]);
   }
}