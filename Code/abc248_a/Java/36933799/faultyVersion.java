import java.util.*;
import java.io.*;
import java.math.*;

class Main{
    public static final int [] x8 = {0 , 1,1,1,0,-1,-1,-1};
    public static final int [] y8 = {-1,-1,0,1,1, 1, 0,-1};
    public static final int [] y4 = {0,1,0,-1};
    public static final int [] x4 = {1,0,-1,0};
    public static final int MOD = 1000000007;
    public static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        PrintWriter output = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //st = new StringTokenizer(buff.readLine());
        String s = sc.next();
        for(int i=1;i<=9;i++){
            boolean ok = false;
            for(int j=0;j<s.length();j++){
                if(Integer.parseInt(s.substring(j,j+1)) == i) ok = true;
            }
            if(!ok) output.println(i);
        }
        output.flush();
    }
}