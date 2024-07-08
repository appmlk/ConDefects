import java.io.Writer;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.math.BigInteger;
import java.awt.Point;
import java.awt.Dimension;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

final class Main {

	private static final boolean autoFlush = false;
	private static final SimpleScanner sc = new SimpleScanner( System.in );
	private static final SimpleWriter out = new SimpleWriter( System.out, autoFlush );

	public static void main ( String[] args ) {

		int N = sc.nextInt();
		int K = sc.nextInt();
		int P = sc.nextInt();
		int len = (int)MathFunction.pow(P+1,K);
		long max = Long.MAX_VALUE>>1;
		long[] dp = new long[len];
		long[] next = new long[len];
		Arrays.fill(dp,max);
		dp[0] = 0;
		IntegerSet set = new IntegerSet();
		set.add(0);
		while(N-->0){
			int C = sc.nextInt();
			int[] A = sc.nextInt(K);
			Arrays.fill(next,max);
			for(int num:set.toArray()){
				int nextNum = 0;
				int mult = 1;
				int bef = num;
				for(int i=K-1;i>=0;i--){
					int m = num%(P+1);
					num /= P+1;
					nextNum += mult*Math.min(P,m+A[i]);
					mult *= P+1;
				}
				set.add(nextNum);
				next[nextNum] = Math.min(dp[nextNum],dp[bef]+C);
				next[bef] = Math.min(next[bef],dp[bef]);
			}
			var temp = dp;
			dp = next;
			next = temp;
		}
		out.println(dp[len-1]==max?-1:dp[len-1]);

		out.close();
	}
}
/*


             ／￣＼
            |     |
             ＼＿／
               |
          ／ ￣  ￣  ＼
        ／    ＼  ／    ＼
     ／      ⌒    ⌒      ＼      よくぞこの提出結果を開いてくれた
     |      （__人__）      |      褒美としてオプーナを買う権利をやる
     ＼       ｀⌒´       ／   ☆
      /ヽ､--ー､＿＿, -‐ ´  ＼─／
    ／ >     ヽ▼●▼<＼       ||ｰ､.
   /ヽ､    ＼ i |｡| |/  ヽ (ニ､`ヽ.
  l    ヽ     l |｡| | r-､y ｀ﾆ  ﾉ ＼
  l     |     |ー─ | ￣ l   ｀~ヽ＿ノ＿_
      ／￣￣￣￣ヽ-'ヽ--'  ／ オープナ  ／|
     |￣￣￣￣￣￣|／|    |￣￣￣￣￣￣|／| ＿＿＿＿＿＿
 ／￣オプーナ／|￣|__｣／_オープナ ／|￣|__,」＿_      ／|
|￣￣￣￣￣|／オープナ￣／￣￣￣￣|／オプーナ ／|   ／  |
|￣￣￣￣￣|￣￣￣￣￣|／l￣￣￣￣|￣￣￣￣￣|／| ／
|￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|
*/
/*////////////////////////////////////////////////
	* My Library *

	@author viral
*/////////////////////////////////////////////////
final class Factorial {

	//階乗とその逆元
	private final long[] fact, inFact;
	private final long mod;

	/**
	 * 1～Nの階乗とその逆元をmodで割ったあまりを事前に計算します。
	 *
	 * @param N 計算範囲
	 * @param mod 法
	 */
	public Factorial ( final int N, final long mod ) {
		fact = new long[N + 1];
		fact[0] = fact[1] = 1;
		for ( int i = 2; i <= N; ++i ) {
			fact[i] = fact[i - 1] * i % mod;
		}

		inFact = new long[N + 1];
		inFact[N] = MathFunction.modPow( fact[N], mod - 2, mod );
		for ( int i = N; i > 0; --i ) {
			inFact[i - 1] = inFact[i] * i % mod;
		}
		inFact[0] = 1;

		this.mod = mod;
	}

	/**
	 * num!をmodで割ったあまりを返します。
	 *
	 * @param num
	 *
	 * @return num!
	 */
	public long getFact ( final int num ) {
		return fact[num];
	}

	/**
	 * num!^-1をmodで割ったあまりを返します。
	 *
	 * @param num
	 *
	 * @return num!
	 */
	public long getInFact ( final int num ) {
		return inFact[num];
	}

	/**
	 * aCbをmodで割ったあまりを返します。
	 *
	 * @param a
	 * @param b
	 *
	 * @return aCb
	 */
	public long getCombi ( final int a, final int b ) {
		if ( a < b || a < 0 || b < 0 ) {
			return 0;
		}
		return ( fact[a] * inFact[a - b] % mod ) * inFact[b] % mod;
	}
}

final class ArrayFunction {

	/**
	 * カウントソートによるソートです。
	 * 各要素が0以上であり最大値が十分小さい時はこちらの使用を推奨します。
	 *
	 * @param array ソート対象のint型配列
	 * @param maximumLimit array内の最大要素
	 */
	public static void countSort ( final int[] array, final int maximumLimit ) {
		final int[] list = new int[maximumLimit + 1];
		for ( final int num: array ) {
			++list[num];
		}
		int temp = 0;
		for ( int i = 0; i < list.length; ++i ) {
			while ( list[i]-- > 0 ) {
				array[temp++] = i;
			}
		}
	}

	/**
	 * 配列を右周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static int[][] rotateR ( final int[][] array ) {
		final int[][] ans = new int[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[ans[i].length - j - 1][i];
			}
		}
		return ans;
	}

	/**
	 * 配列を右周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static long[][] rotateR ( final long[][] array ) {
		final long[][] ans = new long[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[ans[i].length - j - 1][i];
			}
		}
		return ans;
	}

	/**
	 * 配列を右周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static char[][] rotateR ( final char[][] array ) {
		final char[][] ans = new char[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[ans[i].length - j - 1][i];
			}
		}
		return ans;
	}

	/**
	 * 配列を右周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static double[][] rotateR ( final double[][] array ) {
		final double[][] ans = new double[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[ans[i].length - j - 1][i];
			}
		}
		return ans;
	}

	/**
	 * 配列を右周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static boolean[][] rotateR ( final boolean[][] array ) {
		final boolean[][] ans = new boolean[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[ans[i].length - j - 1][i];
			}
		}
		return ans;
	}

	/**
	 * 配列を右周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static <E> E[][] rotateR ( final E[][] array, final E[][] ans ) {
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[ans[i].length - j - 1][i];
			}
		}
		return ans;
	}

	/**
	 * 配列を左周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static int[][] rotateL ( final int[][] array ) {
		final int[][] ans = new int[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			final int index = i;
			Arrays.setAll( ans[i], k -> array[k][ans.length - index - 1] );
		}
		return ans;
	}

	/**
	 * 配列を左周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static long[][] rotateL ( final long[][] array ) {
		final long[][] ans = new long[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			final int index = i;
			Arrays.setAll( ans[i], k -> array[k][ans.length - index - 1] );
		}
		return ans;
	}

	/**
	 * 配列を左周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static char[][] rotateL ( final char[][] array ) {
		final char[][] ans = new char[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[j][ans.length - i - 1];
			}
		}
		return ans;
	}

	/**
	 * 配列を左周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static double[][] rotateL ( final double[][] array ) {
		final double[][] ans = new double[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[j][ans.length - i - 1];
			}
		}
		return ans;
	}

	/**
	 * 配列を左周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static boolean[][] rotateL ( final boolean[][] array ) {
		final boolean[][] ans = new boolean[array[0].length][array.length];
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[j][ans.length - i - 1];
			}
		}
		return ans;
	}

	/**
	 * 配列を左周りに90度回転させたものを返します。
	 * 長方形でない配列に関しての動作は保証していません。
	 *
	 * @param array 回転させる行列
	 *
	 * @return 回転させた配列
	 */
	public static <E> E[][] rotateL ( final E[][] array, final E[][] ans ) {
		for ( int i = 0; i < ans.length; ++i ) {
			for ( int j = 0; j < ans[i].length; ++j ) {
				ans[i][j] = array[j][ans.length - i - 1];
			}
		}
		return ans;
	}

	/**
	 * 引数の配列の最長狭義増加部分列の長さを返します。
	 *
	 * @param array 最長狭義増加部分列の長さを求める配列
	 *
	 * @return 最長狭義増加部分列の長さ
	 */
	public static int lis ( int[] array ) {
		return lis( array, false );
	}

	/**
	 * 引数の配列指定されたインデックスの最長狭義増加部分列の長さを返します。
	 *
	 * @param arrays 最長狭義増加部分列の長さを求める配列
	 * @param p 探索する配列のインデックス
	 *
	 * @return arrays[i][p](0 < = p < = arrays.length)の最長狭義増加部分列の長さ
	 */
	public static int lis ( int[][] arrays, int p ) {
		return lis( arrays, p, false );
	}

	/**
	 * 引数の配列の最長狭義増加部分列の長さを返します。
	 *
	 * @param array 最長狭義増加部分列の長さを求める配列
	 *
	 * @return 最長狭義増加部分列の長さ
	 */
	public static int lis ( long[] array ) {
		return lis( array, false );
	}

	/**
	 * 引数の配列指定されたインデックスの最長狭義増加部分列の長さを返します。
	 *
	 * @param arrays 最長狭義増加部分列の長さを求める配列
	 * @param p 探索する配列のインデックス
	 *
	 * @return arrays[i][p](0 < = p < = arrays.length)の最長狭義増加部分列の長さ
	 */
	public static int lis ( long[][] arrays, int p ) {
		return lis( arrays, p, false );
	}

	/**
	 * 引数の配列の最長増加部分列の長さを返します。
	 *
	 * @param array 最長増加部分列の長さを求める配列
	 * @param include 広義増加列ならtrue、狭義増加列ならfalse
	 *
	 * @return 最長狭義増加部分列の長さ
	 */
	public static int lis ( int[] array, boolean include ) {
		int[] list = new int[array.length];
		Arrays.fill( list, Integer.MAX_VALUE );
		for ( int num: array ) {
			int index = include ? Searcher.overSearch( list, num ) : Searcher.upSearch( list, num );
			list[index] = Math.min( list[index], num );
		}
		int answer = Searcher.underSearch( list, Integer.MAX_VALUE );
		return answer + 1;
	}

	/**
	 * 引数の配列指定されたインデックスの最長狭義増加部分列の長さを返します。
	 *
	 * @param arrays 最長狭義増加部分列の長さを求める配列
	 * @param p 探索する配列のインデックス
	 * @param include 広義増加列ならtrue、狭義増加列ならfalse
	 *
	 * @return arrays[i][p](0 < = p < = arrays.length)の最長狭義増加部分列の長さ
	 */
	public static int lis ( int[][] arrays, int p, boolean include ) {
		int[] list = new int[arrays.length];
		Arrays.fill( list, Integer.MAX_VALUE );
		for ( int[] array: arrays ) {
			int index = include ? Searcher.overSearch( list, array[p] ) : Searcher.upSearch( list, array[p] );
			list[index] = Math.min( list[index], array[p] );
		}
		int answer = Searcher.underSearch( list, Integer.MAX_VALUE );
		return answer + 1;
	}

	/**
	 * 引数の配列の最長増加部分列の長さを返します。
	 *
	 * @param array 最長増加部分列の長さを求める配列
	 * @param include 広義増加列ならtrue、狭義増加列ならfalse
	 *
	 * @return 最長狭義増加部分列の長さ
	 */
	public static int lis ( long[] array, boolean include ) {
		long[] list = new long[array.length];
		Arrays.fill( list, Long.MAX_VALUE );
		for ( long num: array ) {
			int index = include ? Searcher.overSearch( list, num ) : Searcher.upSearch( list, num );
			list[index] = Math.min( list[index], num );
		}
		int answer = Searcher.underSearch( list, Long.MAX_VALUE );
		return answer + 1;
	}

	/**
	 * 引数の配列指定されたインデックスの最長狭義増加部分列の長さを返します。
	 *
	 * @param arrays 最長狭義増加部分列の長さを求める配列
	 * @param p 探索する配列のインデックス
	 * @param include 広義増加列ならtrue、狭義増加列ならfalse
	 *
	 * @return arrays[i][p](0 < = p < = arrays.length)の最長狭義増加部分列の長さ
	 */
	public static int lis ( long[][] arrays, int p, boolean include ) {
		long[] list = new long[arrays.length];
		Arrays.fill( list, Long.MAX_VALUE );
		for ( long[] array: arrays ) {
			int index = include ? Searcher.overSearch( list, array[p] ) : Searcher.upSearch( list, array[p] );
			list[index] = Math.min( list[index], array[p] );
		}
		int answer = Searcher.underSearch( list, Long.MAX_VALUE );
		return answer + 1;
	}

	/**
	 * 引数の情報から求められる有向辺に対してトポロジカルソートを行ないます。
	 * 戻り値は辺を表すint型二次元配列です。
	 *
	 * @param route 有向グラフの隣接リスト
	 *
	 * @return トポロジカルソート済み有向グラフ
	 */
	public static int[][] topologicalSort ( final ArrayList<ArrayList<Integer>> route ) {
		final int[] count = new int[route.size()];
		int pathCount = 0;
		for ( final ArrayList<Integer> path: route ) {
			for ( final int point: path ) {
				++pathCount;
				++count[point];
			}
		}
		final ArrayDeque<Integer> deq = new ArrayDeque<>();
		for ( int i = 1; i < count.length; ++i ) {
			if ( count[i] == 0 ) {
				deq.add( i );
			}
		}
		final int[][] ans = new int[pathCount][2];
		int index = 0;
		while ( deq.size() > 0 ) {
			int nowP = deq.pollFirst();
			for ( final int nextP: route.get( nowP ) ) {
				ans[index][0] = nowP;
				ans[index++][1] = nextP;
				if ( --count[nextP] == 0 ) {
					deq.add( nextP );
				}
			}
		}
		return ans;
	}

