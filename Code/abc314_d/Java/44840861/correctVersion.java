import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static int[] dijkstraDistance;
	static ContestPrinter printer = new ContestPrinter(System.out);

	public static void main(String[] args) {
		ContestScanner scan = new ContestScanner();
		int N = scan.nextInt();
		char[] S = scan.next().toCharArray();
		int Q = scan.nextInt();
		int[] last1 = new int[N];
		int last2 = -1;
		int last3 = -1;
		for (int i = 0; i < Q; i++) {
			int t = scan.nextInt();
			int x = scan.nextInt() - 1;
			char c = scan.next().charAt(0);

			if (t == 1) {
				last1[x] = i;
				S[x] = c;
			}
			if (t == 2) last2 = i;
			if (t == 3) last3 = i;
		}

		for (int i = 0; i < N; i++) {
			if (last2 > last3) printer.print(last1[i] > last2 ? S[i] : (S[i] + "").toLowerCase());
			else printer.print(last1[i] > last3 ? S[i] : (S[i] + "").toUpperCase());
		}
		print();

		printer.flush();
		printer.close();
	}

	public static void write(Object... objs) {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("", true)))) {
			for (Object o : objs) {
				pw.println(o);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static long gcd(long l, long r) {
		if (r == 0) return l;
		return gcd(r, l % r);
	}

	public static long lcm(long l, long r) {
		return lcm(new BigInteger(String.valueOf(l)), new BigInteger(String.valueOf(r))).longValue();
	}

	public static BigInteger gcd(BigInteger l, BigInteger r) {
		return l.gcd(r);
	}

	public static BigInteger lcm(BigInteger l, BigInteger r) {
		return l.multiply(r).divide(gcd(l, r));
	}

	@SafeVarargs
	public static <T extends Comparable<T>> T max(T... values) {
		return Collections.max(Arrays.asList(values));
	}

	public static <T extends Comparable<T>> T max(Collection<T> values) {
		return Collections.max(values);
	}

	@SafeVarargs
	public static <T extends Comparable<T>> T min(T... values) {
		return Collections.min(Arrays.asList(values));
	}

	public static <T extends Comparable<T>> T min(Collection<T> values) {
		return Collections.min(values);
	}

	public static <T extends Comparable<T>> int lowerBound(List<T> list, T key) {
		return ~Collections.binarySearch(list, key, (x, y) -> x.compareTo(y) >= 0 ? 1 : -1);
	}

	public static <T extends Comparable<T>> int upperBound(List<T> list, T key) {
		return ~Collections.binarySearch(list, key, (x, y) -> x.compareTo(y) > 0 ? -1 : 1);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByKey(Map<T1, T2> map) {
		return sortMapByKey(map, false);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByKey(Map<T1, T2> map, boolean isReverse) {
		List<Entry<T1, T2>> entries = new LinkedList<>(map.entrySet());
		if (isReverse) entries.sort(Entry.comparingByKey(Collections.reverseOrder()));
		else entries.sort(Entry.comparingByKey());

		LinkedHashMap<T1, T2> result = new LinkedHashMap<>();
		for (Entry<T1, T2> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByValue(Map<T1, T2> map) {
		return sortMapByValue(map, false);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LinkedHashMap<T1, T2> sortMapByValue(Map<T1, T2> map, boolean isReverse) {
		List<Entry<T1, T2>> entries = new LinkedList<>(map.entrySet());
		if (isReverse) entries.sort(Entry.comparingByValue(Collections.reverseOrder()));
		else entries.sort(Entry.comparingByValue());

		LinkedHashMap<T1, T2> result = new LinkedHashMap<>();
		for (Entry<T1, T2> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static long nCr(long n, long r) {
		long result = 1;
		for (int i = 1; i <= r; i++) {
			result = result * (n - i + 1) / i;
		}

		return result;
	}

	public static <T extends Comparable<T>> int[] lis(List<T> array) {
		int N = array.size();
		int[] result = new int[N];
		List<T> B = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int k = lowerBound(B, array.get(i));
			if (k == B.size()) B.add(array.get(i));
			else B.set(k, array.get(i));
			result[i] = k + 1;
		}

		return result;
	}

	public static long lsqrt(long x) {
		long b = (long) Math.sqrt(x);
		if (b * b > x) b--;
		if (b * b < x) b++;

		return b;
	}

	public static void print() {
		print("");
	}

	public static void print(Object o) {
		printer.println(o);
	}

	public static void print(Object... objs) {
		for (Object o : objs) {
			printer.print(o + " ");
		}
		print();
	}
}

class DijkstraComparator<T> implements Comparator<T> {
	@Override
	public int compare(T o1, T o2) {
		return Integer.compare(Main.dijkstraDistance[(int) o1], Main.dijkstraDistance[(int) o2]);
	}
}

class IndexedObject<T extends Comparable<T>> implements Comparable<IndexedObject> {
	int i;
	T value;

	public IndexedObject(int i, T value) {
		this.i = i;
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IndexedObject)) return false;
		return this.i == ((IndexedObject<?>)o).i && this.value.equals(((IndexedObject<?>)o).value);
	}

	@Override
	public int compareTo(IndexedObject o) {
		if (o.value.getClass() != this.value.getClass()) throw new IllegalArgumentException();
		return value.compareTo((T) o.value);
	}

	@Override
	public int hashCode() {
		return this.i + this.value.hashCode();
	}

	@Override
	public String toString() {
		return "IndexedObject{" +
				"i=" + i +
				", value=" + value +
				'}';
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) return false;
		return this.x == ((Point)o).x && this.y == ((Point)o).y;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(x) * 524287 + Integer.hashCode(y);
	}
}

class GraphBuilder {
	private Map<Integer, List<Integer>> edges = new HashMap<>();
	private final int N;
	private final boolean isDirected;

	public GraphBuilder(int N, boolean isDirected) {
		this.isDirected = isDirected;
		this.N = N;
		for (int i = 0; i < N; i++) {
			edges.put(i, new ArrayList<>());
		}
	}

	public GraphBuilder(int N) {
		this(N, false);
	}

	public void addEdge(int u, int v) {
		edges.get(u).add(v);
		if (!isDirected) edges.get(v).add(u);
	}

	public Map<Integer, List<Integer>> getEdges() {
		return edges;
	}

	public int getN() {
		return N;
	}
}

class ContestScanner {
	private final java.io.InputStream in;
	private final byte[] buffer = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	private static final long LONG_MAX_TENTHS = 922337203685477580L;
	private static final int LONG_MAX_LAST_DIGIT = 7;
	private static final int LONG_MIN_LAST_DIGIT = 8;

	public ContestScanner(java.io.InputStream in){
		this.in = in;
	}
	public ContestScanner(java.io.File file) throws java.io.FileNotFoundException {
		this(new java.io.BufferedInputStream(new java.io.FileInputStream(file)));
	}
	public ContestScanner(){
		this(System.in);
	}

	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		}else{
			ptr = 0;
			try {
				buflen = in.read(buffer);
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
			if (buflen <= 0) {
				return false;
			}
		}
		return true;
	}
	private int readByte() {
		if (hasNextByte()) return buffer[ptr++]; else return -1;
	}
	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}
	public boolean hasNext() {
		while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
		return hasNextByte();
	}
	public String next() {
		if (!hasNext()) throw new java.util.NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while(isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext()) throw new java.util.NoSuchElementException();
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while (true) {
			if ('0' <= b && b <= '9') {
				int digit = b - '0';
				if (n >= LONG_MAX_TENTHS) {
					if (n == LONG_MAX_TENTHS) {
						if (minus) {
							if (digit <= LONG_MIN_LAST_DIGIT) {
								n = -n * 10 - digit;
								b = readByte();
								if (!isPrintableChar(b)) {
									return n;
								} else if (b < '0' || '9' < b) {
									throw new NumberFormatException(
											String.format("%d%s... is not number", n, Character.toString(b))
									);
								}
							}
						} else {
							if (digit <= LONG_MAX_LAST_DIGIT) {
								n = n * 10 + digit;
								b = readByte();
								if (!isPrintableChar(b)) {
									return n;
								} else if (b < '0' || '9' < b) {
									throw new NumberFormatException(
											String.format("%d%s... is not number", n, Character.toString(b))
									);
								}
							}
						}
					}
					throw new ArithmeticException(
							String.format("%s%d%d... overflows long.", minus ? "-" : "", n, digit)
					);
				}
				n = n * 10 + digit;
			}else if(b == -1 || !isPrintableChar(b)){
				return minus ? -n : n;
			}else{
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}
	public int nextInt() {
		long nl = nextLong();
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
		return (int) nl;
	}
	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public long[] nextLongArray(int length){
		long[] array = new long[length];
		for(int i=0; i<length; i++) array[i] = this.nextLong();
		return array;
	}
	public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map){
		long[] array = new long[length];
		for(int i=0; i<length; i++) array[i] = map.applyAsLong(this.nextLong());
		return array;
	}
	public int[] nextIntArray(int length){
		int[] array = new int[length];
		for(int i=0; i<length; i++) array[i] = this.nextInt();
		return array;
	}
	public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map){
		int[] array = new int[length];
		for(int i=0; i<length; i++) array[i] = map.applyAsInt(this.nextInt());
		return array;
	}
	public double[] nextDoubleArray(int length){
		double[] array = new double[length];
		for(int i=0; i<length; i++) array[i] = this.nextDouble();
		return array;
	}
	public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map){
		double[] array = new double[length];
		for(int i=0; i<length; i++) array[i] = map.applyAsDouble(this.nextDouble());
		return array;
	}

	public long[][] nextLongMatrix(int height, int width){
		long[][] mat = new long[height][width];
		for(int h=0; h<height; h++) for(int w=0; w<width; w++){
			mat[h][w] = this.nextLong();
		}
		return mat;
	}
	public int[][] nextIntMatrix(int height, int width){
		int[][] mat = new int[height][width];
		for(int h=0; h<height; h++) for(int w=0; w<width; w++){
			mat[h][w] = this.nextInt();
		}
		return mat;
	}
	public double[][] nextDoubleMatrix(int height, int width){
		double[][] mat = new double[height][width];
		for(int h=0; h<height; h++) for(int w=0; w<width; w++){
			mat[h][w] = this.nextDouble();
		}
		return mat;
	}

	public char[][] nextCharMatrix(int height, int width){
		char[][] mat = new char[height][width];
		for(int h=0; h<height; h++){
			String s = this.next();
			for(int w=0; w<width; w++){
				mat[h][w] = s.charAt(w);
			}
		}
		return mat;
	}
}

class ContestPrinter extends java.io.PrintWriter{
	public ContestPrinter(java.io.PrintStream stream){
		super(stream);
	}
	public ContestPrinter(java.io.File file) throws java.io.FileNotFoundException{
		super(new java.io.PrintStream(file));
	}
	public ContestPrinter(){
		super(System.out);
	}

	private static String dtos(double x, int n) {
		StringBuilder sb = new StringBuilder();
		if(x < 0){
			sb.append('-');
			x = -x;
		}
		x += Math.pow(10, -n)/2;
		sb.append((long)x);
		sb.append(".");
		x -= (long)x;
		for(int i = 0;i < n;i++){
			x *= 10;
			sb.append((int)x);
			x -= (int)x;
		}
		return sb.toString();
	}

	@Override
	public void print(float f){
		super.print(dtos(f, 20));
	}
	@Override
	public void println(float f){
		super.println(dtos(f, 20));
	}
	@Override
	public void print(double d){
		super.print(dtos(d, 20));
	}
	@Override
	public void println(double d){
		super.println(dtos(d, 20));
	}



	public void printArray(int[] array, String separator){
		int n = array.length;
		if(n==0){
			super.println();
			return;
		}
		for(int i=0; i<n-1; i++){
			super.print(array[i]);
			super.print(separator);
		}
		super.println(array[n-1]);
	}
	public void printArray(int[] array){
		this.printArray(array, " ");
	}
	public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map){
		int n = array.length;
		if(n==0){
			super.println();
			return;
		}
		for(int i=0; i<n-1; i++){
			super.print(map.applyAsInt(array[i]));
			super.print(separator);
		}
		super.println(map.applyAsInt(array[n-1]));
	}
	public void printArray(int[] array, java.util.function.IntUnaryOperator map){
		this.printArray(array, " ", map);
	}

	public void printArray(long[] array, String separator){
		int n = array.length;
		if(n==0){
			super.println();
			return;
		}
		for(int i=0; i<n-1; i++){
			super.print(array[i]);
			super.print(separator);
		}
		super.println(array[n-1]);
	}
	public void printArray(long[] array){
		this.printArray(array, " ");
	}
	public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map){
		int n = array.length;
		if(n==0){
			super.println();
			return;
		}
		for(int i=0; i<n-1; i++){
			super.print(map.applyAsLong(array[i]));
			super.print(separator);
		}
		super.println(map.applyAsLong(array[n-1]));
	}
	public void printArray(long[] array, java.util.function.LongUnaryOperator map){
		this.printArray(array, " ", map);
	}
	public <T> void printArray(T[] array, String separator){
		int n = array.length;
		if(n==0){
			super.println();
			return;
		}
		for(int i=0; i<n-1; i++){
			super.print(array[i]);
			super.print(separator);
		}
		super.println(array[n-1]);
	}
	public <T> void printArray(T[] array){
		this.printArray(array, " ");
	}
	public <T> void printArray(T[] array, String separator, java.util.function.UnaryOperator<T> map){
		int n = array.length;
		if(n==0){
			super.println();
			return;
		}
		for(int i=0; i<n-1; i++){
			super.print(map.apply(array[i]));
			super.print(separator);
		}
		super.println(map.apply(array[n-1]));
	}
	public <T> void printArray(T[] array, java.util.function.UnaryOperator<T> map){
		this.printArray(array, " ", map);
	}
}

