import java.math.*;
import java.util.*;
import java.io.*;

public class Main {
    static int t, n, m, k, p, s, e, c, h, q, g, l, r, count;
    static long answer = 0, mod = 1000000007, L;
    static long max = 0, min = Long.MAX_VALUE;
    static int[] parent;
    static boolean f = false;
    static boolean[][] map;
    static StringBuilder sb = new StringBuilder();
    static String[] list;
    static int[] order;
    static int len;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String ss;
        int i0, i1, i2, i3, t0, t1, t2, t3, q, ll, rr, len, pre;
        n = Integer.parseInt(br.readLine());
        char[] c = br.readLine().toCharArray();
        for(i0 = 1; i0 < n; i0+=2)
            if(c[i0]=='A')
                c[i0]++;
            else
                c[i0]--;
        answer = 1;
        pre = c[0];
        t0 = 0;
        for(i0 = 0; i0 < n; i0++)
        {
            if(pre==c[i0])
                t0++;
            else
            {
                answer = (answer*((t0+1)/2))%mod;
                t0 = 1;
                pre = c[i0];
            }
        }
        answer = (answer*((t0+1)/2))%mod;
        System.out.println(answer);
    }
    static int GCD(int x, int y)
    {
        return y==0?x:GCD(y,x%y);
    }
    static double ccw(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        return x1*y2+x2*y3+x3*y1-x2*y1-x3*y2-x1*y3;
    }
    static int Find(int x)
    {
        if(0==parent[x])
            return x;
        return parent[x] = Find(parent[x]);
    }
    static void Union(int x, int y)
    {
        parent[Find(x)] = Find(y);
    }
    public static long readLong() throws Exception{
        long val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
    public static int readInt() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean flag = (c == '-');
        if (flag)
            c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);

        if (flag)
            return -val;
        return val;
    }
}