	/**
	 * 引数の情報から求められる有向辺に対してトポロジカルソートを行ないます。
	 * 戻り値は辺を表すint型二次元配列です。
	 *
	 * @param route 有向グラフの隣接リスト
	 *
	 * @return トポロジカルソート済み有向グラフ
	 */
	public static int[][] topologicalSort ( final int[][] route ) {
		final int[] count = new int[route.length];
		int pathCount = 0;
		for ( final int[] path: route ) {
			for ( final int point: path ) {
				++pathCount;
				++count[point];
			}
		}
		final ArrayDeque<Integer> deq = new ArrayDeque<>();
		for ( int i = 1; i < count.length; ++i ) {
			if ( count[i] == 0 ) {
				deq.add( i );
			}
		}
		final int[][] ans = new int[pathCount][2];
		int index = 0;
		while ( deq.size() > 0 ) {
			int nowP = deq.pollFirst();
			for ( final int nextP: route[nowP] ) {
				ans[index][0] = nowP;
				ans[index++][1] = nextP;
				if ( --count[nextP] == 0 ) {
					deq.add( nextP );
				}
			}
		}
		return ans;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元
	 * @param b 交換先
	 */
	public static void swap ( final int[] array, final int a, final int b ) {
		final int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元
	 * @param b 交換先
	 */
	public static void swap ( final long[] array, final int a, final int b ) {
		final long temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元
	 * @param b 交換先
	 */
	public static void swap ( final double[] array, final int a, final int b ) {
		final double temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元
	 * @param b 交換先
	 */
	public static void swap ( final char[] array, final int a, final int b ) {
		final char temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元
	 * @param b 交換先
	 */
	public static void swap ( final boolean[] array, final int a, final int b ) {
		final boolean temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元
	 * @param b 交換先
	 */
	public static <E> void swap ( final E[] array, final int a, final int b ) {
		final E temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元の第1インデックス
	 * @param b 交換元の第2インデックス
	 * @param c 交換先の第1インデックス
	 * @param d 交換先の第2インデックス
	 */
	public static void swap ( final int[][] array, final int a, final int b, final int c, final int d ) {
		final int temp = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元の第1インデックス
	 * @param b 交換元の第2インデックス
	 * @param c 交換先の第1インデックス
	 * @param d 交換先の第2インデックス
	 */
	public static void swap ( final long[][] array, final int a, final int b, final int c, final int d ) {
		final long temp = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元の第1インデックス
	 * @param b 交換元の第2インデックス
	 * @param c 交換先の第1インデックス
	 * @param d 交換先の第2インデックス
	 */
	public static void swap ( final double[][] array, final int a, final int b, final int c, final int d ) {
		final double temp = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元の第1インデックス
	 * @param b 交換元の第2インデックス
	 * @param c 交換先の第1インデックス
	 * @param d 交換先の第2インデックス
	 */
	public static void swap ( final char[][] array, final int a, final int b, final int c, final int d ) {
		final char temp = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元の第1インデックス
	 * @param b 交換元の第2インデックス
	 * @param c 交換先の第1インデックス
	 * @param d 交換先の第2インデックス
	 */
	public static void swap ( final boolean[][] array, final int a, final int b, final int c, final int d ) {
		final boolean temp = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = temp;
	}

	/**
	 * 引数の位置の要素を交換します。
	 *
	 * @param array 交換する要素の含まれる配列
	 * @param a 交換元の第1インデックス
	 * @param b 交換元の第2インデックス
	 * @param c 交換先の第1インデックス
	 * @param d 交換先の第2インデックス
	 */
	public static <E> void swap ( final E[][] array, final int a, final int b, final int c, final int d ) {
		final E temp = array[a][b];
		array[a][b] = array[c][d];
		array[c][d] = temp;
	}

	/**
	 * 引数の配列の要素を並び替えた時の、辞書順で引数の次に当たる順番に並び替えます。
	 * 原則として、要素に重複が無いものとして処理します。重複がある場合の戻り値は保証されていません。
	 *
	 * @param array 並び替え対象の配列
	 *
	 * @return 辞書順で次に当たる配列がある場合はtrue、arrayが降順に並んでいるならfalse
	 */
	public static boolean nextPermutation ( final int[] array ) {
		int index1 = 0;
		for ( int i = 1; i < array.length; ++i ) {
			if ( array[i - 1] < array[i] ) {
				index1 = i;
			}
		}
		if ( --index1 < 0 ) {
			return false;
		}
		int index2 = 0;
		int min = Integer.MAX_VALUE;
		for ( int i = index1 + 1; i < array.length; ++i ) {
			if ( MathFunction.rangeCheckOpen( array[i], array[index1], min ) ) {
				min = array[i];
				index2 = i;
			}
		}
		swap( array, index1, index2 );
		Arrays.sort( array, index1 + 1, array.length );
		return true;
	}

	/**
	 * 引数の配列の要素を並び替えた時の、辞書順で引数の次に当たる順番に並び替えます。
	 * 原則として、要素に重複が無いものとして処理します。重複がある場合の戻り値は保証されていません。
	 *
	 * @param array 並び替え対象の配列
	 *
	 * @return 辞書順で次に当たる配列がある場合はtrue、arrayが降順に並んでいるならfalse
	 */
	public static boolean nextPermutation ( final long[] array ) {
		int index1 = 0;
		for ( int i = 1; i < array.length; ++i ) {
			if ( array[i - 1] < array[i] ) {
				index1 = i;
			}
		}
		if ( --index1 < 0 ) {
			return false;
		}
		int index2 = 0;
		long min = Long.MAX_VALUE;
		for ( int i = index1 + 1; i < array.length; ++i ) {
			if ( MathFunction.rangeCheckOpen( array[i], array[index1], min ) ) {
				min = array[i];
				index2 = i;
			}
		}
		swap( array, index1, index2 );
		Arrays.sort( array, index1 + 1, array.length );
		return true;
	}

	/**
	 * 引数の配列の要素を並び替えた時の、辞書順で引数の次に当たる順番に並び替えます。
	 * 原則として、要素に重複が無いものとして処理します。重複がある場合の戻り値は保証されていません。
	 *
	 * @param array 並び替え対象の配列
	 *
	 * @return 辞書順で次に当たる配列がある場合はtrue、arrayが降順に並んでいるならfalse
	 */
	public static boolean nextPermutation ( final char[] array ) {
		int index1 = 0;
		for ( int i = 1; i < array.length; ++i ) {
			if ( array[i - 1] < array[i] ) {
				index1 = i;
			}
		}
		if ( --index1 < 0 ) {
			return false;
		}
		int index2 = 0;
		int min = Integer.MAX_VALUE;
		for ( int i = index1 + 1; i < array.length; ++i ) {
			if ( MathFunction.rangeCheckOpen( array[i], array[index1], min ) ) {
				min = array[i];
				index2 = i;
			}
		}
		swap( array, index1, index2 );
		Arrays.sort( array, index1 + 1, array.length );
		return true;
	}

	/**
	 * 引数の配列の要素を並び替えた時の、辞書順で引数の次に当たる順番に並び替えます。
	 * 原則として、要素に重複が無いものとして処理します。重複がある場合の戻り値は保証されていません。
	 *
	 * @param array 並び替え対象の配列
	 *
	 * @return 辞書順で次に当たる配列がある場合はtrue、arrayが降順に並んでいるならfalse
	 */
	public static <E extends Comparable<E>> boolean nextPermutation ( final E[] array ) {
		int index1 = 0;
		for ( int i = 1; i < array.length; ++i ) {
			if ( array[i - 1].compareTo( array[i] ) < 0 ) {
				index1 = i;
			}
		}
		if ( --index1 < 0 ) {
			return false;
		}
		int index2 = -1;
		E min = MathFunction.max( array );
		int subIndex = -1;
		E max = array[index1];
		for ( int i = index1 + 1; i < array.length; ++i ) {
			if ( MathFunction.rangeCheckOpen( array[i], array[index1], min ) ) {
				min = array[i];
				index2 = i;
			}
			if ( max.compareTo( array[i] ) < 0 ) {
				subIndex = i;
				max = array[i];
			}
		}
		if ( index2 == -1 ) {
			swap( array, index1, subIndex );
		}
		else {
			swap( array, index1, index2 );
		}
		Arrays.sort( array, index1 + 1, array.length );
		return true;
	}
}

final class Converter {

	/**
	 * 渡された文字列を逆順にした文字列を返します。
	 *
	 * @param str 元の文字列
	 *
	 * @return strを逆順にした文字列
	 */
	public static String reverse ( final String str ) {
		final StringBuilder sb = new StringBuilder();
		for ( int i = str.length() - 1; i >= 0; --i ) {
			sb.append( str.charAt( i ) );
		}
		return sb.toString();
	}
}

final class MathFunction {

	private static final long[] numberForPrime = {2, 7, 61, 325, 9375, 28178, 450775, 9780504, 1795265022};

	/**
	 * aとbの最大公約数を求めます。戻り値は0以上であることが保証されます。
	 *
	 * @param a 公約数を求める整数
	 * @param b 公約数を求める整数
	 *
	 * @return aとbの最大公約数
	 */
	public static long gcd ( long a, long b ) {
		a = Math.abs( a );
		b = Math.abs( b );
		if ( b == 0 ) {
			return a;
		}
		long temp;
		while ( ( temp = a % b ) != 0 ) {
			a = b;
			b = temp;
		}
		return b;
	}

	/**
	 * aとbの最小公倍数を求めます。
	 * オーバーフロー検知は出来ません。
	 *
	 * @param a 公倍数を求める整数
	 * @param b 公倍数を求める整数
	 *
	 * @return aとbの最小公倍数
	 */
	public static long lcm ( final long a, final long b ) {
		return a / gcd( a, b ) * b;
	}

	/**
	 * 引数が素数か判定します。
	 *
	 * @param n 検査対象
	 *
	 * @return nが素数であるならtrue、素数でないならfalse
	 */
	public static boolean isPrime ( long n ) {
		n = Math.abs( n );
		if ( n == 2 ) {
			return true;
		}
		if ( n == 1 || ( n & 1 ) == 0 ) {
			return false;
		}
		long d = n - 1;
		while ( ( d & 1 ) == 0 ) {
			d >>= 1;
		}
		final BigInteger bigN = BigInteger.valueOf(n);
		for ( final long a: numberForPrime ) {
			if ( a >= n ) {
				return true;
			}
			long t = d;
			long y = bigModPow( a, t, bigN );
			while ( t < n - 1 && y != 1 && y != n - 1 ) {
				y = bigModPow( y, 2, bigN );
				t <<= 1;
			}
			if ( y != n - 1 && ( t & 1 ) == 0 ) {
				return false;
			}
		}
		return true;
	}

	private static long bigModPow ( final long a, final long b, final BigInteger m ) {
		return BigInteger.valueOf( a ).modPow( BigInteger.valueOf( b ), m ).longValue();
	}

	/**
	 * num以下の素数を列挙します。
	 *
	 * @param num 素数を探す上限値
	 *
	 * @return num以下の素数のint型配列
	 */
	public static int[] primes ( final int num ) {
		if ( num < 2 ) {
			return new int[0];
		}
		final BitSet numbers = new BitSet( num + 1 );
		numbers.set( 2, num + 1 );
		final int limit = ( int )Math.sqrt( num );
		for ( int i = 2; i <= limit; ++i ) {
			if ( numbers.get( i ) ) {
				for ( int j = i * i; j <= num; j += i ) {
					if ( numbers.get(j) ) {
						numbers.clear( j );
					}
				}
			}
		}
		final int[] answer = new int[numbers.cardinality()];
		int i = 2, index = 0;
		do {
			i = numbers.nextSetBit( i );
			answer[index++] = i++;
		} while ( index != answer.length );
		return answer;
	}

	/**
	 * a**bを計算します。
	 *
	 * @param a 被累乗数
	 * @param b 指数
	 *
	 * @return a**b
	 */
	public static long pow ( long a, long b ) {
		long ans = 1;
		while ( b > 0 ) {
			if ( ( b & 1 ) == 1 ) {
				ans *= a;
			}
			a *= a;
			b >>= 1;
		}
		return ans;
	}

	/**
	 * a**bをmodで割ったあまりを計算します。
	 *
	 * @param a 被累乗数
	 * @param b 指数
	 * @param mod 法とする整数
	 *
	 * @return a**bをmodで割ったあまり
	 */
	public static long modPow ( long a, long b, final long mod ) {
		long ans = 1;
		a %= mod;
		while ( b > 0 ) {
			if ( ( b & 1 ) == 1 ) {
				ans *= a;
			}
			ans %= mod;
			a *= a;
			a %= mod;
			b >>= 1;
		}
		return ans;
	}

	/**
	 * N!を計算します。
	 *
	 * @param N 階乗を求めるのに用いる値
	 *
	 * @return N!
	 */
	public static long fact ( final int N ) {
		long ans = 1;
		for ( int i = 2; i <= N; ++i ) {
			ans *= i;
		}
		return ans;
	}

	/**
	 * N!をmodで割ったあまりを計算します。
	 *
	 * @param N 階乗を求めるのに用いる値
	 * @param mod 法とする整数
	 *
	 * @return N!をmodで割ったあまり
	 */
	public static long modFact ( final int N, final long mod ) {
		long ans = 1;
		for ( int i = 2; i <= N; ++i ) {
			ans *= i;
			ans %= mod;
		}
		return ans;
	}

	/**
	 * nCrを計算します。
	 *
	 * @param n 二項係数を求めるのに用いる値
	 * @param r 二項係数を求めるのに用いる値
	 *
	 * @return nCr
	 */
	public static long combi ( final long n, long r ) {
		if ( r < 0 || n < r ) {
			return 0;
		}
		long ans = 1;
		r = Math.min( n - r, r );
		for ( int i = 0; i < r; ++i ) {
			ans *= n - i;
			ans /= i + 1;
		}
		return ans;
	}

	/**
	 * nCrをmodで割ったあまりを計算します。
	 *
	 * @param n 二項係数を求めるのに用いる値
	 * @param r 二項係数を求めるのに用いる値
	 * @param mod 法とする整数
	 *
	 * @return nCrをmodで割ったあまり
	 */
	public static long modCombi ( final long n, long r, final long mod ) {
		if ( r < 0 || n < r ) {
			return 0;
		}
		long ans = 1;
		r = Math.min( n - r, r );
		for ( int i = 0; i < r; ++i ) {
			ans *= ( n - i ) % mod;
			ans %= mod;
			ans *= modPow( i + 1, mod - 2, mod );
			ans %= mod;
		}
		return ans;
	}

	/**
	 * 引数の前半二点、後半二点で構成される二線分が交差しているか返します。
	 *
	 * @param x1 点1のx座標
	 * @param y1 点1のy座標
	 * @param x2 点2のx座標
	 * @param y2 点2のy座標
	 * @param x3 点3のx座標
	 * @param y3 点3のy座標
	 * @param x4 点4のx座標
	 * @param y4 点4のy座標
	 *
	 * @return 交差している(線分の端が他方の線分上に存在する場合も含む)場合は1、同一線分直線上なら0、それ以外は-1
	 */
	public static int isCrossed ( final int x1, final int y1,
								  final int x2, final int y2,
								  final int x3, final int y3,
								  final int x4, final int y4 ) {
		final long s1 = ( long )( x1 - x2 ) * ( y3 - y1 ) - ( long )( y1 - y2 ) * ( x3 - x1 );
		final long t1 = ( long )( x1 - x2 ) * ( y4 - y1 ) - ( long )( y1 - y2 ) * ( x4 - x1 );
		final long s2 = ( long )( x3 - x4 ) * ( y1 - y3 ) - ( long )( y3 - y4 ) * ( x1 - x3 );
		final long t2 = ( long )( x3 - x4 ) * ( y2 - y3 ) - ( long )( y3 - y4 ) * ( x2 - x3 );
		final long temp1 = s1 * t1;
		final long temp2 = s2 * t2;
		if ( temp1 > 0 || temp2 > 0 ) {
			return -1;
		}
		if ( temp1 == 0 && temp2 == 0 ) {
			return 0;
		}
		return 1;
	}

	/**
	 * 引数の前半二点、後半二点で構成される二線分が交差しているか返します。
	 *
	 * @param p1 点1
	 * @param p2 点2
	 * @param p3 点3
	 * @param p4 点4
	 *
	 * @return 交差している(線分の端が他方の線分上に存在する場合も含む)場合は1、同一線分直線上なら0、それ以外は-1
	 */
	public static int isCrossed ( final Point p1, final Point p2, final Point p3, final Point p4 ) {
		return isCrossed( p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y );
	}

	/**
	 * 指定された頂点を順に結んで出来上がる多角形が凸多角形か判定します。
	 *
	 * @param points 多角形を構成する点
	 *
	 * @return 多角形が凸多角形ならtrue
	 */
	public static boolean isConvex ( final Point... points ) {
		final int n = points.length;
		if ( n < 3 ) {
			return false;
		}
		if ( n == 3 ) {
			return true;
		}
		boolean conv = true;
		for ( int i = 0; i < n; ++i ) {
			int result = isCrossed( points[i], points[( i + 2 ) % n],
									points[( i + 1 ) % n], points[( i + 1 + n / 2 ) % n] );
			conv &= result >= 0;
		}
		return conv;
	}

	/**
	 * numをmodで割ったあまりを返します。
	 * 戻り値は0以上mod未満であることが保証されます。
	 *
	 * @param num 被除算数
	 * @param mod 法とする値
	 *
	 * @return numをmodで割ったあまり
	 */
	public static long remainder ( long num, final long mod ) {
		num %= mod;
		if ( num < 0 ) {
			num += mod;
		}
		return num;
	}

	/**
	 * numが何桁かを返します。
	 * 0は1桁として捉えます。
	 *
	 * @param num 調べる整数
	 *
	 * @return numの桁数
	 */
	public static int digit ( final long num ) {
		if ( num < 10L ) {
			return 1;
		}
		if ( num < 100L ) {
			return 2;
		}
		if ( num < 1000L ) {
			return 3;
		}
		if ( num < 10000L ) {
			return 4;
		}
		if ( num < 100000L ) {
			return 5;
		}
		if ( num < 1000000L ) {
			return 6;
		}
		if ( num < 10000000L ) {
			return 7;
		}
		if ( num < 100000000L ) {
			return 8;
		}
		if ( num < 1000000000L ) {
			return 9;
		}
		if ( num < 10000000000L ) {
			return 10;
		}
		if ( num < 100000000000L ) {
			return 11;
		}
		if ( num < 1000000000000L ) {
			return 12;
		}
		if ( num < 10000000000000L ) {
			return 13;
		}
		if ( num < 100000000000000L ) {
			return 14;
		}
		if ( num < 1000000000000000L ) {
			return 15;
		}
		if ( num < 10000000000000000L ) {
			return 16;
		}
		if ( num < 100000000000000000L ) {
			return 17;
		}
		if ( num < 1000000000000000000L ) {
			return 18;
		}
		return 19;
	}

	public static int max ( final int... nums ) {
		int ans = Integer.MIN_VALUE;
		for ( int num: nums ) {
			ans = Math.max( ans, num );
		}
		return ans;
	}

	public static long max ( final long... nums ) {
		long ans = Long.MIN_VALUE;
		for ( long num: nums ) {
			ans = Math.max( ans, num );
		}
		return ans;
	}

	public static double max ( final double... nums ) {
		double ans = -Double.MIN_VALUE;
		for ( double num: nums ) {
			ans = Math.max( ans, num );
		}
		return ans;
	}

	public static <E extends Comparable<E>> E max ( final E[] nums ) {
		E ans = nums[0];
		for ( E value: nums ) {
			if ( ans.compareTo( value ) > 0 ) {
				ans = value;
			}
		}
		return ans;
	}

	public static int min ( final int... nums ) {
		int ans = Integer.MAX_VALUE;
		for ( int num: nums ) {
			ans = Math.min( ans, num );
		}
		return ans;
	}

	public static long min ( final long... nums ) {
		long ans = Long.MAX_VALUE;
		for ( long num: nums ) {
			ans = Math.min( ans, num );
		}
		return ans;
	}

	public static double min ( final double... nums ) {
		double ans = Double.MAX_VALUE;
		for ( double num: nums ) {
			ans = Math.min( ans, num );
		}
		return ans;
	}

	public static <E extends Comparable<E>> E min ( final E[] nums ) {
		E ans = nums[0];
		for ( E value: nums ) {
			if ( ans.compareTo( value ) < 0 ) {
				ans = value;
			}
		}
		return ans;
	}

	public static long sum ( final int... nums ) {
		long ans = 0;
		for ( int num: nums ) {
			ans += num;
		}
		return ans;
	}

	public static long sum ( final long... nums ) {
		long ans = 0;
		for ( long num: nums ) {
			ans += num;
		}
		return ans;
	}

	public static long modSum ( final long mod, final int... nums ) {
		long ans = 0;
		for ( int num: nums ) {
			ans += num;
			ans %= mod;
		}
		if ( ans < 0 ) {
			ans += mod;
		}
		return ans;
	}

	public static long modSum ( final long mod, final long... nums ) {
		long ans = 0;
		for ( long num: nums ) {
			ans += num;
			ans %= mod;
		}
		if ( ans < 0 ) {
			ans += mod;
		}
		return ans;
	}

	public static long sum ( final int[] nums, int from, int to ) {
		long ans = 0;
		for ( int i = from; i < to; ++i ) {
			ans += nums[i];
		}
		return ans;
	}

	public static long sum ( final long[] nums, int from, int to ) {
		long ans = 0;
		for ( int i = from; i < to; ++i ) {
			ans += nums[i];
		}
		return ans;
	}

	public static long modSum ( final int[] nums, int from, int to, long mod ) {
		long ans = 0;
		for ( int i = from; i < to; ++i ) {
			ans += nums[i];
			ans %= mod;
		}
		if ( ans < 0 ) {
			ans += mod;
		}
		return ans;
	}

	public static long modSum ( final long[] nums, int from, int to, long mod ) {
		long ans = 0;
		for ( int i = from; i < to; ++i ) {
			ans += nums[i];
			ans %= mod;
		}
		if ( ans < 0 ) {
			ans += mod;
		}
		return ans;
	}

	/**
	 * 引数numがl以上r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含まない)
	 *
	 * @return l <= num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheck ( final int num, final int l, final int r ) {
		return l <= num && num < r;
	}

	/**
	 * 引数numがl以上r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含まない)
	 *
	 * @return l <= num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheck ( final long num, final long l, final long r ) {
		return l <= num && num < r;
	}

	/**
	 * 引数numがl以上r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含まない)
	 *
	 * @return l <= num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheck ( final double num, final double l, final double r ) {
		return l <= num && num < r;
	}

	/**
	 * 引数numがl以上r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含まない)
	 *
	 * @return l <= num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static <E extends Comparable<E>> boolean rangeCheck ( final E num, final E l, final E r ) {
		return 0 <= l.compareTo( num ) && 0 < num.compareTo( r );
	}

	/**
	 * 引数numがlより大きく、r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含まない)
	 * @param r 上限(rを含まない)
	 *
	 * @return l < num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheckOpen ( final int num, final int l, final int r ) {
		return l < num && num < r;
	}

	/**
	 * 引数numがlより大きく、r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含まない)
	 * @param r 上限(rを含まない)
	 *
	 * @return l < num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheckOpen ( final long num, final long l, final long r ) {
		return l < num && num < r;
	}

	/**
	 * 引数numがlより大きく、r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含まない)
	 * @param r 上限(rを含まない)
	 *
	 * @return l < num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheckOpen ( final double num, final double l, final double r ) {
		return l < num && num < r;
	}

	/**
	 * 引数numがlより大きく、r未満の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含まない)
	 * @param r 上限(rを含まない)
	 *
	 * @return l < num < rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static <E extends Comparable<E>> boolean rangeCheckOpen ( final E num, final E l, final E r ) {
		return 0 < l.compareTo( num ) && 0 < num.compareTo( r );
	}

	/**
	 * 引数numがl以上r以下の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含む)
	 *
	 * @return l <= num <= rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheckClose ( final int num, final int l, final int r ) {
		return l <= num && num <= r;
	}

	/**
	 * 引数numがl以上r以下の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含む)
	 *
	 * @return l <= num <= rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheckClose ( final long num, final long l, final long r ) {
		return l <= num && num <= r;
	}

	/**
	 * 引数numがl以上r以下の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含む)
	 *
	 * @return l <= num <= rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static boolean rangeCheckClose ( final double num, final double l, final double r ) {
		return l <= num && num <= r;
	}

	/**
	 * 引数numがl以上r以下の範囲内か判定します。
	 *
	 * @param num 判定する値
	 * @param l 下限(lを含む)
	 * @param r 上限(rを含む)
	 *
	 * @return l <= num <= rを満たしていればtrue 、 満たしていなければfalse
	 */
	public static <E extends Comparable<E>> boolean rangeCheckClose ( final E num, final E l, final E r ) {
		return 0 <= l.compareTo( num ) && 0 <= num.compareTo( r );
	}

	/**
	 * 引数の中でのmexを求めます。
	 * 
	 * @param num 
	 */
	public static int mex ( final int... nums ) {
		final IntegerSet set = new IntegerSet( nums.length << 1 );
		for ( final int num : nums ) {
			set.add( num );
		}
		int ans = 0;
		while ( set.contains( ans ) ) {
			++ans;
		}
		return ans;
	}
}

final class Searcher {

	private static final int CYCLE_COUNT = Double.MAX_EXPONENT + 53;

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最大のインデックスを返します。
	 * 見つからなかった場合は指定された要素未満で最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以下で最大のインデックス
	 */
	public static int downSearch ( final int[] array, final int value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最大のインデックスを返します。
	 * 見つからなかった場合は指定された要素未満で最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以下で最大のインデックス
	 */
	public static int downSearch ( final long[] array, final long value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最大のインデックスを返します。
	 * 見つからなかった場合は指定された要素未満で最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以下で最大のインデックス
	 */
	public static int downSearch ( final double[] array, final double value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最大のインデックスを返します。
	 * 見つからなかった場合は指定された要素未満で最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以下で最大のインデックス
	 */
	public static int downSearch ( final char[] array, final int value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最大のインデックスを返します。
	 * 見つからなかった場合は指定された要素未満で最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以下で最大のインデックス
	 */
	public static <E extends Comparable<E>> int downSearch ( final E[] array, final E value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c].compareTo( value ) > 0 ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * リスト内の指定された要素を探します。
	 * リスト内で見つかった場合はその要素と同一で最大のインデックスを返します。
	 * 見つからなかった場合は指定された要素未満で最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param list 探索対象のリスト
	 * @param value 探索要素
	 *
	 * @return 指定された要素以下で最大のインデックス
	 */
	public static <E extends Comparable<E>> int downSearch ( final List<E> list, final E value ) {
		int a = 0, b = list.size() - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( list.get( c ).compareTo( value ) > 0 ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 * 関数内で見つかった場合はその値と同一で最大の引数を返します。
	 * 見つからなかった場合は指定された値未満で最大の引数を返します。
	 * もしそのような要素が存在しない場合は下限-1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 指定された値以下で最大の引数
	 */
	public static int downSearch ( int a, int b, final int value, final IntUnaryOperator func ) {
		int ans = a - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsInt( c ) > value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 * 関数内で見つかった場合はその値と同一で最大の引数を返します。
	 * 見つからなかった場合は指定された値未満で最大の引数を返します。
	 * もしそのような要素が存在しない場合は下限-1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 指定された値以下で最大の引数
	 */
	public static long downSearch ( long a, long b, final long value, final LongUnaryOperator func ) {
		long ans = a - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsLong( c ) > value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 * 関数内で見つかった場合はその値と同一で最大の引数を返します。
	 * 見つからなかった場合は指定された値未満で最大の引数を返します。
	 * もしそのような要素が存在しない場合は下限より小さい値を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 指定された値以下で最大の引数
	 */
	public static double search ( double a, double b, final double value, final DoubleUnaryOperator func ) {
		double ans = a - Math.abs( a ), c;
		for ( int $ = 0; $ < CYCLE_COUNT; ++$ ) {
			c = ( a + b ) / 2;
			if ( func.applyAsDouble( c ) > value ) {
				b = c;
			}
			else {
				a = ( ans = c );
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayにvalueが含まれているかを表すboolean
	 */
	public static boolean contains ( final int[] array, final int value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else if ( array[c] < value ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayにvalueが含まれているかを表すboolean
	 */
	public static boolean contains ( final long[] array, final long value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else if ( array[c] < value ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayにvalueが含まれているかを表すboolean
	 */
	public static boolean contains ( final double[] array, final double value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else if ( array[c] < value ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayにvalueが含まれているかを表すboolean
	 */
	public static <E extends Comparable<E>> boolean contains ( final E[] array, final E value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			int result = array[c].compareTo( value );
			if ( result > 0 ) {
				b = c - 1;
			}
			else if ( result < 0 ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * リスト内の指定された要素を探します。
	 *
	 * @param list 探索対象のリスト
	 * @param value 探索要素
	 *
	 * @return listにvalueが含まれているかを表すboolean
	 */
	public static <E extends Comparable<E>> boolean contains ( final List<E> list, final E value ) {
		int a = 0, b = list.size() - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			int result = list.get( c ).compareTo( value );
			if ( result > 0 ) {
				b = c - 1;
			}
			else if ( result < 0 ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return この関数がa以上b以下で探索値を取るかを表すboolean
	 */
	public static boolean contains ( int a, int b, final int value, final IntUnaryOperator func ) {
		int c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			int num = func.applyAsInt( c );
			if ( num > value ) {
				b = c - 1;
			}
			else if ( num < value ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return この関数がa以上b以下で探索値を取るかを表すboolean
	 */
	public static boolean contains ( long a, long b, final long value, final LongUnaryOperator func ) {
		long c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			long num = func.applyAsLong( c );
			if ( num > value ) {
				b = c - 1;
			}
			else if ( num < value ) {
				a = c + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayのvalueのインデックス(無ければ - 1)
	 */
	public static int search ( final int[] array, final int value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else if ( array[c] < value ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayのvalueのインデックス(無ければ - 1)
	 */
	public static int search ( final long[] array, final long value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else if ( array[c] < value ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayのvalueのインデックス(無ければ - 1)
	 */
	public static int search ( final double[] array, final double value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = c - 1;
			}
			else if ( array[c] < value ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * 配列内の指定された要素を探します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return arrayにvalueが含まれているかを表すboolean
	 */
	public static <E extends Comparable<E>> int search ( final E[] array, final E value ) {
		int a = 0, b = array.length - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			int result = array[c].compareTo( value );
			if ( result > 0 ) {
				b = c - 1;
			}
			else if ( result < 0 ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * リスト内の指定された要素を探します。
	 *
	 * @param list 探索対象のリスト
	 * @param value 探索要素
	 *
	 * @return listのvalueのインデックス(無ければ - 1)
	 */
	public static <E extends Comparable<E>> int search ( final List<E> list, final E value ) {
		int a = 0, b = list.size() - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			int result = list.get( c ).compareTo( value );
			if ( result > 0 ) {
				b = c - 1;
			}
			else if ( result < 0 ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return -1;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return この関数がvalueを取る引数(無ければa - 1)
	 */
	public static int search ( int a, int b, final int value, final IntUnaryOperator func ) {
		int c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			int num = func.applyAsInt( c );
			if ( num > value ) {
				b = c - 1;
			}
			else if ( num < value ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return a - 1;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return この関数がvalueを取る引数(無ければa - 1)
	 */
	public static long search ( long a, long b, final long value, final LongUnaryOperator func ) {
		long c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			long num = func.applyAsLong( c );
			if ( num > value ) {
				b = c - 1;
			}
			else if ( num < value ) {
				a = c + 1;
			}
			else {
				return c;
			}
		}
		return a - 1;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最小のインデックスを返します。
	 * 見つからなかった場合は指定された要素以上で最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以上で最小のインデックス
	 */
	public static int upSearch ( final int[] array, final int value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] >= value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最小のインデックスを返します。
	 * 見つからなかった場合は指定された要素以上で最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以上で最小のインデックス
	 */
	public static int upSearch ( final long[] array, final long value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] >= value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最小のインデックスを返します。
	 * 見つからなかった場合は指定された要素以上で最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以上で最小のインデックス
	 */
	public static int upSearch ( final double[] array, final double value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] >= value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素を探します。
	 * 配列内で見つかった場合はその要素と同一で最小のインデックスを返します。
	 * 見つからなかった場合は指定された要素以上で最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 指定された要素以上で最小のインデックス
	 */
	public static <E extends Comparable<E>> int upSearch ( final E[] array, final E value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c].compareTo( value ) >= 0 ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * リスト内の指定された要素を探します。
	 * リスト内で見つかった場合はその要素と同一で最小のインデックスを返します。
	 * 見つからなかった場合は指定された要素以上で最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はlist.size()を返します。
	 *
	 * @param list 探索対象のリスト
	 * @param value 探索要素
	 *
	 * @return 指定された要素以上で最小のインデックス
	 */
	public static <E extends Comparable<E>> int upSearch ( final List<E> list, final E value ) {
		int a = 0, b = list.size() - 1, ans = list.size(), c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( list.get( c ).compareTo( value ) >= 0 ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 * 関数内で見つかった場合はその値と同一で最小の引数を返します。
	 * 見つからなかった場合は指定された値以上で最大の引数を返します。
	 * もしそのような要素が存在しない場合は上限+1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 指定された値以上で最小の引数
	 */
	public static int upSearch ( int a, int b, final int value, final IntUnaryOperator func ) {
		int ans = b + 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsInt( c ) >= value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値を探します。
	 * 関数内で見つかった場合はその値と同一で最小の引数を返します。
	 * 見つからなかった場合は指定された値以上で最大の引数を返します。
	 * もしそのような要素が存在しない場合は上限+1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 指定された値以上で最小の引数
	 */
	public static long upSearch ( long a, long b, final long value, final LongUnaryOperator func ) {
		long ans = b + 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsLong( c ) >= value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より小さい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最大のインデックス
	 */
	public static int underSearch ( final int[] array, final int value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] >= value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より小さい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最大のインデックス
	 */
	public static int underSearch ( final long[] array, final long value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] >= value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より小さい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最大のインデックス
	 */
	public static int underSearch ( final double[] array, final double value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] >= value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より小さい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最大のインデックス
	 */
	public static <E extends Comparable<E>> int underSearch ( final E[] array, final E value ) {
		int a = 0, b = array.length - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c].compareTo( value ) >= 0 ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * リスト内の指定された要素より小さい要素を探します。
	 * リスト内で見つかった場合は条件を満たす最大のインデックスを返します。
	 * もしそのような要素が存在しない場合は-1を返します。
	 *
	 * @param list 探索対象のリスト
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最大のインデックス
	 */
	public static <E extends Comparable<E>> int underSearch ( final List<E> list, final E value ) {
		int a = 0, b = list.size() - 1, ans = -1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( list.get( c ).compareTo( value ) >= 0 ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値より小さい値を探します。
	 * 関数内で見つかった場合は条件を満たす最大の引数を返します。
	 * もしそのような要素が存在しない場合は下限-1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 条件を満たす最大の引数
	 */
	public static int underSearch ( int a, int b, final int value, final IntUnaryOperator func ) {
		int ans = a - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsInt( c ) >= value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値より小さい値を探します。
	 * 関数内で見つかった場合は条件を満たす最大の引数を返します。
	 * もしそのような要素が存在しない場合は下限-1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 条件を満たす最大の引数
	 */
	public static long underSearch ( long a, long b, final long value, final LongUnaryOperator func ) {
		long ans = a - 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsLong( c ) >= value ) {
				b = c - 1;
			}
			else {
				a = ( ans = c ) + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より大きい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最小のインデックス
	 */
	public static int overSearch ( final int[] array, final int value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より大きい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最小のインデックス
	 */
	public static int overSearch ( final long[] array, final long value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より大きい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最小のインデックス
	 */
	public static int overSearch ( final double[] array, final double value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c] > value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 配列内の指定された要素より大きい要素を探します。
	 * 配列内で見つかった場合は条件を満たす最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はarray.lengthを返します。
	 *
	 * @param array 探索対象の配列
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最小のインデックス
	 */
	public static <E extends Comparable<E>> int overSearch ( final E[] array, final E value ) {
		int a = 0, b = array.length - 1, ans = array.length, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( array[c].compareTo( value ) > 0 ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * リスト内の指定された要素より大きい要素を探します。
	 * リスト内で見つかった場合は条件を満たす最小のインデックスを返します。
	 * もしそのような要素が存在しない場合はlist.size()を返します。
	 *
	 * @param list 探索対象のリスト
	 * @param value 探索要素
	 *
	 * @return 条件を満たす最小のインデックス
	 */
	public static <E extends Comparable<E>> int overSearch ( final List<E> list, final E value ) {
		int a = 0, b = list.size() - 1, ans = list.size(), c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( list.get( c ).compareTo( value ) > 0 ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値より大きい値を探します。
	 * 関数内で見つかった場合は条件を満たす最小の引数を返します。
	 * もしそのような要素が存在しない場合は上限+1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 条件を満たす最小の引数
	 */
	public static int overSearch ( int a, int b, final int value, final IntUnaryOperator func ) {
		int ans = b + 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsInt( c ) > value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}

	/**
	 * 広義単調増加な関数内の指定された値より大きい値を探します。
	 * 関数内で見つかった場合は条件を満たす最小の引数を返します。
	 * もしそのような要素が存在しない場合は上限+1を返します。
	 *
	 * @param a 探索範囲の下限
	 * @param b 探索範囲の上限
	 * @param value 探索値
	 *
	 * @return 条件を満たす最小の引数
	 */
	public static long overSearch ( long a, long b, final long value, final LongUnaryOperator func ) {
		long ans = b + 1, c;
		while ( a - b < 1 ) {
			c = ( a + b ) / 2;
			if ( func.applyAsLong( c ) > value ) {
				b = ( ans = c ) - 1;
			}
			else {
				a = c + 1;
			}
		}
		return ans;
	}
}

// Binary Indexed Tree
final class BIT {
	final int size;
	final private long[] tree;

	public BIT ( int n ) {
		size = n;
		tree = new long[n + 1];
	}

	public long sum ( int i ) {
		long sum = 0;
		while ( i > 0 ) {
			sum += tree[i];
			i ^= i & ( -i );
		}
		return sum;
	}

	public void add ( int i, long x ) {
		while ( i <= size ) {
			tree[i] += x;
			i += i & ( -i );
		}
	}

	public void clear () {
		Arrays.fill( tree, 0L );
	}
}

final class Bitset implements Cloneable {
	private final long[] bit;
	private final int size, len;
	private final long MASK;

	public Bitset ( final int len ) {
		this.len = len;
		size = ( len + 63 ) >> 6;
		bit = new long[size];
		MASK = ( -1L ) >>> ( ( size << 6 ) - len );
	}

	private Bitset ( final long[] arr ) {
		this( arr.length );
		System.arraycopy( arr, 0, bit, 0, size );
	}

	public void set ( final int index ) {
		if ( index >= size << 6 ) {
			throw new ArrayIndexOutOfBoundsException( index + " is out of this bitset's size " + size );
		}
		bit[index >> 6] |= ( 1L << ( index & 0b111111 ) );
	}

	public void clear ( final int index ) {
		if ( index >= size << 6 ) {
			throw new ArrayIndexOutOfBoundsException( index + " is out of this bitset's size " + size );
		}
		long m = ~( 1L << ( index & 0b111111 ) );
		bit[index >> 6] &= m;
	}

	public boolean get ( final int index ) {
		if ( index >= size << 6 ) {
			throw new ArrayIndexOutOfBoundsException( index + " is out of this bitset's size " + size );
		}
		return ( bit[index >> 6] & ( 1L << ( index & 0b111111 ) ) ) != 0;
	}

	public void shiftLeft ( int num ) {
		if ( num >= size << 6 ) {
			Arrays.fill( bit, 0L );
			return;
		}
		final int n = num >> 6;
		num &= 0b111111;
		for ( int i = size - 1; i >= n; --i ) {
			bit[i] = ( bit[i - n] << num ) | ( i != n && num != 0 ? bit[i - n - 1] >>> ( 64 - num ) : 0L );
		}
		for ( int i = 0; i < n; ++i ) {
			bit[i] = 0L;
		}
		bit[size - 1] &= MASK;
	}

	public void shiftRight ( int num ) {
		if ( num >= size << 6 ) {
			Arrays.fill( bit, 0L );
			return;
		}
		final int n = num >> 6;
		num &= 0b111111;
		for ( int i = 0; i < size - n; ++i ) {
			bit[i] = ( bit[i + n] >>> num ) | ( i + n + 1 != size && num != 0 ? bit[i + n + 1] << ( 64 - num ) : 0L );
		}
		for ( int i = size - 1; i >= size - n; --i ) {
			bit[i] = 0L;
		}
	}

	public long[] longValues () {
		return bit;
	}

	public long longValue () {
		return bit[0];
	}

	public void and ( final Bitset b ) {
		final long[] bit2 = b.longValues();
		final int m = Math.min( bit2.length, size );
		for ( int i = 0; i < m; ++i ) {
			bit[i] &= bit2[i];
		}
		for ( int i = m; i < size; ++i ) {
			bit[i] = 0;
		}
		bit[size - 1] &= MASK;
	}

	public void or ( final Bitset b ) {
		final long[] bit2 = b.longValues();
		final int m = Math.min( bit2.length, size );
		for ( int i = 0; i < m; ++i ) {
			bit[i] |= bit2[i];
		}
		bit[size - 1] &= MASK;
	}

	public void xor ( final Bitset b ) {
		final long[] bit2 = b.longValues();
		final int m = Math.min( bit2.length, size );
		for ( int i = 0; i < m; ++i ) {
			bit[i] ^= bit2[i];
		}
		bit[size - 1] &= MASK;
	}

	public Bitset clone () throws CloneNotSupportedException {
		super.clone();
		final Bitset b = new Bitset( bit );
		return b;
	}
}

final class IntegerSet {

	private class Node {
		private final int value;
		private Node next;

		private Node ( final int val ) {
			value = val;
		}

		private boolean add ( final int x ) {
			if ( value == x ) {
				return false;
			}
			if ( next != null ) {
				return next.add( x );
			}
			++size;
			next = new Node( x );
			return true;
		}

		private void add ( final Node n ) {
			if ( value != n.value ) {
				if ( next != null ) {
					next.add( n );
				}
				else {
					next = n;
				}
			}
		}

		private boolean remove ( final int x ) {
			if ( next == null ) {
				return false;
			}
			if ( next.value != x ) {
				return next.remove( x );
			}
			--size;
			next = next.next;
			return true;
		}

		private boolean contains ( final int x ) {
			if ( value == x ) {
				return true;
			}
			if ( next != null ) {
				return next.contains( x );
			}
			return false;
		}
	}

	private Node[] table;
	private int size;

	public IntegerSet () {
		this( 16 );
	}

	public IntegerSet ( final int s ) {
		if ( s < 1 ) {
			throw new NegativeArraySizeException( "hash table's size must be positive" );
		}
		table = new Node[s];
		size = 0;
	}

	public boolean add ( final int x ) {
		sizeCheck();
		final int index = hash( x );
		if ( table[index] != null ) {
			return table[index].add( x );
		}
		++size;
		table[index] = new Node( x );
		return true;
	}

	private void add ( final Node n ) {
		final int index = hash( n.value );
		if ( table[index] != null ) {
			table[index].add( n );
		}
		else {
			table[index] = n;
		}
	}

	public boolean remove ( final int x ) {
		final int index = hash( x );
		if ( table[index] == null ) {
			return false;
		}
		if ( table[index].value != x ) {
			return table[index].remove( x );
		}
		--size;
		table[index] = table[index].next;
		return true;
	}

	public boolean contains ( final int x ) {
		final int index = hash( x );
		if ( table[index] == null ) {
			return false;
		}
		return table[index].contains( x );
	}

	private void reHash () {
		final Node[] oldTable = table;
		table = new Node[table.length << 1];
		for ( Node node: oldTable ) {
			while ( node != null ) {
				final Node next = node.next;
				node.next = null;
				add( node );
				node = next;
			}
		}
	}

	private void sizeCheck () {
		if ( table.length * 0.6 < size ) {
			reHash();
		}
	}

	private int hash ( final int val ) {
		final int h = val % table.length;
		return h < 0 ? h + table.length : h;
	}

	public int size () {
		return size;
	}

	public int[] toArray () {
		final int[] array = new int[size];
		int index = 0;
		for ( Node node: table ) {
			while ( node != null ) {
				array[index++] = node.value;
				node = node.next;
			}
		}
		return array;
	}

	public String toString () {
		return Arrays.toString( toArray() );
	}
}

final class Matrix {
	private final long[][] matrix;

	public Matrix ( final int H, final int W, final long def ) {
		matrix = new long[H][W];
		if ( def != 0 ) {
			for ( final long[] mat: matrix ) {
				Arrays.fill( mat, def );
			}
		}
	}

	public Matrix ( final int H, final int W ) {
		this( H, W, 0 );
	}

	public Matrix ( final Dimension d, final long def ) {
		this( d.height, d.width, def );
	}

	public Matrix ( final long[][] mat ) {
		matrix = new long[mat.length][];
		for ( int i = 0; i < mat.length; ++i ) {
			matrix[i] = Arrays.copyOf( mat[i], mat[i].length );
		}
	}

	public long get ( final int i, final int j ) {
		return matrix[i][j];
	}

	public long set ( final int i, final int j, final long value ) {
		return matrix[i][j] = value;
	}

	public Matrix copy () {
		return new Matrix( matrix );
	}

	public Dimension size () {
		return new Dimension( matrix[0].length, matrix.length );
	}

	public Matrix add ( final Matrix m ) {
		if ( !size().equals( m.size() ) ) {
			throw new IllegalArgumentException( "matrix size is not same" );
		}
		final Matrix ans = new Matrix( size(), 0 );
		for ( int i = 0; i < matrix.length; ++i ) {
			for ( int j = 0; j < matrix[i].length; ++j ) {
				ans.set( i, j, matrix[i][j] + m.get( i, j ) );
			}
		}
		return ans;
	}

	public Matrix subtract ( final Matrix m ) {
		if ( !size().equals( m.size() ) ) {
			throw new IllegalArgumentException( "matrix size is not same" );
		}
		final Matrix ans = new Matrix( size(), 0 );
		for ( int i = 0; i < matrix.length; ++i ) {
			for ( int j = 0; j < matrix[i].length; ++j ) {
				ans.set( i, j, matrix[i][j] - m.get( i, j ) );
			}
		}
		return ans;
	}

	public Matrix multiply ( final Matrix m ) {
		if ( size().width != m.size().height ) {
			throw new IllegalArgumentException( "matrix length is not same" );
		}
		final Matrix ans = new Matrix( size().height, m.size().width );
		final Dimension size = ans.size();
		final int len = size().width;
		for ( int i = 0; i < size.height; ++i ) {
			for ( int j = 0; j < size.width; ++j ) {
				long sum = 0;
				for ( int k = 0; k < len; ++k ) {
					sum += matrix[i][k] * m.get( k, j );
				}
				ans.set( i, j, sum );
			}
		}
		return ans;
	}

	public Matrix modAdd ( final Matrix m, final long mod ) {
		if ( !size().equals( m.size() ) ) {
			throw new IllegalArgumentException( "matrix size is not same" );
		}
		final Matrix ans = new Matrix( size(), 0 );
		for ( int i = 0; i < matrix.length; ++i ) {
			for ( int j = 0; j < matrix[i].length; ++j ) {
				ans.set( i, j, MathFunction.remainder( matrix[i][j] + m.get( i, j ), mod ) );
			}
		}
		return ans;
	}

	public Matrix modSubtract ( final Matrix m, final long mod ) {
		if ( !size().equals( m.size() ) ) {
			throw new IllegalArgumentException( "matrix size is not same" );
		}
		final Matrix ans = new Matrix( size(), 0 );
		for ( int i = 0; i < matrix.length; ++i ) {
			for ( int j = 0; j < matrix[i].length; ++j ) {
				ans.set( i, j, MathFunction.remainder( matrix[i][j] - m.get( i, j ), mod ) );
			}
		}
		return ans;
	}

	public Matrix modMultiply ( final Matrix m, final long mod ) {
		if ( size().width != m.size().height ) {
			throw new IllegalArgumentException( "matrix length is not same" );
		}
		final Matrix ans = new Matrix( size().height, m.size().width );
		final Dimension size = ans.size();
		final int len = size().width;
		for ( int i = 0; i < size.height; ++i ) {
			for ( int j = 0; j < size.width; ++j ) {
				long sum = 0;
				for ( int k = 0; k < len; ++k ) {
					sum = MathFunction.remainder( sum + matrix[i][k] * m.get( k, j ), mod );
				}
				ans.set( i, j, sum );
			}
		}
		return ans;
	}

	public static Matrix pow ( final Matrix original, final Matrix pw, long exp ) {
		Matrix a = original.copy();
		Matrix b = pw.copy();
		while ( 0 < exp ) {
			if ( ( exp & 1 ) == 1 ) {
				a = b.multiply( a );
			}
			b = b.multiply( b );
			exp >>= 1;
		}
		return a;
	}

	public static Matrix modPow ( final Matrix original, final Matrix pw, long exp, final long mod ) {
		Matrix a = original.copy();
		Matrix b = pw.copy();
		while ( 0 < exp ) {
			if ( ( exp & 1 ) == 1 ) {
				a = b.modMultiply( a, mod );
			}
			b = b.modMultiply( b, mod );
			exp >>= 1;
		}
		return a;
	}

	public long determinant () {
		return determinant( matrix );
	}

	private static long determinant ( final long[][] mat ) {
		if ( mat.length == 1 ) {
			return mat[0][0];
		}
		final long[][] miniMat = new long[mat.length - 1][mat.length - 1];
		for ( int i = 1; i < mat.length; ++i ) {
			System.arraycopy( mat[i], 1, miniMat[i - 1], 0, miniMat.length );
		}
		long ans = mat[0][0] * determinant( miniMat );
		for ( int i = 1; i < mat.length; ++i ) {
			for ( int j = 1; j < mat.length; ++j ) {
				miniMat[j - 1][i - 1] = mat[j][i - 1];
			}
			final long num = mat[0][i] * determinant( miniMat );
			ans += i % 2 == 0 ? num : -num;
		}
		return ans;
	}

	@Override
	public String toString () {
		final StringBuilder ans = new StringBuilder();
		ans.append( Arrays.toString( matrix[0] ) );
		for ( int i = 1; i < matrix.length; ++i ) {
			ans.append( "\n" );
			ans.append( Arrays.toString( matrix[i] ) );
		}
		return ans.toString();
	}
}

class Pair<K extends Comparable<K>, V extends Comparable<V>>
		implements Comparable<Pair<K, V>> {
	private AbstractMap.SimpleEntry<K, V> map;

	public Pair ( K key, V value ) {
		map = new AbstractMap.SimpleEntry<>( key, value );
	}

	public K getKey () {
		return map.getKey();
	}

	public V getValue () {
		return map.getValue();
	}

	public K setKey ( K key ) {
		K oldKey = map.getKey();
		V value = map.getValue();
		map = new AbstractMap.SimpleEntry<>( key, value );
		return oldKey;
	}

	public V setValue ( V value ) {
		return map.setValue( value );
	}

	@Override
	public int compareTo ( Pair<K, V> pair ) {
		int com = getKey().compareTo( pair.getKey() );
		return com != 0 ? com : getValue().compareTo( pair.getValue() );
	}

	@Override
	public boolean equals ( Object o ) {
		if ( o instanceof Pair<?, ?> pair ) {
			return getKey().equals( pair.getKey() ) && getValue().equals( pair.getValue() );
		}
		return false;
	}

	@Override
	public String toString () {
		return getKey() + "=" + getValue();
	}

	@Override
	public int hashCode () {
		return ( getKey().hashCode() << 2 ) ^ ( getValue().hashCode() );
	}
}

final class RollingHash implements Comparable<RollingHash> {
	private static final long BASE = new Random().nextInt( 1000 ) + Character.MAX_VALUE + 1;
	private static final long MASK30 = ( 1L << 30 ) - 1;
	private static final long MASK31 = ( 1L << 31 ) - 1;
	private static final long MOD = ( 1L << 61 ) - 1;
	private static final long MASK61 = MOD;
	private long[] hash;
	private String string;

	public RollingHash ( final String str ) {
		string = str;
		hash = new long[str.length() + 1];
		roll();
	}

	private void roll () {
		final int len = string.length();
		for ( int i = 1; i <= len; ++i ) {
			hash[i] = multiply( hash[i - 1], BASE ) + string.charAt( i - 1 ) - ' ' + 1;
			if ( MOD <= hash[i] ) {
				hash[i] -= MOD;
			}
		}
	}

	private static long multiply ( final long a, final long b ) {
		final long au = a >> 31;
		final long ad = a & MASK31;
		final long bu = b >> 31;
		final long bd = b & MASK31;
		final long mid = ad * bu + au * bd;
		final long midu = mid >> 30;
		final long midd = mid & MASK30;
		return mod( au * bu * 2 + midu + ( midd << 31 ) + ad * bd );
	}

	private static long mod ( final long x ) {
		final long xu = x >> 61;
		final long xd = x & MASK61;
		long ans = xu + xd;
		if ( MOD <= ans ) {
			ans -= MOD;
		}
		return ans;
	}

	public long getHash ( final int l, final int r ) {
		return ( hash[r] - multiply( hash[l], modBasePow( r - l ) ) + MOD ) % MOD;
	}

	private static long modBasePow ( long b ) {
		long ans = 1;
		long a = BASE;
		while ( b > 0 ) {
			if ( ( b & 1 ) == 1 ) {
				ans = multiply( ans, a );
			}
			a = multiply( a, a );
			b >>= 1;
		}
		return ans;
	}

	public boolean equals ( final RollingHash rh, final int l1, final int r1, final int l2, final int r2 ) {
		if ( r1 - l1 != r2 - l2 ) {
			return false;
		}
		return getHash( l1, r1 ) == rh.getHash( l2, r2 );
	}

	public int length () {
		return string.length();
	}

	@Override
	public int hashCode () {
		return string.hashCode();
	}

	@Override
	public String toString () {
		return string;
	}

	@Override
	public boolean equals ( Object o ) {
		if ( o instanceof final RollingHash rh ) {
			return equals( rh, 0, length(), 0, rh.length() );
		}
		return false;
	}

	@Override
	public int compareTo ( RollingHash rh ) {
		return string.compareTo( rh.toString() );
	}

	public int compareTo ( String str ) {
		return string.compareTo( str );
	}

	public char charAt ( final int i ) {
		return string.charAt( i );
	}

	public int compareToIgnoreCase ( final RollingHash rh ) {
		return string.compareToIgnoreCase( rh.toString() );
	}

	public int compareToIgnoreCase ( final String str ) {
		return string.compareToIgnoreCase( str );
	}

	public void concat ( final RollingHash rh ) {
		concat( rh.toString() );
	}

	public void concat ( final String str ) {
		string = string.concat( str );
		hash = new long[string.length() + 1];
		roll();
	}

	public boolean contains ( final RollingHash rh ) {
		final long hash = rh.getHash( 0, rh.length() );
		final int len = length() - rh.length();
		for ( int i = 0; i <= len; ++i ) {
			if ( hash == getHash( i, rh.length() + i ) ) {
				return true;
			}
		}
		return false;
	}

	public boolean contains ( final String str ) {
		return indexOf( str ) != -1;
	}

	public int indexOf ( final int ch ) {
		return indexOf( ch, 0 );
	}

	public int indexOf ( final int ch, final int fromIndex ) {
		final int len = length();
		for ( int i = fromIndex; i < len; ++i ) {
			if ( string.charAt( i ) == ch ) {
				return i;
			}
		}
		return -1;
	}

	public int indexOf ( final String str ) {
		return indexOf( str, 0 );
	}

	public int indexOf ( final String str, final int fromIndex ) {
		long hash = 0;
		for ( final char c: str.toCharArray() ) {
			hash = multiply( hash, BASE ) + c - ' ' + 1;
			if ( MOD <= hash ) {
				hash -= MOD;
			}
		}
		final int len = length() - str.length();
		for ( int i = fromIndex; i <= len; ++i ) {
			if ( hash == getHash( i, str.length() + i ) ) {
				return i;
			}
		}
		return -1;
	}

	public boolean isEmpty () {
		return length() == 0;
	}

	public int lastIndexOf ( final int ch, final int fromIndex ) {
		for ( int i = fromIndex; i >= 0; --i ) {
			if ( string.charAt( i ) == ch ) {
				return i;
			}
		}
		return -1;
	}

	public int lastIndexOf ( final int ch ) {
		return lastIndexOf( ch, length() - 1 );
	}

	public static RollingHash valueOf ( final boolean b ) {
		return new RollingHash( b ? "true" : "false" );
	}

	public static RollingHash valueOf ( final char c ) {
		return new RollingHash( "" + c );
	}

	public static RollingHash valueOf ( final char[] c ) {
		return new RollingHash( String.valueOf( c, 0, c.length ) );
	}

	public static RollingHash valueOf ( final char[] c, final int offset, final int count ) {
		return new RollingHash( String.valueOf( c, offset, count ) );
	}

	public static RollingHash valueOf ( final double d ) {
		return new RollingHash( String.valueOf( d ) );
	}

	public static RollingHash valueOf ( final float f ) {
		return new RollingHash( String.valueOf( f ) );
	}

	public static RollingHash valueOf ( final int i ) {
		return new RollingHash( String.valueOf( i ) );
	}

	public static RollingHash valueOf ( final long l ) {
		return new RollingHash( String.valueOf( l ) );
	}

	public static RollingHash valueOf ( final Object obj ) {
		return new RollingHash( String.valueOf( obj ) );
	}
}

abstract class SegmentTree<E> {
	final int N, size;
	final E def;
	final Object[] node;

	public SegmentTree ( final int n, final E def, final boolean include ) {
		int num = 2;
		while ( num < n << 1 ) {
			num <<= 1;
		}
		N = num;
		size = num >> 1 - ( include ? 1 : 0 );
		node = new Object[N];
		this.def = def;
		Arrays.fill( node, this.def );
	}

	public SegmentTree ( final E[] arr, final E def, final boolean include ) {
		int num = 2;
		while ( num < arr.length << 1 ) {
			num <<= 1;
		}
		N = num;
		size = num >> 1 - ( include ? 1 : 0 );
		node = new Object[N];
		this.def = def;
		System.arraycopy( arr, 0, node, N >> 1, arr.length );
		for ( int i = arr.length + ( N >> 1 ); i < N; i++ ) {
			node[i] = def;
		}
		updateAll();
	}

	public SegmentTree ( final int n, final E def ) {
		this( n, def, false );
	}

	@SuppressWarnings( "unchecked" )
	private void updateAll () {
		for ( int i = ( N >> 1 ) - 1; i > 0; i-- ) {
			node[i] = function( ( E )node[i << 1], ( E )node[( i << 1 ) + 1] );
		}
	}

	@SuppressWarnings( "unchecked" )
	public void update ( int n, final E value ) {
		n += size;
		node[n] = value;
		n >>= 1;
		while ( n > 0 ) {
			node[n] = function( ( E )node[n << 1], ( E )node[( n << 1 ) + 1] );
			n >>= 1;
		}
	}

	@SuppressWarnings( "unchecked" )
	public E get ( final int a ) {
		return ( E )node[a + size];
	}

	@SuppressWarnings( "unchecked" )
	public E answer () {
		return ( E )node[1];
	}

	@SuppressWarnings( "unchecked" )
	public E query ( int l, int r ) {
		l += size;
		r += size;
		E answer = def;
		while ( l > 0 && r > 0 && l <= r ) {
			if ( l % 2 == 1 ) {
				answer = function( ( E )node[l++], answer );
			}
			l >>= 1;
			if ( r % 2 == 0 ) {
				answer = function( answer, ( E )node[r--] );
			}
			r >>= 1;
		}
		return answer;
	}

	abstract public E function ( E a, E b );
}

final class UnionFind {
	private final int[] par, rank, size, path;
	private int count;

	public UnionFind ( final int N ) {
		count = N;
		par = new int[N];
		rank = new int[N];
		size = new int[N];
		path = new int[N];
		Arrays.fill( par, -1 );
		Arrays.fill( size, 1 );
	}

	public int root ( final int ind ) {
		if ( par[ind] == -1 ) {
			return ind;
		}
		else {
			return par[ind] = root( par[ind] );
		}
	}

	public boolean isSame ( final int x, final int y ) {
		return root( x ) == root( y );
	}

	public boolean unite ( final int x, final int y ) {
		int rx = root( x );
		int ry = root( y );
		++path[rx];
		if ( rx == ry ) {
			return false;
		}
		if ( rank[rx] < rank[ry] ) {
			int temp = rx;
			rx = ry;
			ry = temp;
		}
		par[ry] = rx;
		if ( rank[rx] == rank[ry] ) {
			++rank[rx];
		}
		path[rx] += path[ry];
		size[rx] += size[ry];
		--count;
		return true;
	}

	public int groupCount () {
		return count;
	}

	public int pathCount ( final int x ) {
		return path[root( x )];
	}

	public int size ( final int x ) {
		return size[root( x )];
	}
}

final class Tree<E extends Comparable<E>> {
	private Node<E> root;
	private int size, hash;

	public Tree () {
		size = 0;
		root = null;
		hash = 0;
	}

	static final private class Node<E> {
		E value;
		int height, size;
		Node<E> left, right, parent;

		public Node ( final Node<E> p, final E v ) {
			value = v;
			parent = p;
			height = 1;
			size = 1;
		}
	}

	public boolean add ( final E x ) {
		boolean bool = true;
		if ( root == null ) {
			root = new Node<>( null, x );
		}
		else {
			Node<E> par;
			Node<E> now = root;
			do {
				par = now;
				final int result = x.compareTo( now.value );
				if ( result < 0 ) {
					now = now.left;
				}
				else if ( result > 0 ) {
					now = now.right;
				}
				else {
					bool = false;
					break;
				}
			} while ( now != null );
			if ( bool ) {
				final int result = x.compareTo( par.value );
				if ( result < 0 ) {
					par.left = new Node<>( par, x );
				}
				else {
					par.right = new Node<>( par, x );
				}
				fix( par );
			}
		}
		if ( bool ) {
			++size;
			hash ^= x.hashCode();
		}
		return bool;
	}

	public E get ( int index ) {
		if ( root == null || size <= index ) {
			throw new NullPointerException();
		}
		Node<E> now = root;
		while ( true ) {
			assert now != null;
			final int ls = now.left != null ? now.left.size : 0;
			if ( index < ls ) {
				now = now.left;
			}
			else if ( ls < index ) {
				now = now.right;
				index -= ls + 1;
			}
			else {
				break;
			}
		}
		return now.value;
	}

	public boolean remove ( final E x ) {
		final Node<E> n = getNode( x );
		if ( n == null ) {
			return false;
		}
		--size;
		hash ^= n.value.hashCode();
		delete( n );
		return true;
	}

	private void delete ( final Node<E> node ) {
		if ( node != null ) {
			if ( node.left == null && node.right == null ) {
				if ( node.parent != null ) {
					if ( node.parent.left == node ) {
						node.parent.left = null;
					}
					else {
						node.parent.right = null;
					}
					fix( node.parent );
				}
				else {
					root = null;
				}
				node.parent = null;
			}
			else {
				if ( node.left != null && node.right != null ) {
					final Node<E> rep = getFirstNode( node.right );
					node.value = rep.value;
					delete( rep );
				}
				else {
					final Node<E> rep = node.left != null ? node.left : node.right;
					rep.parent = node.parent;
					if ( node.parent != null ) {
						if ( node.parent.left == node ) {
							node.parent.left = rep;
						}
						else {
							node.parent.right = rep;
						}
						fix( node.parent );
					}
					else {
						root = rep;
					}
					node.parent = null;
				}
			}
		}
	}

	private Node<E> getNode ( final E x ) {
		Node<E> now = root;
		while ( now != null ) {
			final int result = x.compareTo( now.value );
			if ( result < 0 ) {
				now = now.left;
			}
			else if ( result > 0 ) {
				now = now.right;
			}
			else {
				break;
			}
		}
		return now;
	}

	public E first () {
		if ( root == null ) {
			return null;
		}
		return getFirstNode( root ).value;
	}

	private Node<E> getFirstNode ( Node<E> node ) {
		assert node != null;
		Node<E> par = null;
		while ( node != null ) {
			par = node;
			node = par.left;
		}
		return par;
	}

	public E last () {
		if ( root == null ) {
			return null;
		}
		return getLastNode( root ).value;
	}

	private Node<E> getLastNode ( Node<E> node ) {
		assert node != null;
		Node<E> par = null;
		while ( node != null ) {
			par = node;
			node = par.right;
		}
		return par;
	}

	public boolean contains ( final E x ) {
		if ( root == null ) {
			return false;
		}
		return getNode( x ) != null;
	}

	public E pollFirst () {
		if ( root == null ) {
			return null;
		}
		--size;
		final Node<E> min = getFirstNode( root );
		hash ^= min.value.hashCode();
		delete( min );
		return min.value;
	}

	public E pollLast () {
		if ( root == null ) {
			return null;
		}
		--size;
		final Node<E> max = getLastNode( root );
		hash ^= max.value.hashCode();
		delete( max );
		return max.value;
	}

	public E ceiling ( final E x ) {
		return ceiling( root, x );
	}

	private E ceiling ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result > 0 ) {
				node = node.right;
			}
			else if ( result < 0 ) {
				ans = node;
				node = node.left;
			}
			else {
				return x;
			}
		}
		return ans != null ? ans.value : null;
	}

	public E higher ( final E x ) {
		return higher( root, x );
	}

	private E higher ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result >= 0 ) {
				node = node.right;
			}
			else {
				ans = node;
				node = node.left;
			}
		}
		return ans != null ? ans.value : null;
	}

	public E floor ( final E x ) {
		return floor( root, x );
	}

	private E floor ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result < 0 ) {
				node = node.left;
			}
			else if ( result > 0 ) {
				ans = node;
				node = node.right;
			}
			else {
				return x;
			}
		}
		return ans != null ? ans.value : null;
	}

	public E lower ( final E x ) {
		return lower( root, x );
	}

	private E lower ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result <= 0 ) {
				node = node.left;
			}
			else {
				ans = node;
				node = node.right;
			}
		}
		return ans != null ? ans.value : null;
	}

	public void clear () {
		root = null;
		size = 0;
		hash = 0;
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public int size () {
		return size;
	}

	public ArrayList<E> toList () {
		final ArrayList<E> list = new ArrayList<>();
		if ( root != null ) {
			final ArrayDeque<Node<E>> deq = new ArrayDeque<>();
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node<E> now = deq.pollLast();
				if ( list.size() == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						list.add( now.value );
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list.get( list.size() - 1 ).compareTo( now.left.value ) < 0 ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					list.add( now.value );
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	@Override
	public String toString () {
		final ArrayList<E> list = toList();
		return list.toString();
	}

	@Override
	public boolean equals ( final Object o ) {
		if ( o instanceof final Tree<?> tree ) {
			if ( size == tree.size() ) {
				return false;
			}
			final ArrayList<E> list1 = toList();
			final ArrayList<?> list2 = tree.toList();
			for ( int i = 0; i < size; ++i ) {
				if ( !list1.get( i ).equals( list2.get( i ) ) ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode () {
		return hash;
	}

	/*
	 * 以下、平衡用メソッド
	 */

	private void fix ( Node<E> node ) {
		while ( node != null ) {
			final int lh = node.left == null ? 0 : node.left.height;
			final int rh = node.right == null ? 0 : node.right.height;
			if ( lh - rh > 1 ) {
				assert node.left != null;
				if ( node.left.right != null && node.left.right.height == lh - 1 ) {
					rotateL( node.left );
				}
				rotateR( node );
			}
			else if ( rh - lh > 1 ) {
				assert node.right != null;
				if ( node.right.left != null && node.right.left.height == rh - 1 ) {
					rotateR( node.right );
				}
				rotateL( node );
			}
			else {
				setStates( node );
			}
			node = node.parent;
		}
	}

	private void rotateR ( final Node<E> node ) {
		final Node<E> temp = node.left;
		node.left = temp.right;
		if ( node.left != null ) {
			node.left.parent = node;
		}
		temp.right = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void rotateL ( final Node<E> node ) {
		final Node<E> temp = node.right;
		node.right = temp.left;
		if ( node.right != null ) {
			node.right.parent = node;
		}
		temp.left = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void setStates ( final Node<E> node ) {
		final int lh = node.left != null ? node.left.height : 0;
		final int rh = node.right != null ? node.right.height : 0;
		node.height = Math.max( lh, rh ) + 1;
		final int ls = node.left != null ? node.left.size : 0;
		final int rs = node.right != null ? node.right.size : 0;
		node.size = ls + rs + 1;
	}
}

final class TreeInt {
	private Node root;
	private int size, hash;

	public TreeInt () {
		size = 0;
		root = null;
		hash = 0;
	}

	static final private class Node {
		int value;
		int height, size;
		Node left, right, parent;

		public Node ( final Node p, final int v ) {
			value = v;
			parent = p;
			height = 1;
			size = 1;
		}
	}

	public boolean add ( final int x ) {
		boolean bool = true;
		if ( root == null ) {
			root = new Node( null, x );
		}
		else {
			Node par;
			Node now = root;
			do {
				par = now;
				if ( x < now.value ) {
					now = now.left;
				}
				else if ( x > now.value ) {
					now = now.right;
				}
				else {
					bool = false;
					break;
				}
			} while ( now != null );
			if ( bool ) {
				if ( x < par.value ) {
					par.left = new Node( par, x );
				}
				else {
					par.right = new Node( par, x );
				}
				fix( par );
			}
		}
		if ( bool ) {
			++size;
			hash ^= x;
		}
		return bool;
	}

	public int get ( int index ) {
		if ( root == null || size <= index ) {
			throw new NullPointerException();
		}
		Node now = root;
		while ( true ) {
			assert now != null;
			final int ls = now.left != null ? now.left.size : 0;
			if ( index < ls ) {
				now = now.left;
			}
			else if ( ls < index ) {
				now = now.right;
				index -= ls + 1;
			}
			else {
				break;
			}
		}
		return now.value;
	}

	public boolean remove ( final int x ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return false;
		}
		--size;
		hash ^= n.value;
		delete( n );
		return true;
	}

	private void delete ( final Node node ) {
		if ( node != null ) {
			if ( node.left == null && node.right == null ) {
				if ( node.parent != null ) {
					if ( node.parent.left == node ) {
						node.parent.left = null;
					}
					else {
						node.parent.right = null;
					}
					fix( node.parent );
				}
				else {
					root = null;
				}
				node.parent = null;
			}
			else {
				if ( node.left != null && node.right != null ) {
					final Node rep = getFirstNode( node.right );
					node.value = rep.value;
					delete( rep );
				}
				else {
					final Node rep = node.left != null ? node.left : node.right;
					rep.parent = node.parent;
					if ( node.parent != null ) {
						if ( node.parent.left == node ) {
							node.parent.left = rep;
						}
						else {
							node.parent.right = rep;
						}
						fix( node.parent );
					}
					else {
						root = rep;
					}
					node.parent = null;
				}
			}
		}
	}

	private Node getNode ( final int x ) {
		Node now = root;
		while ( now != null ) {
			if ( x < now.value ) {
				now = now.left;
			}
			else if ( x > now.value ) {
				now = now.right;
			}
			else {
				break;
			}
		}
		return now;
	}

	public int first () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getFirstNode( root ).value;
	}

	private Node getFirstNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.left;
		}
		return par;
	}

	public int last () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getLastNode( root ).value;
	}

	private Node getLastNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.right;
		}
		return par;
	}

	public boolean contains ( final int x ) {
		if ( root == null ) {
			return false;
		}
		return getNode( x ) != null;
	}

	public int pollFirst () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node min = getFirstNode( root );
		hash ^= min.value;
		delete( min );
		return min.value;
	}

	public int pollLast () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node max = getLastNode( root );
		hash ^= max.value;
		delete( max );
		return max.value;
	}

	public int ceiling ( final int x ) {
		return ceiling( root, x );
	}

	private int ceiling ( Node node, final int x ) {
		Node ans = new Node( null, x - 1 );
		while ( node != null ) {
			if ( x > node.value ) {
				node = node.right;
			}
			else if ( x < node.value ) {
				ans = node;
				node = node.left;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public int higher ( final int x ) {
		return higher( root, x );
	}

	private int higher ( Node node, final int x ) {
		Node ans = new Node( null, x - 1 );
		while ( node != null ) {
			if ( x >= node.value ) {
				node = node.right;
			}
			else {
				ans = node;
				node = node.left;
			}
		}
		return ans.value;
	}

	public int floor ( final int x ) {
		return floor( root, x );
	}

	private int floor ( Node node, final int x ) {
		Node ans = new Node( null, x + 1 );
		while ( node != null ) {
			if ( x < node.value ) {
				node = node.left;
			}
			else if ( x > node.value ) {
				ans = node;
				node = node.right;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public int lower ( final int x ) {
		return lower( root, x );
	}

	private int lower ( Node node, final int x ) {
		Node ans = new Node( null, x + 1 );
		while ( node != null ) {
			if ( x <= node.value ) {
				node = node.left;
			}
			else {
				ans = node;
				node = node.right;
			}
		}
		return ans.value;
	}

	public void clear () {
		root = null;
		size = 0;
		hash = 0;
	}

	public boolean isEmpty () {
		return size == 0;
	}

	public int size () {
		return size;
	}

	public int[] toArray () {
		final int[] list = new int[size];
		if ( root != null ) {
			int index = 0;
			final ArrayDeque<Node> deq = new ArrayDeque<>();
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node now = deq.pollLast();
				if ( index == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						list[index++] = now.value;
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list[index - 1] < now.left.value ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					list[index++] = now.value;
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	@Override
	public String toString () {
		final int[] list = toArray();
		return Arrays.toString( list );
	}

	@Override
	public boolean equals ( final Object o ) {
		if ( o instanceof final TreeInt tree ) {
			if ( size == tree.size() ) {
				return false;
			}
			final int[] array1 = toArray();
			final int[] array2 = tree.toArray();
			for ( int i = 0; i < size; ++i ) {
				if ( array1[i] != array2[i] ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode () {
		return hash;
	}

	/*
	 * 以下、平衡用メソッド
	 */

	private void fix ( Node node ) {
		while ( node != null ) {
			final int lh = node.left == null ? 0 : node.left.height;
			final int rh = node.right == null ? 0 : node.right.height;
			if ( lh - rh > 1 ) {
				assert node.left != null;
				if ( node.left.right != null && node.left.right.height == lh - 1 ) {
					rotateL( node.left );
				}
				rotateR( node );
			}
			else if ( rh - lh > 1 ) {
				assert node.right != null;
				if ( node.right.left != null && node.right.left.height == rh - 1 ) {
					rotateR( node.right );
				}
				rotateL( node );
			}
			else {
				setStates( node );
			}
			node = node.parent;
		}
	}

	private void rotateR ( final Node node ) {
		final Node temp = node.left;
		node.left = temp.right;
		if ( node.left != null ) {
			node.left.parent = node;
		}
		temp.right = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void rotateL ( final Node node ) {
		final Node temp = node.right;
		node.right = temp.left;
		if ( node.right != null ) {
			node.right.parent = node;
		}
		temp.left = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void setStates ( final Node node ) {
		final int lh = node.left != null ? node.left.height : 0;
		final int rh = node.right != null ? node.right.height : 0;
		node.height = Math.max( lh, rh ) + 1;
		final int ls = node.left != null ? node.left.size : 0;
		final int rs = node.right != null ? node.right.size : 0;
		node.size = ls + rs + 1;
	}
}

final class TreeLong {
	private Node root;
	private int size, hash;

	public TreeLong () {
		size = 0;
		root = null;
		hash = 0;
	}

	static final private class Node {
		long value;
		int height, size;
		Node left, right, parent;

		public Node ( final Node p, final long v ) {
			value = v;
			parent = p;
			height = 1;
			size = 1;
		}
	}

	public boolean add ( final long x ) {
		boolean bool = true;
		if ( root == null ) {
			root = new Node( null, x );
		}
		else {
			Node par;
			Node now = root;
			do {
				par = now;
				if ( x < now.value ) {
					now = now.left;
				}
				else if ( x > now.value ) {
					now = now.right;
				}
				else {
					bool = false;
					break;
				}
			} while ( now != null );
			if ( bool ) {
				if ( x < par.value ) {
					par.left = new Node( par, x );
				}
				else {
					par.right = new Node( par, x );
				}
				fix( par );
			}
		}
		if ( bool ) {
			++size;
			hash ^= ( int )x;
		}
		return bool;
	}

	public long get ( int index ) {
		if ( root == null || size <= index ) {
			throw new NullPointerException();
		}
		Node now = root;
		while ( true ) {
			assert now != null;
			final int ls = now.left != null ? now.left.size : 0;
			if ( index < ls ) {
				now = now.left;
			}
			else if ( ls < index ) {
				now = now.right;
				index -= ls + 1;
			}
			else {
				break;
			}
		}
		return now.value;
	}

	public boolean remove ( final long x ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return false;
		}
		--size;
		hash ^= ( int )x;
		delete( n );
		return true;
	}

	private void delete ( final Node node ) {
		if ( node != null ) {
			if ( node.left == null && node.right == null ) {
				if ( node.parent != null ) {
					if ( node.parent.left == node ) {
						node.parent.left = null;
					}
					else {
						node.parent.right = null;
					}
					fix( node.parent );
				}
				else {
					root = null;
				}
				node.parent = null;
			}
			else {
				if ( node.left != null && node.right != null ) {
					final Node rep = getFirstNode( node.right );
					node.value = rep.value;
					delete( rep );
				}
				else {
					final Node rep = node.left != null ? node.left : node.right;
					rep.parent = node.parent;
					if ( node.parent != null ) {
						if ( node.parent.left == node ) {
							node.parent.left = rep;
						}
						else {
							node.parent.right = rep;
						}
						fix( node.parent );
					}
					else {
						root = rep;
					}
					node.parent = null;
				}
			}
		}
	}

	private Node getNode ( final long x ) {
		Node now = root;
		while ( now != null ) {
			if ( x < now.value ) {
				now = now.left;
			}
			else if ( x > now.value ) {
				now = now.right;
			}
			else {
				break;
			}
		}
		return now;
	}

	public long first () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getFirstNode( root ).value;
	}

	private Node getFirstNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.left;
		}
		return par;
	}

	public long last () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getLastNode( root ).value;
	}

	private Node getLastNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.right;
		}
		return par;
	}

	public boolean contains ( final long x ) {
		if ( root == null ) {
			return false;
		}
		return getNode( x ) != null;
	}

	public long pollFirst () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node min = getFirstNode( root );
		hash ^= ( int )min.value;
		delete( min );
		return min.value;
	}

	public long pollLast () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node max = getLastNode( root );
		hash ^= ( int )max.value;
		delete( max );
		return max.value;
	}

	public long ceiling ( final long x ) {
		return ceiling( root, x );
	}

	private long ceiling ( Node node, final long x ) {
		Node ans = new Node( null, x - 1 );
		while ( node != null ) {
			if ( x > node.value ) {
				node = node.right;
			}
			else if ( x < node.value ) {
				ans = node;
				node = node.left;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public long higher ( final long x ) {
		return higher( root, x );
	}

	private long higher ( Node node, final long x ) {
		Node ans = new Node( null, x - 1 );
		while ( node != null ) {
			if ( x >= node.value ) {
				node = node.right;
			}
			else {
				ans = node;
				node = node.left;
			}
		}
		return ans.value;
	}

	public long floor ( final long x ) {
		return floor( root, x );
	}

	private long floor ( Node node, final long x ) {
		Node ans = new Node( null, x + 1 );
		while ( node != null ) {
			if ( x < node.value ) {
				node = node.left;
			}
			else if ( x > node.value ) {
				ans = node;
				node = node.right;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public long lower ( final long x ) {
		return lower( root, x );
	}

	private long lower ( Node node, final long x ) {
		Node ans = new Node( null, x + 1 );
		while ( node != null ) {
			if ( x <= node.value ) {
				node = node.left;
			}
			else {
				ans = node;
				node = node.right;
			}
		}
		return ans.value;
	}

	public int size () {
		return size;
	}

	public long[] toArray () {
		final long[] list = new long[size];
		if ( root != null ) {
			int index = 0;
			final ArrayDeque<Node> deq = new ArrayDeque<>();
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node now = deq.pollLast();
				if ( index == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						list[index++] = now.value;
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list[index - 1] < now.left.value ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					list[index++] = now.value;
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	@Override
	public String toString () {
		final long[] list = toArray();
		return Arrays.toString( list );
	}

	@Override
	public boolean equals ( final Object o ) {
		if ( o instanceof final TreeLong tree ) {
			if ( size == tree.size() ) {
				return false;
			}
			final long[] array1 = toArray();
			final long[] array2 = tree.toArray();
			for ( int i = 0; i < size; ++i ) {
				if ( array1[i] != array2[i] ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode () {
		return hash;
	}

	/*
	 * 以下、平衡用メソッド
	 */

	private void fix ( Node node ) {
		while ( node != null ) {
			final int lh = node.left == null ? 0 : node.left.height;
			final int rh = node.right == null ? 0 : node.right.height;
			if ( lh - rh > 1 ) {
				assert node.left != null;
				if ( node.left.right != null && node.left.right.height == lh - 1 ) {
					rotateL( node.left );
				}
				rotateR( node );
			}
			else if ( rh - lh > 1 ) {
				assert node.right != null;
				if ( node.right.left != null && node.right.left.height == rh - 1 ) {
					rotateR( node.right );
				}
				rotateL( node );
			}
			else {
				setStates( node );
			}
			node = node.parent;
		}
	}

	private void rotateR ( final Node node ) {
		final Node temp = node.left;
		node.left = temp.right;
		if ( node.left != null ) {
			node.left.parent = node;
		}
		temp.right = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void rotateL ( final Node node ) {
		final Node temp = node.right;
		node.right = temp.left;
		if ( node.right != null ) {
			node.right.parent = node;
		}
		temp.left = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void setStates ( final Node node ) {
		final int lh = node.left != null ? node.left.height : 0;
		final int rh = node.right != null ? node.right.height : 0;
		node.height = Math.max( lh, rh ) + 1;
		final int ls = node.left != null ? node.left.size : 0;
		final int rs = node.right != null ? node.right.size : 0;
		node.size = ls + rs + 1;
	}
}

final class TreeMulti<E extends Comparable<E>> {
	private Node<E> root;
	private long size;
	private int uniqueSize;
	private int hash;

	public TreeMulti () {
		size = 0;
		uniqueSize = 0;
		root = null;
		hash = 0;
	}

	static final private class Node<E> {
		E value;
		long count, size;
		int height;
		Node<E> left, right, parent;

		public Node ( final Node<E> p, final E v, final long c ) {
			value = v;
			parent = p;
			count = c;
			height = 1;
			size = c;
		}
	}

	public void add ( final E x ) {
		if ( root == null ) {
			root = new Node<>( null, x, 1 );
			++uniqueSize;
		}
		else {
			Node<E> par;
			Node<E> now = root;
			boolean bool = true;
			do {
				par = now;
				final int result = x.compareTo( now.value );
				if ( result < 0 ) {
					now = now.left;
				}
				else if ( result > 0 ) {
					now = now.right;
				}
				else {
					bool = false;
					++now.count;
					break;
				}
			} while ( now != null );
			if ( bool ) {
				++uniqueSize;
				final int result = x.compareTo( par.value );
				if ( result < 0 ) {
					par.left = new Node<>( par, x, 1 );
				}
				else {
					par.right = new Node<>( par, x, 1 );
				}
			}
			fix( par );
		}
		++size;
		hash ^= x.hashCode();
	}

	public void add ( final E x, final long sum ) {
		if ( root == null ) {
			root = new Node<>( null, x, sum );
			++uniqueSize;
		}
		else {
			Node<E> par;
			Node<E> now = root;
			boolean bool = true;
			do {
				par = now;
				final int result = x.compareTo( now.value );
				if ( result < 0 ) {
					now = now.left;
				}
				else if ( result > 0 ) {
					now = now.right;
				}
				else {
					bool = false;
					now.count += sum;
					fix( now );
					break;
				}
			} while ( now != null );
			if ( bool ) {
				++uniqueSize;
				final int result = x.compareTo( par.value );
				if ( result < 0 ) {
					par.left = new Node<>( par, x, sum );
				}
				else {
					par.right = new Node<>( par, x, sum );
				}
				fix( par );
			}
		}
		size += sum;
		hash ^= sum % 2 == 1 ? x.hashCode() : 0;
	}

	public E get ( long index ) {
		if ( root == null || size <= index ) {
			throw new NullPointerException();
		}
		Node<E> now = root;
		while ( true ) {
			assert now != null;
			final long ls = now.left != null ? now.left.size : 0;
			if ( index < ls ) {
				now = now.left;
			}
			else if ( ls + now.count <= index ) {
				index -= ls + now.count;
				now = now.right;
			}
			else {
				break;
			}
		}
		return now.value;
	}

	public boolean remove ( final E x ) {
		final Node<E> n = getNode( x );
		if ( n == null ) {
			return false;
		}
		--size;
		hash ^= n.hashCode();
		delete( n );
		return true;
	}

	public long remove ( final E x, final long sum ) {
		final Node<E> n = getNode( x );
		if ( n == null ) {
			return 0;
		}
		final long ans = Math.min( sum, n.count );
		size -= ans;
		hash ^= ans % 2 == 1 ? n.hashCode() : 0;
		n.count -= ans - 1;
		delete( n );
		return ans;
	}

	public long removeAll ( final E x ) {
		final Node<E> n = getNode( x );
		if ( n == null ) {
			return 0;
		}
		size -= n.count;
		final long ans = n.count;
		hash ^= ans % 2 == 1 ? n.hashCode() : 0;
		n.count = 0;
		delete( n );
		return ans;
	}

	private void delete ( final Node<E> node ) {
		if ( node != null ) {
			if ( node.count > 1 ) {
				--node.count;
				fix( node );
				return;
			}
			if ( node.left == null && node.right == null ) {
				if ( node.parent != null ) {
					if ( node.parent.left == node ) {
						node.parent.left = null;
					}
					else {
						node.parent.right = null;
					}
					fix( node.parent );
				}
				else {
					root = null;
				}
				node.parent = null;
				--uniqueSize;
			}
			else {
				if ( node.left != null && node.right != null ) {
					final Node<E> rep = getFirstNode( node.right );
					node.value = rep.value;
					node.count = rep.count;
					rep.count = 0;
					delete( rep );
				}
				else {
					final Node<E> rep = node.left != null ? node.left : node.right;
					rep.parent = node.parent;
					if ( node.parent != null ) {
						if ( node.parent.left == node ) {
							node.parent.left = rep;
						}
						else {
							node.parent.right = rep;
						}
						fix( node.parent );
					}
					else {
						root = rep;
					}
					node.parent = null;
					--uniqueSize;
				}
			}
		}
	}

	private Node<E> getNode ( final E x ) {
		Node<E> now = root;
		while ( now != null ) {
			final int result = x.compareTo( now.value );
			if ( result < 0 ) {
				now = now.left;
			}
			else if ( result > 0 ) {
				now = now.right;
			}
			else {
				break;
			}
		}
		return now;
	}

	public E first () {
		if ( root == null ) {
			return null;
		}
		return getFirstNode( root ).value;
	}

	private Node<E> getFirstNode ( Node<E> node ) {
		assert node != null;
		Node<E> par = null;
		while ( node != null ) {
			par = node;
			node = par.left;
		}
		return par;
	}

	public E last () {
		if ( root == null ) {
			return null;
		}
		return getLastNode( root ).value;
	}

	private Node<E> getLastNode ( Node<E> node ) {
		assert node != null;
		Node<E> par = null;
		while ( node != null ) {
			par = node;
			node = par.right;
		}
		return par;
	}

	public boolean contains ( final E x ) {
		if ( root == null ) {
			return false;
		}
		return getNode( x ) != null;
	}

	public long sum ( final E x ) {
		if ( root == null ) {
			return 0;
		}
		Node<E> node = getNode( x );
		return node != null ? node.count : 0;
	}

	public E pollFirst () {
		if ( root == null ) {
			return null;
		}
		--size;
		final Node<E> min = getFirstNode( root );
		hash ^= min.value.hashCode();
		final E ans = min.value;
		delete( min );
		return ans;
	}

	public E pollLast () {
		if ( root == null ) {
			return null;
		}
		--size;
		final Node<E> max = getLastNode( root );
		hash ^= max.value.hashCode();
		final E ans = max.value;
		delete( max );
		return ans;
	}

	public E ceiling ( final E x ) {
		return ceiling( root, x );
	}

	private E ceiling ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result > 0 ) {
				node = node.right;
			}
			else if ( result < 0 ) {
				ans = node;
				node = node.left;
			}
			else {
				return x;
			}
		}
		return ans != null ? ans.value : null;
	}

	public E higher ( final E x ) {
		return higher( root, x );
	}

	private E higher ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result >= 0 ) {
				node = node.right;
			}
			else {
				ans = node;
				node = node.left;
			}
		}
		return ans != null ? ans.value : null;
	}

	public E floor ( final E x ) {
		return floor( root, x );
	}

	private E floor ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result < 0 ) {
				node = node.left;
			}
			else if ( result > 0 ) {
				ans = node;
				node = node.right;
			}
			else {
				return x;
			}
		}
		return ans != null ? ans.value : null;
	}

	public E lower ( final E x ) {
		return lower( root, x );
	}

	private E lower ( Node<E> node, final E x ) {
		Node<E> ans = null;
		while ( node != null ) {
			final int result = x.compareTo( node.value );
			if ( result <= 0 ) {
				node = node.left;
			}
			else {
				ans = node;
				node = node.right;
			}
		}
		return ans != null ? ans.value : null;
	}

	public int size () {
		return uniqueSize;
	}

	public long sumSize () {
		return size;
	}

	public ArrayList<E> toList () {
		final ArrayList<E> list = new ArrayList<>();
		if ( root != null ) {
			final ArrayDeque<Node<E>> deq = new ArrayDeque<>();
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node<E> now = deq.pollLast();
				if ( list.size() == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						for ( int i = 0; i < now.count; ++i ) {
							list.add( now.value );
						}
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list.get( list.size() - 1 ).compareTo( now.left.value ) < 0 ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					for ( int i = 0; i < now.count; ++i ) {
						list.add( now.value );
					}
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	@Override
	public String toString () {
		final ArrayList<E> list = toList();
		return list.toString();
	}

	@Override
	public boolean equals ( final Object o ) {
		if ( o instanceof final TreeMulti<?> tree ) {
			if ( size == tree.size() ) {
				return false;
			}
			final ArrayList<E> array1 = toList();
			final ArrayList<?> array2 = tree.toList();
			for ( int i = 0; i < size; ++i ) {
				if ( !array1.get( i ).equals( array2.get( i ) ) ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode () {
		return hash;
	}

	/*
	 * 以下、平衡用メソッド
	 */

	private void fix ( Node<E> node ) {
		while ( node != null ) {
			final int lh = node.left == null ? 0 : node.left.height;
			final int rh = node.right == null ? 0 : node.right.height;
			if ( lh - rh > 1 ) {
				assert node.left != null;
				if ( node.left.right != null && node.left.right.height == lh - 1 ) {
					rotateL( node.left );
					setStates( node.left );
				}
				rotateR( node );
			}
			else if ( rh - lh > 1 ) {
				assert node.right != null;
				if ( node.right.left != null && node.right.left.height == rh - 1 ) {
					rotateR( node.right );
					setStates( node.right );
				}
				rotateL( node );
			}
			setStates( node );
			node = node.parent;
		}
	}

	private void rotateR ( final Node<E> node ) {
		final Node<E> temp = node.left;
		node.left = temp.right;
		if ( node.left != null ) {
			node.left.parent = node;
		}
		temp.right = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void rotateL ( final Node<E> node ) {
		final Node<E> temp = node.right;
		node.right = temp.left;
		if ( node.right != null ) {
			node.right.parent = node;
		}
		temp.left = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void setStates ( final Node<E> node ) {
		final int lh = node.left != null ? node.left.height : 0;
		final int rh = node.right != null ? node.right.height : 0;
		node.height = Math.max( lh, rh ) + 1;
		final long ls = node.left != null ? node.left.size : 0;
		final long rs = node.right != null ? node.right.size : 0;
		node.size = ls + rs + node.count;
	}
}

final class TreeMultiInt {
	private Node root;
	private long size;
	private int uniqueSize;
	private int hash;

	public TreeMultiInt () {
		size = 0;
		uniqueSize = 0;
		root = null;
		hash = 0;
	}

	static final private class Node {
		int value;
		long count, size;
		int height;
		Node left, right, parent;

		public Node ( final Node p, final int v, final long c ) {
			value = v;
			parent = p;
			count = c;
			height = 1;
			size = c;
		}
	}

	public void add ( final int x ) {
		if ( root == null ) {
			root = new Node( null, x, 1 );
			++uniqueSize;
		}
		else {
			Node par;
			Node now = root;
			boolean bool = true;
			do {
				par = now;
				if ( x < now.value ) {
					now = now.left;
				}
				else if ( x > now.value ) {
					now = now.right;
				}
				else {
					bool = false;
					++now.count;
					break;
				}
			} while ( now != null );
			if ( bool ) {
				++uniqueSize;
				if ( x < par.value ) {
					par.left = new Node( par, x, 1 );
				}
				else {
					par.right = new Node( par, x, 1 );
				}
			}
			fix( par );
		}
		++size;
		hash ^= x;
	}

	public void add ( final int x, final long sum ) {
		if ( root == null ) {
			root = new Node( null, x, sum );
			++uniqueSize;
		}
		else {
			Node par;
			Node now = root;
			boolean bool = true;
			do {
				par = now;
				if ( x < now.value ) {
					now = now.left;
				}
				else if ( x > now.value ) {
					now = now.right;
				}
				else {
					bool = false;
					now.count += sum;
					fix( now );
					break;
				}
			} while ( now != null );
			if ( bool ) {
				++uniqueSize;
				if ( x < par.value ) {
					par.left = new Node( par, x, sum );
				}
				else {
					par.right = new Node( par, x, sum );
				}
				fix( par );
			}
		}
		size += sum;
		if ( sum % 2 == 1 ) {
			hash ^= x;
		}
	}

	public int get ( long index ) {
		if ( root == null || size <= index ) {
			throw new NullPointerException();
		}
		Node now = root;
		while ( true ) {
			assert now != null;
			final long ls = now.left != null ? now.left.size : 0;
			if ( index < ls ) {
				now = now.left;
			}
			else if ( ls + now.count <= index ) {
				index -= ls + now.count;
				now = now.right;
			}
			else {
				break;
			}
		}
		return now.value;
	}

	public boolean remove ( final int x ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return false;
		}
		--size;
		hash ^= x;
		delete( n );
		return true;
	}

	public long remove ( final int x, final long sum ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return 0;
		}
		final long ans = Math.min( sum, n.count );
		size -= ans;
		n.count -= ans - 1;
		if ( ans % 2 == 1 ) {
			hash ^= x;
		}
		delete( n );
		return ans;
	}

	public long removeAll ( final int x ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return 0;
		}
		size -= n.count;
		final long ans = n.count;
		if ( n.count % 2 == 1 ) {
			hash ^= x;
		}
		n.count = 0;
		delete( n );
		return ans;
	}

	private void delete ( final Node node ) {
		if ( node != null ) {
			if ( node.count > 1 ) {
				--node.count;
				fix( node );
				return;
			}
			if ( node.left == null && node.right == null ) {
				if ( node.parent != null ) {
					if ( node.parent.left == node ) {
						node.parent.left = null;
					}
					else {
						node.parent.right = null;
					}
					fix( node.parent );
				}
				else {
					root = null;
				}
				node.parent = null;
				--uniqueSize;
			}
			else {
				if ( node.left != null && node.right != null ) {
					final Node rep = getFirstNode( node.right );
					node.value = rep.value;
					node.count = rep.count;
					rep.count = 0;
					delete( rep );
				}
				else {
					final Node rep = node.left != null ? node.left : node.right;
					rep.parent = node.parent;
					if ( node.parent != null ) {
						if ( node.parent.left == node ) {
							node.parent.left = rep;
						}
						else {
							node.parent.right = rep;
						}
						fix( node.parent );
					}
					else {
						root = rep;
					}
					node.parent = null;
					--uniqueSize;
				}
			}
		}
	}

	private Node getNode ( final int x ) {
		Node now = root;
		while ( now != null ) {
			if ( x < now.value ) {
				now = now.left;
			}
			else if ( x > now.value ) {
				now = now.right;
			}
			else {
				break;
			}
		}
		return now;
	}

	public int first () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getFirstNode( root ).value;
	}

	private Node getFirstNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.left;
		}
		return par;
	}

	public int last () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getLastNode( root ).value;
	}

	private Node getLastNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.right;
		}
		return par;
	}

	public boolean contains ( final int x ) {
		if ( root == null ) {
			return false;
		}
		return getNode( x ) != null;
	}

	public long sum ( final int x ) {
		if ( root == null ) {
			return 0;
		}
		Node node = getNode( x );
		return node != null ? node.count : 0;
	}

	public int pollFirst () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node min = getFirstNode( root );
		hash ^= min.value;
		final int ans = min.value;
		delete( min );
		return ans;
	}

	public int pollLast () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node max = getLastNode( root );
		hash ^= max.value;
		final int ans = max.value;
		delete( max );
		return ans;
	}

	public int ceiling ( final int x ) {
		return ceiling( root, x );
	}

	private int ceiling ( Node node, final int x ) {
		Node ans = new Node( null, x - 1, 0 );
		while ( node != null ) {
			if ( x > node.value ) {
				node = node.right;
			}
			else if ( x < node.value ) {
				ans = node;
				node = node.left;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public int higher ( final int x ) {
		return higher( root, x );
	}

	private int higher ( Node node, final int x ) {
		Node ans = new Node( null, x - 1, 0 );
		while ( node != null ) {
			if ( x >= node.value ) {
				node = node.right;
			}
			else {
				ans = node;
				node = node.left;
			}
		}
		return ans.value;
	}

	public int floor ( final int x ) {
		return floor( root, x );
	}

	private int floor ( Node node, final int x ) {
		Node ans = new Node( null, x + 1, 0 );
		while ( node != null ) {
			if ( x < node.value ) {
				node = node.left;
			}
			else if ( x > node.value ) {
				ans = node;
				node = node.right;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public int lower ( final int x ) {
		return lower( root, x );
	}

	private int lower ( Node node, final int x ) {
		Node ans = new Node( null, x + 1, 0 );
		while ( node != null ) {
			if ( x <= node.value ) {
				node = node.left;
			}
			else {
				ans = node;
				node = node.right;
			}
		}
		return ans.value;
	}

	public int size () {
		return uniqueSize;
	}

	public long sumSize () {
		return size;
	}

	public long[][] toArray () {
		final long[][] list = new long[uniqueSize][2];
		if ( root != null ) {
			int index = 0;
			final ArrayDeque<Node> deq = new ArrayDeque<>( root.height << 1 );
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node now = deq.pollLast();
				if ( index == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						list[index][0] = now.value;
						list[index++][1] = now.count;
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list[index - 1][0] < now.left.value ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					list[index][0] = now.value;
					list[index++][1] = now.count;
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	public int[] toOneArray () {
		final int[] list = new int[( int )size];
		if ( root != null ) {
			int index = 0;
			final ArrayDeque<Node> deq = new ArrayDeque<>();
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node now = deq.pollLast();
				if ( index == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						for ( int i = 0; i < now.count; ++i ) {
							list[index++] = now.value;
						}
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list[index - 1] < now.left.value ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					for ( int i = 0; i < now.count; ++i ) {
						list[index++] = now.value;
					}
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	@Override
	public String toString () {
		final int[] list = toOneArray();
		return Arrays.toString( list );
	}

	@Override
	public boolean equals ( final Object o ) {
		if ( o instanceof final TreeMultiInt tree ) {
			if ( size == tree.size() ) {
				return false;
			}
			final long[][] array1 = toArray();
			final long[][] array2 = tree.toArray();
			for ( int i = 0; i < size; ++i ) {
				if ( array1[i][0] != array2[i][0] || array1[i][1] != array2[i][1] ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode () {
		return hash;
	}

	/*
	 * 以下、平衡用メソッド
	 */

	private void fix ( Node node ) {
		while ( node != null ) {
			final int lh = node.left == null ? 0 : node.left.height;
			final int rh = node.right == null ? 0 : node.right.height;
			if ( lh - rh > 1 ) {
				assert node.left != null;
				if ( node.left.right != null && node.left.right.height == lh - 1 ) {
					rotateL( node.left );
					setStates( node.left );
				}
				rotateR( node );
			}
			else if ( rh - lh > 1 ) {
				assert node.right != null;
				if ( node.right.left != null && node.right.left.height == rh - 1 ) {
					rotateR( node.right );
					setStates( node.right );
				}
				rotateL( node );
			}
			setStates( node );
			node = node.parent;
		}
	}

	private void rotateR ( final Node node ) {
		final Node temp = node.left;
		node.left = temp.right;
		if ( node.left != null ) {
			node.left.parent = node;
		}
		temp.right = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void rotateL ( final Node node ) {
		final Node temp = node.right;
		node.right = temp.left;
		if ( node.right != null ) {
			node.right.parent = node;
		}
		temp.left = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void setStates ( final Node node ) {
		final int lh = node.left != null ? node.left.height : 0;
		final int rh = node.right != null ? node.right.height : 0;
		node.height = Math.max( lh, rh ) + 1;
		final long ls = node.left != null ? node.left.size : 0;
		final long rs = node.right != null ? node.right.size : 0;
		node.size = ls + rs + node.count;
	}
}

final class TreeMultiLong {
	private Node root;
	private long size;
	private int uniqueSize;
	private int hash;

	public TreeMultiLong () {
		size = 0;
		uniqueSize = 0;
		root = null;
		hash = 0;
	}

	static final private class Node {
		long value;
		long count, size;
		int height;
		Node left, right, parent;

		public Node ( final Node p, final long v, final long c ) {
			value = v;
			parent = p;
			count = c;
			height = 1;
			size = c;
		}
	}

	public void add ( final long x ) {
		if ( root == null ) {
			root = new Node( null, x, 1 );
			++uniqueSize;
		}
		else {
			Node par;
			Node now = root;
			boolean bool = true;
			do {
				par = now;
				if ( x < now.value ) {
					now = now.left;
				}
				else if ( x > now.value ) {
					now = now.right;
				}
				else {
					bool = false;
					++now.count;
					break;
				}
			} while ( now != null );
			if ( bool ) {
				++uniqueSize;
				if ( x < par.value ) {
					par.left = new Node( par, x, 1 );
				}
				else {
					par.right = new Node( par, x, 1 );
				}
			}
			fix( par );
		}
		++size;
		hash ^= ( int )x;
	}

	public void add ( final long x, final long sum ) {
		if ( root == null ) {
			root = new Node( null, x, sum );
			++uniqueSize;
		}
		else {
			Node par;
			Node now = root;
			boolean bool = true;
			do {
				par = now;
				if ( x < now.value ) {
					now = now.left;
				}
				else if ( x > now.value ) {
					now = now.right;
				}
				else {
					bool = false;
					now.count += sum;
					fix( now );
					break;
				}
			} while ( now != null );
			if ( bool ) {
				++uniqueSize;
				if ( x < par.value ) {
					par.left = new Node( par, x, sum );
				}
				else {
					par.right = new Node( par, x, sum );
				}
				fix( par );
			}
		}
		size += sum;
		if ( sum % 2 == 1 ) {
			hash ^= ( int )x;
		}
	}

	public long get ( long index ) {
		if ( root == null || size <= index ) {
			throw new NullPointerException();
		}
		Node now = root;
		while ( true ) {
			assert now != null;
			final long ls = now.left != null ? now.left.size : 0;
			if ( index < ls ) {
				now = now.left;
			}
			else if ( ls + now.count <= index ) {
				index -= ls + now.count;
				now = now.right;
			}
			else {
				break;
			}
		}
		return now.value;
	}

	public boolean remove ( final long x ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return false;
		}
		--size;
		hash ^= x;
		delete( n );
		return true;
	}

	public long remove ( final long x, final long sum ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return 0;
		}
		final long ans = Math.min( sum, n.count );
		size -= ans;
		n.count -= ans - 1;
		if ( ans % 2 == 1 ) {
			hash ^= ( int )x;
		}
		delete( n );
		return ans;
	}

	public long removeAll ( final long x ) {
		final Node n = getNode( x );
		if ( n == null ) {
			return 0;
		}
		size -= n.count;
		final long ans = n.count;
		if ( n.count % 2 == 1 ) {
			hash ^= ( int )x;
		}
		n.count = 0;
		delete( n );
		return ans;
	}

	private void delete ( final Node node ) {
		if ( node != null ) {
			if ( node.count > 1 ) {
				--node.count;
				fix( node );
				return;
			}
			if ( node.left == null && node.right == null ) {
				if ( node.parent != null ) {
					if ( node.parent.left == node ) {
						node.parent.left = null;
					}
					else {
						node.parent.right = null;
					}
					fix( node.parent );
				}
				else {
					root = null;
				}
				node.parent = null;
				--uniqueSize;
			}
			else {
				if ( node.left != null && node.right != null ) {
					final Node rep = getFirstNode( node.right );
					node.value = rep.value;
					node.count = rep.count;
					rep.count = 0;
					delete( rep );
				}
				else {
					final Node rep = node.left != null ? node.left : node.right;
					rep.parent = node.parent;
					if ( node.parent != null ) {
						if ( node.parent.left == node ) {
							node.parent.left = rep;
						}
						else {
							node.parent.right = rep;
						}
						fix( node.parent );
					}
					else {
						root = rep;
					}
					node.parent = null;
					--uniqueSize;
				}
			}
		}
	}

	private Node getNode ( final long x ) {
		Node now = root;
		while ( now != null ) {
			if ( x < now.value ) {
				now = now.left;
			}
			else if ( x > now.value ) {
				now = now.right;
			}
			else {
				break;
			}
		}
		return now;
	}

	public long first () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getFirstNode( root ).value;
	}

	private Node getFirstNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.left;
		}
		return par;
	}

	public long last () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		return getLastNode( root ).value;
	}

	private Node getLastNode ( Node node ) {
		assert node != null;
		Node par = null;
		while ( node != null ) {
			par = node;
			node = par.right;
		}
		return par;
	}

	public boolean contains ( final long x ) {
		if ( root == null ) {
			return false;
		}
		return getNode( x ) != null;
	}

	public long sum ( final long x ) {
		if ( root == null ) {
			return 0;
		}
		final Node node = getNode( x );
		return node != null ? node.count : 0;
	}

	public long pollFirst () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node min = getFirstNode( root );
		hash ^= ( int )min.value;
		final long ans = min.value;
		delete( min );
		return ans;
	}

	public long pollLast () {
		if ( root == null ) {
			throw new NullPointerException();
		}
		--size;
		final Node max = getLastNode( root );
		hash ^= ( int )max.value;
		final long ans = max.value;
		delete( max );
		return ans;
	}

	public long ceiling ( final long x ) {
		return ceiling( root, x );
	}

	private long ceiling ( Node node, final long x ) {
		Node ans = new Node( null, x - 1, 0 );
		while ( node != null ) {
			if ( x > node.value ) {
				node = node.right;
			}
			else if ( x < node.value ) {
				ans = node;
				node = node.left;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public long higher ( final long x ) {
		return higher( root, x );
	}

	private long higher ( Node node, final long x ) {
		Node ans = new Node( null, x - 1, 0 );
		while ( node != null ) {
			if ( x >= node.value ) {
				node = node.right;
			}
			else {
				ans = node;
				node = node.left;
			}
		}
		return ans.value;
	}

	public long floor ( final long x ) {
		return floor( root, x );
	}

	private long floor ( Node node, final long x ) {
		Node ans = new Node( null, x + 1, 0 );
		while ( node != null ) {
			if ( x < node.value ) {
				node = node.left;
			}
			else if ( x > node.value ) {
				ans = node;
				node = node.right;
			}
			else {
				return x;
			}
		}
		return ans.value;
	}

	public long lower ( final long x ) {
		return lower( root, x );
	}

	private long lower ( Node node, final long x ) {
		Node ans = new Node( null, x + 1, 0 );
		while ( node != null ) {
			if ( x <= node.value ) {
				node = node.left;
			}
			else {
				ans = node;
				node = node.right;
			}
		}
		return ans.value;
	}

	public int size () {
		return uniqueSize;
	}

	public long sumSize () {
		return size;
	}

	public long[][] toArray () {
		final long[][] list = new long[uniqueSize][2];
		if ( root != null ) {
			int index = 0;
			ArrayDeque<Node> deq = new ArrayDeque<>( root.height << 1 );
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node now = deq.pollLast();
				if ( index == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						list[index][0] = now.value;
						list[index++][1] = now.count;
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list[index - 1][0] < now.left.value ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					list[index][0] = now.value;
					list[index++][1] = now.count;
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	public long[] toOneArray () {
		final long[] list = new long[( int )size];
		if ( root != null ) {
			int index = 0;
			final ArrayDeque<Node> deq = new ArrayDeque<>();
			deq.add( root );
			while ( !deq.isEmpty() ) {
				final Node now = deq.pollLast();
				if ( index == 0 ) {
					if ( now.left != null ) {
						deq.add( now );
						deq.add( now.left );
					}
					else {
						for ( int i = 0; i < now.count; ++i ) {
							list[index++] = now.value;
						}
						if ( now.right != null ) {
							deq.add( now.right );
						}
					}
				}
				else if ( now.left != null && list[index - 1] < now.left.value ) {
					deq.add( now );
					deq.add( now.left );
				}
				else {
					for ( int i = 0; i < now.count; ++i ) {
						list[index++] = now.value;
					}
					if ( now.right != null ) {
						deq.add( now.right );
					}
				}
			}
		}
		return list;
	}

	@Override
	public String toString () {
		final long[] list = toOneArray();
		return Arrays.toString( list );
	}

	@Override
	public boolean equals ( final Object o ) {
		if ( o instanceof final TreeMultiInt tree ) {
			if ( size == tree.size() ) {
				return false;
			}
			final long[][] array1 = toArray();
			final long[][] array2 = tree.toArray();
			for ( int i = 0; i < size; ++i ) {
				if ( array1[i][0] != array2[i][0] || array1[i][1] != array2[i][1] ) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode () {
		return hash;
	}

	/*
	 * 以下、平衡用メソッド
	 */

	private void fix ( Node node ) {
		while ( node != null ) {
			final int lh = node.left == null ? 0 : node.left.height;
			final int rh = node.right == null ? 0 : node.right.height;
			if ( lh - rh > 1 ) {
				assert node.left != null;
				if ( node.left.right != null && node.left.right.height == lh - 1 ) {
					rotateL( node.left );
					setStates( node.left );
				}
				rotateR( node );
			}
			else if ( rh - lh > 1 ) {
				assert node.right != null;
				if ( node.right.left != null && node.right.left.height == rh - 1 ) {
					rotateR( node.right );
					setStates( node.right );
				}
				rotateL( node );
			}
			setStates( node );
			node = node.parent;
		}
	}

	private void rotateR ( final Node node ) {
		final Node temp = node.left;
		node.left = temp.right;
		if ( node.left != null ) {
			node.left.parent = node;
		}
		temp.right = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void rotateL ( final Node node ) {
		final Node temp = node.right;
		node.right = temp.left;
		if ( node.right != null ) {
			node.right.parent = node;
		}
		temp.left = node;
		temp.parent = node.parent;
		if ( node.parent != null ) {
			if ( node.parent.left == node ) {
				node.parent.left = temp;
			}
			else {
				node.parent.right = temp;
			}
		}
		else {
			root = temp;
		}
		node.parent = temp;
		setStates( node );
	}

	private void setStates ( final Node node ) {
		final int lh = node.left != null ? node.left.height : 0;
		final int rh = node.right != null ? node.right.height : 0;
		node.height = Math.max( lh, rh ) + 1;
		final long ls = node.left != null ? node.left.size : 0;
		final long rs = node.right != null ? node.right.size : 0;
		node.size = ls + rs + node.count;
	}
}

final class SimpleScanner {
	private final int BUFF_SIZE = 1 << 17;
	private final InputStream is;
	private final byte[] buff;
	private int point, length;

	public SimpleScanner ( final InputStream is ) {
		this.is = is;
		buff = new byte[BUFF_SIZE];
		point = length = 0;
	}

	private void reload () {
		do {
			try {
				length = is.read( buff, point = 0, BUFF_SIZE );
			} catch ( final IOException e ) {
				e.printStackTrace();
				System.exit( 1 );
			}
		} while ( length == -1 );
	}

	private byte read () {
		if ( point == length ) {
			reload();
		}
		return buff[point++];
	}

	public byte nextByte () {
		byte c = read();
		while ( c <= ' ' ) {
			c = read();
		}
		return c;
	}

	public int nextInt () {
		int ans = 0;
		byte c = nextByte();
		final boolean negate = c == '-';
		if ( !MathFunction.rangeCheckClose( c, '0', '9' ) ) {
			c = read();
		}
		while ( MathFunction.rangeCheckClose( c, '0', '9' ) ) {
			ans = ans * 10 + c - '0';
			c = read();
		}
		return negate ? -ans : ans;
	}

	public long nextLong () {
		long ans = 0;
		byte c = nextByte();
		final boolean negate = c == '-';
		if ( !MathFunction.rangeCheckClose( c, '0', '9' ) ) {
			c = read();
		}
		while ( MathFunction.rangeCheckClose( c, '0', '9' ) ) {
			ans = ans * 10 + c - '0';
			c = read();
		}
		return negate ? -ans : ans;
	}

	public char nextChar () {
		return ( char )nextByte();
	}

	public String next () {
		final StringBuilder ans = new StringBuilder();
		byte c = nextByte();
		while ( c > ' ' ) {
			ans.append( ( char )c );
			c = read();
		}
		return ans.toString();
	}

	public byte[] nextByte ( final int n ) {
		final byte[] ans = new byte[n];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextByte();
		}
		return ans;
	}

	public int[] nextInt ( final int n ) {
		final int[] ans = new int[n];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextInt();
		}
		return ans;
	}

	public long[] nextLong ( final int n ) {
		final long[] ans = new long[n];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextLong();
		}
		return ans;
	}

	public String[] next ( final int n ) {
		final String[] ans = new String[n];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = next();
		}
		return ans;
	}

	public byte[][] nextByte ( final int n, final int m ) {
		final byte[][] ans = new byte[n][];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextByte( m );
		}
		return ans;
	}

	public int[][] nextInt ( final int n, final int m ) {
		final int[][] ans = new int[n][];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextInt( m );
		}
		return ans;
	}

	public long[][] nextLong ( final int n, final int m ) {
		final long[][] ans = new long[n][];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextLong( m );
		}
		return ans;
	}

	public String[][] next ( final int n, final int m ) {
		final String[][] ans = new String[n][];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = next( m );
		}
		return ans;
	}

	public char[] nextCharArray () {
		return next().toCharArray();
	}

	public char[][] nextCharArray ( final int n ) {
		final char[][] ans = new char[n][];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextCharArray();
		}
		return ans;
	}

	public int[][] nextGraph ( final int N, final int M ) {
		if ( M == 0 ) {
			return new int[N + 1][0];
		}
		final int[][] ans = new int[N + 1][];
		final int[] count = new int[N + 1];
		final int[][] path = nextInt( M, 2 );
		for ( final int[] temp: path ) {
			++count[temp[0]];
			++count[temp[1]];
		}
		for ( int i = 1; i <= N; ++i ) {
			ans[i] = new int[count[i]];
		}
		for ( final int[] temp: path ) {
			ans[temp[0]][--count[temp[0]]] = temp[1];
			ans[temp[1]][--count[temp[1]]] = temp[0];
		}
		ans[0] = new int[0];
		return ans;
	}

	public Point nextPoint () {
		return new Point( nextInt(), nextInt() );
	}

	public Point[] nextPoint ( final int n ) {
		final Point[] ans = new Point[n];
		for ( int i = 0; i < n; ++i ) {
			ans[i] = nextPoint();
		}
		return ans;
	}

	public void close () {
		try {
			is.close();
		} catch ( final IOException e ) {
			e.printStackTrace();
			System.exit( 1 );
		}
	}
}

final class SimpleOutputStream extends FilterOutputStream {
	private final byte buf[];
	private int count;

	public SimpleOutputStream(final OutputStream out) {
		this(out, 1<<17);
	}

	public SimpleOutputStream(final OutputStream out, final int size) {
		super(out);
		if (size <= 0) {
			throw new IllegalArgumentException("Buffer size <= 0");
		}
		buf = new byte[size];
	}

	private void flushBuffer() throws IOException {
		if (count > 0) {
			out.write(buf, 0, count);
			count = 0;
		}
	}

	public void write(final int b) throws IOException {
		if (count >= buf.length) {
			flushBuffer();
		}
		buf[count++] = (byte)b;
	}

	public void write(final byte b[], final int off, final int len) throws IOException {
		if (len >= buf.length) {
			flushBuffer();
			out.write(b, off, len);
			return;
		}
		if (len > buf.length - count) {
			flushBuffer();
		}
		System.arraycopy(b, off, buf, count, len);
		count += len;
	}

	public void flush() throws IOException {
		flushBuffer();
		out.flush();
	}
}


final class SimpleWriter implements Appendable, Closeable, Flushable, AutoCloseable{
	private Writer out;
	private final boolean autoFlush;
	private boolean trouble = false;
	private Formatter formatter;
	private PrintStream psOut = null;

	private static Charset toCharset ( final String csn ) {
		if ( csn == null ) {
			throw new NullPointerException( "charsetName" );
		}
		try {
			return Charset.forName( csn );
		} catch ( IllegalCharsetNameException |
				  UnsupportedCharsetException e ) {
			e.printStackTrace();
			System.exit( 1 );
			return null;
		}
	}

	public SimpleWriter ( final Writer out ) {
		this( out, false );
	}

	public SimpleWriter ( final Writer out, final boolean autoFlush ) {
		this.out = out;
		this.autoFlush = autoFlush;
	}

	public SimpleWriter ( final OutputStream out ) {
		this( out, false );
	}

	public SimpleWriter ( final OutputStream out, final boolean autoFlush ) {
		this(out, autoFlush, Charset.defaultCharset());
	}

	public SimpleWriter(final OutputStream out, final boolean autoFlush, final Charset charset) {
		this(new BufferedWriter(new OutputStreamWriter(new SimpleOutputStream(out), charset)), autoFlush);
		if (out instanceof PrintStream) {
			psOut = (PrintStream) out;
		}
	}

	private void ensureOpen () throws IOException {
		if ( out == null ) {
			throw new IOException( "Stream closed" );
		}
	}

	public void flush () {
		try {
			ensureOpen();
			out.flush();
		} catch ( IOException x ) {
			trouble = true;
		}
	}

	public void close () {
		try {
			if ( out == null ) {
				return;
			}
			out.close();
			out = null;
		} catch ( IOException x ) {
			trouble = true;
		}
	}

	public boolean checkError () {
		if ( out != null ) {
			flush();
		}
		else if ( psOut != null ) {
			return psOut.checkError();
		}
		return trouble;
	}

	private void setError () {
		trouble = true;
	}

	private void clearError () {
		trouble = false;
	}

	public void write ( final int c ) {
		try {
			ensureOpen();
			out.write( c );
		} catch ( InterruptedIOException x ) {
			Thread.currentThread().interrupt();
		} catch ( IOException x ) {
			trouble = true;
		}
	}

	public void write ( final char[] buf, final int off, final int len ) {
		try {
			ensureOpen();
			out.write( buf, off, len );
		} catch ( InterruptedIOException x ) {
			Thread.currentThread().interrupt();
		} catch ( IOException x ) {
			trouble = true;
		}
	}

	public void write ( final char[] buf ) {
		write( buf, 0, buf.length );
	}

	public void write ( final String s, final int off, final int len ) {
		try {
			ensureOpen();
			out.write( s, off, len );
		} catch ( InterruptedIOException x ) {
			Thread.currentThread().interrupt();
		} catch ( IOException x ) {
			trouble = true;
		}
	}

	public void write ( final String s ) {
		write( s, 0, s.length() );
	}

	private void newLine () {
		try {
			ensureOpen();
			out.write( System.lineSeparator() );
			if ( autoFlush ) {
				out.flush();
			}
		} catch ( InterruptedIOException x ) {
			Thread.currentThread().interrupt();
		} catch ( IOException x ) {
			trouble = true;
		}
	}

	public void print ( final boolean b ) {
		write( b ? "true" : "false" );
	}

	public void print ( final char c ) {
		write( c );
	}

	public void print ( final int i ) {
		write( String.valueOf( i ) );
	}

	public void print ( final long l ) {
		write( String.valueOf( l ) );
	}

	public void print ( final float f ) {
		write( String.valueOf( f ) );
	}

	public void print ( final double d ) {
		write( String.valueOf( d ) );
	}

	public void print ( final char[] s ) {
		write( s );
	}

	public void print ( final String s ) {
		write( s );
	}

	public void print ( final Object obj ) {
		write( obj.toString() );
	}

	public void println () {
		newLine();
	}

	public void println ( final boolean x ) {
		print( x );
		println();
	}

	public void println ( final char x ) {
		print( x );
		println();
	}

	public void println ( final int x ) {
		print( x );
		println();
	}

	public void println ( final long x ) {
		print( x );
		println();
	}

	public void println ( final float x ) {
		print( x );
		println();
	}

	public void println ( final double x ) {
		print( x );
		println();
	}

	public void println ( final char[] x ) {
		print( x );
		println();
	}

	public void println ( final String x ) {
		print( x );
		println();
	}

	public void println ( final Object x ) {
		print( x.toString() );
		println();
	}

	public SimpleWriter printf ( final String format, final Object... args ) {
		return format( format, args );
	}

	public SimpleWriter printf ( final Locale l, final String format, final Object... args ) {
		return format( l, format, args );
	}

	public SimpleWriter format ( final String format, final Object... args ) {
		try {
			ensureOpen();
			if ( ( formatter == null )
				 || ( formatter.locale() != Locale.getDefault() ) ) {
				formatter = new Formatter( this );
			}
			formatter.format( Locale.getDefault(), format, args );
			if ( autoFlush ) {
				out.flush();
			}
		} catch ( InterruptedIOException x ) {
			Thread.currentThread().interrupt();
		} catch ( IOException x ) {
			trouble = true;
		}
		return this;
	}

	public SimpleWriter format ( final Locale l, final String format, final Object... args ) {
		try {
			ensureOpen();
			if ( ( formatter == null ) || ( formatter.locale() != l ) ) {
				formatter = new Formatter( this, l );
			}
			formatter.format( l, format, args );
			if ( autoFlush ) {
				out.flush();
			}
		} catch ( InterruptedIOException x ) {
			Thread.currentThread().interrupt();
		} catch ( IOException x ) {
			trouble = true;
		}
		return this;
	}

	public SimpleWriter append ( final CharSequence csq ) {
		write( String.valueOf( csq ) );
		return this;
	}

	public SimpleWriter append ( CharSequence csq, final int start, final int end ) {
		if ( csq == null ) {
			csq = "null";
		}
		return append( csq.subSequence( start, end ) );
	}

	public SimpleWriter append ( final char c ) {
		write( c );
		return this;
	}

	public void println ( final int[] array ) {
		println( array, ' ' );
	}

	public void println ( final int[] array, final String str ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( str );
			print( array[i] );
		}
		println();
	}

	public void println ( final int[] array, final char c ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( c );
			print( array[i] );
		}
		println();
	}

	public void println ( final int[][] array ) {
		println( array, ' ' );
	}

	public void println ( final int[][] arrays, final String str ) {
		for ( final int[] array: arrays ) {
			println( array, str );
		}
	}

	public void println ( final int[][] arrays, final char c ) {
		for ( final int[] array: arrays ) {
			println( array, c );
		}
	}

	public void println ( final long[] array ) {
		println( array, ' ' );
	}

	public void println ( final long[] array, final String str ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( str );
			print( array[i] );
		}
		println();
	}

	public void println ( final long[] array, final char c ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( c );
			print( array[i] );
		}
		println();
	}

	public void println ( final long[][] array ) {
		println( array, ' ' );
	}

	public void println ( final long[][] arrays, final String str ) {
		for ( final long[] array: arrays ) {
			println( array, str );
		}
	}

	public void println ( final long[][] arrays, final char c ) {
		for ( final long[] array: arrays ) {
			println( array, c );
		}
	}

	public void println ( final double[] array ) {
		println( array, ' ' );
	}

	public void println ( final double[] array, final String str ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( str );
			print( array[i] );
		}
		println();
	}

	public void println ( final double[] array, final char c ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( c );
			print( array[i] );
		}
		println();
	}

	public void println ( final double[][] array ) {
		println( array, ' ' );
	}

	public void println ( final double[][] arrays, final String str ) {
		for ( final double[] array: arrays ) {
			println( array, str );
		}
	}

	public void println ( final double[][] arrays, final char c ) {
		for ( final double[] array: arrays ) {
			println( array, c );
		}
	}

	public void println ( final char[] cs, final String str ) {
		print( cs[0] );
		for ( int i = 1; i < cs.length; ++i ) {
			print( str );
			print( cs[i] );
		}
		println();
	}

	public void println ( final char[] cs, final char c ) {
		print( cs[0] );
		for ( int i = 1; i < cs.length; ++i ) {
			print( c );
			print( cs[i] );
		}
		println();
	}

	public void println ( final char[][] cs ) {
		for ( final char[] c: cs ) {
			println( c );
		}
	}

	public void println ( final char[][] cs, final String str ) {
		for ( final char[] c: cs ) {
			println( c, str );
		}
	}

	public void println ( final char[][] cs, final char c ) {
		for ( final char[] cc: cs ) {
			println( cc, c );
		}
	}

	public <E> void println ( final E[] array ) {
		println( array, ' ' );
	}

	public <E> void println ( final E[] array, final String str ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( str );
			print( array[i] );
		}
		println();
	}

	public <E> void println ( final E[] array, final char c ) {
		print( array[0] );
		for ( int i = 1; i < array.length; ++i ) {
			print( c );
			print( array[i] );
		}
		println();
	}

	public <E> void println ( final E[][] arrays ) {
		println( arrays, ' ' );
	}

	public <E> void println ( final E[][] arrays, final String str ) {
		for ( final E[] array: arrays ) {
			println( array, str );
		}
	}

	public <E> void println ( final E[][] arrays, final char c ) {
		for ( final E[] array: arrays ) {
			println( array, c );
		}
	}
}
