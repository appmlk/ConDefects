//import java.util.ArrayList;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//double x = sc.nextDouble();
		int n = sc.nextInt();
		//int m = sc.nextInt();
		//int X = sc.nextInt();
		//int Y = sc.nextInt();

		//long h = sc.nextLong();

		//String s = sc.next();
		//String t = sc.next();
		//int L = s.length();
		//String ans = "";

		//String [] s = new String[10];
		//int [] L = new int[n];

		//Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
		//List<Integer> A = new ArrayList<>();
		//Set<String> s = Set.of("H", "2B", "3B", "HR");	
		//Set<Integer> E = new HashSet<>();
		//Set<Integer> L = new HashSet<>();

		//String S = Long.toBinaryString(M);
		//int N = (int)Math.ceil(x);

		//double [] h = new double[n];	
		int ans = 0;

		for (int i=1; i<=n; i++) {
			int d = sc.nextInt();
			if (i<10 || i==11 || i==22 || i==33 || i==44 || i== 55 || i==66 || i==77 || i==88 || i==99) {
				int r = i%10;
				if (d >= r && d < r+r*10) {
					ans++;
				} else if (r+r*10 <= d) {
					ans += 2;
				}
			}
		}
		
		System.out.println(ans);
		sc.close();
		//System.out.println("Yes");
		//System.out.println("No");
		//System.exit(0);
	}

	//Math.floor(1.1); //→1 切り捨て
	//Math.ceil(1.1); //→2 切り上げ
	//Math.round(1.5); //→2 四捨五入

	public static int max(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static int mi(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}

	public static long tenjo(long a) {
		return (long)Math.floor(a/2.0);
	}

	public static int dis(int [] a, int [] b) {
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
	}

	public static String sub(String S, int i, int j) {
		return S.substring(i,j);
	}

	public static String rep(String S, int N) {//SをN個連結
		return String.join("", 
				Stream
				.generate(() -> S) //Sを無限生成
				.limit(N) //上限を指定
				.collect(Collectors.toList()) //Listに変換し, joinの第2引数に渡す
				);
	}

	public static String abc(String S) {//辞書式で最も早いものに並べ替える

		Map<String, Integer> map = new TreeMap<>();

		for (int i=0; i<S.length(); i++) {
			String s = S.substring(i,i+1);
			if (map.containsKey(s)) {
				map.put(s, map.get(s)+1);
			} else {
				map.put(s, 1);
			}
		}
		String T = "";
		for (String a : map.keySet()) {
			T = T + rep(a, map.get(a));			
		}
		return T;
	}

	public static String cba(String S) {//辞書式で最も後ろのものに並べ替える

		Map<String, Integer> map = new TreeMap<>();

		for (int i=0; i<S.length(); i++) {
			String s = S.substring(i,i+1);
			if (map.containsKey(s)) {
				map.put(s, map.get(s)+1);
			} else {
				map.put(s, 1);
			}
		}
		String T = "";
		for (String a : map.keySet()) {
			T = rep(a, map.get(a)) + T;			
		}
		return T;
	}

	public static String rev(String S) {//文字列を逆に並べ替える
		StringBuilder A = new StringBuilder(S);
		return A.reverse().toString();
	}

}