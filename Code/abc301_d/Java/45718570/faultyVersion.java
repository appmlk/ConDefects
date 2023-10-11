import java.io.*;
import java.util.*;
public class Main {    
    private void tc() {
        String s=io.next();long t=io.nextLong();
        long a=Long.parseLong(s.replaceAll("\\?", "0"),2);if(a>t){io.println(-1);return;}
        for(int i=0,n=s.length();i<n;i++)if(s.charAt(i)=='?'){var b=1<<(n-i-1);if(a+b<=t)a+=b;}
        io.println(a);return;
    }
    
    public static void main(String[] args) {
        boolean fm=false;//fm=true;
        try{io=fm?new Kattio(new FileInputStream(p("input.txt"))):new Kattio();}catch (FileNotFoundException e) {e.printStackTrace();}
        int n=fm?io.nextInt():1;for(int i=0;i<n;i++)new Main().tc();io.flush();io.close();
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
