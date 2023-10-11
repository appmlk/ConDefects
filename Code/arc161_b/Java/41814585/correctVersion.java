import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args){
        FastScanner sc = new FastScanner();
        int T = sc.nextInt();
        List<Long> zi = new ArrayList<Long>();
        List<Long> san = new ArrayList<Long>();
        // long[] san = new long[100000];
        for(long i = 1; i < 1000000000000000000l; i *= 2){
            zi.add(i);
        }
        // int now = 0;
        for(int a = 0; a < zi.size() - 2; a++){
            for(int b = a + 1; b < zi.size() - 1; b++){
                for(int c = b + 1; c < zi.size(); c++){
                    // san[now] = zi.get(a) + zi.get(b) + zi.get(c);
                    san.add(zi.get(a) + zi.get(b) + zi.get(c));
                    // now++;
                }
            }
        }
        // Arrays.parallelSort(san);
        Collections.sort(san);
        san.remove(san.size()-1);
        // System.out.println(san[99998]);
        // System.out.println(san.get(san.size()-1));
        for(int i = 0; i < T; i++){
            long N = sc.nextLong();
            if(N < 7){
                System.out.println(-1);
            }else{
                System.out.println(san.get(bisect_left(N,san) - 1));
            }
            //　線形探索
            // for(int j = san.size()-1; j >= 0; j--){
            //     if(N >= san.get(j)){
            //         System.out.println(san.get(j));
            //         break;
            //     }
            // }
        }
    }
    public static int bisect_left(long a, List<Long> b){
        int ng = -1;
        int ok = b.size();
        while (Math.abs(ok - ng) > 1){
            int mid = (ok + ng) / 2;
            if(a <= b.get(mid)){
                ok = mid;
                if(a == b.get(mid)){
                    return ok+1;
                }
            }else{
                ng = mid;
            }
        }
        // System.out.println(b.get(ok));
        // System.out.println(a);
        return ok;
    } 
}
class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
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
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
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
    public double nextDouble() { return Double.parseDouble(next());}
}
