import java.util.*;

import java.io.*;
public class Main 
{
	static int start=0;
	static int end=0;
	static HashMap<Integer,Integer> map=new HashMap<>();
	public static boolean  cyclic(ArrayList<ArrayList<Integer>> arr,int src,int v[],int sv[])
	{

	     v[src]=1;
	     sv[src]=1;
	     boolean flag1=false;
	    
	     for(Integer dep:arr.get(src))
	     {
	    	 
	    	 if(v[dep]==1)
	    	 {
	    		 if(sv[dep]==1)
	    		 {
	    			 start=dep;
	    			 end=src;
	    			 flag1=true;
	    		 }
	    	 }
	    	 else
	    	 { 
	    		 map.put(dep,src);
	    		 flag1|=cyclic(arr,dep,v,sv);     
	    	 }
	     }
	     sv[src]=0;
	     return flag1;
	}
	public static void process()throws IOException
	{
		int n=I();
		int a[]=Ai(n);
		ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
		for(int i=0;i<=n;i++) 
		{
		    arr.add(new ArrayList<Integer>());	
		}
		for(int i=0;i<n;i++)
		{
		    arr.get(i+1).add(a[i]);
		}
		int v[]=new int[n+1];
		int sv[]=new int[n+1];
		boolean flag=true;
		
		for(int i=1;i<=n;i++)
		{

			if(cyclic(arr,i,v,sv))
			{
				
				flag=false;
				break;
			}
		}
		
		ArrayList<Integer> res=new ArrayList<Integer>();
		if(!flag)
		{
			while(end!=start)
			{
		         res.add(end);
		         end=map.get(end);
			}
		}
		res.add(start);
	    for(int i=0;i<res.size();i++)
		{
			p(res.get(i)+" ");
		}
	    pn("");	
			
	}
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;
		
		public FastReader()
		{
			br = new BufferedReader(
					new InputStreamReader(System.in));
		}
		
		String next()
		{
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() { return Integer.parseInt(next()); }
		
		long nextLong() { return Long.parseLong(next()); }
		
		double nextDouble()
		{
			return Double.parseDouble(next());
		}
		
		String nextLine()
		{
			String str = "";
			try {
				str = br.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
	static void sort(char[] a) {
		int n = a.length;
		Random r = new Random();
		for (int i = 0; i < a.length; i++) {
			int oi = r.nextInt(n);
			char temp = a[i];
			a[i] = a[oi];
			a[oi] = temp;
		}
		Arrays.sort(a);
	}
	static FastReader sc = new FastReader();
	static PrintWriter out = new PrintWriter(System.out);
	static void pn(Object o){out.println(o);out.flush();}
	static void p(Object o){out.print(o);out.flush();}
	static void pni(Object o){out.println(o);System.out.flush();}
	static int I() throws IOException{return sc.nextInt();}
	static long L() throws IOException{return sc.nextLong();}
	static double D() throws IOException{return sc.nextDouble();}
	static String S() throws IOException{return sc.next();}
	static char C() throws IOException{return sc.next().charAt(0);}
	static int[] Ai(int n) throws IOException{int[] arr = new int[n];for (int i = 0; i < n; i++)arr[i] = I();return arr;}
	static String[] As(int n) throws IOException{String s[] = new String[n];for (int i = 0; i < n; i++)s[i] = S();return s;}
	static long[] Al(int n) throws IOException {long[] arr = new long[n];for (int i = 0; i < n; i++)arr[i] = L();return arr;}
	static void dyn(int dp[][],int n,int m,int z)throws IOException {for(int i=0;i<n;i++){ for(int j=0;j<m;j++){ dp[i][j]=z;}} }
	
//	*--------------------------------------------------------------------------------------------------------------------------------*//
	
	public static void main(String[] args)throws IOException{try{boolean oj=true;if(oj==true)
	{PrintWriter out=new PrintWriter(System.out);}
	else
	{out=new PrintWriter("output.txt");}
	{long startTime = System.currentTimeMillis();
	process();
	long endTime = System.currentTimeMillis();
	}out.flush();out.close();}catch(Exception e){return;}}}
//*-----------------------------------------------------------------------------------------------------------------------------------