import java.util.*;
import java.io.*;
public class Main {
	 	static long mod = (long)(1e9+7);
		static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		public static void main(String[] args) throws IOException  {
			FastReader sc = new FastReader();
			int n = sc.nextInt();
			long dp[] = new long[1<<n];
			int arr[][]= new int[n][n];
			for( int i =0 ; i< n;i++) {
				for( int j =i+1 ;j< n; j++) {
					arr[i][j] = sc.nextInt(); arr[j][i] = arr[i][j];
				}
			}
				for( int j =0 ; j< (1<< n) ;j++) {
					for( int i = 0 ;i < n;i++) {
					if( ((j>>i)&1) == 1) continue;
					for( int k =0  ;k < n ;k++) { 
						if( ((j>>k)&1) == 1) continue;
						dp[j|(1<<i)|(1<<k)] = Math.max( dp[j|(1<<i)|(1<<k)], dp[j] + arr[i][k]);
					}
				}
			}
			out.println(dp[(1<<n)-1]);
			
			out.flush();
		}
		
		
		/*
		 * Accept that you maybe thinking wrong
		 * have patience	
		 * Read the question carefully 
		 * Look at the bigger picture
		 * Do mathetical calculations
		 * use the hints
		 * Read the question again
		 * think of binary search
		 * look at test cases
		 * do significant case work 
		 */
		


		static class DSU {
			int[] p, rank, setSize;
			int numSets;
			public DSU(int N) {
				p = new int[numSets = N];
				rank = new int[N];
				setSize = new int[N];
				for (int i = 0; i < N; i++) {
					p[i] = i;
					setSize[i] = 1;
				}
			}
			
			public int findSet(int i) { 
				return p[i] == i ? i : (p[i] = findSet(p[i]));
			}

			public boolean isSameSet(int i, int j) { 
				return findSet(i) == findSet(j);
			}

			public void unionSet(int i, int j) { 
				if (isSameSet(i, j))
					return;
				numSets--;
				int x = findSet(i), y = findSet(j);
				if (rank[x] > rank[y]) {
					p[y] = x;
					setSize[x] += setSize[y];
				} else {
					p[x] = y;
					setSize[y] += setSize[x];
					if (rank[x] == rank[y])
						rank[y]++;
				}
			}	

			public int numDisjointSets() { 
				return numSets;
			}

			public int sizeOfSet(int i) { 
				return setSize[findSet(i)];
			}
				
			public ArrayList<Integer> getParents()
			{
				ArrayList<Integer>pr = new ArrayList<>();
				for(int i=0;i<p.length;i++)
				{
					if(p[i]==i && setSize[i]>2)
						pr.add(i);
				}
				return pr;
			}
		}
		
		static long modInverse(long A, long M)
	    {
	        int g = (int)gcd(A, M);
	        if (g != 1)
	            return -1;
	        else {
	            return power(A, M - 2, M);
	        }
	    }
	
		
		 static long power(long x, long y, long p)
		 {
			 long res = 1; 
			 
			 x = x % p; 
			 
			 if (x == 0)
				 return 0; 
		 
			 while (y > 0)
			 {

				 if ((y & 1) != 0)
					 res = (res * x) % p;
				 
				 y = y >> 1; 
                 x = (x * x) % p;
			 }
			 return res%p;
		 }
		 
		 
		public static boolean ifpowof2(long n ) {
			return ((n&(n-1)) == 0);
		}
		
		static boolean isprime(long x ) {
			if( x== 2) {
				return true;
			}
			if( x%2 == 0) {
				return false;
			}
			for( long i = 3 ;i*i <= x ;i+=2) {
				if( x%i == 0) {
					return false;
				}	
			}
			return true;
		}
		
		static boolean[] sieveOfEratosthenes(long n) { 
			boolean prime[] = new boolean[(int)n + 1];
			for (int i = 0; i <= n; i++) {
				prime[i] = true;
			}
			for (long p = 2; p * p <= n; p++) {   
				if (prime[(int)p] == true) {
					for (long i = p * p; i <= n; i += p)
						prime[(int)i] = false;
				}
			}	
			prime[0] = prime[1] = false;
			return prime;
		}
		 static int[] SieveOfEratosthenes(long n) { 
				boolean prime[] = new boolean[(int)n + 1];
				int arr[] = new int[(int)n+1];
				for (int i = 0; i <= n; i++) {
					prime[i] = true;
					arr[i] = i;
				}
				for (long p = 2; p * p <= n; p++) {   
					if (prime[(int)p] == true) {
						arr[(int)p] = (int)p;
						for (long i = p * p; i <= n; i += p){
						arr[(int)i] =  Math.min(arr[(int)i]  , (int)p);
						prime[(int)i] = false;
						}
					}
				}	
				prime[0] = prime[1] = false;
				arr[0] = arr[1] = 0;
				return arr;
		 }
		 
