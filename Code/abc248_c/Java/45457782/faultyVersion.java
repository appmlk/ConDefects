
import java.io.*;
import java.util.*;
public class Main {    
    private static void tc() {
        int p=998244353;        
        int n=io.nextInt(),m=io.nextInt(),k=io.nextInt();
        long []f=new long[k+1];f[0]=1;
        for(int i=0;i<n;i++) {
            int up=Math.min(k,m*(i+1));
            long s=0;for(int j=up-m+1;j<=up;j++)s+=f[j];
            for(int j=up;j>=0;j--){
                s+=j-m>=0?f[j-m]:0;
                s-=f[j];f[j]=s;
            }
        }
        io.println(Arrays.stream(f).sum()%p);
    }    
    public static void main(String[] args) {
        boolean fm=false;//fm=true;
        try{io=fm?new Kattio(new FileInputStream(p("input.txt"))):new Kattio();}catch (FileNotFoundException e) {e.printStackTrace();}
        int n=fm?io.nextInt():1;for(int i=0;i<n;i++)tc();io.flush();io.close();
    }
    private static Kattio io=null;
    public static File p(String a){return new File(Main.class.getResource("").getPath()+a);}    
    public static class Kattio extends PrintWriter{
        BufferedReader r;StringTokenizer st;
        public Kattio(){this(System.in,System.out);}
        public Kattio(FileInputStream f){this(f,System.out);}
        public Kattio(InputStream i,OutputStream o){super(o);r=new BufferedReader(new InputStreamReader(i));}
        public Kattio(String intput, String output)throws IOException{super(output);r=new BufferedReader(new FileReader(intput));}
        public String next(){try{while(st==null||!st.hasMoreTokens())st=new StringTokenizer(r.readLine());return st.nextToken();}catch(Exception e){}return null;}
        public int nextInt(){return Integer.parseInt(next());}
        public double nextDouble(){return Double.parseDouble(next());}
        public long nextLong(){return Long.parseLong(next());}
    }
}
