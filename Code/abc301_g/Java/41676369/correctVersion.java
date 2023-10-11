import java.io.*;
import java.util.*;

class Main {
	int N;
	Point[] p;
	Line[] lines;
	Map<Integer, Integer> mulinv;
	
	void calc() {
		N = nextInt();
		p = new Point[N];
		for (int i = 0; i < N; i++) {
			int[] c = nextInts();
			p[i] = new Point(c[0], c[1], c[2]);
		}
		mulinv = new HashMap<>();
		for (int i = 2; i <= N; i++) mulinv.put(i*(i-1), i);

		lines = new Line[N*(N-1)/2];
		int lc = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				lines[lc++] = new Line(p[i], p[j]);
			}
		}
		for (int i = 0; i < lines.length - 1; i++) {
			for (int j = i+1; j < lines.length; j++) {
				if (lines[i] == null) break;
				if (lines[j] == null) continue;
				Point v1 = lines[i].p2.sub(lines[i].p1);
				Point v2 = lines[j].p2.sub(lines[j].p1);
				Point op = v1.outProd(v2);
				if (op.isZero()) {
					Point v3 = lines[j].p2.sub(lines[i].p1);
					Point op2 = v1.outProd(v3);
					if (op2.isZero()) {
						lines[j].mul += lines[i].mul;
						lines[i] = null;
					}
				}
			}
		}
		List<Line> ll = new ArrayList<>();
		for (int i = 0; i < lines.length; i++)
			if (lines[i] != null) {
				ll.add(lines[i]);
				lines[i].mul = mulinv.get(lines[i].mul) - 1;
			}

		Line[] l = ll.toArray(new Line[0]);
		Map<Point, Set<Integer>> list = new HashMap<>();

		for (int i = 0; i < l.length-1; i++) {
			for (int j = i+1; j < l.length; j++) {
				Point v1 = l[i].p2.sub(l[i].p1);
				Point v2 = l[j].p2.sub(l[j].p1);
				Point op = v1.outProd(v2);
				Point pi1 = l[i].p1, pj1 = l[j].p1;
				if (lv(op.x * (pj1.x - pi1.x) + op.y * (pj1.y - pi1.y) + op.z * (pj1.z - pi1.z)) != 0) {
					continue;
				}
				if (lv(op.y) == 0 && lv(op.z) == 0) continue;
				double t;
				if (lv(op.z) != 0) t = - ((pi1.x * v1.y - pi1.y * v1.x ) - (pj1.x * v1.y - pj1.y * v1.x)) / op.z;
				else if (lv(op.x) != 0) t = - ((pi1.y * v1.z - pi1.z * v1.y ) - (pj1.y * v1.z - pj1.z * v1.y)) / op.x;
				else t = - ((pi1.z * v1.x - pi1.x * v1.z ) - (pj1.z * v1.x - pj1.x * v1.z)) / op.y;

				double x = pj1.x + t * v2.x;
				if (lv(x) >= 0) continue;
				double y = pj1.y + t * v2.y;
				double z = pj1.z + t * v2.z;
				Point p = new Point(x, y, z);
				Set<Integer> s = list.get(p);
				if (s == null) s = new HashSet<>();
				s.add(i); s.add(j);
				list.put(p, s);
			}
		}
		int max = 0;
		for (Point p: list.keySet()) {
			int c = 0;
			for (int i: list.get(p)) c += l[i].mul;
			max = Math.max(max, c);
		}
		for (Line line: l) if (line.p1.x != line.p2.x) max = Math.max(max, line.mul);

		System.out.println(N-max);
	}

	static long lv(double x) { return Point.lv(x); }
	// ---
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String next() { try { return br.readLine(); } catch (Exception e) { return null; } }
	String[] nexts() { return next().split(" "); }

	static int i(String s) { return Integer.parseInt(s); }
	int nextInt() { return i(next()); }
	int[] nextInts() { return Arrays.stream(nexts()).mapToInt(Main::i).toArray(); }

	public static void main(String[] args) {
		new Main().calc();
	}
}

final class Point {
	static final double EPS = 1e-7;
	double x, y, z;
	Point(double a, double b, double c) { x = a; y = b; z = c; }
	Point sub(Point p) { return new Point(x-p.x, y-p.y, z-p.z); }
	Point outProd(Point p) { return new Point(y*p.z - z*p.y, z*p.x - x*p.z, x*p.y - y*p.x); }
	double sqLen() { return x*x + y*y + z*z; }
	boolean isZero() { return lv(sqLen()) == 0; }
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		return lv(x) == lv(p.x) && lv(y) == lv(p.y) && lv(z) == lv(p.z);
	}
	@Override
	public int hashCode() {
		long v = lv(x) + lv(y) * 41 + lv(z) * 97;
		return (int)(v ^ (v >>> 32));
	}
	static long lv(double x) { return (long)Math.floor(x/EPS + 0.5); }
}
class Line {
	Point p1, p2;
	int mul = 2;
	Line(Point p, Point q) {
		if (p.x < q.x) { p1 = p; p2 = q; }
		else {p1 = q; p2 = p; }
	}
}