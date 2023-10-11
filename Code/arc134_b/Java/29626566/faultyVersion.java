import java.io.*;
import java.util.*;
import java.lang.*;
// import java.math.BigInteger;

public class Main {
    public static void swap(int firstIndex, int secondIndex,char[] a,boolean[] vis){
        char temp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = temp;
        vis[firstIndex] = true;
        vis[secondIndex] = true;
    }
    public static void solve(FastReader in, PrintWriter out, int nTestCase){   
        int n = in.nextInt();
        char[] a = in.next().toCharArray();
        boolean[] vis = new boolean[n];
        ArrayList<Pair> arr = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            arr.add(new Pair(a[i],i));
        }
        Collections.sort(arr);
        
        int last = n+1;
        int index = 0;

        // for(Pair p:arr) out.println(p.a+" "+p.b);
        
        for(int i=0;i<n;i++){
            
            while(index<arr.size() && arr.get(index).b<=i) index++;
            if(index==arr.size()) break;

            Pair p = arr.get(index);
            if(p.b>i && p.b<last && p.a<a[i]){

               if(!vis[i] && !vis[p.b]){

                    // out.println(i+" "+p.b);
                    swap(i,p.b,a,vis);
                    last = p.b;
                    index++;
               }
            }
        }
        for(char c:a) out.print(c);
        out.println();
    }

    public static void fastSort(int[] a){
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            int next = random.nextInt(a.length);
            int curr = a[next];
            a[next] = a[i];
            a[i] = curr;
        }
        Arrays.sort(a);
    }

    public static void main(String[] args) throws IOException {
        FastReader in;
        PrintWriter out;
        boolean isInputOutputFiles = args!=null&&args.length>0&&args[0].equals("LOCAL_RUN");
        if (isInputOutputFiles) {
            in = new FastReader(new BufferedReader(new FileReader("./input/input.txt")));
            out = new PrintWriter(new BufferedWriter(new FileWriter("./output/output.txt")));
        }
        else{
            in = new FastReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        long startT = 0;
        if (isInputOutputFiles) startT = System.currentTimeMillis();
        int T = 1;
        // T = in.nextInt();
        for (int tt = 1; tt <= T; tt++) {
            solve(in, out, tt);
        }
        if (isInputOutputFiles) out.println("Total time: "+(System.currentTimeMillis()-startT)+"ms");
        out.close();
    }


    static class Pair implements Comparable<Pair> {
    char a;
    int b;

    public Pair(char a,int b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(Pair o) {
        if(this.a==o.a)
            return -(this.b-o.b);
        return (int) (this.a - o.a);
    }
}


static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader(Reader rd) {
        br = new BufferedReader(rd);
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
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

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public int[] readArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = nextInt();
        return a;
    }
}
}