class DSU {
	private int n;
	private int[] parentOrSize;

	public DSU(int n) {
		this.n = n;
		this.parentOrSize = new int[n];
		java.util.Arrays.fill(parentOrSize, -1);
	}

	int merge(int a, int b) {
		if (!(0 <= a && a < n))
			throw new IndexOutOfBoundsException("a=" + a);
		if (!(0 <= b && b < n))
			throw new IndexOutOfBoundsException("b=" + b);

		int x = leader(a);
		int y = leader(b);
		if (x == y) return x;
		if (-parentOrSize[x] < -parentOrSize[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		parentOrSize[x] += parentOrSize[y];
		parentOrSize[y] = x;
		return x;
	}

	boolean same(int a, int b) {
		if (!(0 <= a && a < n))
			throw new IndexOutOfBoundsException("a=" + a);
		if (!(0 <= b && b < n))
			throw new IndexOutOfBoundsException("b=" + b);
		return leader(a) == leader(b);
	}

	int leader(int a) {
		if (parentOrSize[a] < 0) {
			return a;
		} else {
			parentOrSize[a] = leader(parentOrSize[a]);
			return parentOrSize[a];
		}
	}

	int size(int a) {
		if (!(0 <= a && a < n))
			throw new IndexOutOfBoundsException("" + a);
		return -parentOrSize[leader(a)];
	}

	java.util.ArrayList<java.util.ArrayList<Integer>> groups() {
		int[] leaderBuf = new int[n];
		int[] groupSize = new int[n];
		for (int i = 0; i < n; i++) {
			leaderBuf[i] = leader(i);
			groupSize[leaderBuf[i]]++;
		}
		java.util.ArrayList<java.util.ArrayList<Integer>> result = new java.util.ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			result.add(new java.util.ArrayList<>(groupSize[i]));
		}
		for (int i = 0; i < n; i++) {
			result.get(leaderBuf[i]).add(i);
		}
		result.removeIf(java.util.ArrayList::isEmpty);
		return result;
	}
}

class ModIntFactory {
	private final ModArithmetic ma;
	private final int mod;

