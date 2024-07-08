import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiFunction;

public class Main{
	static Scanner scn = new Scanner(System.in);
	static FastScanner sc = new FastScanner();
	static CheckingScanner cs = new CheckingScanner();
	static Mathplus mp = new Mathplus();
	static PrintWriter ot = new PrintWriter(System.out);
	static Random rand = new Random();
	static StringManager sm = new StringManager(" ");
	static int mod = 1000000007;
	static long modmod = (long)mod * mod;
	static long inf = (long)3e18;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[] dx8 = {-1,-1,-1,0,0,1,1,1};
	static int[] dy8 = {-1,0,1,-1,1,-1,0,1};
	static char[] dc = {'R','D','L','U'};
	static BiFunction<Integer,Integer,Integer> fmax = (a,b)-> {return Math.max(a,b);};
	static BiFunction<Integer,Integer,Integer> fmin = (a,b)-> {return Math.min(a,b);};
	static BiFunction<Integer,Integer,Integer> fsum = (a,b)-> {return a+b;};
	static BiFunction<Long,Long,Long> fmaxl = (a,b)-> {return Math.max(a,b);};
	static BiFunction<Long,Long,Long> fminl = (a,b)-> {return Math.min(a,b);};
	static BiFunction<Long,Long,Long> fsuml = (a,b)-> {return a+b;};
	static BiFunction<Integer,Integer,Integer> fadd = fsum;
	static BiFunction<Integer,Integer,Integer> fupd = (a,b)-> {return b;};
	static BiFunction<Long,Long,Long> faddl = fsuml;
	static BiFunction<Long,Long,Long> fupdl = (a,b)-> {return b;};
	static BiFunction<Long,Long,Long> fmsuml = (a,b)-> {return (a+b)%mod;};
	static BiFunction<Long,Long,Long> fmmull = (a,b)-> {return (a*b)%mod;};
	
	static String sp = " ";
	
	
	public static void main(String[] args) throws IOException {
		
		mod = 998244353;
		mp.mod = mod;
		long hf = mp.rev(2);
		long[] A = new long[1000002];
		long[] B = new long[1000002];
		A[2] = 1;
		for(int i=3;i<=1000001;i++) {
			A[i] = A[i-1] * (2*i-5) % mod * hf % mod;
		}
		B[2] = 1;
		for(int i=3;i<=1000001;i++) {
			B[i] = B[i-1] * (i-1) % mod;
		}
		int T = sc.nextInt();
		for(int i=0;i<T;i++) {
			int N = sc.nextInt();
			long NN = N;
			long ans = mp.pow(4, N-2) * ((NN * NN - 3) % mod) % mod;
			ans *= A[N];
			ans %= mod;
			ans *= mp.rev(B[N]);
			ans %= mod;
			ot.println(ans);
		}
		ot.flush();
		
		
		

	}



	
}

class StringLengthComparator implements Comparator<String>{
	public int compare(String P, String Q) {
		return (int) Math.signum(P.length()-Q.length());
	}
}

class Point{
	double x;
	double y;
	Point(double X,double Y){
		x = X;
		y = Y;
	}

	double atan(){
		return Math.atan2(y,x);
	}
	double length() {
		return Math.sqrt(x*x+y*y);
	}
	Point add(Point Q) {
		return new Point(x+Q.x,y+Q.y);
	}
	Point sub(Point Q) {
		return new Point(x-Q.x,y-Q.y);
	}
	Point mul(double d) {
		return new Point(x*d,y*d);
	}
	Point div(double d) {
		return new Point(x/d,y/d);
	}

}


class Triangle{
	double eps = 1e-7;
	Point A;
	Point B;
	Point C;
	double edgeA;
	double edgeB;
	double edgeC;
	double argA;
	double argB;
	double argC;
	Triangle(Point a,Point b,Point c){
		A = a;
		B = b;
		C = c;
		edgeA = b.sub(c).length();
		edgeB = c.sub(a).length();
		edgeC = a.sub(b).length();
		argA = Math.acos(getcos(edgeA,edgeB,edgeC));
		argB = Math.acos(getcos(edgeB,edgeC,edgeA));
		argC = Math.acos(getcos(edgeC,edgeA,edgeB));
	}
	double getcos(double a,double b,double c){
		return (b*b+c*c-a*a)/(2*b*c);
	}
	Point outheart() {
		return A.mul(Math.sin(2*argA)).add(B.mul(Math.sin(2*argB))).add(C.mul(Math.sin(2*argC))).div(Math.sin(2*argA)+Math.sin(2*argB)+Math.sin(2*argC));
	}
}

class Slidemax{
	int[] dat;

	ArrayDeque<LongIntPair> q = new ArrayDeque<LongIntPair>();

	long get() {
		if(q.isEmpty()) return (long) -1e17;
		return q.peek().a;
	}

	void remove() {
		q.getFirst().b--;
		if(q.getFirst().b==0)q.pollFirst();
	}

	void add(long x) {
		int num = 1;
		while(!q.isEmpty()&&q.peekLast().a<=x) {
			num += q.peekLast().b;
			q.pollLast();
		}
		q.addLast(new LongIntPair(x,num));
	}
}
class Slidemin{
	int[] dat;
	int l = 0;
	int r = -1;
	ArrayDeque<LongIntPair> q = new ArrayDeque<LongIntPair>();

	long get() {
		if(q.isEmpty()) return (long)1e17;
		return q.peek().a;
	}

	void remove() {
		q.getFirst().b--;
		if(q.getFirst().b==0)q.pollFirst();
	}

	void add(long x) {
		int num = 1;
		while(!q.isEmpty()&&q.peekLast().a>=x) {
			num += q.peekLast().b;
			q.pollLast();
		}
		q.addLast(new LongIntPair(x,num));
	}
}


class Counter{

	int[] cnt;
	Counter(int M){
		cnt = new int[M+1];
	}
	Counter(int M,int[] A){
		cnt = new int[M+1];
		for(int i=0;i<A.length;i++)add(A[i]);
	}
	void add(int e) {
		cnt[e]++;
	}
	void remove(int e) {
		cnt[e]--;
	}
	int count(int e) {
		return cnt[e];
	}
}

class MultiHashSet{
	HashMap<Integer,Integer> set;
	int size;
	long sum;
	MultiHashSet(){
		set = new HashMap<Integer,Integer>();
		size = 0;
		sum = 0;
	}
	void add(int e){
		if(set.containsKey(e))set.put(e,set.get(e)+1);
		else set.put(e,1);
		size++;
		sum += e;
	}
	void remove(int e) {
		set.put(e,set.get(e)-1);
		if(set.get(e)==0)set.remove(e);
		size--;
		sum -= e;
	}

	boolean contains(int e) {
		return set.containsKey(e);
	}
	boolean isEmpty() {
		return set.isEmpty();
	}
	int count(int e) {
		if(contains(e))return set.get(e);
		else return 0;
	}

	Set<Integer> keyset(){
		return set.keySet();
	}

}


class MultiSet{
	TreeMap<Integer,Integer> set;
	long size;
	long sum;
	MultiSet(){
		set = new TreeMap<Integer,Integer>();
		size = 0;
		sum = 0;
	}
	void add(int e){
		if(set.containsKey(e))set.put(e,set.get(e)+1);
		else set.put(e,1);
		size++;
		sum += e;
	}
	void addn(int e,int n){
		if(set.containsKey(e))set.put(e,set.get(e)+n);
		else set.put(e,n);
		size += n;
		sum += e*(long)n;
	}
	void remove(int e) {
		set.put(e,set.get(e)-1);
		if(set.get(e)==0)set.remove(e);
		size--;
		sum -= e;
	}
	void removeall(int e) {
		if(!set.containsKey(e))return;
		int n = count(e);
		set.remove(e);
		size -= n;
		sum -= e*(long)n;
	}
	int first() {return set.firstKey();}
	int last() {return set.lastKey();}
	int lower(int e) {return set.lowerKey(e);}
	int higher(int e) {return set.higherKey(e);}
	int floor(int e) {return set.floorKey(e);}
	int ceil(int e) {return set.ceilingKey(e);}
	boolean contains(int e) {return set.containsKey(e);}
	boolean isEmpty() {return set.isEmpty();}
	int count(int e) {
		if(contains(e))return set.get(e);
		else return 0;
	}
	MultiSet marge(MultiSet T) {
		if(size>T.size) {
			while(!T.isEmpty()) {
				add(T.first());
				T.remove(T.first());
			}
			return this;
		}else {
			while(!isEmpty()) {
				T.add(first());
				remove(first());
			}
			return T;
		}
	}
	Set<Integer> keyset(){
		return set.keySet();
	}

}

class MultiSetL{
	TreeMap<Long,Integer> set;
	int size;
	long sum;
	MultiSetL(){
		set = new TreeMap<Long,Integer>();
		size = 0;
		sum = 0;
	}
	void add(long e){
		if(set.containsKey(e))set.put(e,set.get(e)+1);
		else set.put(e,1);
		size++;
		sum += e;
	}
	void remove(long e) {
		set.put(e,set.get(e)-1);
		if(set.get(e)==0)set.remove(e);
		size--;
		sum -= e;
	}
	long first() {return set.firstKey();}
	long last() {return set.lastKey();}
	long lower(long e) {return set.lowerKey(e);}
	long higher(long e) {return set.higherKey(e);}
	long floor(long e) {return set.floorKey(e);}
	long ceil(long e) {return set.ceilingKey(e);}
	boolean contains(long e) {return set.containsKey(e);}
	boolean isEmpty() {return set.isEmpty();}
	int count(long e) {
		if(contains(e))return set.get(e);
		else return 0;
	}
	MultiSetL marge(MultiSetL T) {
		if(size>T.size) {
			while(!T.isEmpty()) {
				add(T.first());
				T.remove(T.first());
			}
			return this;
		}else {
			while(!isEmpty()) {
				T.add(first());
				remove(first());
			}
			return T;
		}
	}
	Set<Long> keyset(){
		return set.keySet();
	}
}




class GridGraph extends Graph{

	int N;
	int M;
	String[] S;
	HashMap<Character,Integer> map;
	GridGraph(int n,int m,String[] s){
		super(n*m);
		N = n;
		M = m;
		S = s;
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<m;j++) {
				if(S[i].charAt(j)=='.'&&S[i+1].charAt(j)=='.') {
					addWeightedEdge(toint(i,j),toint(i+1,j),1);
					addWeightedEdge(toint(i+1,j),toint(i,j),1);
				}
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m-1;j++) {
				if(S[i].charAt(j)=='.'&&S[i].charAt(j+1)=='.') {
					addWeightedEdge(toint(i,j),toint(i,j+1),1);
					addWeightedEdge(toint(i,j+1),toint(i,j),1);
				}

			}
		}

	}
	GridGraph(int n,int m,String[] s,char[] c){
		super(n*m);
		N = n;
		M = m;
		S = s;
		map = new HashMap<Character,Integer>();
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<m;j++) {
				if(S[i].charAt(j)!=S[i+1].charAt(j)) {
					addWeightedEdge(toint(i,j),toint(i+1,j),1);
					addWeightedEdge(toint(i+1,j),toint(i,j),1);
				}else {
					addWeightedEdge(toint(i,j),toint(i+1,j),0);
					addWeightedEdge(toint(i+1,j),toint(i,j),0);
				}
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m-1;j++) {
				if(S[i].charAt(j)!=S[i].charAt(j+1)) {
					addWeightedEdge(toint(i,j),toint(i,j+1),1);
					addWeightedEdge(toint(i,j+1),toint(i,j),1);
				}else{
					addWeightedEdge(toint(i,j),toint(i,j+1),0);
					addWeightedEdge(toint(i,j+1),toint(i,j),0);
				}

			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=0;k<c.length;k++) {
					if(S[i].charAt(j)==c[k])map.put(c[k],toint(i,j));
				}
			}
		}
	}

	int toint(int i,int j) {
		return i*M+j;
	}

}

