import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        long[] n = new long[t];
        long[] a = new long[t];
        long[] b = new long[t];
        long[] x = new long[t];
        long[] y = new long[t];
        long[] z = new long[t];
        for(var i=0; i<t; i++) {
            n[i]=sc.nextLong();
            a[i]=sc.nextLong();
            b[i]=sc.nextLong();
            x[i]=sc.nextLong();
            y[i]=sc.nextLong();
            z[i]=sc.nextLong();
        }

        sc.close();
        System.out.println(arc139b(t,n,a,b,x,y,z));
        System.out.flush();
    }

    private static StringBuilder arc139b(int t, long[] n, long[] a, long[] b, long[] x, long[] y, long[] z){
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<t; i++){
            ans.append(solve(n[i],a[i],b[i],x[i],y[i],z[i])).append("\n");
        }
        return ans;
    }

    private static long solve(long n, long a, long b, long x, long y, long z){
        // A増やす操作について、1増やす操作の方がコスパが良い場合は,1をA回増やす操作に置き換える
        y = Math.min(y, a*x);
        // B増やす操作も同様
        z = Math.min(z, b*x);

        // コストパフォーマンスの良い方をAにする
        double cospaA = (double)y / a;
        double cospaB = (double)z / b;
        if(cospaA > cospaB){
            long tempA = a;
            long tempY = y;
            double tempCospaA = cospaA;
            a = b;
            y = z;
            cospaA = cospaB;
            b = tempA;
            z = tempY;
            cospaB = tempCospaA;
        }

        long ans = Long.MAX_VALUE;
        if(x <= cospaA){
            ans = n * x;
        }else if(cospaA <= x && x <= cospaB){
            ans = (n / a) * y + (n % a) * x;
        }else if(cospaA <= cospaB && cospaB <= x){
            ans = n * x; // n回1増やす操作をするのが最大コスト

            // a増やす操作->b増やす操作->1増やす操作の順で考えるとTLEする
            // b回a増やす操作とa回b増やす操作は同義なので
            // ab増やす操作->a増やす操作->b増やす操作->1増やす操作の順で考える
            // ab増やす操作はコスパの良いb回a増やす操作の方を使う
            long ab = a*b;
            long w = y*b;

            // i:ab増やす操作回数(最大使うと最善にできない可能性があるので1つだけ余裕を持たせる)
            long i = Math.max(0, n / ab - 1);
            // ab増やした後のnまでの残り
            n -= ab * i;

            // j:a増やす操作回数
            for(long j=0; j<=n/a; j++){
                // k:b増やす操作回数
                long k = (n - a*j) / b;
                // l:1増やす操作回数
                long l = n - a*j - b*k;

                long cost = i*w + j*y + k*z + l*x;
                ans = Math.min(ans, cost);
            }
        }

        return ans;
    }
}
