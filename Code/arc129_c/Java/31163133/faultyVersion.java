//package com.example.practice.atcoder.sc500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
//Multiple of 7
public class Main {
    final static int M = 1600;
    final static int[][] PAIR = new int[M*(M+1)/2][];
    final static HashMap<Integer,Integer> MAP = new HashMap<>();
    static {
        for (int i=1;i<=M;++i){
            int t = i*(i+1)/2;
            MAP.put(t, i);
            for (int j=i;j<=M;++j){
                int t2 = j*(j+1)/2;
                if (t+t2<PAIR.length && PAIR[t+t2]==null && (i%5!=2 || j%5!=2)){
                    if (i%5!=2){
                        PAIR[t+t2] = new int[]{i, j};
                    }else {
                        PAIR[t+t2] = new int[]{j, i};
                    }
                }
            }
        }
    }
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        // input file name goes above
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

        System.out.println(calc(Integer.parseInt(input.readLine())));
        //out.close();       // close the output file
    }

    private static String calc(final int n) {
        for (int i=1; i<=M; ++i){
            int t = i*(i+1)/2;
            if (t==n){
                return getString7(i);
            }else if (t>n){
                break;
            }
        }
        for (int i=1; i<=M; ++i){
            int t = i*(i+1)/2;
            if (MAP.containsKey(n-t)){
                return getString7(i) + "1" + getString7(MAP.get(n-t));
            }
        }
        for (int i=1; i<=M; ++i){
            int t = i*(i+1)/2;
            if (t>n)break;
            if(PAIR[n-t] != null){
                return getString7(i) + "1" + getString7(PAIR[n-t][0]) + "1" + getString7(PAIR[n-t][1]);
            }
        }
        return "-1" + PAIR[6][3];
    }

    private static String getString7(int n){
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            sb.append('7');
            n--;
        }
        return sb.toString();
    }
}
