//Some of the methods are copied from GeeksforGeeks Website  
import java.util.*;
import java.lang.*;
import java.io.*;
@SuppressWarnings("unchecked")
//@SuppressWarnings("deprecation")

public class Main
{ 
  //static Scanner sc=new Scanner(System.in);
  //static  Reader sc=new Reader();
   static FastReader sc=new FastReader(System.in);
  
  static long mod = (long)(1e9)+7;
  static int max_num=(int)1e5+5;
  static int MAXN=(int)1e5+5;
  static List<Integer> gr[];
  

 public static void main (String[] args) throws java.lang.Exception
   {
	    /* out.println("Case #"+tt+": "+ans ); 
           gr=new ArrayList[n];
           for(int i=0;i<n;i++) gr[i]=new ArrayList<>();
           
           int l=0,r=n,ans=0;
           while(l<=r)
            {
              int m=l+(r-l)/2;
              if(val(m))
               {
                 ans=m;
                 l=m+1;
               }
              else
                r=m-1;
            }

          Collections.sort(al,Collections.reverseOrder());
	      StringBuilder sb=new StringBuilder(""); sb.append(cur); sb=sb.reverse(); String rev=sb.toString();
           
          map.put(a[i],map.getOrDefault(a[i],0)+1);
	      map.putIfAbsent(x,new ArrayList<>());
	        
          long n=sc.nextLong();

	      String s=sc.next();
	      char a[]=s.toCharArray();
          
       */
		  int t = sc.nextInt();
		  for(int tt=1;tt<=t;tt++)
		    { 
		      long a=sc.nextLong();
          long b=sc.nextLong();
		      long ans=b-2l*a;
		      boolean f=false;
          if(ans>=0 && (ans&a)==0) f=true;
          flag(f);
		     
		    }
	     out.flush();
	     out.close();
    }

/*------------------SOLUTION ENDS HERE--------------------------- */

static void flag(boolean flag)
   {
       out.println(flag ? "Yes" : "No");
       out.flush();
   }

//   static class Pair
//    {
//       int x,y;
//       Pair(int x,int y)
//        {
//           this.x=x;
//           this.y=y;
//        }
//    }

/*
    Arrays.sort(p, new Comparator<Pair>()
    {
       @Override
       public int compare(Pair o1,Pair o2)
       {
          if(o1.x>o2.x) return 1;
          else if(o1.x==o2.x)
            {
               if(o1.y>o2.y) return 1;
               else return -1;
             }
          else return -1;
      }});
*/ 

 /*                     
             Map<Integer,Integer> map=new HashMap<>();
             for(int i=0;i<n;i++)
               {
                  if(!map.containsKey(a[i]))
                   map.put(a[i],1);
                 else
                   map.replace(a[i],map.get(a[i])+1);
               }
             Set<Map.Entry<Integer,Integer>> hmap=map.entrySet();
             for(Map.Entry<Integer,Integer> data : hmap)
               {
          
               }
            Iterator<Integer> itr = set.iterator();
            while(itr.hasNext())
            {
              int val=itr.next();
            }
   */
   
static void print(int a[])
  {
     int n=a.length;
     for(int i=0;i<n;i++)
       {
          out.print(a[i]+" ");
       }
     out.println();
     out.flush();
  }
static void print(long a[])
  {
     int n=a.length;
     for(int i=0;i<n;i++)
       {
          out.print(a[i]+" ");
       }
     out.println();
     out.flush();
  } 
static void print_int(List<Integer> al)
  {
     int si=al.size();
     for(int i=0;i<si;i++)
       {
          out.print(al.get(i)+" ");
       }
     out.println();
     out.flush();
  }
static void print_long(List<Long> al)
  {
     int si=al.size();
     for(int i=0;i<si;i++)
       {
          out.print(al.get(i)+" ");
       }
     out.println();
     out.flush();
  }
  static void pn(int x)
  {
     out.println(x);
     out.flush();
  }
 static void pn(long x)
  {
     out.println(x);
     out.flush();
  }
  static void pn(String x)
  {
     out.println(x);
     out.flush();
  }
static int LowerBound(int a[], int start,int end,int x) 
 {
    int l=start,r=end,ans=-1;
    while(l<=r) 
     {
        int m=l+(r-l)/2;
        if(a[m]<=x) 
         {
            ans=m;
            l=m+1;
         }
        else r=m-1;
     }
    return ans;
 }
 static int UpperBound(int a[], int start,int end,int x) 
 {
    int l=start,r=end,ans=a.length;
    while(l<=r) 
     {
        int m=l+(r-l)/2;
        if(a[m]>=x) 
         {
            ans=m;
            r=m-1;
         }
        else l=m+1;
     }
    return ans;
 }

static List<Integer> get_primes(int num)
 {
	 boolean[] bool = new boolean[num];
	 for (int i = 0; i< bool.length; i++) 
       {
		    bool[i] = true;
	   }
	 for(int i = 2; i< Math.sqrt(num); i++) 
       {
		   if(bool[i] == true) 
            {
		        for(int j = (i*i); j<num; j = j+i)
                    {
		               bool[j] = false;
		            }
		    }
		}
	   if(num >= 0) 
        {
		   bool[0] = false;
		   bool[1] = false;
		}
      List<Integer> al=new ArrayList<>();
      for(int i=0;i<bool.length;i++) if(bool[i]) al.add(i);
	  return al;
 }
static int spf[] = new int[MAXN];
// Calculating SPF (Smallest Prime Factor) for every number till MAXN
// Time Complexity : O(nloglogn)
static void sieve()
  {
        spf[1] = 1;
        for (int i=2; i<MAXN; i++)
      
            // marking smallest prime factor for every
            // number to be itself.
            spf[i] = i;
      
        // separately marking spf for every even
        // number as 2
        for (int i=4; i<MAXN; i+=2)
            spf[i] = 2;
      
        for (int i=3; i*i<MAXN; i++)
        {
            // checking if i is prime
            if (spf[i] == i)
            {
                // marking SPF for all numbers divisible by i
                for (int j=i*i; j<MAXN; j+=i)
      
                    // marking spf[j] if it is not
                    // previously marked
                    if (spf[j]==j)
                        spf[j] = i;
            }
        }
  }
// A O(log n) function returning primefactorization
// by dividing by smallest prime factor at every step
static List<Integer> getFactorization(int x)
    {
        List<Integer> ret = new ArrayList<>();
        while (x != 1)
        {
            ret.add(spf[x]);
            x = x / spf[x];
        }
        return ret;
    }
static long nCr(long a,long b,long mod)
 {
  return (((fact[(int)a] * modInverse(fact[(int)b],mod))%mod * modInverse(fact[(int)(a - b)],mod))%mod + mod)%mod;
 }
static long fact[]=new long[max_num];
static void fact_fill()
 {
   fact[0]=1l;
	for(int i=1;i<max_num;i++)
	  {
	    fact[i]=(fact[i-1]*(long)i);
	    if(fact[i]>=mod)
	      fact[i]%=mod;
	  } 
 }
static long modInverse(long a, long m)
	    {
	       return power(a, m - 2, m);
	    }
static long power(long x, long y, long m)
	    {
	        if (y == 0)
	            return 1;
	        long p = power(x, y / 2, m) % m;
	        p = (long)((p * (long)p) % m);
	        if (y % 2 == 0)
	            return p;
	        else
	            return (long)((x * (long)p) % m);
	    }
        static class UF 
        {
            int[] parents;
            int count;
            UF(int n) 
            {
                parents = new int[n];
                for (int i = 0; i < n; i++) 
                {
                    parents[i] = i;
                }
                count = n;
            }
            
