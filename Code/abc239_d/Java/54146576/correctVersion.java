import java.util.*;

public class Main {

    static int minFactor[];
    static boolean prime[];

    static void prepareOsaK(int n){
        prime = new boolean[n+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        minFactor = new int[n+1];
        Arrays.fill(minFactor, -1);
        minFactor[1] = 1;
        for(int i = 2;i <= n;i++){
            if(!prime[i]) continue;
            minFactor[i] = i;
            for(int j = i * 2;j <= n; j+=i){
                prime[j] = false;
                if(minFactor[j] == -1) minFactor[j] = i;
            }
        }

    }

    public static void main(String[] args) {
        prepareOsaK(10000);
        int a = getInt();
        int b = getInt();
        int c = getInt();
        int d = getInt();
        for(int i = a;i <= b;i++){
            boolean find = false;
            for(int j = c;j <= d;j++){
                if(prime[i+j]){
                    find = true;
                }
            }
            if(!find){
                out("Takahashi");
                return;
            }
        }
        out("Aoki");
    }


    static Comparator<Long> lower = (x, y) -> x.compareTo(y) >= 0 ? 1 : -1; // 指定した値以上の初めての要素の位置
    static Comparator<Long> upper = (x, y) -> x.compareTo(y) > 0 ? 1 : -1;// 指定した値より大きい初めての要素の位置

    static long ceil(long n, long d){
        return (n + d - 1) / d;
    }

    static int sqrtI(long n){
        return toInt(Math.sqrt(n));
    }

    static long toLong(double v) {
        return Double.valueOf(v).longValue();
    }

    static int toInt(double v) {
        return Double.valueOf(v).intValue();
    }

    static String[] createTiles(int w, int h, String out){
        String[] s = new String[h + 2];
        s[0] = s[h + 1] = out.repeat(w + 2);

        for (int i = 1; i <= h; i++) {
            s[i] = out + getString() + out;
        }
        return s;
    }

    static void outH(List<?> o){
        int nl = o.size()-1;
        for (int i = 0; i < o.size(); i++) {
            System.out.print(o.get(i)+(i != nl ? " ":"\n"));
        }
    }
    static void out(List<?> o){
        for (Object oo: o) {
            System.out.println(oo);
        }
    }

    static void outH(Object[] o){
        int nl = o.length-1;
        for (int i = 0; i < o.length; i++) {
            System.out.print(o[i]+(i != nl ? " ":""));
        }
        out();
    }

    static void outH(char[] d){
        int nl = d.length-1;
        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i]+(i != nl ? " ":""));
        }
        out();
    }

    static void outH(double[] d){
        int nl = d.length-1;
        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i]+(i != nl ? " ":""));
        }
        out();
    }

    static void outH(int[] in){
        int nl = in.length-1;
        for (int i = 0; i < in.length; i++) {
            System.out.print(in[i]+(i != nl ? " ":""));
        }
        out();
    }

    static void outH(long[] l){
        int nl = l.length-1;
        for (int i = 0; i < l.length; i++) {
            System.out.print(l[i]+(i != nl ? " ":""));
        }
        out();
    }
    static void outH(String[] s){
        int nl = s.length-1;
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]+(i != nl ? " ":""));
        }
        out();
    }

    static void out(){
        System.out.println();
    }

    static void out(Object[] o){
        for (Object oo : o) {
            System.out.println(oo);
        }
    }

    static String sortString(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    static String sortStringDesc(String s) {
        return new StringBuilder(sortString(s)).reverse().toString();
    }

    static void out(char[] c){
        for (Character aChar : c) {
            System.out.println(aChar);
        }
    }

    static void out(double[] d){
        for (Double aDouble : d) {
            System.out.println(aDouble);
        }
    }

    static void out(int[] i){
        for (Integer iInteger: i) {
            System.out.println(iInteger);
        }
    }

    static void out(long[] l){
        for (Long lLong: l) {
            System.out.println(lLong);
        }
    }
    static void out(String[] s){
        for (String sString: s) {
            System.out.println(sString);
        }
    }

    static void out(Double d){
        System.out.println(d);
    }


    static void out(Integer i){
        System.out.println(i);
    }

    static void out(Long l){
        System.out.println(l);
    }
    static void out(String s){
        System.out.println(s);
    }

    static void YesOrNo(boolean b){
        System.out.println(b ? "Yes" : "No");
    }
    static void YesOrNo(boolean b, String yes, String no){
        System.out.println(b ? yes : no);
    }
    static void Yes(){
        System.out.println("Yes");
    }

    static void No(){
        System.out.println("No");
    }

    /*
    static StringTokenizer st;
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static String getString()  {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine().trim());
            } catch (IOException e) {
                System.exit(0);
            }
        }
        return st.nextToken();
    }
    static long getLong() {
        return Long.parseLong(getString());
    }
    static int getInt()  {
        return Integer.parseInt(getString());
    }

    static Double getDouble() {
        return parseDouble(getString());
    }

     */
    private static final java.io.InputStream in = System.in;
    private static final byte[] buffer = new byte[1024];
    private static int ptr = 0;
    private static int buflen = 0;

    private static final long LONG_MAX_TENTHS = 922337203685477580L;
    private static final int LONG_MAX_LAST_DIGIT = 7;
    private static final int LONG_MIN_LAST_DIGIT = 8;

    static boolean hasNextByte() {
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
    static int readByte() {
        if (hasNextByte()) return buffer[ptr++]; else return -1;
    }
    static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }
    static boolean hasNext() {
        while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        return hasNextByte();
    }
    static String getString() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    static long getLong() {
        if (!hasNext()) throw new NoSuchElementException();
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
    static public int getInt() {
        long nl = getLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    static public double getDouble() {
        return Double.parseDouble(getString());
    }

    static public Long[] getLongArray(int length){
        Long[] array = new Long[length];
        for(int i=0; i<length; i++) array[i] = getLong();
        return array;
    }
    static public Long[] getLongArray(int length, java.util.function.LongUnaryOperator map){
        Long[] array = new Long[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsLong(getLong());
        return array;
    }
    static public int[] getIntArray(int length){
        int[] array = new int[length];
        for(int i=0; i<length; i++) array[i] = getInt();
        return array;
    }
    static public int[] getIntArray(int length, java.util.function.IntUnaryOperator map){
        int[] array = new int[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsInt(getInt());
        return array;
    }
    static public double[] getDoubleArray(int length){
        double[] array = new double[length];
        for(int i=0; i<length; i++) array[i] = getDouble();
        return array;
    }
    static public double[] getDoubleArray(int length, java.util.function.DoubleUnaryOperator map){
        double[] array = new double[length];
        for(int i=0; i<length; i++) array[i] = map.applyAsDouble(getDouble());
        return array;
    }

    static public long[][] getLongMatrix(int height, int width){
        long[][] mat = new long[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = getLong();
        }
        return mat;
    }
    static int[][] getIntMatrix(int height, int width){
        int[][] mat = new int[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = getInt();
        }
        return mat;
    }
    static public double[][] getDoubleMatrix(int height, int width){
        double[][] mat = new double[height][width];
        for(int h=0; h<height; h++) for(int w=0; w<width; w++){
            mat[h][w] = getDouble();
        }
        return mat;
    }

    static public char[][] nextCharMatrix(int height, int width){
        char[][] mat = new char[height][width];
        for(int h=0; h<height; h++){
            String s = getString();
            for(int w=0; w<width; w++){
                mat[h][w] = s.charAt(w);
            }
        }
        return mat;
    }
    public static long mod1097 = 1000000007L;
    public static long mod9982 = 998244353L;
}