class StringManager{
	ArrayList<Character> S;
	static Mathplus mp;
	static boolean calced;
	static int base;
	static long baserev;
	ArrayList<Long> l;
	StringManager(String s){
		S = new ArrayList<Character>();
		for(int i=0;i<s.length();i++) {
			S.add(s.charAt(i));
		}
		if(!calced) {
			calced = true;
			mp = new Mathplus();
			base = 1000003;
			baserev = mp.rev(base);
			mp.buildpow(base,2000050);
			mp.buildrevpow(base, 2000050);

		}
		l = new ArrayList<Long>();

		l.add((long)S.get(0));

		for(int i=1;i<S.size();i++) {
			char c = S.get(i);
			l.add((l.get(i-1) + mp.pow[i] * c)%mp.mod);

		}
	}
	StringManager(String s,int b){
		S = new ArrayList<Character>();
		for(int i=0;i<s.length();i++) {
			S.add(s.charAt(i));
		}
		base = b;
		if(!calced) {
			calced = true;
			mp = new Mathplus();
			base = b;
			baserev = mp.rev(base);
			mp.buildpow(base,2000050);
			mp.buildrevpow(base, 2000050);

		}
		l = new ArrayList<Long>();

		l.add((long)S.get(0));

		for(int i=1;i<S.size();i++) {
			char c = S.get(i);
			l.add((l.get(i-1) + mp.pow[i] * c)%mp.mod);

		}
	}
	void add(char C){
		int i = S.size();
		S.add(C);
		l.add((l.get(i-1) + mp.pow[i] * C)%mp.mod);
	}
	long gethash(int le,int ri) {
		long res = l.get(ri);
		if(le!=0) {
			res -= l.get(le-1);
			res += mp.mod;
			res %= mp.mod;
			res *= mp.revpow[le];
			res %= mp.mod;
		}
		return res;
	}

	ArrayList<CIPair> runlength(String s){
		ArrayList<CIPair> ret = new ArrayList<CIPair>();
		s += ' ';
		char bef = ' ';
		int len = 0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)!=bef) {
				if(bef != ' ')ret.add(new CIPair(bef,len));
				len = 1;
			}else {
				len++;
			}
			bef = s.charAt(i);
		}
		return ret;
	}
	ArrayList<CIPair> runlength(ArrayList<Character> s){
		ArrayList<CIPair> ret = new ArrayList<CIPair>();
		s.add(' ');
		char bef = ' ';
		int len = 0;
		for(int i=0;i<s.size();i++) {
			if(s.get(i)!=bef) {
				if(bef != ' ')ret.add(new CIPair(bef,len));
				len = 1;
			}else {
				len++;
			}
			bef = s.get(i);
		}
		return ret;
	}
	boolean isPalindrome(String S) {
		boolean b = true;
		for(int i=0;i<S.length();i++) {
			if(S.charAt(i)!=S.charAt(S.length()-1-i))b = false;
		}
		return b;
	}


}




class BetterGridGraph{
	int N;
	int M;
	char[][] S;
	HashMap<Character,ArrayList<Integer>> map;
	int[] dx = {0,1,0,-1};
	int[] dy = {1,0,-1,0};
	int[] dx6 = {0,1,0,-1,-1,1};
	int[] dy6 = {1,0,-1,0,1,1};
	char w;
	char b = '#';
	BetterGridGraph(int n,int m,String[] s,char[] c){

		N = n;
		M = m;
		for(int i=0;i<s.length;i++) {
			S[i] = s[i].toCharArray();
		}
		map = new HashMap<Character,ArrayList<Integer>>();
		for(int i=0;i<c.length;i++) {
			map.put(c[i],new ArrayList<Integer>());
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=0;k<c.length;k++) {
					if(S[i][j]==c[k])map.get(c[k]).add(toint(i,j));
				}
			}
		}
	}
	BetterGridGraph(int n,int m,char[][] s,char[] c){

		N = n;
		M = m;
		S = s;
		map = new HashMap<Character,ArrayList<Integer>>();
		for(int i=0;i<c.length;i++) {
			map.put(c[i],new ArrayList<Integer>());
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=0;k<c.length;k++) {
					if(S[i][j]==c[k])map.get(c[k]).add(toint(i,j));
				}
			}
		}
	}

	BetterGridGraph(int n,int m,String[] s,char[] c,char W,char B){

		N = n;
		M = m;
		for(int i=0;i<s.length;i++) {
			S[i] = s[i].toCharArray();
		}
		w = W;
		b = B;
		map = new HashMap<Character,ArrayList<Integer>>();
		for(int i=0;i<c.length;i++) {
			map.put(c[i],new ArrayList<Integer>());
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=0;k<c.length;k++) {
					if(S[i][j]==c[k])map.get(c[k]).add(toint(i,j));
				}
			}
		}
	}
	BetterGridGraph(int n,int m,char[][] s,char[] c,char W,char B){

		N = n;
		M = m;
		S = s;
		w = W;
		b = B;
		map = new HashMap<Character,ArrayList<Integer>>();
		for(int i=0;i<c.length;i++) {
			map.put(c[i],new ArrayList<Integer>());
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=0;k<c.length;k++) {
					if(S[i][j]==c[k])map.get(c[k]).add(toint(i,j));
				}
			}
		}
	}

	int toint(int i,int j) {
		return i*M+j;
	}

	ArrayList<Integer> getposlist(char c) {
		return map.get(c);
	}

	int getpos(char c) {
		return map.get(c).get(0);
	}

	int[] bfs(char C) {
		int[] L = new int[N*M];
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		for(int i=0;i<N*M;i++){
			L[i] = -1;
		}
		for(int s:map.get(C)) {
			L[s] = 0;
			Q.add(s);
		}
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			int v = Q.poll();
			for(int i=0;i<4;i++){
				int x = v/M;
				int y = v%M;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&S[nx][ny]!=b) {
					int w = toint(nx,ny);
					if(L[w]==-1){
						L[w] = L[v] + 1;
						Q.add(w);
					}
				}
			}
		}
		return L;
	}
	int[] bfs6(char C) {
		int[] L = new int[N*M];
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		for(int i=0;i<N*M;i++){
			L[i] = -1;
		}
		for(int s:map.get(C)) {
			L[s] = 0;
			Q.add(s);
		}
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			int v = Q.poll();
			for(int i=0;i<6;i++){
				int x = v/M;
				int y = v%M;
				int nx = x+dx6[i];
				int ny = y+dy6[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&S[nx][ny]!=b) {
					int w = toint(nx,ny);
					if(L[w]==-1){
						L[w] = L[v] + 1;
						Q.add(w);
					}
				}
			}
		}
		return L;
	}

	int[] bfsb(int s) {
		int[] L = new int[N*M];
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		for(int i=0;i<N*M;i++){
			L[i] = -1;
		}
		Q.add(s);
		L[s] = 0;
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			int v = Q.poll();
			for(int i=0;i<4;i++){
				int x = v/M;
				int y = v%M;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)) {
					int w = toint(nx,ny);
					if(L[w]==-1){
						if(S[x][y]==S[nx][ny]) {
							L[w] = L[v];
							Q.addFirst(w);
						}else {
							L[w] = L[v] + 1;
							Q.addLast(w);
						}
					}
				}
			}
		}
		return L;
	}

	int[][] bfs2(char C,int K){
		int[][] L = new int[N*M][K+1];
		ArrayDeque<IntIntPair> Q = new ArrayDeque<IntIntPair>();
		for(int i=0;i<N*M;i++){
			for(int j=0;j<=K;j++) {
				L[i][j] = 1000000007;
			}
		}
		for(int s:map.get(C)) {
			L[s][0] = 0;
			Q.add(new IntIntPair(0,s));
		}
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			IntIntPair v = Q.poll();
			for(int i=0;i<4;i++){
				int x = v.b/M;
				int y = v.b%M;
				int h = v.a;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&S[nx][ny]!=b) {
					int ni = toint(nx,ny);
					int nh = S[nx][ny]==w?h+1:h;
					if(nh>K) continue;
					if(L[ni][nh]==1000000007){
						L[ni][nh] = L[v.b][h]+1;
						Q.add(new IntIntPair(nh,ni));
					}
				}
			}
		}
		for(int i=0;i<N*M;i++) {
			for(int j=1;j<=K;j++) {
				L[i][j] = Math.min(L[i][j],L[i][j-1]);
			}
		}
		return L;
	}

	int[] dijkstra(char C) {
		int[] L = new int[N*M];
		PriorityQueue<IntIntPair> Q = new PriorityQueue<IntIntPair>(new IntIntComparator());
		for(int i=0;i<N*M;i++){
			L[i] = -1;
		}
		for(int s:map.get(C)) {
			L[s] = 0;
			Q.add(new IntIntPair(0,s));
		}
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			IntIntPair P = Q.poll();
			int v = P.b;
			int x = v/M;
			int y = v%M;
			for(int i=0;i<4;i++){
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&S[nx][ny]!=b) {
					int w = toint(nx,ny);
					if(L[w]==-1||L[w]>L[v] + (S[nx][ny]!='t'?S[nx][ny]-'0':0)){
						L[w] = L[v] + (S[nx][ny]!='t'?S[nx][ny]-'0':0);
						Q.add(new IntIntPair(L[w],w));
					}
				}
			}
			

		}
		return L;
	}
}
class IntGridGraph{
	int N;
	int M;
	int[][] B;
	int[] dx = {0,1,0,-1};
	int[] dy = {1,0,-1,0};
	BiFunction<Integer,Integer,Boolean> F;

	IntGridGraph(int n,int m,int[][] b){

		N = n;
		M = m;
		B = b;
	}
	IntGridGraph(int n,int m,int[][] b,BiFunction<Integer,Integer,Boolean> f){

		N = n;
		M = m;
		B = b;
		F = f;
	}


	int toint(int i,int j) {
		return i*M+j;
	}


	int[] bfs(int s) {
		int[] L = new int[N*M];
		for(int i=0;i<N*M;i++){
			L[i] = -1;
		}
		L[s] = 0;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(s);
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			int v = Q.poll();
			for(int i=0;i<4;i++){
				int x = v/M;
				int y = v%M;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&F.apply(B[x][y],B[nx][ny])) {
				int w = toint(nx,ny);
					if(L[w]==-1){
						L[w] = L[v] + 1;
						Q.add(w);
					}
				}
			}
		}
		return L;
	}

	void bfs(int s,int[] L) {

		if(L[s]!=-1) return;
		L[s] = 0;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(s);
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			int v = Q.poll();
			for(int i=0;i<4;i++){
				int x = v/M;
				int y = v%M;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&F.apply(B[x][y],B[nx][ny])) {
				int w = toint(nx,ny);
					if(L[w]==-1){
						L[w] = L[v] + 1;
						Q.add(w);
					}
				}
			}
		}
		return;
	}

	int[][] bfs2(int s,int K){
		int[][] L = new int[N*M][K+1];
		for(int i=0;i<N*M;i++){
			for(int j=0;j<=K;j++)
			L[i][j] = 1000000007;
		}
		L[s][0] = 0;
		PriorityQueue<IntIntPair> Q = new PriorityQueue<IntIntPair>(new IntIntComparator());
		Q.add(new IntIntPair(0,s));
		Range X = new Range(0,N-1);
		Range Y = new Range(0,M-1);
		while(!Q.isEmpty()){
			IntIntPair v = Q.poll();
			for(int i=0;i<4;i++){
				int x = v.b/M;
				int y = v.b%M;
				int h = v.a;
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(X.isIn(nx)&&Y.isIn(ny)&&F.apply(B[x][y],B[nx][ny])) {
					int ni = toint(nx,ny);
					int nh = h + B[nx][ny];
					if(nh>K) continue;
					if(L[ni][nh]==1000000007){
						L[ni][nh] = L[v.b][h] + 1;
						Q.add(new IntIntPair(nh,ni));
					}
				}
			}
		}
		for(int i=0;i<N*M;i++) {
			for(int j=1;j<=K;j++) {
				L[i][j] = Math.min(L[i][j],L[i][j-1]);
			}
		}
		return L;
	}
}
class Trie{
	int nodenumber = 1;
	ArrayList<TrieNode> l;
	Trie(){
		l = new ArrayList<TrieNode>();
		l.add(new TrieNode());
	}

	void add(String S,int W){
		int now = 0;
		for(int i=0;i<S.length();i++) {
			TrieNode n = l.get(now);
			char c = S.charAt(i);
			if(n.Exist[c-'a']!=-1) {
				now = n.Exist[c-'a'];
			}else {
				l.add(new TrieNode());
				n.Exist[c-'a'] = nodenumber;
				now = nodenumber;
				nodenumber++;
			}
		}
		l.get(now).weight = W;
	}

