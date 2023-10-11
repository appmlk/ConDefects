import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();
		int[] P = inputInt(N,sc);
		for(int i=0;i<N;i++) {
			if(P[i]==X) {
				print(i);
				break;
			}
		}
		
		
		sc.close();
	}
	
	private static boolean turn(boolean isClosed) {
		// TODO 自動生成されたメソッド・スタブ
		if(isClosed)
			return false;
		return true;
	}

	private static boolean isClear(char[][] s, int i, int j,int M) {
		// TODO 自動生成されたメソッド・スタブ
		for(int k=0;k<M;k++) {
			if(!(s[i][k]=='o'||s[j][k]=='o')) {
				//System.out.println("i="+i+"j="+j+"k="+k);
				return false;
			}
		}
		return true;
	}

	public static List<String> getUpperAlphabets(int K) {
		//final int ALPHABET_SIZE = 'Z' - 'A';
		int ALPHABET_SIZE = K;
		char alphabet = 'A';
		
		List<String> upperAlphabets = new ArrayList<String>();
		for (int i = 0; i <= ALPHABET_SIZE; i++) {
			upperAlphabets.add(String.valueOf(alphabet++));
		}
		return upperAlphabets;
	}

	public static boolean isAlphabet(char c) {
		String s = String.valueOf(c);
		boolean result = false;
		if (s != null) {
			Pattern pattern = Pattern.compile("^[A-Z]+$");
			result = pattern.matcher(s).matches();
		}
		return result;
	}

	public static void print(int i) {
		System.out.print(i);
	}

	public static void println(int i) {
		System.out.println(i);
	}

	public static void print(long i) {
		System.out.print(i);
	}

	public static void println(long i) {
		System.out.println(i);
	}

	public static void print(String st) {
		System.out.print(st);
	}

	public static void println(String st) {
		System.out.println(st);
	}

	public static void printIndex(int[] ans) {
		for (int o : ans) {
			System.out.print(o + " ");
		}
		System.out.println();
	}

	public static int[] inputInt(int N, Scanner sc) {
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();

		}
		return a;
	}

	public static double[] inputDouble(int N, Scanner sc) {
		double[] a = new double[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextDouble();

		}
		return a;
	}

	public static String[] inputString(int N, Scanner sc) {
		String[] a = new String[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.next();

		}
		return a;
	}

	public static Long[] inputLong(int N, Scanner sc) {
		Long[] a = new Long[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextLong();

		}
		return a;
	}

	public static void sort(int[] array, int left, int right) {
		if (left <= right) {
			int p = array[(left + right) >>> 1];
			int l = left;
			int r = right;
			while (l <= r) {
				while (array[l] < p) {
					l++;
				}
				while (array[r] > p) {
					r--;
				}
				if (l <= r) {
					int tmp = array[l];
					array[l] = array[r];
					array[r] = tmp;
					l++;
					r--;
				}
			}
			Main.sort(array, left, r);
			Main.sort(array, l, right);
		}
	}

	public static void sort(ArrayList<Integer> array, int left, int right) {
		if (left <= right) {
			int p = array.get((left + right) >>> 1);
			int l = left;
			int r = right;
			while (l <= r) {
				while (array.get(l) < p) {
					l++;
				}
				while (array.get(r) > p) {
					r--;
				}
				if (l <= r) {
					int tmp = array.get(l);
					array.set(l, array.get(r));
					array.set(r, tmp);
					l++;
					r--;
				}
			}
			Main.sort(array, left, r);
			Main.sort(array, l, right);
		}
	}

	public static void sort(double[] array, int left, int right) {
		if (left <= right) {
			double p = array[(left + right) >>> 1];
			int l = left;
			int r = right;
			while (l <= r) {
				while (array[l] < p) {
					l++;
				}
				while (array[r] > p) {
					r--;
				}
				if (l <= r) {
					double tmp = array[l];
					array[l] = array[r];
					array[r] = tmp;
					l++;
					r--;
				}
			}
			Main.sort(array, left, r);
			Main.sort(array, l, right);
		}
	}

	public static void sort(Long[] array, int left, int right) {
		if (left <= right) {
			Long p = array[(left + right) >>> 1];
			int l = left;
			int r = right;
			while (l <= r) {
				while (array[l] < p) {
					l++;
				}
				while (array[r] > p) {
					r--;
				}
				if (l <= r) {
					Long tmp = array[l];
					array[l] = array[r];
					array[r] = tmp;
					l++;
					r--;
				}
			}
			Main.sort(array, left, r);
			Main.sort(array, l, right);
		}
	}

	public static void printYes() {
		System.out.println("Yes");
	}

	public static void printNo() {
		System.out.println("No");
	}

}