            int find(int i)
            {
                if (parents[i] == i) 
                    return i;
                parents[i] = find(parents[i]);
                return parents[i];
            }
            
           void union(int i, int j) 
           {
                int a = find(i);
                int b = find(j);
                if (a != b)
                {
                    parents[a] = b;
                    count--;
                }
            }
        }
static long sum_array(int a[])
 {
    int n=a.length;
    long sum=0;
    for(int i=0;i<n;i++)
     sum+=a[i];
    return sum;
 }
static long sum_array(long a[])
 {
    int n=a.length;
    long sum=0;
    for(int i=0;i<n;i++)
     sum+=a[i];
    return sum;
 }

static void sort(int[] a) 
   {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}
static void sort(long[] a) 
   {
		ArrayList<Long> l=new ArrayList<>();
		for (long i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}

static void reverse_array(int a[])
 {
    int n=a.length;
    int i,t; 
    for (i = 0; i < n / 2; i++) { 
            t = a[i]; 
            a[i] = a[n - i - 1]; 
            a[n - i - 1] = t; 
        } 
 }
static void reverse_array(long a[])
 {
    int n=a.length;
    int i; long t; 
    for (i = 0; i < n / 2; i++) { 
            t = a[i]; 
            a[i] = a[n - i - 1]; 
            a[n - i - 1] = t; 
        } 
 }

static long gcd(long a, long b) 
    { 
        if (a == 0) 
            return b; 
          
        return gcd(b%a, a); 
    } 
static int gcd(int a, int b) 
    { 
        if (a == 0) 
            return b; 
          
        return gcd(b%a, a); 
    } 

   static class FastReader{
 
        byte[] buf = new byte[2048];
        int index, total;
        InputStream in;
 
        FastReader(InputStream is) {
            in = is;
        }
 
        int scan() throws IOException {
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0) {
                    return -1;
                }
            }
            return buf[index++];
        }
 
        String next() throws IOException {
            int c;
            for (c = scan(); c <= 32; c = scan());
            StringBuilder sb = new StringBuilder();
            for (; c > 32; c = scan()) {
                sb.append((char) c);
            }
            return sb.toString();
        }
 
        int nextInt() throws IOException {
            int c, val = 0;
            for (c = scan(); c <= 32; c = scan());
            boolean neg = c == '-';
            if (c == '-' || c == '+') {
                c = scan();
            }
            for (; c >= '0' && c <= '9'; c = scan()) {
                val = (val << 3) + (val << 1) + (c & 15);
            }
            return neg ? -val : val;
        }
 
        long nextLong() throws IOException {
            int c;
            long val = 0;
            for (c = scan(); c <= 32; c = scan());
            boolean neg = c == '-';
            if (c == '-' || c == '+') {
                c = scan();
            }
            for (; c >= '0' && c <= '9'; c = scan()) {
                val = (val << 3) + (val << 1) + (c & 15);
            }
            return neg ? -val : val;
        }
    }
   
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }
  static  PrintWriter out=new PrintWriter(System.out);
  static int int_max=Integer.MAX_VALUE;
  static int int_min=Integer.MIN_VALUE;
  static long long_max=Long.MAX_VALUE;
  static long long_min=Long.MIN_VALUE;
}
// Thank You !