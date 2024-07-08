import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    static ArrayList<int[]>[] link;
    static ArrayList<int[]>[] child; 
    static long[][] dp;//0 达到de[i] max ,1 不足de[i] max;
    static int[] de;
    static void buildTree(int node,int parent) {
        ArrayList<int[]> e = link[node];
        child[node] = new ArrayList<>();
        for(int[] v:e) {
            if(v[0] == parent) continue;
            child[node].add(v);
            buildTree(v[0], node);
        }
    }
    
    static void dfs(int node) {
        if(child[node].size() == 0) {
            return ;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>((o1,o2)->Long.compare(o2, o1));
        long sum = 0;
        for(int[] v:child[node]) {
            dfs(v[0]);
            sum += dp[v[0]][0];
            long diff = v[1]+dp[v[0]][1]-dp[v[0]][0];
            if(diff>0 && de[v[0]]>0) pq.add(diff);
        }
        int cnt = de[node];
        while(cnt>1 && pq.size()>0) {
            sum += pq.poll();
            cnt--;
        }
        dp[node][1] = sum;
        if(pq.size()>0) {
            dp[node][0] = sum+pq.poll();
        } else {
            dp[node][0] = sum;
        }
    }
    
    static void out(ArrayList<int[]>[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++) {
            sb.append("[");
            for(int[] e:arr[i]) {
                sb.append("{");
                sb.append(String.format("%d,%d",e[0],e[1]));
                sb.append("},");
            }
            sb.append("],");
        }
        System.out.println(sb);
    }
    
    public static void deal(int n,int[] d,int[][] edges) {
        de = d;
        link = new ArrayList[n];
        child = new ArrayList[n];
        for(int i=0;i<n;i++) link[i] = new ArrayList<>();
        for(int[] e:edges) {
            link[e[0]].add(new int[]{e[1],e[2]});
            link[e[1]].add(new int[]{e[0],e[2]});
        }
//        System.out.println(Arrays.toString(de));
//        out(link);
        buildTree(0,-1);
//        out(child);
        dp = new long[n][2];
        dfs(0);
//        System.out.println(Arrays.deepToString(dp));
        out.println(dp[0][0]);
    }
    
	public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();
        int[] d = new int[n];
        for(int i=0;i<n;i++) d[i] = sc.nextInt();
        int[][] edges = new int[n-1][3];
        for(int i=0;i<n-1;i++) {
            edges[i][0] = sc.nextInt()-1;
            edges[i][1] = sc.nextInt()-1;
            edges[i][2] = sc.nextInt();
        }
        deal(n,d,edges);
        out.close();
    }
    
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;
    
    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
        
        public MyScanner() {
                br = new BufferedReader(new InputStreamReader(System.in));
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
        
        String nextLine(){
                String str = "";
    try {
            str = br.readLine();
    } catch (IOException e) {
            e.printStackTrace();
    }
    return str;
        }
        
    }
}