import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Description D
 * @Author Leon
 * @Date 2023/7/17
 */
public class Main {
    static int MOD = 998244353;
    static int MAX = (int)1E5*6;
    static long[] arr = new long[MAX+1];

    static {
        arr[0] = 1;
        for (int i = 1; i <=MAX ; i++) {
            arr[i] = 10*arr[i-1]%MOD;
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(1);
        long cur = 1;
        while (q-->0){
            int x = sc.nextInt();
            if(x==1){
                int c = sc.nextInt();
                list.addLast(c);
                cur = (cur*10+ c)%MOD;

            }else if(x==2){
                int  a = list.pop();
                cur = ((cur - (long)a*arr[list.size()])%MOD + MOD)%MOD;
            }else {
                System.out.println(cur);
            }
        }
    }
}
