
import java.io.*;
import java.util.*;

public class Main {
	
	static IOHandler sc = new IOHandler();
	static PrintWriter out = new PrintWriter(System.out);
	
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
			
			int [] arr = sc.readArray2(n);
			
			int q = sc.nextInt();
			
			int [][] ops = new int [q + 1][];
 			
 			int target = 1;
 			
 			while (target < n) {
 				target = target << 1;
 			}
 			
 			GroupTemplate gT = new GroupTemplate(n);
 			
 			int type;
			int res;
			int [] test;
			int l, r, lg, rg, x;
			
			for (int i = 1; i <= n; ++i) {
				gT.update(i, i, new int [] {arr[i], 0}, 1);
			}
			
			StringBuilder sb = new StringBuilder();
			
			int idx;
			
			for (int i = 1; i <= q; ++i) {
				
				type = sc.nextInt();
				
				if (type == 1) {
					l = sc.nextInt();
					r = sc.nextInt();
					x = sc.nextInt();
					ops[i] = new int [] {x, i, l, r};

					gT.update(l, r, ops[i], 1);
					
				}else if (type == 2) {
					idx = sc.nextInt();
					test = ops[idx];
					l = test[2];
					r = test[3];
					
					gT.update(l, r, ops[idx], 0);
				}else {
					
					idx = sc.nextInt();
					sb.append(Math.max(arr[idx], gT.retrieve(idx)));
					sb.append("\n");
				}
			}
			
			out.print(sb);
			
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

class GroupTemplate {
    List<TreeSet<int []> []> values;    
    
    GroupTemplate(int n){
        int size = 1;
        
        while (size < n)
        	size = size << 1;
        
        values = new ArrayList<>();
        
        while (size > 1){
            values.add(new TreeSet[size]);
            size += 1;
            size /= 2;
        }
        
        values.add(new TreeSet [1]);
    }
    
    void update(TreeSet<int []> [] setArr, int idx, int [] val, int type) {
    	
    	if (type == 1) {
    		if (setArr[idx] == null)
    			setArr[idx] = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
    	
    		setArr[idx].add(val);	
    	} else {
    		setArr[idx].remove(val);
    	}
    }
    
    void update(int l, int h, int [] val, int type){
    	
    	
        
        l = Math.max(l, 0);
        
        for (TreeSet<int []> [] arr : values){
        	
            //System.out.println(Arrays.toString(arr));
            //System.out.println(l + " " + h + " " + arr[l] + " " + arr[h]);
        	
        	
        	
        	if (l % 2 == 1) {
        		update(arr, l, val, type);
        		++l;
        	}
        	
        	if (h % 2 == 0) {
        		update(arr, h, val, type);
        		--h;
        	}
        	
        	if (h == l) break;
            
            l /= 2;
            h /= 2;
            
            if (h < l) break;
        }
    }
    
    int retrieve(int idx){
    	
    	int result = 0;
    	
        for (TreeSet<int []> [] setArr : values){
            // arr[idx] = next;
            
            if (setArr[idx] != null && !setArr[idx].isEmpty())  {
            	int [] first = setArr[idx].first();
            	
            	result = Math.max(result, first[0]);
            }
            
            idx /= 2;
        }
        
        return result;
    }
}