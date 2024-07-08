//Har Har Mahadev
//Om Namah Shivay
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(buf.readLine());
        String s = buf.readLine();
        char[]ch = s.toCharArray();
        Arrays.sort(ch);
        long j = Long.parseLong(new String(ch));
        int ans = 0;
        for(long i = 1; i*i<=(long)Math.pow(10, n);i++){
            long p = i*i;
            char[] sub = Long.toString(p).toCharArray();
            Arrays.sort(sub);
            long d = Long.parseLong(new String(sub));
            if(d==j)ans++;
        }
        System.out.println(ans);
    }
}
