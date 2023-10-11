import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) 
	{
		FastReader sc = new FastReader();
		long h = sc.nextInt();
		long w = sc.nextInt();
		k = sc.nextInt();
		
		long[] vera = new long[(int)h];
		long sv = 0;
		long sh = 0;
		for(int i=0;i<h;i++)
		{
			vera[i]=sc.nextLong();
			sv+=vera[i];
		}
		long[] hora = new long[(int)w];
		for(int i=0;i<w;i++)
		{
			hora[i]=sc.nextLong();
			sh+=hora[i];
		}
		if(sv%k!=sh%k)
		{
			//System.out.println("TEST " + sv + " " + sh);
			System.out.println(-1);
			return;
		}
		sv=0;
		sh=0;
		
		long[] a = new long[(int)h-1];
		long[] b = new long[(int)w-1];
		
		for(int i=0;i<h-1;i++)
		{
			a[i] = vera[i] + w-1;
			a[i]=mod(a[i]);
			sv+=a[i];
		}
		for(int i=0;i<w-1;i++)
		{
			b[i] = hora[i] + h-1;
			b[i]=mod(b[i]);
			sh+=b[i];
		}
		
		cor = hora[(int)w-1]-sv;
		cor=mod(cor);
		{
			long cor2 = vera[(int)h-1]-sh;
			cor2=mod(cor2);
			if(cor!=cor2)
			{
				System.out.println("ERROR! " + sv + " " + sh + "->" + cor + " " + cor2);
			}
		}
		
		if(debug)
		{
			System.out.println(Arrays.toString(a));
			System.out.println(Arrays.toString(b));
		}
		
		//maximize
		Arrays.sort(a);
		Arrays.sort(b);
		
		long ma = 0;
		{
			int y=0;
			int x=0;
			
			//turn all to 9? except corner.
			an = (k-1)*(w-1)*(h-1)+cor;
			if(debug)System.out.println("Startan " + an + ", cor " + cor);
			
			//pairoff?
			long sum1 = 0;
			long sum2 = 0;
			
			long[] a1=b; //bigger array
			long[] a2=a;
			if(h>=w)
			{
				a1=a;a2=b;
			}
			
			int s1 = a1.length;
			int s2 = a2.length;
			
			long remx = 0;
			long remy = 0;
			for(x=0;x<s1;x++)
			{
				sum1+=a1[x];
				remx += k-1-a1[x];
			}
			for(y=0;y<s2;y++)
			{
				sum2+=a2[y];
				remy += k-1-a2[y];
			}
			
			System.out.println(remx + " " + remy);
			y=0;
			for(x=0;x<s1;x++)
			{
				while(a1[x] < k-1)
				{
					while(y<s2&&a2[y]==k-1)y++;
					if(y>=s2)break;
					
					long need = k-1-a1[x];
					//if sumy don't have this much... try maximizing cor instead?
					//System.out.println(x + " " + y + " need "+  need + ", rem " + remx + " " + remy);
					if(need > remy)
					{
						//System.out.println("Break " + x + " " + y);
						long rem = Math.min(remx, remy);
						
						if(rem>=cor)
						{
							an+=k;
						}
						x=s1;
						y=s2;
						break;
					}
					
					if(a1[x] <= a2[y])
					{ //a2 becomes 9
						long d = k-1-a2[y];
						//System.out.println("Go 1 with " + d + ", val " + a1[x] + " " + a2[y]);
						
						a1[x]=mod(a1[x]+d);
						a2[y]=mod(a2[y]+d);
						if(a2[y]!=k-1)System.out.println("ERROR1!");
						
						y++;
						an -= d; //loss of 9
						an += incor(-d); //corner is decreased
						
						//System.out.println("Pair off " + a1[x] + " " + a2[y] + "->" + d);
						
						remy-=d;
						remx-=d;
					}
					else
					{
						//a1 becomes 9
						long d = k-1-a1[x];
						//System.out.println("Go 2 with " + d + ", val " + a1[x] + " " + a2[y]);
						a1[x]=mod(a1[x]+d);
						a2[y]=mod(a2[y]+d);
						if(a1[x]!=k-1)System.out.println("ERROR2!");
						
						an -= d; //loss of 9
						an += incor(-d); //corner is decreased
						
						remy-=d;
						remx-=d;
					}
				}
				if(y>=s2)
				{
					//System.out.println("Breaky " + x + " " + y + ", rem " + remy);
					break;
				}
				
				//make a1[x] to 9 by increasing it and a2[y].
				//possibly increase a2[y] to 9, if 
				
				
			}
			
			
			for(x=0;x<s1;x++)an+=a1[x];
			for(y=0;y<s2;y++)an+=a2[y];
			
			/*
			long[] remmer=null;
			if(x<s1)remmer = a1;
			if(y<s2)remmer = a2;
			*/
			
			if(debug)
			{
				System.out.println(Arrays.toString(a1));
				System.out.println(Arrays.toString(a2));
			}
		}
		System.out.println(an);
		
		//corner case w,h=1
		
	}
	static boolean debug = false;
	static long an, cor;
	static int k;
	
	static long incor(long inc)
	{
		long cor2 = mod(cor+inc);
		
		long d = cor2-cor;
		
		cor=cor2;
		
		return d;
	}
	
	static long mod(long a)
	{
		if(a>=k)a%=k;
		while(a<0)a+=k;
		return a;
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
                if(st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
	
}
