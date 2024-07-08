import java.util.*;

public class Main {

    public static int digitsCount(int n){
        int count=0;
        while(n>0){
            count++;
            n=n/10;
        }
        return count;
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mod = 998244353;
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        long[] prefix=new long[n];
        long total=0;
        for(int i=0;i<n;i++){
            prefix[i]=total;
            total=(total + (long)arr[i])%mod;
        }
        long ans=0;
        for(int i=1;i<n;i++){
            long temp = (i*(long)arr[i])%mod;
            int digits = digitsCount(arr[i]);

            long temp2 = ((long)Math.pow(10,digits)*(prefix[i])) %mod ;
            ans = (ans%mod + temp%mod + temp2%mod)%mod;
        }
        System.out.println(ans);

    }
}