	void find(String S,int i,int[] dp) {
		int now = 0;
		dp[i+1] = Math.max(dp[i],dp[i+1]);
		for(int j=0;;j++) {
			TrieNode n = l.get(now);
			dp[i+j] = Math.max(dp[i+j],dp[i]+n.weight);
			int slook = i+j;
			if(slook>=S.length())return;
			char c = S.charAt(slook);
			if(n.Exist[c-'a']==-1)return;
			now = n.Exist[c-'a'];
		}
	}
}

class TrieNode{

	int[] Exist = new int[26];
	int weight = 0;
	TrieNode(){
		for(int i=0;i<26;i++) {
			Exist[i] = -1;
		}
	}
}

class SizeComparator implements Comparator<Edge>{
	int[] size;
	SizeComparator(int[] s) {
		size = s;
	}

	public int compare(Edge o1, Edge o2) {
		return size[o1.to]-size[o2.to];

	}

}

class ConvexHullTrick {
	long[] A, B;
	int len;

	public ConvexHullTrick(int n) {
		A = new long[n];
		B = new long[n];
	}

	private boolean check(long a, long b) {
		return (B[len - 2] - B[len - 1]) * (a - A[len - 1]) >= (B[len - 1] - b) * (A[len - 1] - A[len - 2]);
	}

	public void add(long a, long b) {
		while (len >= 2 && check(a, b)) {
			len--;
		}
		A[len] = a;
		B[len] = b;
		len++;
	}

	public long query(long x) {
		int l = -1, r = len - 1;
		while (r - l > 1) {
			int mid = (r + l) / 2;
			if (get(mid,x)>=get(mid+1,x)) {
				l = mid;
			} else {
				r = mid;
			}
		}
		return get(r,x);
	}

	private long get(int k, long x) {
		return A[k] * x + B[k];
	}
}

class Range{
	long l;
	long r;
	long length;
	Range(int L,int R){
		l = L;
		r = R;
		length = R-L+1;
	}

	public Range(long L, long R) {
		l = L;
		r = R;
		length = R-L+1;
	}

	boolean isIn(int x) {
		return (l<=x&&x<=r);

	}
	long kasanari(Range S) {
		if(this.r<S.l||S.r<this.l) return 0;
		else return Math.min(this.r,S.r) - Math.max(this.l,S.l)+1;
	}
}
class LeftComparator implements Comparator<Range>{
	public int compare(Range P, Range Q) {
		return (int) Math.signum(P.l-Q.l);
	}
}
class RightComparator implements Comparator<Range>{
	public int compare(Range P, Range Q) {
		return (int) Math.signum(P.r-Q.r);

	}
}
class LengthComparator implements Comparator<Range>{
	public int compare(Range P, Range Q) {
		return (int) Math.signum(P.length-Q.length);
	}
}
class SegmentTree<T,E>{
	int N;
	BiFunction<T,T,T> f;
	BiFunction<T,E,T> g;
	T d1;
	ArrayList<T> dat;
	SegmentTree(BiFunction<T,T,T> F,BiFunction<T,E,T> G,T D1,T[] v){
		int n = v.length;
		f = F;
		g = G;
		d1 = D1;
		init(n);
		build(v);
	}


	void init(int n) {
		N = 1;
		while(N<n)N*=2;
		dat = new ArrayList<T>();
	}

	void build(T[] v) {
		for(int i=0;i<2*N;i++) {
			dat.add(d1);
		}
		for(int i=0;i<v.length;i++) {
			dat.set(N+i-1,v[i]);
		}
		for(int i=N-2;i>=0;i--) {
			dat.set(i,f.apply(dat.get(i*2+1),dat.get(i*2+2)));
		}
	}

	void update(int k,E a) {
		k += N-1;
		dat.set(k,g.apply(dat.get(k),a));
		while(k>0){
			k = (k-1)/2;
			dat.set(k,f.apply(dat.get(k*2+1),dat.get(k*2+2)));
		}
	}

	T query(int a,int b, int k, int l ,int r) {
		if(r<=a||b<=l) return d1;
		if(a<=l&&r<=b) return dat.get(k);
		T vl = query(a,b,k*2+1,l,(l+r)/2);
		T vr = query(a,b,k*2+2,(l+r)/2,r);
		return f.apply(vl,vr);
	}
	T query(int a,int b){
		return query(a,b,0,0,N);
	}

}

class LazySegmentTree<T,E> extends SegmentTree<T,E>{
	BiFunction<E,E,E> h;
	BiFunction<E,Integer,E> p = (E a,Integer b) ->{return a;};
	E d0;
	ArrayList<E> laz;
	LazySegmentTree(BiFunction<T,T,T> F,BiFunction<T,E,T> G,BiFunction<E,E,E> H,T D1,E D0,T[] v){
		super(F,G,D1,v);
		int n = v.length;
		h = H;
		d0 = D0;
		Init(n);
	}
	void build() {

	}
	void Init(int n){
		laz = new ArrayList<E>();
		for(int i=0;i<2*N;i++) {
			laz.add(d0);
		}
	}

	void eval(int len,int k) {
		if(laz.get(k).equals(d0)) return;
		if(k*2+1<N*2-1) {
			laz.set(k*2+1,h.apply(laz.get(k*2+1),laz.get(k)));
			laz.set(k*2+2,h.apply(laz.get(k*2+2),laz.get(k)));
		}
		dat.set(k,g.apply(dat.get(k), p.apply(laz.get(k), len)));
		laz.set(k,d0);
	}

	T update(int a,int b,E x,int k,int l,int r) {
		eval(r-l,k);
		if(r<=a||b<=l) {
			return dat.get(k);
		}
		if(a<=l&&r<=b) {
			laz.set(k,h.apply(laz.get(k),x));
			return g.apply(dat.get(k),p.apply(laz.get(k),r-l));
		}
		T vl = update(a,b,x,k*2+1,l,(l+r)/2);
		T vr = update(a,b,x,k*2+2,(l+r)/2,r);
		dat.set(k,f.apply(vl,vr));
		return dat.get(k);

	}

	T update(int a,int b,E x) {
		return update(a,b,x,0,0,N);
	}

	T query(int a,int b,int k,int l,int r) {

		eval(r-l,k);
		if(r<=a||b<=l) return d1;
		if(a<=l&&r<=b) return dat.get(k);
		T vl = query(a,b,k*2+1,l,(l+r)/2);
		T vr = query(a,b,k*2+2,(l+r)/2,r);
		return f.apply(vl, vr);
	}

	T query(int a,int b){
		return query(a,b,0,0,N);
	}

}

class AddSumSegmentTree{
	int N;
	int d1;
	ArrayList<Integer> dat;
	AddSumSegmentTree(int[] v){
		int n = v.length;
		init(n);
		build(v);
	}

	void init(int n) {
		N = 1;
		while(N<n)N*=2;
		dat = new ArrayList<Integer>();
	}

	void build(int[] v) {
		for(int i=0;i<2*N;i++) {
			dat.add(d1);
		}
		for(int i=0;i<v.length;i++) {
			dat.set(N+i-1,v[i]);
		}
		for(int i=N-2;i>=0;i--) {
			dat.set(i,dat.get(i*2+1)+dat.get(i*2+2));
		}
	}

	void update(int k,int a) {
		k += N-1;
		dat.set(k,dat.get(k)+a);
		while(k>0){
			k = (k-1)/2;
			dat.set(k,dat.get(k*2+1)+dat.get(k*2+2));
		}
	}

	int query(int a,int b, int k, int l ,int r) {
		if(r<=a||b<=l) return d1;
		if(a<=l&&r<=b) return dat.get(k);
		int vl = query(a,b,k*2+1,l,(l+r)/2);
		int vr = query(a,b,k*2+2,(l+r)/2,r);
		return vl+vr;
	}
	int query(int a,int b){
		return query(a,b,0,0,N);
	}
}
class AddSumLazySegmentTree {
	int N;
	long[] dat;
	long[] laz;
	AddSumLazySegmentTree(long[] v){
		init(v.length);

		for(int i=0;i<v.length;i++) {
			dat[N+i-1]=v[i];
		}
		for(int i=N-2;i>=0;i--) {
			dat[i]=dat[i*2+1]+dat[i*2+2];
		}
	}

	void init(int n) {
		N = 1;
		while(N<n)N*=2;
		dat = new long[2*N];
		laz = new long[2*N];
	}


	void eval(int len,int k) {
		if(laz[k]==0) return;
		if(k*2+1<N*2-1) {
			laz[k*2+1] += laz[k];
			laz[k*2+2] += laz[k];
		}
		dat[k] += laz[k] * len;
		laz[k] = 0;
	}

	long update(int a,int b,long x,int k,int l,int r) {
		eval(r-l,k);
		if(r<=a||b<=l) {
			return dat[k];
		}
		if(a<=l&&r<=b) {
			laz[k] += x;
			return dat[k]+laz[k]*(r-l);
		}
		long vl = update(a,b,x,k*2+1,l,(l+r)/2);
		long vr = update(a,b,x,k*2+2,(l+r)/2,r);
		return dat[k] = vl+vr;


	}

	long update(int a,int b,long x) {
		return update(a,b,x,0,0,N);
	}

	long query(int a,int b,int k,int l,int r) {
		eval(r-l,k);
		if(r<=a||b<=l) return 0;
		if(a<=l&&r<=b) return dat[k];

		long vl = query(a,b,k*2+1,l,(l+r)/2);
		long vr = query(a,b,k*2+2,(l+r)/2,r);
		return vl+vr;
	}

	long query(int a,int b){
		return query(a,b,0,0,N);
	}

}

class BinaryIndexedTree{
	long[] val;
	BinaryIndexedTree(int N){
		val = new long[N+1];
	}
	long sum(int i) {
		if(i==0)return 0;
		long s = 0;
		while(i>0) {
			s += val[i];
			i -= i & (-i);
		}
		return s;
	}
	void add(int i,int x) {
		if(i==0)return;
		while(i<val.length){
			val[i] += x;
			i += i & (-i);
		}
	}
	
	int kth(int k) {
		
		int ok = 0;
		int ng = val.length;
		while(ng-ok>1) {
			int mid = (ok+ng)/2;
			
			if(sum(mid)<k)ok = mid;
			else ng = mid;
		}
		return ng;
	}
}


class UnionFindTree {
	int[] root;
	int[] rank;
	long[] size;
	int[] edge;
	int num;
	UnionFindTree(int N){
		root = new int[N];
		rank = new int[N];
		size = new long[N];
		edge = new int[N];
		num = N;
		for(int i=0;i<N;i++){
			root[i] = i;
			size[i] = 1;
		}
	}
	
	

	public long size(int x) {
		return size[find(x)];
	}
	public boolean isRoot(int x) {
		return x==find(x);
	}
	public long extraEdge(int x) {
		int r = find(x);
		return edge[r] - size[r] + 1;
	}
	public int find(int x){
		if(root[x]==x){
			return x;
		}else{
			return find(root[x]);
		}
	}

	public boolean unite(int x,int y){
		x = find(x);
		y = find(y);
		if(x==y){
			edge[x]++;
			return false;
		}else{
			num--;
			if(rank[x]<rank[y]){
				root[x] = y;
				size[y] += size[x];
				edge[y] += edge[x]+1;
			}else{
				root[y] = x;
				size[x] += size[y];
				edge[x] += edge[y]+1;
				if(rank[x]==rank[y]){
					rank[x]++;
				}
			}
			return true;
		}
	}

	public boolean same(int x,int y){
		return find(x)==find(y);
	}

}
class LightUnionFindTree {
	int[] par;
	int num;
	LightUnionFindTree(int N){
		par = new int[N];
		num = N;
		for(int i=0;i<N;i++){
			par[i] = -1;
		}
	}
	public boolean isRoot(int x) {
		return x==find(x);
	}

	public int find(int x){
		if(par[x]<0){
			return x;
		}else{
			return find(par[x]);
		}
	}

	public void unite(int x,int y){
		x = find(x);
		y = find(y);
		if(x==y){
			return;
		}else{
			num--;
			if(par[x]<par[y]){
				par[x] += par[y];
				par[y] = x;
			}else{
				par[y] += par[x];
				par[x] = y;
			}
		}
	}

