//package com.example.practice.atcoder.dailyprac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

//sc500, C - Row Column Sums
public class Main {
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        // input file name goes above
        StringTokenizer st = new StringTokenizer(input.readLine());
        final int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        final int[] ns = readArrayInt(n, input);
        final int[] ms = readArrayInt(m, input);
        out.println(calc(n, m, k, ns, ms));
        out.close();       // close the output file
    }

    private static long calc(final int n, final int m, final int k, final int[] ns, final int[] ms) {
        final long rw = ((long)m) * (k-1) % k;
        final long cl = ((long)n) * (k-1) % k;
        long res = ((long)m) * n * (k-1), cur = 0;
        long[] rs = new long[n], cs = new long[m];
        Arrays.fill(rs, ((long)m) * (k-1));
        Arrays.fill(cs, ((long)n) * (k-1));
        for (int i=0,j=0;i<n || j<m;++j,++i){
            if (i<n){
                long t = rw - ns[i];
                if (t<0)t+=k;
                rs[i] -= t;
                if (res<t){
                    return -1;
                }
                res -= t;
                cur += t;
            }
            if (j<m){
                long t = cl - ms[j];
                if (t<0)t+=k;
                cs[j] -= t;
                cur -= t;
            }
        }
        if (cur < 0){
            if (Math.abs(cur)%k == 0){
                long cnt = 0;
                for (int i=0;i<n;++i){
                    cnt += rs[i] / k;
                }
                if (cnt >= Math.abs(cur)/k){
                    res += cur;
                }else {
                    return -1;
                }
            }else {
                return -1;
            }
        }else if (cur > 0){
            if (cur%k != 0){
                return -1;
            }
        }
        return res;
    }

    private static void printArray(long[] ns, final PrintWriter out){
        for (int i=0;i<ns.length;++i){
            out.print(ns[i]);
            if (i+1<ns.length)out.print(" ");
            else out.println();
        }
    }

    private static void printArrayInt(int[] ns, final PrintWriter out){
        for (int i=0;i<ns.length;++i){
            out.print(ns[i]);
            if (i+1<ns.length)out.print(" ");
            else out.println();
        }
    }

    private static void printArrayVertical(long[] ns, final PrintWriter out){
        for (long a : ns){
            out.println(a);
        }
    }

    private static void printArrayVerticalInt(int[] ns, final PrintWriter out){
        for (int a : ns){
            out.println(a);
        }
    }

    private static void printArray2D(long[][] ns, final int len, final PrintWriter out){
        int cnt = 0;
        for (long[] kk : ns){
            cnt++;
            if (cnt > len)break;
            for (int i=0;i<kk.length;++i){
                out.print(kk[i]);
                if (i+1<kk.length)out.print(" ");
                else out.println();
            }
        }
    }

    private static void printArray2DInt(int[][] ns, final int len, final PrintWriter out){
        int cnt = 0;
        for (int[] kk : ns){
            cnt++;
            if (cnt > len)break;
            for (int i=0;i<kk.length;++i){
                out.print(kk[i]);
                if (i+1<kk.length)out.print(" ");
                else out.println();
            }
        }
    }

    private static long[] readArray(final int n, final BufferedReader input) throws IOException{
        long[] ns = new long[n];
        StringTokenizer st = new StringTokenizer(input.readLine());
        for (int i=0;i<n;++i){
            ns[i] = Long.parseLong(st.nextToken());
        }
        return ns;
    }

    private static int[] readArrayInt(final int n, final BufferedReader input) throws IOException{
        int[] ns = new int[n];
        StringTokenizer st = new StringTokenizer(input.readLine());
        for (int i=0;i<n;++i){
            ns[i] = Integer.parseInt(st.nextToken());
        }
        return ns;
    }

    private static long[] readArrayVertical(final int n, final BufferedReader input) throws IOException{
        long[] ns = new long[n];
        for (int i=0;i<n;++i){
            ns[i] = Long.parseLong(input.readLine());
        }
        return ns;
    }

    private static int[] readArrayVerticalInt(final int n, final BufferedReader input) throws IOException{
        int[] ns = new int[n];
        for (int i=0;i<n;++i){
            ns[i] = Integer.parseInt(input.readLine());
        }
        return ns;
    }

    private static long[][] readArray2D(final int n, final int len, final BufferedReader input) throws IOException{
        long[][] ns = new long[len][];
        for (int i=0;i<n;++i){
            StringTokenizer st = new StringTokenizer(input.readLine());
            ArrayList<Long> al = new ArrayList<>();
            while (st.hasMoreTokens()){
                al.add(Long.parseLong(st.nextToken()));
            }
            long[] kk = new long[al.size()];
            for (int j=0;j<kk.length;++j){
                kk[j] = al.get(j);
            }
            ns[i] = kk;
        }
        return ns;
    }

    private static int[][] readArray2DInt(final int n, final int len, final BufferedReader input) throws IOException{
        int[][] ns = new int[len][];
        for (int i=0;i<n;++i){
            StringTokenizer st = new StringTokenizer(input.readLine());
            ArrayList<Integer> al = new ArrayList<>();
            while (st.hasMoreTokens()){
                al.add(Integer.parseInt(st.nextToken()));
            }
            int[] kk = new int[al.size()];
            for (int j=0;j<kk.length;++j){
                kk[j] = al.get(j);
            }
            ns[i] = kk;
        }
        return ns;
    }

    private static int GCD(int x, int y){
        if (x > y)return GCD(y, x);
        if (x==0)return y;
        return GCD(y%x, x);
    }
}