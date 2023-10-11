import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), w = scan.nextInt();
        int [] [] cheese = new int[n][2];
      //  long bc = 0,
                long count = 0;
        for (int i = 0; i < n; i++) {
            cheese[i][0] = scan.nextInt();
            cheese[i][1] = scan.nextInt();
        }
        Arrays.sort(cheese, (x,y) -> y[0] - x[0]);

        for (int i = 0; i < n; i++) {

            if(cheese[i][1]>=w){
                count += (long)cheese[i][0] * w;
            //    w -= w;
            } else if (w==0) {
                break;
            } else {
                count += (long)cheese[i][0] * cheese[i][1];
                w -= cheese[i][1];
            }
        }
        System.out.println(count);
    }
}