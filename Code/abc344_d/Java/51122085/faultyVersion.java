
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;








//implements Runnable
public class Main{
	static long md=(long)20020219;
	static long Linf=Long.MAX_VALUE/2;
	static int inf=Integer.MAX_VALUE/2;
	static int M=10000;
	static int N=300;
	static int n=0;
	static int m=0;
	static int k=0;
	
	

	public static void main(String[] args) throws Exception{
		AReader input=new AReader();
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));	
		String string=input.next();
		HashMap<String, Integer> hs=new HashMap<String, Integer>();
		for(int i=0;i<string.length();++i) {
			String now=string.substring(0,i+1);
			hs.put(now, inf);
		}hs.put("", 0);
		n=input.nextInt();
		for(int i=1;i<=n;++i) {
			int x=input.nextInt();
			HashMap<String, Integer> may=new HashMap<String, Integer>();
			for(int j=1;j<=x;++j) {
				String string2=input.next();
				for(String y:hs.keySet()) {
					String now=y+string2;
					if(hs.get(now)==null)continue;
					int z=hs.get(y);
					if(may.get(now)==null) {
						may.put(now, z+1);
					}else {
						int t=may.get(now);
						if(t>z+1)
							may.put(now, z+1);
					}
				}
			}
			for(String now:may.keySet()) {
				int y=may.get(now);
				int z=hs.get(now);
				if(y<z)hs.put(now, y);
				
			}
		}
		if(hs.get(string)==null) {
			out.print("-1");
		}else out.print(hs.get(string));
		out.flush();
	    out.close();
	}
//	public static final void main(String[] args) throws Exception {
//		  new Thread(null, new Main(), "线程名字", 1 << 27).start();
//	}
//		@Override
//		public void run() {
//			try {
//				//原本main函数的内容
//				solve();
//
//			} catch (Exception e) {
//			}
//		}
//	static 
//	class Node{
//		int x;
//		int y;
//		public Node(int u,int v) {
//			x=u;
//			y=v;
//		}
//		@Override
//	    public boolean equals(Object o) {
//	        if (this == o) return true;
//	        if (o == null || getClass() != o.getClass()) return false;
//	        Node may = (Node) o;
//	        return x == may.x && y==may.y;
//	    }
//
//	    @Override
//	    public int hashCode() {
//	        return Objects.hash(x, y);
//	    }
//	}
		static
		class AReader{ 
		    BufferedReader bf;
		    StringTokenizer st;
		    BufferedWriter bw;

		    public AReader(){
		        bf=new BufferedReader(new InputStreamReader(System.in));
		        st=new StringTokenizer("");
		        bw=new BufferedWriter(new OutputStreamWriter(System.out));
		    }
		    public String nextLine() throws IOException{
		        return bf.readLine();
		    }
		    public String next() throws IOException{
		        while(!st.hasMoreTokens()){
		            st=new StringTokenizer(bf.readLine());
		        }
		        return st.nextToken();
		    }
		    public char nextChar() throws IOException{
		        //确定下一个token只有一个字符的时候再用
		        return next().charAt(0);
		    }
		    public int nextInt() throws IOException{
		        return Integer.parseInt(next());
		    }
		    public long nextLong() throws IOException{
		        return Long.parseLong(next());
		    }
		    public double nextDouble() throws IOException{
		        return Double.parseDouble(next());
		    }
		    public float nextFloat() throws IOException{
		        return Float.parseFloat(next());
		    }
		    public byte nextByte() throws IOException{
		        return Byte.parseByte(next());
		    }
		    public short nextShort() throws IOException{
		        return Short.parseShort(next());
		    }
		    public BigInteger nextBigInteger() throws IOException{
		        return new BigInteger(next());
		    }
		    public void println() throws IOException {
		        bw.newLine();
		    }
		    public void println(int[] arr) throws IOException{
		        for (int value : arr) {
		            bw.write(value + " ");
		        }
		        println();
		    }
		    public void println(int l, int r, int[] arr) throws IOException{
		        for (int i = l; i <= r; i ++) {
		            bw.write(arr[i] + " ");
		        }
		        println();
		    }
		    public void println(int a) throws IOException{
		        bw.write(String.valueOf(a));
		        bw.newLine();
		    }
		    public void print(int a) throws IOException{
		        bw.write(String.valueOf(a));
		    }
		    public void println(String a) throws IOException{
		        bw.write(a);
		        bw.newLine();
		    }
		    public void print(String a) throws IOException{
		        bw.write(a);
		    }
		    public void println(long a) throws IOException{
		        bw.write(String.valueOf(a));
		        bw.newLine();
		    }
		    public void print(long a) throws IOException{
		        bw.write(String.valueOf(a));
		    }
		    public void println(double a) throws IOException{
		        bw.write(String.valueOf(a));
		        bw.newLine();
		    }
		    public void print(double a) throws IOException{
		        bw.write(String.valueOf(a));
		    }
		    public void print(char a) throws IOException{
		        bw.write(String.valueOf(a));
		    }
		    public void println(char a) throws IOException{
		        bw.write(String.valueOf(a));
		        bw.newLine();
		    }
		}
	}

		

	