	public boolean same(int x,int y){
		return find(x)==find(y);
	}

}

class RollbackableUnionFind{
	int[] par;
	int[] size;
	int num;
	Stack<Integer> pointx;
	Stack<Integer> pointy;
	Stack<Integer> befpar;
	Stack<Integer> befsz;
	RollbackableUnionFind(int N){
		par = new int[N];
		size = new int[N];
		num = N;
		for(int i=0;i<N;i++){
			par[i] = -1;
			size[i] = 1;
		}
		pointx = new Stack<Integer>();
		pointy = new Stack<Integer>();
		befpar = new Stack<Integer>();
		befsz = new Stack<Integer>();
	}
	public boolean isRoot(int x) {
		return x==find(x);
	}
	
	public int size(int x) {
		return size[find(x)];
	}

	public int find(int x){
		if(par[x]<0){
			return x;
		}else{
			return find(par[x]);
		}
	}

	public boolean unite(int x,int y){
		x = find(x);
		y = find(y);
		if(x==y){
			return false;
		}else{
			int xs = size[x];
			int ys = size[y];
			if(xs<ys) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			pointx.push(x);
			pointy.push(y);
			befpar.push(par[y]);
			befsz.push(size[x]);
			par[y] = x;
			size[x] += size[y];
			
			return true;
		}
	}
	
	public void rollback() {
		int x = pointx.pop();
		int y = pointy.pop();
		par[y] = befpar.pop();
		size[x] = befsz.pop();
	}

	public boolean same(int x,int y){
		return find(x)==find(y);
	}
}

class JustCompressUnionFind{
	int[] par;
	int num;
	JustCompressUnionFind(int N){
		par = new int[N];
		num = N;
		for(int i=0;i<N;i++){
			par[i] = -1;
		}
	}
	public boolean isRoot(int x) {
		return x==find(x);
	}

	public int find(int x){
		if(par[x]<0){
			return x;
		}else{
			return par[x] = find(par[x]);
		}
	}

	public boolean unite(int x,int y){
		x = find(x);
		y = find(y);
		if(x==y){
			return false;
		}else{
			num--;
			par[y] = x;
			return true;
		}
	}

	public boolean same(int x,int y){
		return find(x)==find(y);
	}
}

class ParticalEternalLastingUnionFindTree extends UnionFindTree{
	int[] time;
	int now;
	ParticalEternalLastingUnionFindTree(int N){
		super(N);
		time = new int[N];
		for(int i=0;i<N;i++) {
			time[i] = 1000000007;
		}
	}

	public int find(int t,int i) {
		if(time[i]>t) {
			return i;
		}else {
			return find(t,root[i]);
		}
	}

	public void unite(int x,int y,int t) {
		now = t;
		x = find(t,x);
		y = find(t,y);
		if(x==y)return;
		if(rank[x]<rank[y]){
			root[x] = y;
			size[y] += size[x];
			time[x] = t;
		}else{
			root[y] = x;
			size[x] += size[y];
			if(rank[x]==rank[y]){
				rank[x]++;
			}
			time[y] = t;
		}
	}

	public int sametime(int x,int y) {
		if(find(now,x)!=find(now,y)) return -1;
		int ok = now;
		int ng = 0;
		while(ok-ng>1) {
			int mid = (ok+ng)/2;
			if(find(mid,x)==find(mid,y)) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}


}
class FlowEdge{
	int to;
	long cap;
	int rev = 0;
	long cost = 0;
	FlowEdge(int To,long Cap,int Rev){
		to = To;
		cap = Cap;
		rev = Rev;
	}
	FlowEdge(int To,long Cap,int Rev, long Cost){
		to = To;
		cap = Cap;
		rev = Rev;
		cost = Cost;
	}
}
class FlowGraph{
	int size;
	ArrayList<FlowEdge>[] list;
	int[] level;
	int[] iter;
	long[] h;
	long[] dist;
	int[] prevv;
	int[] preve;
	ArrayDeque<Integer> q;
	FlowGraph(int N){
		size = N;
		list = new ArrayList[N];
		for(int i=0;i<N;i++) {
			list[i] = new ArrayList<FlowEdge>();
		}
		level = new int[N];
		iter = new int[N];
		h = new long[N];
		dist = new long[N];
		prevv = new int[N];
		preve = new int[N];
		q = new ArrayDeque<Integer>();
	}

	void addEdge(int i, int to, long cap) {
		list[i].add(new FlowEdge(to,cap,list[to].size()));
		list[to].add(new FlowEdge(i,0,list[i].size()-1));
	}
	void addEdge(int i, int to, long cap,long cost) {
		list[i].add(new FlowEdge(to,cap,list[to].size(),cost));
		list[to].add(new FlowEdge(i,0,list[i].size()-1,-cost));
	}

	void bfs(int s) {
		Arrays.fill(level,-1);
		level[s] = 0;
		q.add(s);
		while(!q.isEmpty()) {
			int v = q.poll();
			for(FlowEdge e:list[v]) {
				if(e.cap>0&&level[e.to]<0) {
					level[e.to] = level[v] + 1;
					q.add(e.to);
				}
			}
		}
	}

	long dfs(int v,int t,long f) {
		if(v==t) return f;
		for(int i = iter[v];i<list[v].size();i++) {
			FlowEdge e = list[v].get(i);
			if(e.cap>0&&level[v]<level[e.to]) {
				long d = dfs(e.to,t,Math.min(f,e.cap));
				if(d>0) {
					e.cap -= d;
					list[e.to].get(e.rev).cap += d;
					return d;
				}
			}
			iter[v]++;
		}
		return 0;
	}

	long flow(int s,int t,long lim) {
		long flow = 0;
		while(true) {
			bfs(s);
			if(level[t]<0||lim==0) return flow;
			Arrays.fill(iter,0);
			while(true) {
				long f = dfs(s,t,lim);
				if(f>0) {
					flow += f;
					lim -= f;
				}

				else break;
			}
		}

	}
	long flow(int s,int t) {
		return flow(s,t,1000000007);
	}
	long mincostflow(int s,int t,long f) {
		long res = 0;
		while(f>0) {
			PriorityQueue<LongIntPair> q = new PriorityQueue<LongIntPair>(new LongIntComparator());
			for(int i=0;i<size;i++)dist[i] = (long)1e18;
			dist[s] = 0;
			q.add(new LongIntPair(0,s));
			while(!q.isEmpty()) {
				LongIntPair p = q.poll();
				int v = p.b;
				if(dist[v]<p.a)continue;
				for(int i=0;i<list[v].size();i++) {
					FlowEdge e = list[v].get(i);
					if(e.cap>0&&dist[e.to]>dist[v]+e.cost+h[v]-h[e.to]) {
						dist[e.to] = dist[v]+e.cost+h[v]-h[e.to];
						prevv[e.to] = v;
						preve[e.to] = i;
						q.add(new LongIntPair(dist[e.to],e.to));
					}
				}
			}
			if(dist[t]==(long)1e18) return -1;
			for(int v=0;v<size;v++)h[v]+=dist[v];
			long d = f;
			for(int v=t;v!=s;v=prevv[v]) {
				d = Math.min(d,list[prevv[v]].get(preve[v]).cap);
			}
			f -= d;
			res += d*h[t];
			for(int v=t;v!=s;v=prevv[v]) {
				FlowEdge e = list[prevv[v]].get(preve[v]);
				e.cap -= d;
				list[v].get(e.rev).cap += d;
			}
		}
		return res;
	}
}
class Graph {
	ArrayList<Edge>[] list;
	int size;
	TreeSet<LinkEdge> Edges = new TreeSet<LinkEdge>(new LinkEdgeComparator());

	@SuppressWarnings("unchecked")
	Graph(int N){
		size = N;
		list = new ArrayList[N];
		for(int i=0;i<N;i++){
			list[i] = new ArrayList<Edge>();
		}
	}

	public long[] dicount(int s) {
		long[] L = new long[size];
		long[] c = new long[size];
		int mod = 1000000007;
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		int[] v = new int[size];
		L[s] = 0;
		c[s] = 1;
		PriorityQueue<LongIntPair> Q = new PriorityQueue<LongIntPair>(new LongIntComparator());
		Q.add(new LongIntPair(0,s));

		while(!Q.isEmpty()){

			LongIntPair C = Q.poll();

			if(v[C.b]==0){
				L[C.b] = C.a;
				v[C.b] = 1;
				for(Edge D:list[C.b]) {
					//System.out.println(C.b +" "+ D.to);
					if(L[D.to]==-1||L[D.to]>L[C.b]+D.cost) {
						L[D.to]=L[C.b]+D.cost;
						c[D.to] = c[C.b];
						Q.add(new LongIntPair(L[C.b]+D.cost,D.to));
					}else if(L[D.to]==L[C.b]+D.cost) {
						c[D.to] += c[C.b];
					}
					c[D.to] %= mod;

				}
			}
		}
		return c;
	}

	public long[] roots(int s) {
		int[] in = new int[size];
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		long[] N = new long[size];
		long mod = 1000000007;
		for(int i=0;i<size;i++) {
			for(Edge e:list[i])in[e.to]++;
		}
		for(int i=0;i<size;i++) {
			if(in[i]==0)q.add(i);
		}
		N[s] = 1;
		while(!q.isEmpty()) {
			int v = q.poll();
			for(Edge e:list[v]) {
				N[e.to] += N[v];
				if(N[e.to]>=mod)N[e.to]-= mod;
				in[e.to]--;
				if(in[e.to]==0)q.add(e.to);
			}
		}
		return N;

	}

	void addEdge(int a,int b){
		list[a].add(new Edge(b,1));
	}

	void addWeightedEdge(int a,int b,long c){
		list[a].add(new Edge(b,c));
	}

	void addEgdes(int[] a,int[] b){
		for(int i=0;i<a.length;i++){
			list[a[i]].add(new Edge(b[i],1));
		}
	}

	void addWeightedEdges(int[] a ,int[] b ,int[] c){
		for(int i=0;i<a.length;i++){
			list[a[i]].add(new Edge(b[i],c[i]));
		}
	}
	long[] bfs(int s){
		long[] L = new long[size];
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		L[s] = 0;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(s);

		while(!Q.isEmpty()){
			int v = Q.poll();
			for(Edge e:list[v]){
				int w = e.to;
				long c = e.cost;
				if(L[w]==-1){
					L[w] = L[v] + c;
					Q.add(w);
				}
			}
		}
		return L;
	}


