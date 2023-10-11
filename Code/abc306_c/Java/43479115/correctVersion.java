
import java.io.*;
import java.math.BigInteger;
import java.util.*;


class Main{
    static int arr[];
    public static void main(String[] args) {
        ac in=new ac();
        int n=in.nextInt();
        arr=new int[n+1];
        for(int i=0;i<3*n;i++){
            int tmp=in.nextInt();
            arr[tmp]++;
            if(arr[tmp]==2)in.print(tmp+" ");
        }

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
