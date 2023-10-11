import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();
        int x = sc.nextInt();
        int time_t = x;
        int dis_t = 0;
        int time_a = x;
        int dis_a = 0;
       
        while(time_t > 0){
            if(time_t > a){
                dis_t  += a * b;
                time_t -= (a + c);
            }else{
                dis_t += time_t * b;
                time_t = 0;
            }
        }
        while(time_a > 0){
            if(time_a > d){
                dis_a  += d * e;
                time_a -= (d + f);
            }else{
                dis_a += time_a * e;
                time_a = 0;
            }
        }
        if(dis_t > dis_a){
            System.out.println("Takahashi");
        }else if(dis_t < dis_a){
            System.out.println("Aoki");
        }else{
            System.out.println("Drow");
        }
    }
}