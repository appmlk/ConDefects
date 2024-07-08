

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int days = sc.nextInt();	// N
		int T_M = sc.nextInt();		// M
		String S = sc.next();		// S
		
		// ロゴ入りTシャツ必要枚数算出
		int needLogoT = 0;
		int needLogoT_tmp = 0;
		for (int i=0; i < S.length(); i++) {
			char c = S.charAt(i);
			switch(c) {
			case '0':	// 洗濯
				needLogoT_tmp = 0;
				continue;
			case '2':	// コンペ
				needLogoT_tmp++;
				break;
			}			
			if(needLogoT_tmp > needLogoT) {
				needLogoT = needLogoT_tmp;
			}
		}
//		System.out.println("needLogoT: "+needLogoT);
		
		// Tシャツ（無地+ロゴ入り）必要枚数算出
		int needT = 0;
		int needT_tmp = 0;
		for (int i=0; i < S.length(); i++) {
			char c = S.charAt(i);
			switch(c) {
			case '0':
				needT_tmp = 0;
				continue;
			case '1':
				needT_tmp++;
				break;
			case '2':
				needT_tmp++;
				break;
			}
			if(needT_tmp > needT) {
				needT = needT_tmp;
			}
		}
//		System.out.println("needT: "+needT);
		
		int ans = 0;
		// ロゴ入りTシャツが足りないパターン
		if(needLogoT + T_M >= needT) {
			ans = needLogoT;
		} 
		else {	// 全Tシャツが足りないパターン
			ans = needT - T_M;
		}
		System.out.println(ans);
	}

}

class FastScanner
{
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private boolean hasNextByte()
    {
        if (ptr < buflen)
        {
            return true;
        }
        else
        {
            ptr = 0;
            try
            {
                buflen = in.read(buffer);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            if (buflen <= 0)
            {
                return false;
            }
        }
        return true;
    }

    private int readByte()
    {
        if (hasNextByte()) return buffer[ptr++];
        else return -1;
    }

    private static boolean isPrintableChar(int c)
    {
        return 33 <= c && c <= 126;
    }

    public boolean hasNext()
    {
        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
        return hasNextByte();
    }

    public String next()
    {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b))
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong()
    {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-')
        {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b)
        {
            throw new NumberFormatException();
        }
        while (true)
        {
            if ('0' <= b && b <= '9')
            {
                n *= 10;
                n += b - '0';
            }
            else if (b == -1 || !isPrintableChar(b))
            {
                return minus ? -n : n;
            }
            else
            {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }

    public int nextInt()
    {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }

    public double nextDouble()
    {
        return Double.parseDouble(next());
    }
}

