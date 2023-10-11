import java.math.BigInteger;
import java.util.Scanner;

/* --- ARC154A --- */
public class Main {
    public static void main(String[] args){
        /* --- Input --- */
        var sc = new Scanner(System.in);
        var N = sc.nextInt();
        var A = sc.next().toCharArray();
        var B = sc.next().toCharArray();
        sc.close();
        /* --- Process --- */
        for(var i=0; i<N; i++){
            if(A[i] > B[i]){
                var t = A[i];
                A[i] = B[i];
                B[i] = t;
            }
        }
        BigInteger ba = new BigInteger(String.valueOf(A));
        BigInteger bb = new BigInteger(String.valueOf(B));
        BigInteger bm = BigInteger.valueOf(998244353);
        BigInteger ans = ba.multiply(bb).mod(bm);
        /* --- Output --- */
        System.out.println(ans);
        System.out.flush();
    }
}
