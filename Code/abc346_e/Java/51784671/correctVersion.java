import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	
	static Reader in = new Reader();
	static PrintWriter out = new PrintWriter(System.out);

	public static void main(String[] args) throws IOException {
		int h = in.nextInt();
		int w = in.nextInt();
		boolean[] hs = new boolean[h + 1];
		boolean[] ws = new boolean[w + 1];
		int m = in.nextInt();
		int[] ts = new int[m];
		int[] as = new int[m];
		int[] xs = new int[m];
		int i = 0;
		while (m-- > 0) {
			int t = in.nextInt();
			int a = in.nextInt();
			int x = in.nextInt();
			ts[i] = t;
			as[i] = a;
			xs[i] = x;
			i++;
		}
		long[] cnt = new long[200001];
		for (int j = ts.length - 1; j >= 0; j--) {
			if (ts[j] == 1) {
				if (!hs[as[j]]) {
					cnt[xs[j]] += (long) w;
					h--;
					hs[as[j]] = true;	
				}
			} else {
				if (!ws[as[j]]) {
					cnt[xs[j]] += (long) h;
					w--;
					ws[as[j]] = true;	
				}
			}
		}
		cnt[0] += (long) h * w;
		Map<Integer, Long> map = new HashMap<>();
		for (int j = 0; j <= 200000; j++) {
			if (cnt[j] > 0) {
				map.put(j, cnt[j]);
			}
		}
		out.println(map.size());
		Map<Integer, Long> map2 = map.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue, 
						(o, n) -> o,
						LinkedHashMap::new));
		for (Entry<Integer, Long> e : map2.entrySet()) {
			out.println(e.getKey() + " " + e.getValue());
		}
		out.flush();
	}
	

}
	

class Reader {
	
	BufferedReader br;
	StringTokenizer st;
	
	public Reader() {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer("");
	}
	
	public String next() throws IOException {
		while (!st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
	
	public int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
	
    public BigInteger nextBigInteger() throws IOException {
        return new BigInteger(next());
    }
    
	public long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}