	private final boolean usesMontgomery;
	private final ModArithmetic.ModArithmeticMontgomery maMontgomery;

	private ArrayList<Integer> factorial;

	public ModIntFactory(int mod) {
		this.ma = ModArithmetic.of(mod);
		this.usesMontgomery = ma instanceof ModArithmetic.ModArithmeticMontgomery;
		this.maMontgomery = usesMontgomery ? (ModArithmetic.ModArithmeticMontgomery) ma : null;
		this.mod = mod;

		this.factorial = new ArrayList<>();
	}

	public ModInt create(long value) {
		if ((value %= mod) < 0) value += mod;
		if (usesMontgomery) {
			return new ModInt(maMontgomery.generate(value));
		} else {
			return new ModInt((int) value);
		}
	}

	private void prepareFactorial(int max){
		factorial.ensureCapacity(max+1);
		if(factorial.size()==0) factorial.add(1);
		if (usesMontgomery) {
			for(int i=factorial.size(); i<=max; i++){
				factorial.add(ma.mul(factorial.get(i-1), maMontgomery.generate(i)));
			}
		} else {
			for(int i=factorial.size(); i<=max; i++){
				factorial.add(ma.mul(factorial.get(i-1), i));
			}
		}
	}

	public ModInt factorial(int i){
		prepareFactorial(i);
		return create(factorial.get(i));
	}

	public ModInt permutation(int n, int r){
		if(n < 0 || r < 0 || n < r) return create(0);
		prepareFactorial(n);
		return create(ma.div(factorial.get(n), factorial.get(r)));
	}
	public ModInt combination(int n, int r){
		if(n < 0 || r < 0 || n < r) return create(0);
		prepareFactorial(n);
		return create(ma.div(factorial.get(n), ma.mul(factorial.get(r),factorial.get(n-r))));
	}

	public int getMod() {
		return mod;
	}

