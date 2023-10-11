//package com.example.practice.codeforces.below2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//atcoder: D - M<=ab
public class Main {
    public static void main(String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter out = new PrintWriter(System.out);
        // input file name goes above
        int Q = 1;//Integer.parseInt(input.readLine());
        while (Q > 0) {
            StringTokenizer st = new StringTokenizer(input.readLine());
            final long n = Long.parseLong(st.nextToken()), m = Long.parseLong(st.nextToken());
            out.println(calc(n, m));
            Q--;
        }
        out.close();       // close the output file
    }

    private static long calc(final long n, final long m) {
        if (m <= n)return m;
        if (m/n > n || (m/n == n && m%n > 0)){
            return -1;
        }
        final long d = (long) Math.sqrt(m);
        long res = -1;
        for (int i=2;i<=d+1 && i<=n;++i){
            if (m%i == 0){
                if (m/i <= n){
                    return m;
                }
            }else {
                long k = m/i;
                if (k < n){
                    if (res==-1 || res > i*(k+1)){
                        res = i * (k+1);
                    }
                }
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

    private static long GCD(long x, long y){
        if (x > y)return GCD(y, x);
        if (x==0)return y;
        return GCD(y%x, x);
    }

    private static ArrayList<int[]>[] convertToGraphUnDirectWithWeight(final int n, final int[][] es){
        ArrayList<int[]>[] als = new ArrayList[n+1];
        for (int i=0;i<=n;++i){
            als[i] = new ArrayList<>();
        }
        for (int[] e : es){
            als[e[0]].add(new int[]{e[1], e[2]});
            als[e[1]].add(new int[]{e[0], e[2]});
        }
        return als;
    }

    private static ArrayList<int[]>[] convertToGraphDirectWithWeight(final int n, final int[][] es){
        ArrayList<int[]>[] als = new ArrayList[n+1];
        for (int i=0;i<=n;++i){
            als[i] = new ArrayList<>();
        }
        for (int[] e : es){
            als[e[0]].add(new int[]{e[1], e[2]});
        }
        return als;
    }

    private static ArrayList<Integer>[] convertToGraphUnDirect(final int n, final int[][] es){
        ArrayList<Integer>[] als = new ArrayList[n+1];
        for (int i=0;i<=n;++i){
            als[i] = new ArrayList<>();
        }
        for (int[] e : es){
            als[e[0]].add(e[1]);
            als[e[1]].add(e[0]);
        }
        return als;
    }

    private static ArrayList<Integer>[] convertToGraphDirect(final int n, final int[][] es){
        ArrayList<Integer>[] als = new ArrayList[n+1];
        for (int i=0;i<=n;++i){
            als[i] = new ArrayList<>();
        }
        for (int[] e : es){
            als[e[0]].add(e[1]);
        }
        return als;
    }

    private static int find(final int[] rd, int idx){
        while (idx != rd[idx]){
            rd[idx] = rd[rd[idx]];
            idx = rd[idx];
        }
        return idx;
    }
}