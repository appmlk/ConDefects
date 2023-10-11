import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        long a=Long.parseLong(s[0]); int b=Integer.parseInt(s[1]);
        if(b==0) System.out.println(a%2==0?"Aoki":"Takahashi");
        else{
            long p=1,q=-1;
            long sum=0;
            for(int i=0; i<b; i++){
                s = bf.readLine().split(" ");
                long x=Long.parseLong(s[0]),y=Long.parseLong(s[1]);
                if(q==-1) sum^=x-1;
                else if(q==y) sum^=1;
                q=y; p=x;
            }
            sum^=a-p;
            System.out.println(sum>0?"Takahashi":"Aoki");
        }
    }
}