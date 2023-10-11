
import java.io.*;
import java.math.BigInteger;
import java.util.*;


class Main{
    static long p[]=new long[64];
    public static void main(String[] args) {
        ac in=new ac();
        p[0]=1;
        long res=0L;
        for(int i=1;i<64;i++){
            p[i]=p[i-1]*2L;
        }
        for(int i=0;i<63;i++){
            res+=p[i]*in.nextInt();
        }p[63]=in.nextInt();
        String ans="";
        if (p[63] == 1) {
            ans= String.valueOf(new BigInteger("9223372036854775808").add(new BigInteger(String.valueOf(res))));
        }if(p[63]!=1)
        in.print(res);
        in.print(ans);

        in.flush(); 
    }

}
class ac extends PrintWriter {
    BufferedReader br;
    StringTokenizer st;

    ac() {
        this(System.in, System.out);
    }

    ac(InputStream i, OutputStream o) {
        super(o);
        br = new BufferedReader(new InputStreamReader(i));
    }

    String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}
