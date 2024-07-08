// package A;
import java.util.*;
import java.io.*;

public class Main{

    static final int N=(int)5e5+7;
    static String s;
    static int n,m;
    static Node[] tr=new Node[N<<2];

    private static void pu(Node t, Node l, Node r) {    //对象就是传的地址
        int l_len=l.r-l.l+1,r_len=r.r-r.l+1;
        t.lb=(l.lb==l_len)?l.lb+r.lb:l.lb;
        t.rb=(r.rb==r_len)?r.rb+l.rb:r.rb;
        t.mb=Math.max(Math.max(l.mb,r.mb),l.rb+r.lb);

        t.lc=(l.lc==l_len)?l.lc+r.lc:l.lc;
        t.rc=(r.rc==r_len)?r.rc+l.rc:r.rc;
        t.mc=Math.max(Math.max(l.mc,r.mc),l.rc+r.lc);
    }

    static void pushup(int u){
        pu(tr[u],tr[u<<1],tr[u<<1|1]);
    }

    static void pd(Node s){
        int t=s.lb; s.lb=s.lc;  s.lc=t;
        t=s.rb; s.rb=s.rc;   s.rc=t;
        t=s.mb; s.mb=s.mc;  s.mc=t;
        s.rev^=1;
    }

    static void pushdown(int u){
        if(tr[u].rev==0)    return;
        pd(tr[u<<1]); pd(tr[u<<1|1]);
        tr[u].rev=0;
    }

    static void build(int u,int l,int r){
        int t=s.charAt(l)-'0';
        tr[u]=new Node(l,r,t,t,t,t^1,t^1,t^1,0);
        if(l==r)    return;
        int mid=(l+r)>>1;
        build(u<<1,l,mid);  build(u<<1|1,mid+1,r);
        pushup(u);
    }

    static void modify(int u,int l,int r){
        if(tr[u].l>r||tr[u].r<l)    return;
        if(l<=tr[u].l&&tr[u].r<=r)    pd(tr[u]);
        else{
            pushdown(u);
            modify(u<<1,l,r); modify(u<<1|1,l,r);
            pushup(u);
        }
    }

    static Node query(int u,int l,int r){
        if(l<=tr[u].l&&tr[u].r<=r)  return tr[u];
        pushdown(u);
        int mid=(tr[u].l+tr[u].r)>>1;
        if(mid<l)   return query(u<<1|1,l,r);
        if(r<=mid)  return query(u<<1,l,r);
        Node left=query(u<<1,l,r),right=query(u<<1|1,l,r);
        Node res=new Node();    pu(res,left,right);
        return res;
    }


    public static void solve()throws IOException{
//        String[] str=br.readLine().split(" ");
//        n=Integer.parseInt(str[0]); m=Integer.parseInt(str[1]);

        n=nextInt();    m=nextInt();    //此时数据没有空格，有空格的话要用br.readLine()
        s=br.readLine();    s=" "+s;
//        System.out.println(s);

        build(1,1,n);
        for(int i=0;i<m;i++){
//            str=br.readLine().split(" ");
//            int opt=Integer.parseInt(str[0]),l=Integer.parseInt(str[1]),r=Integer.parseInt(str[2]);
            int opt=nextInt(),l=nextInt(),r=nextInt();
            if(opt==1)  modify(1,l,r);
            else pw.println(query(1,l,r).mb);
        }
        pw.flush();
    }


    public static void main(String[] args)throws IOException{

        int T=1;
//        T=nextInt();
        while(T-->0){
            solve();
        }

    }

    static Scanner sc=new Scanner(System.in);
    //这个读字符串可以带有空格，br.readLine(),不用刷新缓存区
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //读字符串时空格，回车，换行都进行分割
    static StreamTokenizer st = new StreamTokenizer(br);

    //pw.println(),没写一次记得刷新缓存区pw.flush()
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    public static int nextInt() throws IOException { st.nextToken(); return (int)st.nval; }
    public static long nextLong() throws IOException { st.nextToken(); return (long)st.nval; }

    public static float nextFloat() throws IOException{ st.nextToken(); return (float)st.nval; }
    public static double nextDouble() throws IOException{ st.nextToken(); return st.nval; }


    public static String next() throws IOException{ st.nextToken(); return st.sval;}
}

class Node{
    int l,r;
    int lb,rb,mb,lc,rc,mc;
    int rev;

    public Node(){}

    public Node(int l, int r, int lb, int rb, int tb, int lc, int rc, int tc, int rev) {
        this.l = l;
        this.r = r;
        this.lb = lb;
        this.rb = rb;
        this.mb = tb;
        this.lc = lc;
        this.rc = rc;
        this.mc = tc;
        this.rev = rev;
    }
}