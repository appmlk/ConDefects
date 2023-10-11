import java.util.*;
import static java.lang.System.out;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int h1 = sc.nextInt();
        int h2 = sc.nextInt();
        int h3 = sc.nextInt();
        int w1 = sc.nextInt();
        int w2 = sc.nextInt();
        int w3 = sc.nextInt();

        long cnt = 0;
        for(int i = 1;i <= 30;i++){
            for(int j = 1;j <= 30;j++){
                for(int k = 1;k <= 30;k++){
                    for(int l = 1;l <= 30;l++){
                        int a = h1 - (i + j);
                        int b = h2 - (k + l);
                        int c = w1 - (i + k);
                        int d = w2 - (j + l);
                        if(a >= 1 && b >= 1 && c >= 1 && d >= 1){
                            if((h3 - (c + d) >= 1) && (w3 - (a + b) >= 1))cnt++;
                        }
                    }
                }
            }
        }
        out.println(cnt);
    }
}
