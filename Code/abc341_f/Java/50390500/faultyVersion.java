import java.io.*;
import java.util.*;

public class Main {
	
	static IOHandler sc = new IOHandler();
	static PrintWriter out = new PrintWriter(System.out);
	static Long [][] memo;
	
	static List<Integer> [] graph;
	
	public static void main(String[] args) throws IOException {
		
        new Thread(null, new Solution(), "solution", 1 << 26 ).start();
    }
	
	private static class Solution implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			int testCases = 1;
			
			for (int i = 1; i <= testCases; ++i) {
				solve(i);
			}
			
			out.flush();
		}
		
		private void solve(int t) {
			// Read question and Test cases thoroughly
			// Check for overflow 
			// Think before solving
			// Search for concepts
			// expected alg like seg tree, trie, binSearch, sqrt decom ... 
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			graph = new List[n + 1];
			
			for (int i = 1; i <= n; ++i) {
				graph[i] = new ArrayList<>();
			}
			
			int u, v;
			
			for (int i = 0; i < m; ++i) {
				u = sc.nextInt();
				v = sc.nextInt();
				
				graph[u].add(v);
				graph[v].add(u);
			}
			
			
			int [][] wp = new int [n + 1][3];
			
			int [] weights = new int [n + 1];
			int [] piece = new int [n + 1];
			
			for (int i = 1; i <= n; ++i) {
				wp[i][0] = i;
				wp[i][1] = sc.nextInt();
				weights[i] = wp[i][1];
			}
			
			for (int i = 1; i <= n; ++i) {
				wp[i][2] = sc.nextInt();
				piece[i] = wp[i][2];
			}
			
			Set<Integer> set;
			
			for (int i = 1; i <= n; ++i) {
				set = new HashSet<>(graph[i]);
				
				graph[i].clear();
				
				for (int num : set) {
					if (weights[num] < weights[i])
						graph[i].add(num);
				}
				
				//Collections.sort(graph[i], (a, b) -> wp[a][1] - wp[b][1]);
			}
			
			Arrays.sort(wp, (a, b) -> a[1] - b[1]);
			
			int node, weight;
			
			
			
			long [] dp = new long [n + 1];
			
			for (int [] mArr : wp) {
				node = mArr[0];
				weight = mArr[1];
				
				if (node == 0)
					continue;
				
				
				memo = new Long [graph[node].size()][weight];
				//System.out.println(node + " " + weight);
				dp[node] = 1 + getScore(graph[node], 0, weight - 1, 0, dp, weights);
			}
			
			// System.out.println(Arrays.toString(dp));
			// System.out.println(Arrays.deepToString(wp));
			
			long result = 0;
			
			for (int i = 1; i <= n; ++i) {
				result += dp[i] * piece[i];
			}
			
			out.println(result);
		}
		
		
		private long getScore(List<Integer> list, int idx, int left, int max, long [] dp , int [] w) {
			if (idx >= list.size() || w[list.get(idx)] > left || left == 0)
				return 0;
			else if (memo[idx][left] != null)
				return memo[idx][left];
			
			long result = getScore(list, idx + 1, left, max, dp, w);
			
			result = Math.max(result, dp[list.get(idx)] + 
					getScore(list, idx + 1, left - w[list.get(idx)], max, dp, w) );
			
			
			memo[idx][left] = result;
			return result;
		}
	}
	
	private static class IOHandler {
      BufferedReader br;
      StringTokenizer st;
 
      public IOHandler() {
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
      
      int [] readArray(int n) {
    	  int [] res = new int [n];
    	  
    	  for (int i = 0; i < n; ++i)
    		  res[i] = nextInt();
    	  
    	  return res;
      }
      
      int [] readArray2(int n) {
    	  int [] res = new int [n + 1];
    	  
    	  for (int i = 1; i <= n; ++i)
    		  res[i] = nextInt();
    	  
    	  return res;
      }
      
      int [][] readGraph(int n, int m, int c) {
    	  int [][] graph = new int [n + 1][];
    	  
    	  int [] count = new int [n + 1];
    	  int [][] arr = new int [m][1 + c];
    	  
    	  int a, b;
    	  
    	  for (int i = 0; i < m; ++i) {
    		  a = sc.nextInt();
    		  b = sc.nextInt();
    		  
    		  arr[i][0] = a;
    		  arr[i][1] = b;
    		  
    		  ++count[a];
    		  ++count[b];
    		  
    		  for (int j = 2; j <= c; ++j) {
    			  arr[i][j] = sc.nextInt();
    		  }
    	  }
    	  
    	  for (int i = 0; i <= n; ++i) {
    		  graph[i] = new int [count[i] * c];
    		  count[i] = 0;
    	  }
    	  int swap;
    	  
    	  for (int [] tArr : arr) {
    		  for (int j = 1; j < tArr.length; ++j) {
    			  graph[tArr[0]][count[tArr[0]]++] = tArr[j]; 
    		  }
    		  
    		  swap = tArr[0];
    		  tArr[0] = tArr[1];
    		  tArr[1] = swap;
    		  
    		  for (int j = 1; j < tArr.length; ++j) {
    			  graph[tArr[0]][count[tArr[0]]++] = tArr[j]; 
    		  }
    	  }
    	  
    	  return graph;
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