	long[][] bfswithrev(int s){
		long[][] L = new long[2][size];
		for(int i=0;i<size;i++){
			L[0][i] = -1;
			L[1][i] = -1;
		}
		L[0][s] = 0;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(s);

		while(!Q.isEmpty()){
			int v = Q.poll();
			for(Edge e:list[v]){
				int w = e.to;
				long c = e.cost;
				if(L[0][w]==-1){
					L[0][w] = L[0][v] + c;
					L[1][w] = v;
					Q.add(w);
				}
			}
		}
		return L;
	}
	long[] bfs2(int[] d,int s){
		long[] L = new long[size];
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		int p = 0;
		L[s] = 0;
		d[s] = p;
		p++;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(s);

		while(!Q.isEmpty()){
			int v = Q.poll();
			for(Edge e:list[v]){
				int w = e.to;
				long c = e.cost;
				if(L[w]==-1){
					d[w] = p;
					p++;
					L[w] = L[v] + c;
					Q.add(w);
				}
			}
		}
		return L;
	}
	boolean bfs3(int s,long[] L, int[] vi){

		if(vi[s]==1) return true;

		vi[s] = 1;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(s);

		while(!Q.isEmpty()){
			int v = Q.poll();
			for(Edge e:list[v]){
				int w = e.to;
				long c = e.cost;
				if(vi[e.to]==0) {
					L[e.to] = (int)c - L[v];
					Q.add(w);
					vi[e.to] = 1;
				}else {
					if(L[e.to]!=(int)c - L[v]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	int[] isTwoColor(){
		int[] L = new int[size];
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		L[0] = 0;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(0);

		while(!Q.isEmpty()){
			int v = Q.poll();
			for(Edge e:list[v]){
				int w = e.to;
				if(L[w]==-1){
					L[w] = 1-L[v];
					Q.add(w);
				}else{
					if(L[v]+L[w]!=1){
						L[0] = -2;
					}
				}
			}
		}
		return L;
	}
	void isTwoColor2(int i,int[] L){


		L[i] = 0;
		ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
		Q.add(i);

		while(!Q.isEmpty()){
			int v = Q.poll();
			for(Edge e:list[v]){
				int w = e.to;
				if(L[w]==-1){
					L[w] = 1-L[v];
					Q.add(w);
				}else{
					if(L[v]+L[w]!=1){
						L[0] = -2;
					}
				}
			}
		}
	}

	long[] dijkstra(int s){
		long[] L = new long[size];
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		int[] v = new int[size];
		L[s] = 0;
		PriorityQueue<LongIntPair> Q = new PriorityQueue<LongIntPair>(new LongIntComparator());
		Q.add(new LongIntPair(0,s));

		while(!Q.isEmpty()){

			LongIntPair C = Q.poll();

			if(v[C.b]==0){
				L[C.b] = C.a;
				v[C.b] = 1;
				for(Edge D:list[C.b]) {
					if(L[D.to]==-1||L[D.to]>L[C.b]+D.cost) {
						L[D.to]=L[C.b]+D.cost;
						Q.add(new LongIntPair(L[C.b]+D.cost,D.to));
					}

				}
			}
		}
		return L;
	}








	ArrayList<Graph> makeapart(){
		ArrayList<Graph> ans = new ArrayList<Graph>();
		boolean[] b = new boolean[size];
		int[] num = new int[size];
		for(int i=0;i<size;i++){
			if(b[i])continue;
			int sz = 0;
			ArrayList<Integer> l = new ArrayList<Integer>();
			ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
			Q.add(i);
			b[i] = true;
			while(!Q.isEmpty()){
				int v = Q.poll();
				num[v] = sz;
				sz++;
				l.add(v);
				for(Edge e:list[v]){
					if(!b[e.to]){
						Q.add(e.to);
						b[e.to] = true;
					}
				}
			}
			Graph H = new Graph(sz);
			for(int e:l){
				for(Edge E:list[e]){
					H.addWeightedEdge(num[e],num[E.to],E.cost);
				}
			}
			ans.add(H);

		}
		return ans;
	}

	long[] bellmanFord(int s) {
		long inf = 1000000000;
		inf *= inf;
		long[] d = new long[size];
		boolean[] n = new boolean[size];
		d[s] = 0;
		for(int i=1;i<size;i++){
			d[i] = inf;
			d[i] *= d[i];
		}
		for(int i=0;i<size-1;i++){
			for(int j=0;j<size;j++){
				for(Edge E:list[j]){
					if(d[j]!=inf&&d[E.to]>d[j]+E.cost){
						d[E.to]=d[j]+E.cost;
					}
				}
			}
		}
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				for(Edge e:list[j]){
					if(d[j]==inf) continue;
					if(d[e.to]>d[j]+e.cost) {
						d[e.to]=d[j]+e.cost;
						n[e.to] = true;
					}
					if(n[j])n[e.to] = true;
				}
			}
		}
		for(int i=0;i<size;i++) {
			if(n[i])d[i] = inf;
		}
		return d;
	}

	long[][] WarshallFloyd(long[][] a){
		int n = a.length;
		long[][] ans = new long[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				ans[i][j] = a[i][j]==0?(long)1e16:a[i][j];
				if(i==j)ans[i][j]=0;
			}
		}
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					ans[i][j] = Math.min(ans[i][j],ans[i][k]+ans[k][j]);
				}
			}
		}
		return ans;
	}
	long[] maxtra(int s,long l){
		long[] L = new long[size];
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		int[] v = new int[size];
		L[s] = -1;

		PriorityQueue<Pair> Q = new PriorityQueue<Pair>(new SampleComparator());
		Q.add(new Pair(l,s));

		while(!Q.isEmpty()){

			Pair C = Q.poll();
			if(v[(int)C.b]==0){
				L[(int)C.b] = C.a;
				v[(int) C.b] = 1;
				for(Edge D:list[(int) C.b])Q.add(new Pair(Math.max(L[(int)C.b],D.cost),D.to));
			}
		}

		return L;
	}
	long[] mintra(int s){
		long[] L = new long[size];
		for(int i=0;i<size;i++){
			L[i] = -1;
		}
		int[] v = new int[size];
		L[s] = s;

		PriorityQueue<Pair> Q = new PriorityQueue<Pair>(new SampleComparator().reversed());
		Q.add(new Pair(s,s));

		while(!Q.isEmpty()){

			Pair C = Q.poll();
			if(v[(int)C.b]==0){
				L[(int)C.b] = C.a;
				v[(int) C.b] = 1;
				for(Edge D:list[(int) C.b])Q.add(new Pair(Math.min(L[(int)C.b],D.cost),D.to));
			}
		}

		return L;
	}
	long Kruskal(){
		long r = 0;
		for(int i=0;i<size;i++) {
			for(Edge e:list[i]) {
				Edges.add(new LinkEdge(e.cost,i,e.to));
			}
		}
		UnionFindTree UF = new UnionFindTree(size);
		for(LinkEdge e:Edges){
			if(e.a>=0&&e.b>=0) {
				if(!UF.same(e.a,e.b)){
					r += e.L;
					UF.unite(e.a,e.b);
				}
			}
		}
		return r;
	}

	ArrayList<Integer> Kahntsort(){

		ArrayList<Integer> ans = new ArrayList<Integer>();
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int[] in = new int[size];
		for(int i=0;i<size;i++) {
			for(Edge e:list[i])in[e.to]++;
		}
		for(int i=0;i<size;i++) {
			if(in[i]==0)q.add(i);
		}
		while(!q.isEmpty()) {
			int v = q.poll();
			ans.add(v);
			for(Edge e:list[v]) {
				in[e.to]--;
				if(in[e.to]==0)q.add(e.to);
			}
		}
		for(int i=0;i<size;i++) {
			if(in[i]>0)return new ArrayList<Integer>();
		}
		return ans;
	}
	public Stack<Integer> findCycle() {
		Stack<Integer> ans = new Stack<Integer>();
		boolean[] v = new boolean[size];
		boolean[] f = new boolean[size];
		for(int i=0;i<size;i++) {
			if(findCycle(i,ans,v,f))break;
		}
		return ans;
	}
	private boolean findCycle(int i, Stack<Integer>ans, boolean[] v,boolean[] f) {
		v[i] = true;
		ans.push(i);
		for(Edge e:list[i]) {
			if(f[e.to]) continue;
			if(v[e.to]&&!f[e.to]) {
				return true;
			}
			if(findCycle(e.to,ans,v,f))return true;
		}
		ans.pop();
		f[i] = true;
		return false;

	}
	RootedTree dfsTree(int i) {
		int[] u = new int[size];
		RootedTree r = new RootedTree(size);
		dfsTree(i,u,r);
		return r;

	}

	private void dfsTree(int i, int[] u, RootedTree r) {
		u[i] = 1;
		r.trans[r.node] = i;
		r.rev[i] = r.node;
		r.node++;
		for(Edge e:list[i]) {
			if(u[e.to]==0) {
				r.list[i].add(e);
				u[e.to] = 1;
				dfsTree(e.to,u,r);
			}
		}


	}
}

class LightGraph {
	ArrayList<Integer>[] list;
	int size;
	TreeSet<LinkEdge> Edges = new TreeSet<LinkEdge>(new LinkEdgeComparator());

	@SuppressWarnings("unchecked")
	LightGraph(int N){
		size = N;
		list = new ArrayList[N];
		for(int i=0;i<N;i++){
			list[i] = new ArrayList<Integer>();
		}
	}






	void addEdge(int a,int b){
		list[a].add(b);
	}


	public Stack<Integer> findCycle() {
		Stack<Integer> ans = new Stack<Integer>();
		boolean[] v = new boolean[size];
		boolean[] f = new boolean[size];
		for(int i=0;i<size;i++) {
			if(findCycle(i,ans,v,f))break;
		}
		return ans;
	}
	private boolean findCycle(int i, Stack<Integer>ans, boolean[] v,boolean[] f) {
		v[i] = true;
		ans.push(i);
		for(int e:list[i]) {
			if(f[e]) continue;
			if(v[e]&&!f[e]) {
				return true;
			}
			if(findCycle(e,ans,v,f))return true;
		}
		ans.pop();
		f[i] = true;
		return false;

	}

}

class FunGraph {
	int[] list;
	int size;
	TreeSet<LinkEdge> Edges = new TreeSet<LinkEdge>(new LinkEdgeComparator());

	@SuppressWarnings("unchecked")
	FunGraph(int N){
		size = N;
		list = new int[N];
		Arrays.fill(list,-1);
	}






	void addEdge(int a,int b){
		list[a] = b;
	}


	public Stack<Integer> findCycle() {
		Stack<Integer> ans = new Stack<Integer>();
		boolean[] v = new boolean[size];
		boolean[] f = new boolean[size];
		for(int i=0;i<size;i++) {
			if(findCycle(i,ans,v,f))break;
		}
		return ans;
	}
	private boolean findCycle(int i, Stack<Integer>ans, boolean[] v,boolean[] f) {
		v[i] = true;
		ans.push(i);
		int e = list[i];
		if(e!=-1&&!f[e]) {
			if(v[e]&&!f[e]) {
				return true;
			}
			if(findCycle(e,ans,v,f))return true;
		}
		
		ans.pop();
		f[i] = true;
		return false;

	}

}


class Tree extends Graph{

	static int[][] lcat;
	static int[] depth;
	public Tree(int N) {
		super(N);
	}


	long[] tyokkei(){
		long[] a = bfs(0);


		int md = -1;
		long m = 0;
		for(int i=0;i<size;i++){
			if(m<a[i]){
				m = a[i];
				md = i;
			}
		}

		long[] b = bfs(md);
		int md2 = -1;
		long m2 = 0;
		for(int i=0;i<size;i++){
			if(m2<b[i]){
				m2 = b[i];
				md2 = i;
			}
		}
		long[] r = {m2,md,md2};
		return r;
	}
	
	int[] size(int r) {
		int[] ret = new int[size];
		dfssize(r,-1,ret);
		return ret;
	}


	private int dfssize(int i, int rev, int[] ret) {
		int sz = 1;
		for(Edge e:list[i]) {
			if(e.to!=rev) sz += dfssize(e.to,i,ret);
		}
		return ret[i] = sz;
		
	}
	void lcadfs(int i,int rev,int d) {
		lcat[0][i] = rev;
		depth[i] = d;
		for(Edge e:list[i]) {
			if(e.to!=rev)lcadfs(e.to,i,d+1);
		}
	}
	
	void buildlca(int r){
		lcat = new int[21][size];
		depth = new int[size];
		lcadfs(r,-1,0);
		for(int i=0;i<20;i++) {
			Arrays.fill(lcat[i+1],-1);
			for(int j=0;j<size;j++) {
				if(lcat[i][j]!=-1)lcat[i+1][j] = lcat[i][lcat[i][j]];
				else lcat[i+1][j] = -1;
			}
		}
	}
	
	int lca(int a,int b) {
		if(depth[a]>depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		int d = depth[b]-depth[a];
		for(int i=0;i<=20;i++) {
			if(((d>>i)&1)==1)b = lcat[i][b];
		}
		if(a==b) return a;
		for(int i=20;i>=0;i--) {
			if(lcat[i][a]!=lcat[i][b]) {
				a = lcat[i][a];
				b = lcat[i][b];
			}
		}
		return lcat[0][a];
	}
	
	



}

class RootedTree extends Graph{
	int[] trans;
	int[] rev;
	int node = 0;
	RootedTree(int N){
		super(N);
		trans = new int[N];
		rev = new int[N];
	}
	public int[] parents() {
		int[] ret = new int[size];
		for(int i=0;i<size;i++) {
			for(Edge e:list[i]) {
				ret[rev[e.to]] = rev[i];
			}
		}
		ret[0] = -1;
		return ret;
	}
}
class LinkEdge{
	long L;
	int a ;
	int b;
	int id;
	LinkEdge(long l,int A,int B){
		L = l;
		a = A;
		b = B;
	}
	LinkEdge(long l,int A,int B,int i){
		L = l;
		a = A;
		b = B;
		id = i;
	}
	public boolean equals(Object o){
		LinkEdge O = (LinkEdge) o;
		return O.a==this.a&&O.b==this.b&&O.L==this.L;
	}

