

import java.io.*;
import java.util.*;

public class Main {
	
	static IOHandler sc = new IOHandler();
	static List<Integer> [] graph;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		int [][] arr = new int [r][c];
		
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int min, max;
		
		List<int []> list1 = new ArrayList<>();
		
		for (int i = 0; i < r; ++i) {
			min = 1_000_001;
			max = 0;
			
			for (int num : arr[i]) {
				if (num == 0) continue;
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
			
			if (max == 0) continue;
			
			list1.add(new int [] {i, min, max});
		}
		
		Collections.sort(list1, (a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
		
		int cR;
		
		int [] currentPos = new int [c];
		
		
		Arrays.fill(currentPos, 0);
		
		int maxV = 0;
		
		List<int []> list2 = new ArrayList<>();
		int idx;
		
		List<List<Integer>> groups = new ArrayList<>();
		boolean [] wait = new boolean [r*c+2];
		int [] groupsLeft = new int [c];
		graph = new List[c];
		
		for (int i = 0; i < c; ++i) {
			graph[i] = new ArrayList<>();
		}
		
		int cG = 0; 
		int s,e;
		
		for (int [] tArr : list1 ) {
			cR = tArr[0];
			
			if (tArr[1] < maxV) {
				System.out.println("No");
				return;
			}else if (tArr[1] == tArr[2]) {
				continue;
			}
			
			maxV = tArr[2];
			
			list2 = new ArrayList<>();
			
			
			for (int j = 0; j < c; ++j) {
				if (arr[cR][j] == 0) 
					continue;
				
				list2.add(new int [] {j,arr[cR][j]} );
				
			}
			
			Collections.sort(list2, (a, b) -> a[1] - b[1]);
			
			idx = 0;
			int maxB = -1;
			int val;
			s = cG;
			
			for (int j = 0; j < list2.size(); ++j) {
				if (j > 0 && list2.get(j)[1] != list2.get(j - 1)[1] ) {
					cG++;
				}
				
				if (groups.size() == cG)
					groups.add(new ArrayList<>());
				
				groups.get(cG).add(list2.get(j)[0]);
				graph[list2.get(j)[0]].add(cG);
				groupsLeft[list2.get(j)[0]]++;
			}
			
			for (int j = s + 1; j <= cG; ++j) {
				wait[j] = true;
			}
			
			e = cG++;
		}
		
		int [] groupsCount = new int [cG];
		
		for (int i = 0; i < groupsCount.length; ++i) {
			groupsCount[i] = groups.get(i) == null ? 0 : groups.get(i).size();
		}

		Queue<Integer> groupsQueue = new LinkedList<>();
		Queue<Integer> numQueue = new LinkedList<>();
		
		for (int i = 0; i < c; ++i) {
			if (groupsLeft[i] == 0) {
				numQueue.add(i);
			}
		}
		
		for (int i = 0; i < cG; ++i) {
			if (!wait[i]) {
				groupsQueue.add(i);
			}
		}
		
		
		int curG, curE;
		int count = 0;
		
		/*
		System.out.println(groups.get(0));
		System.out.println(groups.get(1));
		System.out.println(Arrays.toString(wait));
		System.out.println(numQueue);
		System.out.println(groupsQueue);
		*/
		
		
		while (!numQueue.isEmpty() || !groupsQueue.isEmpty()) {
			
			while (!groupsQueue.isEmpty()) {
				curG = groupsQueue.remove();
				
				
				
				for (int num : groups.get(curG) ) {
					--groupsLeft[num];
					
					if (groupsLeft[num] == 0) {
						numQueue.add(num);
					}
				}
			}
			
			while (!numQueue.isEmpty()) {
				curE = numQueue.remove();
				++count;
			
				for (int num : graph[curE]) {
					groupsCount[num]--;
					
					if (groupsCount[num] == 0) {
						
						if (wait[num + 1]) {
							groupsQueue.add(num + 1);
						}
					}
				}
			}
		}
		
		System.out.println(count == c ? "Yes" : "No");
		
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