		public List<Integer> goodIndices(int[] nums, int k) {
	        int n = nums.length;
	        int fst[] = nextLargerElement(nums, n);
	        int scnd[] = nextlargerElement(nums, n);
	        List<Integer> ans = new ArrayList<>();
	        for( int i = 0 ;i < n; i++) {
	        	if( fst[i] == -1 || scnd[i] == -1) {
	        		continue;
	        	}
	        	if( fst[i]-i >= k && i - scnd[i] >= k) {
	        		ans.add(i);
	        	}
	        }
			
			return ans;
	    }
		public static int[] nextLargerElement(int[] arr, int n)	{ 
			Stack<Integer> stack = new Stack<>();
			int rtrn[] = new int[n];
			rtrn[n-1] = -1;
	        stack.push( n-1);
	        for( int i = n-2 ;i >= 0 ; i--){
	            int temp = arr[i];
	            int lol = -1;
	            while( !stack.isEmpty() && arr[stack.peek()] <= temp){
	            	if(arr[stack.peek()] == temp ) {
	            		lol = stack.peek();
	            	}
	                stack.pop();
	            }
	            if( stack.isEmpty()){
	            	if( lol != -1) {
	            		rtrn[i] = lol;
	            	}
	            	else {
	            		rtrn[i] = -1;
	            	}
	            }
	            else{
	            	rtrn[i] = stack.peek();
	            }
	            stack.push( i);
	        }
	        return rtrn;
		}
		public static int[] nextlargerElement(int[] arr, int n)	{ 
			Stack<Integer> stack = new Stack<>();
			int rtrn[] = new int[n];
			rtrn[0] = -1;
	        stack.add( 0);
	        for( int i = 1 ;i < n ; i++){
	            int temp = arr[i];
	            int lol = -1;
	            while( !stack.isEmpty() && arr[stack.peek()] <= temp){
	            	if(arr[stack.peek()] == temp ) {
	            		lol = stack.peek();
	            	}
	                stack.pop();
	            }
	            if( stack.isEmpty()){
	            	if( lol != -1) {
	            		rtrn[i] = lol;
	            	}
	            	else {
	            		rtrn[i] = -1;
	            	}
	            }
	            else{
	            	rtrn[i] = stack.peek();
	            }
	            stack.add( i);
	        }
	        return rtrn;
		}
		static void mysort(int[] arr) {
			for(int i=0;i<arr.length;i++) {
	            int rand = (int) (Math.random() * arr.length);
	            int loc = arr[rand];
	            arr[rand] = arr[i];
	            arr[i] = loc;
	        }
	        Arrays.sort(arr);
	    }
		
		
		static void mySort(long[] arr) {
	        for(int i=0;i<arr.length;i++) {
	            int rand = (int) (Math.random() * arr.length);
	            long loc = arr[rand];
	            arr[rand] = arr[i];
	            arr[i] = loc;
	        }
	        Arrays.sort(arr);
	    }
		
		static long gcd(long a, long b)
		{
			if (a == 0)
				return b;
			return gcd(b % a, a);
		}
		
			   
		 static long lcm(long a, long b)
		 {
			 return (a / gcd(a, b)) * b;
		 }
		 

		 static long rightmostsetbit(long n) {
			 return n&-n;
		 }
		 
		 static long leftmostsetbit(long n)
		    {
		        long k = (long)(Math.log(n) / Math.log(2));
		        return k;
		    }
	 
		 static HashMap<Long,Long> primefactor( long n){
			 HashMap<Long ,Long> hm = new HashMap<>();
			 long temp = 0;
			 while( n%2 == 0) {
				 temp++;
				 n/=2;
			 }	
			 if( temp!= 0) {
				 hm.put( 2L, temp);
			 }
			 long c = (long)Math.sqrt(n);
			 for( long i = 3 ; i <= c ; i+=2) {
				 temp = 0;
				 while( n% i == 0) {
					 temp++;
					 n/=i;
				 }
				 if( temp!= 0) {
					 hm.put( i, temp);
				 }
	 		 }
	 		 if( n!= 1) {
	 			 hm.put( n , 1L);
	 		 }
	 		 return hm;	
		 }
		 
		 
		 static TreeSet<Long> allfactors(long abs) {
			 HashMap<Long,Integer> hm = new HashMap<>();
			 TreeSet<Long> rtrn = new TreeSet<>();
			 rtrn.add(1L);
			 for( long i = 2 ;i*i <= abs; i++) {
				 if( abs% i == 0) {
					 hm.put( i , 0);
					 hm.put(abs/i, 0);
				 }
			 }
			 for( long x : hm.keySet()) {
				 rtrn.add(x);
			 }
			 
			 if( abs != 0) {
				 rtrn.add(abs);
			 }
			 
			 return rtrn;
		 }
			
		 public static int[][] prefixsum( int n , int m , int arr[][] ){
			 int prefixsum[][] = new int[n+1][m+1];
			 for( int i = 1 ;i <= n ;i++) {
				 for( int j = 1 ; j<= m ; j++) {
					 int toadd = 0;
					 if( arr[i-1][j-1] == 1) {
						 toadd = 1;
					 }
					 prefixsum[i][j] = toadd + prefixsum[i][j-1] + prefixsum[i-1][j] - prefixsum[i-1][j-1];
				 }
			 }
			 return prefixsum;
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