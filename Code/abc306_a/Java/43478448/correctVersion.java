
import java.io.*;
import java.util.*;


class Main{
    
    public static void main(String[] args) {
        ac in=new ac();int n=in.nextInt();
        String a=in.next();
        for(int i=0;i<a.length();i++){
            in.print(a.charAt(i)+""+a.charAt(i));
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
