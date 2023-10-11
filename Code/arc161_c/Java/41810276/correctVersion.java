

import java.io.*;
import java.util.*;

public class Main {
	
	static IOHandler sc = new IOHandler();
	static PrintWriter out = new PrintWriter(System.out);
	static List<Integer> [] graph;
	static char [] targetArr;
	
	static String [][][] dp;
	static boolean [][][] vDp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int testCases = sc.nextInt();
		
		for (int i = 1; i <= testCases; ++i) {
			solve(i);
		}
		
		out.flush();
	}
	
	private static void solve(int t) {
		int n = sc.nextInt();
		
		graph = new List[n + 1];
		
		for (int i = 1; i <= n; ++i) {
			graph[i] = new ArrayList<>();
		}
		
		int u, v;
		
		for (int i = 1; i < n; ++i) {
			u = sc.nextInt();
			v = sc.nextInt();
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		String s = sc.next();
		
		char [] arr = new char [n + 1];
		targetArr = new char [n + 1];
		
		for (int i = 1; i <= n; ++i) {
			arr[i] = s.charAt(i - 1);
		}
		
		dp = new String [2][3][n + 1];
		vDp = new boolean [2][3][n + 1];
		
		
		dfs(1, 0, 2, arr, 0);
		
		if (dp[0][2][1] != null) {
			generateResult(1, 0, 2, arr, 0);
			
			StringBuilder sb = new StringBuilder();
			
			//System.out.println(Arrays.toString(targetArr));
			
			for (int i = 1; i <= n; ++i) {
				sb.append(targetArr[i]);
			}
			
			out.println(sb);
			return;
		}
		
		
		dfs(1, 0, 2, arr, 1);
		
		
		if (dp[1][2][1] != null) {
			generateResult(1, 0,2 ,arr, 1);
			
			StringBuilder sb = new StringBuilder();
			
			for (int i = 1; i <= n; ++i) {
				sb.append(targetArr[i]);
			}
			out.println(sb);
			return;
		} 
		
		
		out.println(-1);
	}
	
	private static void generateResult(int node, int parent, int parentVal, char [] arr, int borw) {
		targetArr[node] = borw == 0 ? 'B' : 'W';
		
		//System.out.println(node + " " + Arrays.toString(targetArr));
		
		char c;
		String current = dp[borw][parentVal][node];
		int idx = 0;
		
		int bVal;
		
		for (int child : graph[node]) {
			if (child == parent)
				continue;
			
			c = current.charAt(idx++);
			bVal = c == 'B' ? 0 : 1;
			
			generateResult(child, node, borw, arr, bVal);
		}
	}
	
	
	private static void dfs(int node, int parent, int parentVal, char [] arr, int borw) {
		if (vDp[borw][parentVal][node])
			return;
		
		//System.out.println(node);
		int childCount = 0;
		
		for (int child : graph[node]) {
			if (child == parent)
				continue;
			
			++childCount;
			dfs(child, node, borw, arr, 0);
			dfs(child, node, borw, arr, 1);
		}
		
		int b = 0;
		int w = 0;
		
		if (parentVal == 0)
			++b;
		else if (parentVal == 1)
			++w;
		
		StringBuilder sb = new StringBuilder();
		
		boolean use = true;
		
		for (int child : graph[node]) {
			if (child == parent)
				continue;
			
			if (arr[node] == 'B') {
				if (dp[0][borw][child] != null) {
					sb.append('B');
					++b;
				}else if (dp[1][borw][child] != null){
					sb.append('W');
					++w;
				}else {
					use = false;
					break;
				}
			} else {
				if (dp[1][borw][child] != null){
					sb.append('W');
					++w;
				} else if (dp[0][borw][child] != null) {
					sb.append('B');
					++b;
				} else {
					use = false;
					break;
				}
			}
		}
		
		if (use && arr[node] == 'B' && b > w) {
			dp[borw][parentVal][node] = sb.toString();
		}else if (use && arr[node] == 'W' && w > b) {
			dp[borw][parentVal][node] = sb.toString();
		}
		
		//System.out.println(b + " " + w + " " + arr[node]);
		//System.out.println(borw + " " + parentVal + " " + node + " " + dp[borw][parentVal][node]);
		vDp[borw][parentVal][node] = true;
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