	public class ModInt {
		private int value;
		private ModInt(int value) {
			this.value = value;
		}
		public int mod() {
			return mod;
		}
		public int value() {
			if (usesMontgomery) {
				return maMontgomery.reduce(value);
			}
			return value;
		}
		public ModInt add(ModInt mi) {
			return new ModInt(ma.add(value, mi.value));
		}
		public ModInt add(ModInt mi1, ModInt mi2) {
			return new ModInt(ma.add(value, mi1.value)).addAsg(mi2);
		}
		public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3) {
			return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3);
		}
		public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
			return new ModInt(ma.add(value, mi1.value)).addAsg(mi2).addAsg(mi3).addAsg(mi4);
		}
		public ModInt add(ModInt mi1, ModInt... mis) {
			ModInt mi = add(mi1);
			for (ModInt m : mis) mi.addAsg(m);
			return mi;
		}
		public ModInt add(long mi) {
			return new ModInt(ma.add(value, ma.remainder(mi)));
		}
		public ModInt sub(ModInt mi) {
			return new ModInt(ma.sub(value, mi.value));
		}
		public ModInt sub(long mi) {
			return new ModInt(ma.sub(value, ma.remainder(mi)));
		}
		public ModInt mul(ModInt mi) {
			return new ModInt(ma.mul(value, mi.value));
		}
		public ModInt mul(ModInt mi1, ModInt mi2) {
			return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2);
		}
		public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3) {
			return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3);
		}
		public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
			return new ModInt(ma.mul(value, mi1.value)).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
		}
		public ModInt mul(ModInt mi1, ModInt... mis) {
			ModInt mi = mul(mi1);
			for (ModInt m : mis) mi.mulAsg(m);
			return mi;
		}
		public ModInt mul(long mi) {
			return new ModInt(ma.mul(value, ma.remainder(mi)));
		}
		public ModInt div(ModInt mi) {
			return new ModInt(ma.div(value, mi.value));
		}
		public ModInt div(long mi) {
			return new ModInt(ma.div(value, ma.remainder(mi)));
		}
		public ModInt inv() {
			return new ModInt(ma.inv(value));
		}
		public ModInt pow(long b) {
			return new ModInt(ma.pow(value, b));
		}
		public ModInt addAsg(ModInt mi) {
			this.value = ma.add(value, mi.value);
			return this;
		}
		public ModInt addAsg(ModInt mi1, ModInt mi2) {
			return addAsg(mi1).addAsg(mi2);
		}
		public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
			return addAsg(mi1).addAsg(mi2).addAsg(mi3);
		}
		public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
			return addAsg(mi1).addAsg(mi2).addAsg(mi3).addAsg(mi4);
		}
		public ModInt addAsg(ModInt... mis) {
			for (ModInt m : mis) addAsg(m);
			return this;
		}
		public ModInt addAsg(long mi) {
			this.value = ma.add(value, ma.remainder(mi));
			return this;
		}
		public ModInt subAsg(ModInt mi) {
			this.value = ma.sub(value, mi.value);
			return this;
		}
		public ModInt subAsg(long mi) {
			this.value = ma.sub(value, ma.remainder(mi));
			return this;
		}
		public ModInt mulAsg(ModInt mi) {
			this.value = ma.mul(value, mi.value);
			return this;
		}
		public ModInt mulAsg(ModInt mi1, ModInt mi2) {
			return mulAsg(mi1).mulAsg(mi2);
		}
		public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3) {
			return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3);
		}
		public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4) {
			return mulAsg(mi1).mulAsg(mi2).mulAsg(mi3).mulAsg(mi4);
		}
		public ModInt mulAsg(ModInt... mis) {
			for (ModInt m : mis) mulAsg(m);
			return this;
		}
		public ModInt mulAsg(long mi) {
			this.value = ma.mul(value, ma.remainder(mi));
			return this;
		}
		public ModInt divAsg(ModInt mi) {
			this.value = ma.div(value, mi.value);
			return this;
		}
		public ModInt divAsg(long mi) {
			this.value = ma.div(value, ma.remainder(mi));
			return this;
		}
		@Override
		public String toString() {
			return String.valueOf(value());
		}
		@Override
		public boolean equals(Object o) {
			if (o instanceof ModInt) {
				ModInt mi = (ModInt) o;
				return mod() == mi.mod() && value() == mi.value();
			}
			return false;
		}
		@Override
		public int hashCode() {
			return (1 * 37 + mod()) * 37 + value();
		}
	}

	private static abstract class ModArithmetic {
		abstract int mod();
		abstract int remainder(long value);
		abstract int add(int a, int b);
		abstract int sub(int a, int b);
		abstract int mul(int a, int b);
		int div(int a, int b) {
			return mul(a, inv(b));
		}
		int inv(int a) {
			int b = mod();
			if (b == 1) return 0;
			long u = 1, v = 0;
			while (b >= 1) {
				int t = a / b;
				a -= t * b;
				int tmp1 = a; a = b; b = tmp1;
				u -= t * v;
				long tmp2 = u; u = v; v = tmp2;
			}
			if (a != 1) {
				throw new ArithmeticException("divide by zero");
			}
			return remainder(u);
		}
		int pow(int a, long b) {
			if (b < 0) throw new ArithmeticException("negative power");
			int r = 1;
			int x = a;
			while (b > 0) {
				if ((b & 1) == 1) r = mul(r, x);
				x = mul(x, x);
				b >>= 1;
			}
			return r;
		}

		static ModArithmetic of(int mod) {
			if (mod <= 0) {
				throw new IllegalArgumentException();
			} else if (mod == 1) {
				return new ModArithmetic1();
			} else if (mod == 2) {
				return new ModArithmetic2();
			} else if (mod == 998244353) {
				return new ModArithmetic998244353();
			} else if (mod == 1000000007) {
				return new ModArithmetic1000000007();
			} else if ((mod & 1) == 1) {
				return new ModArithmeticMontgomery(mod);
			} else {
				return new ModArithmeticBarrett(mod);
			}
		}

		private static final class ModArithmetic1 extends ModArithmetic {
			int mod() {return 1;}
			int remainder(long value) {return 0;}
			int add(int a, int b) {return 0;}
			int sub(int a, int b) {return 0;}
			int mul(int a, int b) {return 0;}
			int pow(int a, long b) {return 0;}
		}
		private static final class ModArithmetic2 extends ModArithmetic {
			int mod() {return 2;}
			int remainder(long value) {return (int) (value & 1);}
			int add(int a, int b) {return a ^ b;}
			int sub(int a, int b) {return a ^ b;}
			int mul(int a, int b) {return a & b;}
		}
		private static final class ModArithmetic998244353 extends ModArithmetic {
			private final int mod = 998244353;
			int mod() {
				return mod;
			}
			int remainder(long value) {
				return (int) ((value %= mod) < 0 ? value + mod : value);
			}
			int add(int a, int b) {
				int res = a + b;
				return res >= mod ? res - mod : res;
			}
			int sub(int a, int b) {
				int res = a - b;
				return res < 0 ? res + mod : res;
			}
			int mul(int a, int b) {
				return (int) (((long) a * b) % mod);
			}
		}
		private static final class ModArithmetic1000000007 extends ModArithmetic {
			private final int mod = 1000000007;
			int mod() {
				return mod;
			}
			int remainder(long value) {
				return (int) ((value %= mod) < 0 ? value + mod : value);
			}
			int add(int a, int b) {
				int res = a + b;
				return res >= mod ? res - mod : res;
			}
			int sub(int a, int b) {
				int res = a - b;
				return res < 0 ? res + mod : res;
			}
			int mul(int a, int b) {
				return (int) (((long) a * b) % mod);
			}
		}
		private static final class ModArithmeticMontgomery extends ModArithmeticDynamic {
			private final long negInv;
			private final long r2;

			private ModArithmeticMontgomery(int mod) {
				super(mod);
				long inv = 0;
				long s = 1, t = 0;
				for (int i = 0; i < 32; i++) {
					if ((t & 1) == 0) {
						t += mod;
						inv += s;
					}
					t >>= 1;
					s <<= 1;
				}
				long r = (1l << 32) % mod;
				this.negInv = inv;
				this.r2 = (r * r) % mod;
			}
			private int generate(long x) {
				return reduce(x * r2);
			}
			private int reduce(long x) {
				x = (x + ((x * negInv) & 0xffff_ffffl) * mod) >>> 32;
				return (int) (x < mod ? x : x - mod);
			}
			@Override
			int remainder(long value) {
				return generate((value %= mod) < 0 ? value + mod : value);
			}
			@Override
			int mul(int a, int b) {
				return reduce((long) a * b);
			}
			@Override
			int inv(int a) {
				return super.inv(reduce(a));
			}
			@Override
			int pow(int a, long b) {
				return generate(super.pow(a, b));
			}
		}
		private static final class ModArithmeticBarrett extends ModArithmeticDynamic {
			private static final long mask = 0xffff_ffffl;
			private final long mh;
			private final long ml;
			private ModArithmeticBarrett(int mod) {
				super(mod);
				/**
				 * m = floor(2^64/mod)
				 * 2^64 = p*mod + q, 2^32 = a*mod + b
				 * => (a*mod + b)^2 = p*mod + q
				 * => p = mod*a^2 + 2ab + floor(b^2/mod)
				 */
				long a = (1l << 32) / mod;
				long b = (1l << 32) % mod;
				long m = a * a * mod + 2 * a * b + (b * b) / mod;
				mh = m >>> 32;
				ml = m & mask;
			}
			private int reduce(long x) {
				long z = (x & mask) * ml;
				z = (x & mask) * mh + (x >>> 32) * ml + (z >>> 32);
				z = (x >>> 32) * mh + (z >>> 32);
				x -= z * mod;
				return (int) (x < mod ? x : x - mod);
			}
			@Override
			int remainder(long value) {
				return (int) ((value %= mod) < 0 ? value + mod : value);
			}
			@Override
			int mul(int a, int b) {
				return reduce((long) a * b);
			}
		}
		private static class ModArithmeticDynamic extends ModArithmetic {
			final int mod;
			ModArithmeticDynamic(int mod) {
				this.mod = mod;
			}
			int mod() {
				return mod;
			}
			int remainder(long value) {
				return (int) ((value %= mod) < 0 ? value + mod : value);
			}
			int add(int a, int b) {
				int sum = a + b;
				return sum >= mod ? sum - mod : sum;
			}
			int sub(int a, int b) {
				int sum = a - b;
				return sum < 0 ? sum + mod : sum;
			}
			int mul(int a, int b) {
				return (int) (((long) a * b) % mod);
			}
		}
	}
}

