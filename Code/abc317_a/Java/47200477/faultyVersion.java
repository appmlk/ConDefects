import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h = sc.nextInt();
        int x = sc.nextInt();

        int[] p = new int[n];
        for(int i=0; i<n; i++){
            p[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++){
            if(h+p[i]>x){
                System.out.println(i+1);
                break;
            }
        }
    }
}