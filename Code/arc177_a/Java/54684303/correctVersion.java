
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();
        System.out.println();
        int n = sc.nextInt();
        boolean ans = true;
        int[] x = new int[100];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            while(x[i]>=500 && f>=1){
                x[i] = x[i]-500;
                f=f-1;
            }
            while(x[i]>=100 && e>=1){
                x[i] = x[i]-100;
                e=e-1;
            }
            while(x[i]>=50 && d>=1){
                x[i] = x[i]-50;
                d=d-1;
            }
            while(x[i]>=10 && c>=1){
                x[i] = x[i]-10;
                c=c-1;
            }
            while(x[i]>=5 && b>=1){
                x[i] = x[i]-5;
                b=b-1;

            }
            while(x[i]>=1 && a>=1){
                x[i] = x[i]-1;
                a=a-1;
            }
            if(x[i]!=0){
                ans = false;
                break;
            }
        }
        if(ans == true){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}
