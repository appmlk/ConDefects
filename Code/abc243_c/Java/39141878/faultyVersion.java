import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        JS sc = new JS();
        PrintWriter out = new PrintWriter(System.out);
        //int T = sc.nextInt();
        int T = 1;
        for(int tt = 0; tt < T; tt++) {
            int n = sc.nextInt();
            HashMap<Integer, Integer> hm = new HashMap<>();
            int[][] arr = new int[n][2];
            for(int i = 0; i < n; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
            }
            String dir = sc.nextLine();
            boolean collides = false;
            for(int i = 0; i < n; i++) {
                if(!hm.containsKey(arr[i][1]) && dir.charAt(i) == 'R') {
                    hm.put(arr[i][1], arr[i][0]);
                }
                else if(hm.containsKey(arr[i][1])) {
                    if(arr[i][0] < hm.get(arr[i][1])) hm.put(arr[i][1], arr[i][0]);
                }
            }
            for(int i = 0; i < n; i++) {
                if(hm.containsKey(arr[i][1])) {
                    if(dir.charAt(i) == 'L' && arr[i][0] > hm.get(arr[i][1])) collides = true;
                }
            }
            if(collides) out.println("Yes");
            else out.println("No");

        }
        out.close();
    }
    static class JS {
        public int BS = 1<<16;
        public char NC = (char)0;
        byte[] buf = new byte[BS];
        int bId = 0, size = 0;
        char c = NC;
        double num = 1;
        BufferedInputStream in;

        public JS() {
            in = new BufferedInputStream(System.in, BS);
        }

        public JS(String s) throws FileNotFoundException {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        }

        public char nextChar(){
            while(bId==size) {
                try {
                    size = in.read(buf);
                }catch(Exception e) {
                    return NC;
                }
                if(size==-1)return NC;
                bId=0;
            }
            return (char)buf[bId++];
        }

        public int nextInt() {
            return (int)nextLong();
        }

        public long nextLong() {
            num=1;
            boolean neg = false;
            if(c==NC)c=nextChar();
            for(;(c<'0' || c>'9'); c = nextChar()) {
                if(c=='-')neg=true;
            }
            long res = 0;
            for(; c>='0' && c <='9'; c=nextChar()) {
                res = (res<<3)+(res<<1)+c-'0';
                num*=10;
            }
            return neg?-res:res;
        }

        public double nextDouble() {
            double cur = nextLong();
            return c!='.' ? cur:cur+nextLong()/num;
        }

        public String next() {
            StringBuilder res = new StringBuilder();
            while(c<=32)c=nextChar();
            while(c>32) {
                res.append(c);
                c=nextChar();
            }
            return res.toString();
        }

        public String nextLine() {
            StringBuilder res = new StringBuilder();
            while(c<=32)c=nextChar();
            while(c!='\n') {
                res.append(c);
                c=nextChar();
            }
            return res.toString();
        }

        public boolean hasNext() {
            if(c>32)return true;
            while(true) {
                c=nextChar();
                if(c==NC)return false;
                else if(c>32)return true;
            }
        }
    }
}
