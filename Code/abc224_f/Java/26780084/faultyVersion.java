import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;

class Main implements Runnable {
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	final long p=998244353;
	
	long[] pow2=new long[(int)2e5+10];
	long[] pow10=new long[(int)2e5+10];
	{
		pow2[0]=1;
		pow10[0]=1;
		for (int i=1;i<pow2.length;++i) {
			pow2[i]=2*pow2[i-1]%p;
			pow10[i]=10*pow10[i-1]%p;
		}
	}
	
	public void run() {
		FastScanner sc = new FastScanner();
		PrintWriter pw=new PrintWriter(System.out);
		char[] s=sc.next().toCharArray();
		int n=s.length;
		long ans=0;
		long cum=1;
		for (int i=n-1;i>=0;--i) {
			int v=(int)(s[i]-'0');
			ans+=v*pow2[i]%p*cum%p;
			ans%=p;
			cum=2*cum-pow10[n-1-i]+pow10[n-i];
			cum%=p;
		}
		pw.println(ans);
		pw.close();
	}
	
	void tr(Object...objects) {
		System.out.println(Arrays.deepToString(objects));
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