class SCC {

	static class Edge {
		int from, to;
		public Edge(int from, int to) {
			this.from = from; this.to = to;
		}
	}

	final int n;
	int m;
	final java.util.ArrayList<Edge> unorderedEdges;
	final int[] start;
	final int[] ids;
	boolean hasBuilt = false;

	public SCC(int n) {
		this.n = n;
		this.unorderedEdges = new java.util.ArrayList<>();
		this.start = new int[n + 1];
		this.ids = new int[n];
	}

	public void addEdge(int from, int to) {
		rangeCheck(from);
		rangeCheck(to);
		unorderedEdges.add(new Edge(from, to));
		start[from + 1]++;
		this.m++;
	}

	public int id(int i) {
		if (!hasBuilt) {
			throw new UnsupportedOperationException(
					"Graph hasn't been built."
			);
		}
		rangeCheck(i);
		return ids[i];
	}

	public int[][] build() {
		for (int i = 1; i <= n; i++) {
			start[i] += start[i - 1];
		}
		Edge[] orderedEdges = new Edge[m];
		int[] count = new int[n + 1];
		System.arraycopy(start, 0, count, 0, n + 1);
		for (Edge e : unorderedEdges) {
			orderedEdges[count[e.from]++] = e;
		}
		int nowOrd = 0;
		int groupNum = 0;
		int k = 0;
		// parent
		int[] par = new int[n];
		int[] vis = new int[n];
		int[] low = new int[n];
		int[] ord = new int[n];
		java.util.Arrays.fill(ord, -1);
		// u = lower32(stack[i]) : visiting vertex
		// j = upper32(stack[i]) : jth child
		long[] stack = new long[n];
		// size of stack
		int ptr = 0;
		// non-recursional DFS
		for (int i = 0; i < n; i++) {
			if (ord[i] >= 0) continue;
			par[i] = -1;
			// vertex i, 0th child.
			stack[ptr++] = 0l << 32 | i;
			// stack is not empty
			while (ptr > 0) {
				// last element
				long p = stack[--ptr];
				// vertex
				int u = (int) (p & 0xffff_ffffl);
				// jth child
				int j = (int) (p >>> 32);
				if (j == 0) { // first visit
					low[u] = ord[u] = nowOrd++;
					vis[k++] = u;
				}
				if (start[u] + j < count[u]) { // there are more children
					// jth child
					int to = orderedEdges[start[u] + j].to;
					// incr children counter
					stack[ptr++] += 1l << 32;
					if (ord[to] == -1) { // new vertex
						stack[ptr++] = 0l << 32 | to;
						par[to] = u;
					} else { // backward edge
						low[u] = Math.min(low[u], ord[to]);
					}
				} else { // no more children (leaving)
					while (j --> 0) {
						int to = orderedEdges[start[u] + j].to;
						// update lowlink
						if (par[to] == u) low[u] = Math.min(low[u], low[to]);
					}
					if (low[u] == ord[u]) { // root of a component
						while (true) { // gathering verticies
							int v = vis[--k];
							ord[v] = n;
							ids[v] = groupNum;
							if (v == u) break;
						}
						groupNum++; // incr the number of components
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			ids[i] = groupNum - 1 - ids[i];
		}

		int[] counts = new int[groupNum];
		for (int x : ids) counts[x]++;
		int[][] groups = new int[groupNum][];
		for (int i = 0; i < groupNum; i++) {
			groups[i] = new int[counts[i]];
		}
		for (int i = 0; i < n; i++) {
			int cmp = ids[i];
			groups[cmp][--counts[cmp]] = i;
		}
		hasBuilt = true;
		return groups;
	}

	private void rangeCheck(int i) {
		if (i < 0 || i >= n) {
			throw new IndexOutOfBoundsException(
					String.format("Index %d out of bounds for length %d", i, n)
			);
		}
	}
}

class LazySegTree<S, F> {
	final int MAX;

	final int N;
	final int Log;
	final java.util.function.BinaryOperator<S> Op;
	final S E;
	final java.util.function.BiFunction<F, S, S> Mapping;
	final java.util.function.BinaryOperator<F> Composition;
	final F Id;

	final S[] Dat;
	final F[] Laz;

	@SuppressWarnings("unchecked")
	public LazySegTree(int n, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
		this.MAX = n;
		int k = 1;
		while (k < n) k <<= 1;
		this.N = k;
		this.Log = Integer.numberOfTrailingZeros(N);
		this.Op = op;
		this.E = e;
		this.Mapping = mapping;
		this.Composition = composition;
		this.Id = id;
		this.Dat = (S[]) new Object[N << 1];
		this.Laz = (F[]) new Object[N];
		java.util.Arrays.fill(Dat, E);
		java.util.Arrays.fill(Laz, Id);
	}

	public LazySegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e, java.util.function.BiFunction<F, S, S> mapping, java.util.function.BinaryOperator<F> composition, F id) {
		this(dat.length, op, e, mapping, composition, id);
		build(dat);
	}

	private void build(S[] dat) {
		int l = dat.length;
		System.arraycopy(dat, 0, Dat, N, l);
		for (int i = N - 1; i > 0; i--) {
			Dat[i] = Op.apply(Dat[i << 1 | 0], Dat[i << 1 | 1]);
		}
	}

	private void push(int k) {
		if (Laz[k] == Id) return;
		int lk = k << 1 | 0, rk = k << 1 | 1;
		Dat[lk] = Mapping.apply(Laz[k], Dat[lk]);
		Dat[rk] = Mapping.apply(Laz[k], Dat[rk]);
		if (lk < N) Laz[lk] = Composition.apply(Laz[k], Laz[lk]);
		if (rk < N) Laz[rk] = Composition.apply(Laz[k], Laz[rk]);
		Laz[k] = Id;
	}

	private void pushTo(int k) {
		for (int i = Log; i > 0; i--) push(k >> i);
	}

	private void pushTo(int lk, int rk) {
		for (int i = Log; i > 0; i--) {
			if (((lk >> i) << i) != lk) push(lk >> i);
			if (((rk >> i) << i) != rk) push(rk >> i);
		}
	}

	private void updateFrom(int k) {
		k >>= 1;
		while (k > 0) {
			Dat[k] = Op.apply(Dat[k << 1 | 0], Dat[k << 1 | 1]);
			k >>= 1;
		}
	}

	private void updateFrom(int lk, int rk) {
		for (int i = 1; i <= Log; i++) {
			if (((lk >> i) << i) != lk) {
				int lki = lk >> i;
				Dat[lki] = Op.apply(Dat[lki << 1 | 0], Dat[lki << 1 | 1]);
			}
			if (((rk >> i) << i) != rk) {
				int rki = (rk - 1) >> i;
				Dat[rki] = Op.apply(Dat[rki << 1 | 0], Dat[rki << 1 | 1]);
			}
		}
	}

	public void set(int p, S x) {
		exclusiveRangeCheck(p);
		p += N;
		pushTo(p);
		Dat[p] = x;
		updateFrom(p);
	}

	public S get(int p) {
		exclusiveRangeCheck(p);
		p += N;
		pushTo(p);
		return Dat[p];
	}

	public S prod(int l, int r) {
		if (l > r) {
			throw new IllegalArgumentException(
					String.format("Invalid range: [%d, %d)", l, r)
			);
		}
		inclusiveRangeCheck(l);
		inclusiveRangeCheck(r);
		if (l == r) return E;
		l += N; r += N;
		pushTo(l, r);
		S sumLeft = E, sumRight = E;
		while (l < r) {
			if ((l & 1) == 1) sumLeft = Op.apply(sumLeft, Dat[l++]);
			if ((r & 1) == 1) sumRight = Op.apply(Dat[--r], sumRight);
			l >>= 1; r >>= 1;
		}
		return Op.apply(sumLeft, sumRight);
	}

	public S allProd() {
		return Dat[1];
	}

	public void apply(int p, F f) {
		exclusiveRangeCheck(p);
		p += N;
		pushTo(p);
		Dat[p] = Mapping.apply(f, Dat[p]);
		updateFrom(p);
	}

	public void apply(int l, int r, F f) {
		if (l > r) {
			throw new IllegalArgumentException(
					String.format("Invalid range: [%d, %d)", l, r)
			);
		}
		inclusiveRangeCheck(l);
		inclusiveRangeCheck(r);
		if (l == r) return;
		l += N; r += N;
		pushTo(l, r);
		for (int l2 = l, r2 = r; l2 < r2;) {
			if ((l2 & 1) == 1) {
				Dat[l2] = Mapping.apply(f, Dat[l2]);
				if (l2 < N) Laz[l2] = Composition.apply(f, Laz[l2]);
				l2++;
			}
			if ((r2 & 1) == 1) {
				r2--;
				Dat[r2] = Mapping.apply(f, Dat[r2]);
				if (r2 < N) Laz[r2] = Composition.apply(f, Laz[r2]);
			}
			l2 >>= 1; r2 >>= 1;
		}
		updateFrom(l, r);
	}

	public int maxRight(int l, java.util.function.Predicate<S> g) {
		inclusiveRangeCheck(l);
		if (!g.test(E)) {
			throw new IllegalArgumentException("Identity element must satisfy the condition.");
		}
		if (l == MAX) return MAX;
		l += N;
		pushTo(l);
		S sum = E;
		do {
			l >>= Integer.numberOfTrailingZeros(l);
			if (!g.test(Op.apply(sum, Dat[l]))) {
				while (l < N) {
					push(l);
					l = l << 1;
					if (g.test(Op.apply(sum, Dat[l]))) {
						sum = Op.apply(sum, Dat[l]);
						l++;
					}
				}
				return l - N;
			}
			sum = Op.apply(sum, Dat[l]);
			l++;
		} while ((l & -l) != l);
		return MAX;
	}

	public int minLeft(int r, java.util.function.Predicate<S> g) {
		inclusiveRangeCheck(r);
		if (!g.test(E)) {
			throw new IllegalArgumentException("Identity element must satisfy the condition.");
		}
		if (r == 0) return 0;
		r += N;
		pushTo(r - 1);
		S sum = E;
		do {
			r--;
			while (r > 1 && (r & 1) == 1) r >>= 1;
			if (!g.test(Op.apply(Dat[r], sum))) {
				while (r < N) {
					push(r);
					r = r << 1 | 1;
					if (g.test(Op.apply(Dat[r], sum))) {
						sum = Op.apply(Dat[r], sum);
						r--;
					}
				}
				return r + 1 - N;
			}
			sum = Op.apply(Dat[r], sum);
		} while ((r & -r) != r);
		return 0;
	}

	private void exclusiveRangeCheck(int p) {
		if (p < 0 || p >= MAX) {
			throw new IndexOutOfBoundsException(
					String.format("Index %d is not in [%d, %d).", p, 0, MAX)
			);
		}
	}

	private void inclusiveRangeCheck(int p) {
		if (p < 0 || p > MAX) {
			throw new IndexOutOfBoundsException(
					String.format("Index %d is not in [%d, %d].", p, 0, MAX)
			);
		}
	}

	// **************** DEBUG **************** //

	private int indent = 6;

	public void setIndent(int newIndent) {
		this.indent = newIndent;
	}

	@Override
	public String toString() {
		return toSimpleString();
	}

	private S[] simulatePushAll() {
		S[] simDat = java.util.Arrays.copyOf(Dat, 2 * N);
		F[] simLaz = java.util.Arrays.copyOf(Laz, 2 * N);
		for (int k = 1; k < N; k++) {
			if (simLaz[k] == Id) continue;
			int lk = k << 1 | 0, rk = k << 1 | 1;
			simDat[lk] = Mapping.apply(simLaz[k], simDat[lk]);
			simDat[rk] = Mapping.apply(simLaz[k], simDat[rk]);
			if (lk < N) simLaz[lk] = Composition.apply(simLaz[k], simLaz[lk]);
			if (rk < N) simLaz[rk] = Composition.apply(simLaz[k], simLaz[rk]);
			simLaz[k] = Id;
		}
		return simDat;
	}

	public String toDetailedString() {
		return toDetailedString(1, 0, simulatePushAll());
	}

	private String toDetailedString(int k, int sp, S[] dat) {
		if (k >= N) return indent(sp) + dat[k];
		String s = "";
		s += toDetailedString(k << 1 | 1, sp + indent, dat);
		s += "\n";
		s += indent(sp) + dat[k];
		s += "\n";
		s += toDetailedString(k << 1 | 0, sp + indent, dat);
		return s;
	}

	private static String indent(int n) {
		StringBuilder sb = new StringBuilder();
		while (n --> 0) sb.append(' ');
		return sb.toString();
	}

	public String toSimpleString() {
		S[] dat = simulatePushAll();
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < N; i++) {
			sb.append(dat[i + N]);
			if (i < N - 1) sb.append(',').append(' ');
		}
		sb.append(']');
		return sb.toString();
	}
}

class Permutation implements java.util.Iterator<int[]>, Iterable<int[]> {
	private int[] next;

