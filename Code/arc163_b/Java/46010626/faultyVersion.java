
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main 
{
	static long l,r;
	static ArrayList<Long> rightie = new ArrayList<>();

	public static void main(String[] args) 
	{
		FastReader sc = new FastReader();
		int n = sc.nextInt();
		int num = n-2;
		int m = sc.nextInt();
		long[] a = new long[n-2];
		
		l=sc.nextLong();
		r=sc.nextLong();
		
		OccurrenceCounter<Long> leftie = new OccurrenceCounter<>();
		
		for(int i=0;i<num;i++)
		{
			a[i]=sc.nextLong();
		}
		
		
		if(l>r)
		{
			ArrayList<Long> descRight = new ArrayList<>();
			ArrayList<Long> descLeft = new ArrayList<>();
			
			//move L to left 
			//add more righties as we pass
			for(int i=0;i<num;i++)
			{
				if(a[i] <= l)
				{ //leftie
					descLeft.add(a[i]);
				}
				else if(a[i] > l)
				{
					descRight.add(a[i]);
				}
			}
			//descRight.sort(null);
		    Collections.sort(descRight, Collections.reverseOrder());
		    Collections.sort(descLeft, Collections.reverseOrder());
			
		    ArrayDeque<Long> req = new ArrayDeque<>();

		    long curAns = 999999999999l;
		    for(int i=-1;i<descLeft.size();i++)
		    {
		    	long leftX = l;
		    	if(i>=0)
		    	{
		    		leftX=descLeft.get(i);
			    	if(leftX>=r)descRight.add(leftX);
		    		//req.addLast(leftX);
		    	}
		    	if(leftX<=r)
		    	{ //L moved past original R: this leftie is included even before moving R to the right.
		    		m--;
		    	}
		    	
		    	//System.out.println("Try moving L to " + leftX + ". m: " + m + " " + (i>=0?"[*]":""));
		    	
		    	//move L all the way to leftX
		    	long curCost = l-leftX;

		    	//move R to max(leftX, orineed)
		    	
		    	//if(r<leftX)curCost+=leftX-r; //move R to current L, first.
		    	
		    	//long tax = leftX-r;
		    	long orineed = 0;
		    	//move R further to the right as needed
				if(m > descRight.size())
				{
					continue;
				}
				else if(m>0)
		    	{
		    		long tarR = descRight.get(descRight.size()-m);
		    		//System.out.println("Move R to include " + tarR + ", as we need " + m + " from " + descRight.toString());
		    		orineed = tarR-r;
		    	}
		    	//curCost += Math.min(tax, orineed);
		    	curCost+=orineed;
	    		curAns = Math.min(curAns, curCost);
	    		//System.out.println(" " + curCost + " from " + (l-leftX) + " + " + orineed + "   ->" + leftX + "," + (r+orineed));
		    	
		    	
		    	//add
		    }
		    System.out.println(curAns);
		    
		    
		    
		
		}
		else if(r>=l)
		{
			for(int i=0;i<num;i++)
			{
				if(a[i] < l)
				{ //leftie
					leftie.add(a[i]);
				}
				else if(a[i] > r)
				{
					rightie.add(a[i]);
				}
				else
				{ //middie
					m--;
				}
			}
			rightie.sort(null);
			
			ArrayList<Long> keyList = new ArrayList<>();
			for(long l : leftie.map.keySet())
			{
				keyList.add(l);
			}
		    Collections.sort(keyList, Collections.reverseOrder());
		    //Make a sorted list of the keys for lefties
			//System.out.println(keyList.toString());
			
			if(m<=0)
			{
				System.out.println(0);
				return;
			}
			
			//long[] needFromRight = new long[keyList.size()];

			long curans = 999999999999999l;

			//zero left:
			{
				int needLeft = m;
				if(needLeft <= rightie.size())
				{
					//System.out.println("Need " + needLeft + " from " + rightie.size());
					long totalUsed = 0;
					totalUsed+=(getXthRightie(needLeft)-r);
					curans = Math.min(curans, totalUsed);
				}
			}
			
			//System.out.println("lefter: " + keyList.toString());
			//System.out.println("RIghtie " + rightie.toString());
			
			int needLeft = m;
			for(int left = 0; left < keyList.size(); left++)
			{ //move left to include this
				long lefter = keyList.get(left);
				needLeft -= leftie.get(lefter);
				//System.out.println("With " + lefter + "x" + leftie.get(lefter)+", we now need only " + needLeft + " more.");
				
				if(needLeft > rightie.size())
				{
				//	System.out.println("Move left until " + lefter + ": Not enough right... need " + needLeft + ".");
					continue;
				}
				//get needLeft from the right... how many needs to be used then?
				long totalUsed = l-lefter;
				//System.out.println("Move left until " + lefter + ": " + totalUsed + ", then need " + needLeft);
				if(needLeft>0)
				{
					totalUsed+=(getXthRightie(needLeft)-r);
					//System.out.println("Get right up until " + getXthRightie(needLeft) + ", thus using " + totalUsed);
				}
				curans = Math.min(curans, totalUsed);
			}
			
			if(curans==999999999999999l)a[-1]=1;
			System.out.println(curans);		
		}

	}
	
	static long getXthRightie(int k)
	{
		return rightie.get(k-1);
	}
	
	
	static class OccurrenceCounter<T>
	{ //Stores and modifies occurrences. Only maintain keys whose # of occurrences is not exactly 0.
		HashMap<T, Integer> map = new HashMap<>();
		
		int add(T t, int amount)
		{
			if(map.containsKey(t))
				map.put(t, map.get(t)+amount);
			else
				map.put(t, amount);
			
			if(map.get(t) == 0)
			{
				map.remove(t);
				return 0;
			}
			else return map.get(t);
		}
		
		int add(T t)
		{
			return add(t,1);
		}
		
		int get(T t)
		{
			if(map.containsKey(t))return map.get(t);
			else return 0;
		}
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
    }}
