
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int p[] = Arrays.stream(new int[n]).map(e -> sc.nextInt() - 1).toArray();

        String str = sc.next();

        int mod = 998244353;

        boolean used[] = new boolean[n];

        //全員右から取らせる。
        long resultR = 1;
        for(int i = 0; i < n; i++) {

            if(used[right(p[i], n)]) {
                resultR = 0;
                break;
            }

            if(str.charAt(p[i]) == '?'){
                if(used[left(p[i], n)]) {
                    resultR *= 2;
                    resultR %= mod;
                }
//                else {
//                    resultR = 0;
//                    break;
//                }
            }
            if(str.charAt(p[i]) == 'L' && !used[left(p[i], n)]) {
                resultR = 0;
                break;
            }

            used[right(p[i], n)] = true;
        }

        //全員左から取らせる。
        long resultL = 1;
        Arrays.fill(used, false);
        for(int i = 0; i < n; i++) {
            if(used[left(p[i], n)]) {
                resultL = 0;
                break;
            }

            if(str.charAt(p[i]) == '?' && used[right(p[i], n)]) {
                resultL *= 2;
                resultL %= mod;
            }
            if(str.charAt(p[i]) == 'R' && !used[right(p[i], n)]) {
                resultL = 0;
                break;
            }

            used[left(p[i], n)] = true;
        }

//        System.out.println(resultR + " " + resultL);
        System.out.println((resultR + resultL) % mod);
    }

    static int right(int pos, int n) {
        return pos;
    }

    static int left(int pos, int n) {
        return (pos + n - 1) % n;
    }
}