	public int hashCode(){
		return Objects.hash(L,a,b);
	}
}

class DoubleLinkEdge{
	double D;
	int a;
	int b;
	DoubleLinkEdge(double d,int A,int B){
		D = d;
		a = A;
		b = B;
	}
	public boolean equals(Object o){
		DoubleLinkEdge O = (DoubleLinkEdge) o;
		return O.a==this.a&&O.b==this.b&&O.D==this.D;
	}

	public int hashCode(){
		return Objects.hash(D,a,b);
	}
}


class Edge{
	int to;
	long cost;
	Edge(int a,long b){
		to = a;
		cost = b;
	}
}

class indexedEdge extends Edge{
	int id;
	indexedEdge(int a, long b, int c) {
		super(a,b);
		id = c;
	}

}

class DoubleLinkEdgeComparator implements Comparator<DoubleLinkEdge>{
	public int compare(DoubleLinkEdge P, DoubleLinkEdge Q) {
		return Double.compare(P.D,Q.D);
	}
}

class LinkEdgeComparator implements Comparator<LinkEdge>{
	public int compare(LinkEdge P, LinkEdge Q) {
		if(P.L==Q.L) {
			if(P.a==Q.a) return Integer.compare(P.b,Q.b);
			return Integer.compare(P.a,Q.a);
		}
		return Long.compare(P.L,Q.L);
	}
}


class Pair{
	long a;
	long b;

	Pair(long p,long q){
		this.a = p;
		this.b = q;
	}

	public boolean equals(Object o){
		Pair O = (Pair) o;
		return O.a==this.a&&O.b==this.b;
	}

	public int hashCode(){
		return Objects.hash(a,b);
	}
}

class SampleComparator implements Comparator<Pair>{
	public int compare(Pair P, Pair Q) {
		long t = P.a-Q.a;
		if(t==0){
			if(P.b==Q.b)return 0;
			return P.b>Q.b?1:-1;

		}
		return t>=0?1:-1;
	}
}


class LongIntPair{
	long a;
	int b;

	LongIntPair(long p,int q){
		this.a = p;
		this.b = q;
	}

	public boolean equals(Object o){
		LongIntPair O = (LongIntPair) o;
		return O.a==this.a&&O.b==this.b;

	}

	public int hashCode(){
		return Objects.hash(a,b);
	}
}

class LongIntComparator implements Comparator<LongIntPair>{
	public int compare(LongIntPair P, LongIntPair Q) {
		long t = P.a-Q.a;
		if(t==0){
			if(P.b>Q.b){
				return 1;
			}else{
				return -1;
			}
		}
		return t>=0?1:-1;
	}
}


class IntIntPair{
	int a;
	int b;

	IntIntPair(int p,int q){
		this.a = p;
		this.b = q;
	}
	IntIntPair(int p,int q,String s){
		if(s.equals("sort")) {
			this.a = Math.min(p,q);
			this.b = Math.max(p,q);
		}
	}


	public boolean equals(Object o){
		IntIntPair O = (IntIntPair) o;
		return O.a==this.a&&O.b==this.b;

	}

	public int hashCode(){
		return Objects.hash(a,b);
	}
}



class IntIntComparator implements Comparator<IntIntPair>{
	public int compare(IntIntPair P, IntIntPair Q) {
		int t = P.a-Q.a;
		if(t==0){
			return P.b-Q.b;
		}
		return t;
	}
}

class IntIntsecondComparator implements Comparator<IntIntPair>{
	public int compare(IntIntPair P, IntIntPair Q) {
		int t = P.b-Q.b;
		if(t==0){
			return P.a-Q.a;
		}
		return t;
	}
}




class CIPair{
	char c;
	int i;
	CIPair(char C,int I){
		c = C;
		i = I;
	}
	public boolean equals(Object o){
		CIPair O = (CIPair) o;
		return O.c==this.c&&O.i==this.i;
	}
	public int hashCode(){
		return Objects.hash(c,i);
	}
}




class DoublePair{
	double a;
	double b;

	DoublePair(double p,double q){
		this.a = p;
		this.b = q;
	}

	public boolean equals(Object o){
		DoublePair O = (DoublePair) o;
		return O.a==this.a&&O.b==this.b;

	}

	public int hashCode(){
		return Objects.hash(a,b);
	}
}
class Triplet{
	long a;
	long b;
	long c;
	Triplet(long p,long q,long r){
		a = p;
		b = q;
		c = r;
	}
	public boolean equals(Object o){
		Triplet O = (Triplet) o;
		return O.a==this.a&&O.b==this.b&&O.c==this.c?true:false;

	}

	public int hashCode(){
		return Objects.hash(a,b,c);
	}
}
class Quadraplet{
	long a;
	long b;
	long c;
	long d;
	Quadraplet(long p,long q,long r,long s){
		a = p;
		b = q;
		c = r;
		d = s;
	}
	public boolean equals(Object o){
		Quadraplet O = (Quadraplet) o;
		return O.a==this.a&&O.b==this.b&&O.c==this.c&&O.d==this.d?true:false;

	}

	public int hashCode(){
		return Objects.hash(a,b,c,d);
	}
}

class TripletComparator implements Comparator<Triplet>{
	public int compare(Triplet P, Triplet Q) {
		long t = P.a-Q.a;
		if(t==0){
			long tt = P.b-Q.b;
			if(tt==0) {
				if(P.c>Q.c) {
					return 1;
				}else if(P.c<Q.c){
					return -1;
				}else {
					return 0;
				}
			}
			return tt>0?1:-1;
		}
		return t>=0?1:-1;
	}
}
class DDComparator implements Comparator<DoublePair>{
	public int compare(DoublePair P, DoublePair Q) {
		return P.a-Q.a>=0?1:-1;
	}
}

class DoubleTriplet{
	double a;
	double b;
	double c;

	DoubleTriplet(double p,double q,double r){
		this.a = p;
		this.b = q;
		this.c = r;
	}

	public boolean equals(Object o){
		DoubleTriplet O = (DoubleTriplet) o;
		return O.a==this.a&&O.b==this.b&&O.c==this.c;

	}

	public int hashCode(){
		return Objects.hash(a,b,c);
	}
}

class DoubleTripletComparator implements Comparator<DoubleTriplet>{
	public int compare(DoubleTriplet P, DoubleTriplet Q) {
		if(P.a==Q.a) return 0;
		return P.a-Q.a>0?1:-1;
	}
}

class CheckingScanner {
	
	Scanner sc = new Scanner(System.in);
	
	String[] CheckedLine(int N){
		
		String input = sc.nextLine();
		if(input.charAt(input.length()-1)==' ') throw new EndSpaceException();
		String[] ret = input.split(" ");
		if(ret.length!=N) throw new ListLengthException();
		return ret;
		
	}
	
	int[] nextboundedints(int N, int minimum, int maximum) {
		
		String[] input = CheckedLine(N);
		int[] ret = new int[N];
		for(int i=0;i<N;i++) {
			ret[i] = Integer.parseInt(input[i]);
			checkbound(ret[i],minimum,maximum);
		}
		
		return ret;
		
	}
	long[] nextboundedlongs(int N, long minimum, long maximum) {
		
		String[] input = CheckedLine(N);
		long[] ret = new long[N];
		for(int i=0;i<N;i++) {
			ret[i] = Long.parseLong(input[i]);
			checkbound(ret[i],minimum,maximum);
		}
		return ret;
		
	}
	
	void checkbound(long v, long minimum, long maximum) {
		if(v<minimum || maximum<v) throw new OutofBoundException();
	}
	
	class ListLengthException extends RuntimeException{
		
	}
	class EndSpaceException extends RuntimeException{
		
	}
	class OutofBoundException extends RuntimeException{
		
	}
    
}


class FastScanner {
    private final java.io.InputStream in = System.in;
    private final byte[] b = new byte[1024];
    private int p = 0;
    private int bl = 0;
    private boolean hNB() {
        if (p<bl) {
            return true;
        }else{
            p = 0;
            try {
                bl = in.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bl<=0) {
                return false;
            }
        }
        return true;
    }

	private int rB() { if (hNB()) return b[p++]; else return -1;}
    private static boolean iPC(int c) { return 33 <= c && c <= 126;}
    private void sU() { while(hNB() && !iPC(b[p])) p++;}
    public boolean hN() { sU(); return hNB();}
    public String next() {
        if (!hN()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = rB();
        while(iPC(b)) {
            sb.appendCodePoint(b);
            b = rB();
        }
        return sb.toString();
    }
    public char nextChar() {
    	return next().charAt(0);
    }
    public long nextLong() {
        if (!hN()) throw new NoSuchElementException();
        long n = 0;
        boolean m = false;
        int b = rB();
        if (b=='-') {
            m=true;
            b=rB();
        }
        if (b<'0'||'9'<b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0'<=b&&b<='9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1||!iPC(b)){
                return (m?-n:n);
            }else{
                throw new NumberFormatException();
            }
            b = rB();
        }
    }
    public int nextInt() {
        if (!hN()) throw new NoSuchElementException();
        long n = 0;
        boolean m = false;
        int b = rB();
        if (b == '-') {
            m = true;
            b = rB();
        }
        if (b<'0'||'9'<b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0'<=b&&b<='9') {
                n *= 10;
                n += b-'0';
            }else if(b==-1||!iPC(b)){
                return (int) (m?-n:n);
            }else{
                throw new NumberFormatException();
            }
            b = rB();
        }
    }
    public int[] nextInts(int n) {
    	int[] a = new int[n];
    	for(int i=0;i<n;i++) {
    		a[i] = nextInt();
    	}
    	return a;
    }
    public int[] nextInts(int n,int s) {
    	int[] a = new int[n+s];
    	for(int i=s;i<n+s;i++) {
    		a[i] = nextInt();
    	}
    	return a;
    }
    public long[] nextLongs(int n, int s) {
    	long[] a = new long[n+s];
    	for(int i=s;i<n+s;i++) {
    		a[i] = nextLong();
    	}
    	return a;
	}
    public long[] nextLongs(int n) {
    	long[] a = new long[n];
    	for(int i=0;i<n;i++) {
    		a[i] = nextLong();
    	}
    	return a;
    }
    public int[][] nextIntses(int n,int m){
    	int[][] a = new int[n][m];
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<m;j++) {
    			a[i][j] = nextInt();
    		}
    	}
    	return a;
    }

    public String[] nexts(int n) {
    	String[] a = new String[n];
    	for(int i=0;i<n;i++) {
    		a[i] = next();
    	}
    	return a;
    }
    void nextIntses(int n,int[] ...m) {
    	int l = m[0].length;
    	for(int i=0;i<l;i++) {
    		for(int j=0;j<m.length;j++) {
    			m[j][i] = nextInt();
    		}
    	}
    }
    void nextLongses(int n,long[] ...m) {
    	int l = m[0].length;
    	for(int i=0;i<l;i++) {
    		for(int j=0;j<m.length;j++) {
    			m[j][i] = nextLong();
    		}
    	}
    }

    Graph nextyukoGraph(int n,int m) {
    	Graph G = new Graph(n);
    	for(int i=0;i<m;i++) {
    		int a = nextInt()-1;
    		int b = nextInt()-1;
    		G.addEdge(a,b);
    	}
    	return G;
    }

    Graph nextGraph(int n,int m) {
    	Graph G = new Graph(n);
    	for(int i=0;i<m;i++) {
    		int a = nextInt()-1;
    		int b = nextInt()-1;
    		G.addEdge(a,b);
    		G.addEdge(b,a);
    	}
    	return G;
    }

    Graph nextWeightedGraph(int n,int m) {
    	Graph G = new Graph(n);
    	for(int i=0;i<m;i++) {
    		int a = nextInt()-1;
    		int b = nextInt()-1;
    		long c = nextLong();
    		G.addWeightedEdge(a,b,c);
    		G.addWeightedEdge(b,a,c);
    	}
    	return G;
    }
    Graph nextWeightedyukoGraph(int n,int m) {
    	Graph G = new Graph(n);
    	for(int i=0;i<m;i++) {
    		int a = nextInt()-1;
    		int b = nextInt()-1;
    		long c = nextLong();
    		G.addWeightedEdge(a,b,c);
    	}
    	return G;
    }

    Tree nextTree(int n) {
    	Tree T = new Tree(n);
    	for(int i=0;i<n-1;i++) {
    		int a = nextInt()-1;
    		int b = nextInt()-1;
    		T.addEdge(a,b);
    		T.addEdge(b,a);
    	}
    	return T;
    }
}