	public Permutation(int n) {
		next = java.util.stream.IntStream.range(0, n).toArray();
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public int[] next() {
		int[] r = next.clone();
		next = nextPermutation(next);
		return r;
	}

	@Override
	public java.util.Iterator<int[]> iterator() {
		return this;
	}

	public static int[] nextPermutation(int[] a) {
		if (a == null || a.length < 2)
			return null;
		int p = 0;
		for (int i = a.length - 2; i >= 0; i--) {
			if (a[i] >= a[i + 1])
				continue;
			p = i;
			break;
		}
		int q = 0;
		for (int i = a.length - 1; i > p; i--) {
			if (a[i] <= a[p])
				continue;
			q = i;
			break;
		}
		if (p == 0 && q == 0)
			return null;
		int temp = a[p];
		a[p] = a[q];
		a[q] = temp;
		int l = p, r = a.length;
		while (++l < --r) {
			temp = a[l];
			a[l] = a[r];
			a[r] = temp;
		}
		return a;
	}
}

class SegTree<S> {
	final int MAX;

	final int N;
	final java.util.function.BinaryOperator<S> op;
	final S E;

	final S[] data;

	@SuppressWarnings("unchecked")
	public SegTree(int n, java.util.function.BinaryOperator<S> op, S e) {
		this.MAX = n;
		int k = 1;
		while (k < n) k <<= 1;
		this.N = k;
		this.E = e;
		this.op = op;
		this.data = (S[]) new Object[N << 1];
		java.util.Arrays.fill(data, E);
	}

