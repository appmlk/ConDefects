import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    public void solve() {
        int n = sc.nextInt();
        int[] a = new int[n];
        Arrays.setAll(a, i -> sc.nextInt());
        if(n==1) {
            out.println("Yes");
            out.println("0");
            return;
        }
        int asum = 0;
        int amax = 0;
        int amin = 0;
        int[] cs = new int[n+1];
        for(int i=0; i<n; i++) {
            asum += a[i];
            cs[i+1] = cs[i] + a[i];
            amax = Math.max(amax, asum);
            amin = Math.min(amin, asum);
        }
        if(n==2) {
            if (asum == 0) {
                out.println("No");
            } else {
                out.println("Yes");
                out.println("-1 1");
            }
            return;
        }
        long[] res = new long[n];
        if(asum == 0) {
            if(amax == 0 || amin == 0 ) {
                out.println("No");
                return;
            }

            for(int i=1; i<n; i++) {
                if(cs[i]==0 && cs[i-1] + cs[i+1] == 0) {
                    long sum = 0;
                    for(int j=0; j<n; j++) {
                        if(j<i) {
                            res[j] = j;
                        } else {
                            res[j] = j + n*100L;
                        }
                        sum += res[j] * a[j];
                    }
                    if(sum >= 0) {
                        if(a[i]==1) {
                            res[i]-=sum;
                        } else {
                            res[i-1]+=sum;
                        }
                    } else {
                        if(a[i]==1) {
                            res[i-1]-=sum;
                        } else {
                            res[i]+=sum;
                        }
                    }
                    printa(res);
                    return;
                }
            }

            throw new RuntimeException();
        } else {
            long d = n * 2;
            for(long s = -d ; s<= d; s+= d) {
                long sum = 0;
                for (int j = 0; j < n - 1; j++) {
                    long num = s + j;
                    res[j] = num;
                    sum += num * a[j];
                }
                res[n - 1] = -sum * a[n - 1];
                if(res[n-2]<res[n-1]) {
                    printa(res);
                    return;
                }
            }
        }


    }
    void printa(long[] res) {
        out.println("Yes");
        int n = res.length;
        for (int i=0; i<n; i++) {
            if (i>0) out.print(" ");
            out.print(res[i]);
        }
        out.println();
    }
}
