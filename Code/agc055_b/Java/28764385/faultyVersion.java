//package com.example.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    final static char bl = '#';
    final static char wh = '.';
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        // input file name goes above
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

        StringTokenizer st = new StringTokenizer(input.readLine());
        int N=Integer.parseInt(st.nextToken());
        char[] S = input.readLine().toCharArray();
        char[] T = input.readLine().toCharArray();
        if (check(N, S, T)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        //out.close();       // close the output file
    }

    private static boolean check(final int N, final char[] S, final char[] T) {
        int p = 0, q = 0;
        LinkedList<Character> ll = new LinkedList<>();
        while(p < N){
            if(ll.isEmpty()){
                ll.add(S[q++]);
            }
            if(ll.peek()==T[p]){
                ll.poll();
                p++;
            }else {
                if (q>=N)return false;
                while (q < N){
                    if (ll.size()<2){
                        ll.add(S[q++]);
                    }else {
                        char c = ll.pollLast();
                        String k = "" + ll.peekLast() + c + S[q];
                        if(k.equals("ABC") || k.equals("BCA") || k.equals("CAB")){
                            ll.poll();
                            if (T[p]=='A'){
                                ll.addFirst('C');
                                ll.addFirst('B');
                            }else if (T[p]=='B'){
                                ll.addFirst('A');
                                ll.addFirst('C');
                            }else {
                                ll.addFirst('B');
                                ll.addFirst('A');
                            }
                            p++;
                            q++;
                            break;
                        }else {
                            ll.add(c);
                            ll.add(S[q++]);
                        }
                    }
                }
            }
        }
        return p==N;
    }
}