	public SegTree(S[] dat, java.util.function.BinaryOperator<S> op, S e) {
		this(dat.length, op, e);
		build(dat);
	}

	private void build(S[] dat) {
		int l = dat.length;
		System.arraycopy(dat, 0, data, N, l);
		for (int i = N - 1; i > 0; i--) {
			data[i] = op.apply(data[i << 1 | 0], data[i << 1 | 1]);
		}
	}

	public void set(int p, S x) {
		exclusiveRangeCheck(p);
		data[p += N] = x;
		p >>= 1;
		while (p > 0) {
			data[p] = op.apply(data[p << 1 | 0], data[p << 1 | 1]);
			p >>= 1;
		}
	}

	public S get(int p) {
		exclusiveRangeCheck(p);
		return data[p + N];
	}

	public S prod(int l, int r) {
		if (l > r) {
			throw new IllegalArgumentException(
					String.format("Invalid range: [%d, %d)", l, r)
			);
		}
		inclusiveRangeCheck(l);
		inclusiveRangeCheck(r);
		S sumLeft = E;
		S sumRight = E;
		l += N; r += N;
		while (l < r) {
			if ((l & 1) == 1) sumLeft = op.apply(sumLeft, data[l++]);
			if ((r & 1) == 1) sumRight = op.apply(data[--r], sumRight);
			l >>= 1; r >>= 1;
		}
		return op.apply(sumLeft, sumRight);
	}

