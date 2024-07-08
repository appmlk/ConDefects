import java.util.Arrays;
import java.util.Scanner;

class Main
{
    static long mod = 998244353;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long [] a = new long[n];

        for(int i=0; i<n; i++)
        {
            a[i] = sc.nextLong();
        }

        sc.close();
        long X = (long)Math.sqrt(n);
        long [] dp = new long[n];
        long [][] lazy = new long[n][(int)X+1]; // Second index just means the speed
        dp[0] = 1;

        

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<X; j++)
            {
                dp[i] = (dp[i] + lazy[i][j])%mod;
            }

            for(int j=0; j<=X; j++)
            {
                    if(i+j<n)
                    {
                        lazy[i+j][j] = (lazy[i+j][j] + lazy[i][j])%mod;
                    }
                    
            }

            if(a[i]>X)
            {
                for(int j=i+(int)a[i]; j<n; j+=a[i])
                {
                    dp[j] = (dp[j] + dp[i])%mod;
                }
            }
            else{
                if(i+(int)a[i]<n)
                {
                    lazy[i+(int)a[i]][(int)a[i]] = (dp[i] + lazy[i+(int)a[i]][(int)a[i]])%mod;
                }
            }

            
        }

        long ans = 0;

        for(int i=0; i<n; i++) ans= (ans + dp[i])%mod;

        System.out.println(ans);
        
    }
    public static String arrayToString(long[][] array) {
        StringBuilder result = new StringBuilder();
        for (long[] row : array) {
            result.append(Arrays.toString(row)).append("\n");
        }
        return result.toString();
    }
}