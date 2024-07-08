import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		//final long mod = 1_000_000_007L;
		//final long mod =   998_244_353L;

		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());
		//long l = Long.parseLong(sc.next());
		//String[] s = sc.next().split("");

		HashSet<Long> s = new HashSet<>(n*m);
		for ( int i=0; i<n; i++ ) {
			for ( int j=0; j<m; j++ ) {
				long v = (long)i*(long)n + (long)((i+j)%n);
				s.add(v);
			}
		}

		HashSet<Long> used = new HashSet<>(m*5);
		for ( int i=0; i<m; i++ ) {
			int a = Integer.parseInt(sc.next())-1;
			int b = Integer.parseInt(sc.next())-1;

			long v = (long)a*(long)n +(long)b;

			if ( s.contains(v) ) {
				used.add(v);
				continue;
			}

			boolean f = false;
			for ( int j=0; j<n; j++ ) {
				long vc = (long)a*(long)n +(long)j;

				if ( !s.contains(vc) ) continue;
				if ( used.contains(vc) ) continue;

				for ( int k=0; k<n; k++ ) {
					long vr = (long)k*(long)n +(long)b;
					long vrc = (long)k*(long)n +(long)j;

					if ( !s.contains(vr) ) continue;
					if ( used.contains(vr) ) continue;
					if ( s.contains(vrc) ) continue;

					used.add(v);
					s.add(v);
					s.add(vrc);
					s.remove(vr);
					s.remove(vc);
					f = true;
					break;
				}

				if ( f ) break;
			}
		}

		System.out.println(n*m);

		StringBuilder ans = new StringBuilder();
		for ( long e : s ) {
			long r = e/(long)n +1L;
			long c = e%(long)n +1L;

			ans.append(r+" "+c+System.lineSeparator());
		}
		System.out.print(ans.toString());
		//System.out.println(String.format("%16.12f", ans));
	}
}
