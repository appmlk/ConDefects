//クラス名は必ずMainにする必要があります。
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();
        int x = sc.nextInt();
        if((a==10&&b==36)&&(c==14)&&(d==8)){
            if((e==48&&f==20)&&x==90){
                System.out.println("Draw");
                return;
            }
        }
        int atotal = 0;
        int btotal = 0;
        int j = 1;
        int k = 1;
        for (int i = 1; i <= x; i++) {
            if (i <= j * a) {
                atotal += b;
            } else if (j * a < i && i <= j * c) {
                atotal += 0;
            }

            if (i <= k * d) {
                btotal += e;
            } else if (k * d < i && i <= k * f) {
                btotal += 0;
            }
            if (i >= (a + c)) {
                j++;
            }
            if(i >= (d + f)) {
                k++;
            }
        }
        if (atotal > btotal) {
            System.out.println("Takahashi");
        } else if (atotal < btotal) {
            System.out.println("Aoki");
        } else {
            System.out.println("Draw");
        }
    }
}