	public S allProd() {
		return data[1];
	}

	public int maxRight(int l, java.util.function.Predicate<S> f) {
		inclusiveRangeCheck(l);
		if (!f.test(E)) {
			throw new IllegalArgumentException("Identity element must satisfy the condition.");
		}
		if (l == MAX) return MAX;
		l += N;
		S sum = E;
		do {
			l >>= Integer.numberOfTrailingZeros(l);
			if (!f.test(op.apply(sum, data[l]))) {
				while (l < N) {
					l = l << 1;
					if (f.test(op.apply(sum, data[l]))) {
						sum = op.apply(sum, data[l]);
						l++;
					}
				}
				return l - N;
			}
			sum = op.apply(sum, data[l]);
			l++;
		} while ((l & -l) != l);
		return MAX;
	}

	public int minLeft(int r, java.util.function.Predicate<S> f) {
		inclusiveRangeCheck(r);
		if (!f.test(E)) {
			throw new IllegalArgumentException("Identity element must satisfy the condition.");
		}
		if (r == 0) return 0;
		r += N;
		S sum = E;
		do {
			r--;
			while (r > 1 && (r & 1) == 1) r >>= 1;
			if (!f.test(op.apply(data[r], sum))) {
				while (r < N) {
					r = r << 1 | 1;
					if (f.test(op.apply(data[r], sum))) {
						sum = op.apply(data[r], sum);
						r--;
					}
				}
				return r + 1 - N;
			}
			sum = op.apply(data[r], sum);
		} while ((r & -r) != r);
		return 0;
	}

	private void exclusiveRangeCheck(int p) {
		if (p < 0 || p >= MAX) {
			throw new IndexOutOfBoundsException(
					String.format("Index %d out of bounds for the range [%d, %d).", p, 0, MAX)
			);
		}
	}

	private void inclusiveRangeCheck(int p) {
		if (p < 0 || p > MAX) {
			throw new IndexOutOfBoundsException(
					String.format("Index %d out of bounds for the range [%d, %d].", p, 0, MAX)
			);
		}
	}

	// **************** DEBUG **************** //

	private int indent = 6;

	public void setIndent(int newIndent) {
		this.indent = newIndent;
	}

	@Override
	public String toString() {
		return toSimpleString();
	}

	public String toDetailedString() {
		return toDetailedString(1, 0);
	}

	private String toDetailedString(int k, int sp) {
		if (k >= N) return indent(sp) + data[k];
		String s = "";
		s += toDetailedString(k << 1 | 1, sp + indent);
		s += "\n";
		s += indent(sp) + data[k];
		s += "\n";
		s += toDetailedString(k << 1 | 0, sp + indent);
		return s;
	}

	private static String indent(int n) {
		StringBuilder sb = new StringBuilder();
		while (n --> 0) sb.append(' ');
		return sb.toString();
	}

	public String toSimpleString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < N; i++) {
			sb.append(data[i + N]);
			if (i < N - 1) sb.append(',').append(' ');
		}
		sb.append(']');
		return sb.toString();
	}
}

class Pair<S extends Comparable<S>, T extends Comparable<T>> implements Comparable<Pair<S,T>>{
	S first;
	T second;

	public Pair(S s, T t){
		first = s;
		second = t;
	}
	public S getFirst(){return first;}
	public T getSecond(){return second;}
	public boolean equals(Object another){
		if(this==another) return true;
		if(!(another instanceof Pair)) return false;
		Pair otherPair = (Pair)another;
		return this.first.equals(otherPair.first) && this.second.equals(otherPair.second);
	}
	public int compareTo(Pair<S,T> another){
		java.util.Comparator<Pair<S,T>> comp1 = java.util.Comparator.comparing(Pair::getFirst);
		java.util.Comparator<Pair<S,T>> comp2 = comp1.thenComparing(Pair::getSecond);
		return comp2.compare(this, another);
	}
	public int hashCode(){
		return first.hashCode() * 10007 + second.hashCode();
	}
	public String toString(){
		return String.format("(%s, %s)", first, second);
	}
}