class Mathplus{
	long mod = 1000000007;
	long[] fac;
	long[] revfac;
	long[][] comb;
	long[] pow;
	long[] revpow;
	boolean isBuild = false;
	boolean isBuildc = false;
	
	boolean isBuildp = false;
	int mindex = -1;
	int maxdex = -1;
	int graydiff = 0;
	int graymark = 0;
	
	int[] ml_prs = {2,3,5,7,11,13,17,19,23,29,31,37};
	
	void printdecimal(double d,int k) {
		BigDecimal A = new BigDecimal(d);
		A = A.setScale(k,BigDecimal.ROUND_HALF_UP);
		String format = "%." + k + "f";
		System.out.printf(format,A);
	}
	

	public boolean isdistinct(int[] k) {
		HashSet<Integer> s = new HashSet<Integer>();
		for(int e:k)s.add(e);
		return k.length==s.size();
	}
	
	public void printtable(Object[] a) {
		for(int i=0;i<a.length-1;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println(a[a.length-1]);
		
	}

	public void printtable(int[] a) {
		for(int i=0;i<a.length-1;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println(a[a.length-1]);
		
	
		
	}

	int LIS(int N, int[] a) {
		int[] dp = new int[N+1];
		Arrays.fill(dp,(int)mod);
		for(int i=0;i<N;i++) {
			int ok = 0;
			int ng = N;
			while(ng-ok>1) {
				int mid = (ok+ng)/2;
				if(dp[mid]<a[i])ok = mid;
				else ng = mid;
			}
			dp[ok+1] = a[i];
		}
		int ok = 0;
		for(int i=1;i<=N;i++) {
			if(dp[i]<mod)ok=i;
		}
		return ok;
	}

	public Integer[] Ints(int n, int i) {
		Integer[] ret = new Integer[n];
		Arrays.fill(ret,i);
		return ret;
	}
	public Long[] Longs(int n, long i) {
		Long[] ret = new Long[n];
		Arrays.fill(ret,i);
		return ret;
	}

	public boolean nexperm(int[] p) {
		int n = p.length;
		for(int i=n-1;i>0;i--) {
			if(p[i-1]<p[i]) {
				int sw = n;
				for(int j=n-1;j>=i;j--) {
					if(p[i-1]<p[j]) {
						sw = j;
						break;
					}
				}

				int tmp = p[i-1];
				p[i-1] = p[sw];
				p[sw] = tmp;
				int[] r = new int[n];
				for(int j=i;j<n;j++) {
					r[j] = p[n-1-j+i];
				}
				for(int j=i;j<n;j++) {
					p[j] = r[j];
				}
				return true;
			}
		}
		return false;
	}
	public int[] shuffledperm(int n) {
		int[] ret = makeperm(n);
		Random rand = new Random();
		for(int i=n-1;i>0;i--) {
			int j = rand.nextInt(i+1);
			swap(ret,i,j);
		}
		return ret;
	}

	public void swap(int[] p, int i, int j) {
		if(i==j) return;
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}


	public int[] makeperm(int n) {
		int[] a = new int[n];
		for(int i=0;i<n;i++) {
			a[i] = i;
		}
		return a;
	}

	int color(int[][] diff,int N) {

		int[] val = new int[1<<N];
		val[0] = 1;
		for(int i=0;i<(1<<N);i++) {
			for(int j=0;j<N;j++) {
				if(contains(i,j)) {
					if(val[bitremove(i,j)]==1) {
						boolean b = true;
						for(int k=0;k<N;k++) {
							if(contains(i,k)&&diff[j][k]==1) {
								b = false;
							}
						}
						if(b)val[i] = 1;
					}
					break;
				}
			}
		}
		int[] dp = new int[1<<N];
		Arrays.fill(dp,N+1);;
		dp[0] = 0;
		for(int i=0;i<(1<<N);i++) {
			for(int j=i;j>0;j=(j-1)&i) {
				if(val[j]==1)dp[i]=Math.min(dp[i],dp[i^j]+1);
			}
		}
		return dp[(1<<N)-1];
	}



	public void timeout() throws InterruptedException {
		Thread.sleep(10000);
	}

	public int gray(int i,int m) {
		for(int j=0;j<m;j++) {
			if(contains(i,j)) {
				graydiff = j;
				if(contains(i,j+1))graymark=-1;
				else graymark = 1;
				break;
			}
		}
		return i ^ (i>>1);


	}

	public void hakidashi(long[] A) {
		Arrays.sort(A);
		int N = A.length;
		int[] index = new int[61];
		for(int i=0;i<=60;i++){
			index[i] = -1;
		}
		int searching = 60;
		int [] used = new int[N];
		while(searching>=0){
			boolean b = true;
			for(int i=N-1;i>=0;i--){
				for(int j=60;j>searching;j--){
					if((A[i]>>j&1)==1){
						if(i!=index[j]&&index[j]!=-1){
							A[i] ^= A[index[j]];
							//System.out.println(i+" changed by " + index[j]);
							//System.out.println(A[i]);
						}
					}
				}
				if((A[i]>>searching&1)==1&&used[i]==0){
					//System.out.println("find " + searching+" is "+i);
					index[searching] = i;
					searching--;
					used[i] = 1;
					b = false;
					if(searching==-1){
						searching = 0;
					}
				}
			}
			if(b){
				searching--;
			}

		}

		for(int i=N-1;i>=0;i--){
			for(int j=60;j>=searching;j--){
				if((A[i]>>j&1)==1){
					if(i!=index[j]&&index[j]!=-1){
						A[i] ^= A[index[j]];
					}
				}
			}
		}

		Arrays.sort(A);

	}

	public void printjudge(boolean b, String y, String n) {
		System.out.println(b?y:n);
	}
	public void printYN(boolean b) {
		printjudge(b,"Yes","No");
	}
	public void printyn(boolean b) {
		printjudge(b,"yes","no");
	}

	public void reverse(int[] x) {
		int[] r = new int[x.length];
		for(int i=0;i<x.length;i++)r[i] = x[x.length-1-i];
		for(int i=0;i<x.length;i++)x[i] = r[i];
	}
	public void reverse(long[] x) {
		long[] r = new long[x.length];
		for(int i=0;i<x.length;i++)r[i] = x[x.length-1-i];
		for(int i=0;i<x.length;i++)x[i] = r[i];
	}

	public DoubleTriplet Line(double x1,double y1,double x2,double y2) {
		double a = y1-y2;
		double b = x2-x1;
		double c = x1*y2-x2*y1;
		return new DoubleTriplet(a,b,c);
	}
	public double putx(DoubleTriplet T,double x) {
		return -(T.a*x+T.c)/T.b;
	}
	public double puty(DoubleTriplet T,double y) {
		return -(T.b*y+T.c)/T.a;
	}
	public double Distance(DoublePair P,DoublePair Q) {
		return Math.sqrt((P.a-Q.a) * (P.a-Q.a) + (P.b-Q.b) * (P.b-Q.b));
	}
	public double DistanceofPointandLine(DoublePair P,Triplet T) {
		return Math.abs(P.a*T.a+P.b*T.b+T.c) / Math.sqrt(T.a*T.a+T.b*T.b);
	}
	public boolean cross(long ax, long ay, long bx, long by, long cx, long cy, long dx, long dy) {
		if((ax-bx)*(cy-dy)==(ay-by)*(cx-dx)) {
			if(ax-bx!=0) {
				Range A = new Range(ax,bx);
				Range B = new Range(cx,dx);
				return A.kasanari(B)>0;
			}else {
				Range A = new Range(ay,by);
				Range B = new Range(cy,dy);
				return A.kasanari(B)>0;
			}
		}
		long ta = (cx - dx) * (ay - cy) + (cy - dy) * (cx - ax);
		long tb = (cx - dx) * (by - cy) + (cy - dy) * (cx - bx);
		long tc = (ax - bx) * (cy - ay) + (ay - by) * (ax - cx);
		long td = (ax - bx) * (dy - ay) + (ay - by) * (ax - dx);
		return((tc>=0&&td<=0)||(tc<=0&&td>=0))&&((ta>=0&&tb<=0)||(ta<=0&&tb>=0));
	}
	public boolean cross2(long ax, long ay, long bx, long by, long cx, long cy, long dx, long dy) {
		
		long ta = (cx - dx) * (ay - cy) + (cy - dy) * (cx - ax);
		long tb = (cx - dx) * (by - cy) + (cy - dy) * (cx - bx);
		long tc = (ax - bx) * (cy - ay) + (ay - by) * (ax - cx);
		long td = (ax - bx) * (dy - ay) + (ay - by) * (ax - dx);
		return((tc>0&&td<0)||(tc<0&&td>0))&&((ta>0&&tb<0)||(ta<0&&tb>0));
	}
	public boolean dcross(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy) {
		double ta = (cx - dx) * (ay - cy) + (cy - dy) * (cx - ax);
		double tb = (cx - dx) * (by - cy) + (cy - dy) * (cx - bx);
		double tc = (ax - bx) * (cy - ay) + (ay - by) * (ax - cx);
		double td = (ax - bx) * (dy - ay) + (ay - by) * (ax - dx);
		return((tc>=0&&td<=0)||(tc<=0&&td>=0))&&((ta>=0&&tb<=0)||(ta<=0&&tb>=0));
	}

	void buildFac(){
		fac = new long[10000003];
		revfac = new long[10000003];
		fac[0] = 1;
		for(int i=1;i<=10000002;i++){
			fac[i] = (fac[i-1] * i)%mod;
		}
		revfac[10000002] = rev(fac[10000002])%mod;
		for(int i=10000001;i>=0;i--) {
			revfac[i] = (revfac[i+1] * (i+1))%mod;
		}
		isBuild = true;
	}
	void buildFacn(int n){
		fac = new long[n+1];
		revfac = new long[n+1];
		fac[0] = 1;
		for(int i=1;i<=n;i++){
			fac[i] = (fac[i-1] * i)%mod;
		}
		revfac[n] = rev(fac[n])%mod;
		for(int i=n-1;i>=0;i--) {
			revfac[i] = (revfac[i+1] * (i+1))%mod;
		}
		isBuild = true;
	}
	public long[] buildrui(int[] a) {
		int n = a.length;
		long[] ans = new long[n];
		ans[0] = a[0];
		for(int i=1;i<n;i++) {
			ans[i] = ans[i-1] + a[i];
		}
		return ans;
	}
	public long[] buildrui(long[] a) {
		int n = a.length;
		long[] ans = new long[n];
		ans[0] = a[0];
		for(int i=1;i<n;i++) {
			ans[i] = ans[i-1] + a[i];
		}
		return ans;
	}
	public int[][] ibuildrui(int[][] a) {
		int n = a.length;
		int m = a[0].length;
		int[][] ans = new int[n][m];
		for(int i=1;i<n;i++) {
			for(int j=1;j<m;j++) {
				ans[i][j] = a[i][j];
			}
		}
		for(int i=1;i<n;i++) {
			for(int j=1;j<m;j++) {
				ans[i][j] += ans[i][j-1] + ans[i-1][j] - ans[i-1][j-1];
			}
		}
		return ans;
	}
	public void buildruin(int[][] a) {
		int n = a.length;
		int m = a[0].length;
		for(int i=1;i<n;i++) {
			for(int j=1;j<m;j++) {
				a[i][j] += a[i][j-1] + a[i-1][j] - a[i-1][j-1];
			}
		}
	}
	public long[][] buildrui(int[][] a) {
		int n = a.length;
		int m = a[0].length;
		long[][] ans = new long[n][m];
		for(int i=1;i<n;i++) {
			for(int j=1;j<m;j++) {
				ans[i][j] = a[i][j];
			}
		}
		for(int i=1;i<n;i++) {
			for(int j=1;j<m;j++) {
				ans[i][j] += ans[i][j-1] + ans[i-1][j] - ans[i-1][j-1];
			}
		}
		return ans;
	}
	public int getrui(int[][] r,int a,int b,int c,int d) {
		return r[c][d] - r[a-1][d] - r[c][b-1] + r[a-1][b-1];
	}
	public long getrui(long[][] r,int a,int b,int c,int d) {
		if(a<0||b<0||c>=r.length||d>=r[0].length) return mod;
		return r[c][d] - r[a-1][d] - r[c][b-1] + r[a-1][b-1];
	}

	long divroundup(long n,long d) {
		if(n==0)return 0;
		return (n-1)/d+1;
	}
	public long sigma(long i) {
		return i*(i+1)/2;
	}
	public int digit(long i) {
		int ans = 1;
		while(i>=10) {
			i /= 10;
			ans++;
		}
		return ans;

	}
	public int digitsum(long n) {
		int ans = 0;
		while(n>0) {
			ans += n%10;
			n /= 10;
		}
		return ans;
	}
	public int popcount(int i) {
		int ans = 0;
		while(i>0) {
			ans += i%2;
			i /= 2;
		}
		return ans;
	}
	public int popcount(long i) {
		int ans = 0;
		while(i>0) {
			ans += i%2;
			i /= 2;
		}
		return ans;
	}
	public boolean contains(int S,int i) {return (S>>i&1)==1;}
	public int bitremove(int S,int i) {return S&(~(1<<i));}
	public int bitadd(int S,int i) {return S|(1<<i);}
	public boolean isSubSet(int S,int T) {return (S-T)==(S^T);}
	public boolean isDisjoint(int S,int T) {return (S+T)==(S^T);}
	public boolean contains(long S,int i) {return (S>>i&1)==1;}
	public long bitremove(long S,int i) {return S&(~(1<<i));}
	public long bitadd(long S,int i) {return S|(1<<i);}
	public boolean isSubSet(long S,long T) {return (S-T)==(S^T);}
	public boolean isDisjoint(long S,long T) {return (S+T)==(S^T);}
	public int isBigger(int[] d, int i) {
		int ok = d.length;
		int ng = -1;
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d[mid]>i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isSmaller(int[] d, int i) {
		int ok = -1;
		int ng = d.length;
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d[mid]<i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isBigger(long[] d, long i) {
		int ok = d.length;
		int ng = -1;
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d[mid]>i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isSmaller(long[] d, long i) {
		int ok = -1;
		int ng = d.length;
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d[mid]<i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isBigger(ArrayList<Integer> d, int i) {
		int ok = d.size();
		int ng = -1;
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d.get(mid)>i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isSmaller(ArrayList<Integer> d, int i) {
		int ok = -1;
		int ng = d.size();
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d.get(mid)<i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isBigger(ArrayList<Long> d, long i) {
		int ok = d.size();
		int ng = -1;
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d.get(mid)>i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public int isSmaller(ArrayList<Long> d, long i) {
		int ok = -1;
		int ng = d.size();
		while(Math.abs(ok-ng)>1) {
			int mid = (ok+ng)/2;
			if(d.get(mid)<i) {
				ok = mid;
			}else {
				ng = mid;
			}
		}
		return ok;
	}
	public HashSet<Integer> primetable(int m) {
		HashSet<Integer> pt = new HashSet<Integer>();
		for(int i=2;i<=m;i++) {
			boolean b = true;
			for(int d:pt) {
				if(i%d==0) {
					b = false;
					break;
				}
			}
			if(b) {
				pt.add(i);
			}
		}
		return pt;
	}
	public ArrayList<Integer> primetablearray(int m) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i=2;i<=m;i++) {
			q.add(i);

		}
		boolean[] b = new boolean[m+1];
		while(!q.isEmpty()) {
			int e = q.poll();
			if(!b[e]) {
				al.add(e);
				for(int j=1;e*j<=m;j++) {
					b[e*j] = true;
				}
			}
		}
		return al;
	}

	public boolean isprime(int e) {
		if(e==1) return false;
		for(int i=2;i*i<=e;i++) {
			if(e%i==0) return false;
		}
		return true;
	}
	public boolean  isprime_ml(long n) {
		if(n>2&&n%2==0) return false;
		
		for(int a:ml_prs) {
			if(a>=n) continue;
			long nn = n-1;
			int s = 0;
			while(nn%2==0) {
				s++;
				nn /= 2;
			}
			long w = pow_mod(a,nn,n);
			for(int i=0;i<s;i++) {
				long ww = w * w % n;
				if(ww==1) {
					if(w!=1 && w!=n-1) return false;
				}
				w = ww;
			}
			if(w!=1) return false;
		}
		return true;
	}
	
	public long factor_pp(long e) {
		if(e==1 || isprime_ml(e)) return e;
		else {
			while(true) {
				long a = 2;
				long b = 2;
				long d = 1;
				long rd1 = new Random().nextInt(100000)%e;
				long rd2 = new Random().nextInt(100000)%e;
				while(d==1) {
					a = (rd1 * a + rd2) % e;
					b = (rd1 * b + rd2) % e;
					b = (rd1 * b + rd2) % e;
					d = gcd(Math.abs(a-b),e);
				}
				if(d!=e) return factor_pp(d);
			}
			
		}
	}
	public MultiSetL factorization(long e) {
		MultiSetL ret = new MultiSetL();
		for(long i=2;i*i<=e;i++) {
			while(e%i==0) {
				ret.add(i);
				e /= i;
			}
		}
		if(e!=1)ret.add(e);
		return ret;
	}
	


	public int[] hipPush(int[] a){
		int[] r = new int[a.length];
		int[] s = new int[a.length];
		for(int i=0;i<a.length;i++) {
			s[i] = a[i];
		}
		Arrays.sort(s);
		HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
		for(int i=0;i<a.length;i++) {
			if(!m.containsKey(s[i]))m.put(s[i],m.size());
		}
		for(int i=0;i<a.length;i++) {
			r[i] = m.get(a[i]);
		}
		return r;
	}
	public HashMap<Integer,Integer> hipPush(ArrayList<Integer> l){
		HashMap<Integer,Integer> r = new HashMap<Integer,Integer>();
		TreeSet<Integer> s = new TreeSet<Integer>();
		for(int e:l)s.add(e);
		int p = 0;
		for(int e:s) {
			r.put(e,p);
			p++;
		}
		return r;
	}
	public TreeMap<Integer,Integer> thipPush(ArrayList<Integer> l){
		TreeMap<Integer,Integer> r = new TreeMap<Integer,Integer>();
		Collections.sort(l);
		int b = -(1000000007+9393);
		int p = 0;
		for(int e:l) {
			if(b!=e) {
				r.put(e,p);
				p++;
			}
			b=e;
		}
		return r;
	}
	int[] count(int[] a) {
		int[] c = new int[max(a)+1];
		for(int i=0;i<a.length;i++) {
			c[a[i]]++;
		}
		return c;
	}
	int[] count(int[] a, int m) {
		int[] c = new int[m+1];
		for(int i=0;i<a.length;i++) {
			c[a[i]]++;
		}
		return c;
	}
	long max(long[] a){
		long M = Long.MIN_VALUE;
		for(int i=0;i<a.length;i++){
			if(M<=a[i]){
				M =a[i];
				maxdex = i;
			}
		}
		return M;
	}
	int max(int[] a){
		int M = Integer.MIN_VALUE;
		for(int i=0;i<a.length;i++){
			if(M<=a[i]){
				M =a[i];
				maxdex = i;
			}
		}
		return M;
	}
	long min(long[] a){
		long m = Long.MAX_VALUE;
		for(int i=0;i<a.length;i++){
			if(m>a[i]){
				m =a[i];
				mindex = i;
			}
		}
		return m;
	}
	int min(int[] a){
		int m = Integer.MAX_VALUE;
		for(int i=0;i<a.length;i++){
			if(m>a[i]){
				m =a[i];
				mindex = i;
			}
		}
		return m;
	}
	long sum(long[] a){
		long s = 0;
		for(int i=0;i<a.length;i++)s += a[i];
		return s;
	}
	long sum(int[] a){
		long s = 0;
		for(int i=0;i<a.length;i++)s += a[i];
		return s;
	}
	long  sum(ArrayList<Integer> l) {
		long s = 0;
		for(int e:l)s += e;
		return s;
	}
	long gcd(long a, long b){
		a = Math.abs(a);
		b = Math.abs(b);
		if(a==0)return b;
		if(b==0)return a;
		if(a%b==0) return b;
		else return gcd(b,a%b);
	}
	long gcd(long [] a){
		long g = a[0];
		for(int i=1;i<a.length;i++) {
			g = gcd(g,a[i]);
		}
		return g;
	}
	int igcd(int a, int b) {
		if(a%b==0) return b;
		else return igcd(b,a%b);
	}
	long lcm(long a, long b) {return a / gcd(a,b) * b;}
	public long perm(int a,int num) {
		if(!isBuild)buildFac();
		return fac[a]*(rev(fac[a-num]))%mod;
	}
	void buildComb(int N) {
		comb = new long[N+1][N+1];
		comb[0][0] = 1;
		for(int i=1;i<=N;i++) {
			comb[i][0] = 1;
			for(int j=1;j<N;j++) {
				comb[i][j] = comb[i-1][j-1]+comb[i-1][j];
				if(comb[i][j]>mod)comb[i][j]-=mod;
			}
			comb[i][i] = 1;
		}
	}
	public long comb(int a,int num){
		if(a-num<0)return 0;
		if(num<0)return 0;
		if(!isBuild)buildFac();
		if(a>10000000) return combN(a,num);
		return fac[a] * ((revfac[num]*revfac[a-num])%mod)%mod;
	}
	long combN(int a,int num) {
		long ans = 1;
		for(int i=0;i<num;i++) {
			ans *= a-i;
			ans %= mod;
		}
		return ans * revfac[num] % mod;
	}
	long mulchoose(int n,int k) {
		if(k==0) return 1;
		return comb(n+k-1,k);
	}
	long rev(long l) {return pow(l,mod-2);}

	void buildpow(int l,int i) {
		pow = new long[i+1];
		pow[0] = 1;
		for(int j=1;j<=i;j++) {
			pow[j] = pow[j-1]*l;
			if(pow[j]>mod)pow[j] %= mod;
		}
	}
	void buildrevpow(int l,int i) {
		revpow = new long[i+1];
		revpow[0] = 1;
		long r = rev(l);
		for(int j=1;j<=i;j++) {
			revpow[j] = revpow[j-1]*r;
			if(revpow[j]>mod) revpow[j] %= mod;
		}
	}
	long pow(long l, long i) {
		
		if(i==0)return 1;
		else{
			if(i%2==0){
				long val = pow(l,i/2);
				return val * val % mod;
			}
			else return pow(l,i-1) * l % mod;
		}
	}
	
	long pow_mod(long l, long i, long m) {
		if(i==0)return 1;
		else{
			if(i%2==0){
				long val = pow_mod(l,i/2,m);
				return val * val % m;
			}
			else return pow_mod(l,i-1,m) * l % m;
		}
	}
	long mon(int i) {
		long ans = 0;
		for(int k=2;k<=i;k++) {
			ans += (k%2==0?1:-1) * revfac[k];
			ans += mod;
		}
		ans %= mod;
		ans *= fac[i];
		return ans%mod;
	}
	long tent(int[] a, int l, int r) {
		if(l+1==r) return 0;
		int mid = (l+r)/2;
		long ans = tent(a,l,mid)+tent(a,mid,r);
		int[] L = new int[mid-l];
		int[] R = new int[r-mid];
		for(int i=l;i<mid;i++) {
			L[i-l] = a[i];
		}
		for(int i=mid;i<r;i++) {
			R[i-mid] = a[i];
		}
		Arrays.sort(L);
		Arrays.sort(R);
		int X = L.length;
		int Y = R.length;
		int k = -1;
		for(int i=0;i<X;i++) {
			while(k!=Y-1&&R[k+1]<L[i])k++;
			ans += k+1;
		}
		return ans;
		
	}

	long dictnum(int[] A) {
		int N = A.length;
		long ans = 0;
		BinaryIndexedTree bit = new BinaryIndexedTree(N+1);
		buildFacn(N);
		for(int i=1;i<=N;i++) {
			bit.add(i,1);
		}
		for(int i=1;i<=N;i++) {
			int a = A[i-1];
			ans += bit.sum(a-1) * fac[N-i] % mod;
			bit.add(a,-1);
		}
		return (ans+1)%mod;
	}

}

