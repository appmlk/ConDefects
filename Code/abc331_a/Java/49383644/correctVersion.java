
import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int D = sc.nextInt();
        int y = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        if(d>=D){
            m++;
            d = 1;
        } else {
            d++;
        }
        if(m>M){
            y++;
            m = 1;
        }
        System.out.println(y + " " + m + " " + d);

        
    }
}