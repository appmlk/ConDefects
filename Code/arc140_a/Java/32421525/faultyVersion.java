import java.util.*;
import java.io.*;
class Main{
	static ArrayList<ArrayList<Integer>> list;
	static FastScanner fs = null;
	public static void main(String[] args) {
		fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int n = fs.nextInt();
		int k = fs.nextInt();
		String s = fs.next();
		char ch[] = new char[n+1];
		for(int i=1;i<=n;i++){
			ch[i] = s.charAt(i-1);
		}
		for(int i=1;i<=n;i++){
			if(n%i!=0)
				continue;
			int co = 0;
			for(int y = 1;y<=i;y++){
			Map<Integer,Integer> map = new HashMap<>();
			int f = 0; 
			int g = 0;
			for(int j = y;j<=n;j+=i){
				f+=1;
				int d = j%(i);
				if(d==0)
					d = i;
				int x = ch[j]-'a';
				if(map.get(x)==null){
					map.put(x,1);
					g = 1;
				}
				else{
					map.replace(x,1+map.get(x));
					g = Math.max(g,map.get(x));
				}
			}
			co+=(f-g);
		}
		// out.println(co+" --");
			if(co<=k){
				out.println((i));
				break;
			}
		}
		out.close();
	}
	static void sort(int[] a) {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}
	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
		double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}