import java.io.*;
import java.util.*;
 
public class Main {
	static PrintWriter out = new PrintWriter(System.out);
    static FastScanner fs = new FastScanner();
	static class MaxSegmentTree {
        private final int[] segtree;
        private final int len;
    
        public MaxSegmentTree(int len) {
            this.len = len;
            segtree = new int[len * 2];
            Arrays.fill(segtree, Integer.MAX_VALUE);
        }
    
        /** Sets the value at ind to val. */
        public void set(int ind, int val) {
            ind += len;
            segtree[ind] = val;
            for (; ind > 1; ind /= 2) {
                segtree[ind / 2] = Math.max(segtree[ind], segtree[ind ^ 1]);
            }
        }
		public int get(int ind) {
			return segtree[ind];
		}
        /** @return the minimum of all elements in [start, end). */
        public int rangeMax(int start, int end) {
            int max = Integer.MIN_VALUE;
            for (start += len, end += len; start < end; start /= 2, end /= 2) {
                if (start % 2 == 1) { max = Math.max(max, segtree[start++]); }
                if (end % 2 == 1) { max = Math.max(max, segtree[--end]); }
            }
            return max;
        }
        public int max(int l, int r) {
            return rangeMax(l, r + 1);
        }
    }
	static class PointScan {
		MaxSegmentTree st;
		PointScan() {
			st = new MaxSegmentTree(600000);
			for (int i = 0; i < 600000; i++) st.set(i, 0);
		}
		public void insert(int[] point) {
			st.set(point[0], Math.max(point[1], st.get(point[0])));
		}
		public boolean containsGreater(int[] point) {
			return st.max(point[0]+1,600000 - 1) > point[1];
		}
	}
	public static HashMap<Integer, Integer> calculateCompressionMap(int[][] points) {
		HashSet<Integer> set = new HashSet<>();
		for (int[] i : points) {
			for (int j : i) set.add(j);
		}
		ArrayList<Integer> order = new ArrayList<>(set);
		Collections.sort(order);
		HashMap<Integer, Integer> compressionMap = new HashMap<>();
		for (int i = 0; i < order.size(); i++) {
			compressionMap.put(order.get(i), i);
		}
		return compressionMap;
	}
	public static void solve() throws IOException {
		int n = fs.nextInt();
		int[][] points = new int[n][3];
		for (int i = 0; i < n; i++) {
			points[i][0] = fs.nextInt();
			points[i][1] = fs.nextInt();
			points[i][2] = fs.nextInt();
		}
		for (int[] i : points) {
			Arrays.sort(i);
		}
		
		HashMap<Integer, Integer> compressionMap = calculateCompressionMap(points);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				points[i][j] = compressionMap.get(points[i][j]);
			}
		}
		
		TreeMap<Integer, ArrayList<int[]>> byX = new TreeMap<>(Collections.reverseOrder());
		for (int[] i : points) {
			if(!byX.containsKey(i[0])) byX.put(i[0], new ArrayList<int[]>());
			byX.get(i[0]).add(new int[] {i[1],i[2]});
		}
		for (int i : byX.keySet()) {
			Collections.sort(byX.get(i), new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					return a[0] - b[0];
				}
			});
		}
		PointScan ps = new PointScan();
		for (int i : byX.keySet()) {
			//out.println(i);
			for (int[] j : byX.get(i)) {
				if(ps.containsGreater(j)) {
					out.println("Yes");
					return;
				}
			}
			for (int[] j : byX.get(i)) {
				ps.insert(j);
			}
		}
		out.println("No");
 	}	
	public static void main(String[] args) throws IOException {
		solve();
		out.close();
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
	}
	public static void shuffleArray(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap arr[i] with arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}