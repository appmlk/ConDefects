import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();

		final int N = reader.readInt();
		int[] Q = new int[N];
		int[] A = new int[N];
		int[] B = new int[N];

		for (int i = 0; i < N; i++) {
			Q[i] = reader.readInt();
		}

		int n_max = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			A[i] = reader.readInt();
			if (A[i] != 0) {
				int n = (int) (Q[i] / A[i]);
				if (n_max > n) {
					n_max = n;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			B[i] = reader.readInt();
		}

		reader.close();

		int total_max = 0;
		for (int n = 0; n < n_max; n++) {
			int m_max = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				if (B[i] != 0) {
					int m = (Q[i] - A[i] * n) / B[i];
					if (m < m_max) {
						m_max = m;
					}
				}
			}
			if (total_max < n + m_max) {
				total_max = n + m_max;
			}
		}

		System.out.println(total_max);
	}
}

class Reader extends BufferedReader {

	final int OFFSET_DIGIT = 48;

	public Reader() {
		super(new InputStreamReader(System.in));
	}

	public char readChar() throws IOException {
		return (char) read();
	}

	public char readFirstNonSpaceChar() throws IOException {
		char ch = readChar();
		while (Character.isWhitespace(ch)) {
			ch = readChar();
		}
		return ch;
	}

	public String readString() throws IOException {
		StringBuffer buf = new StringBuffer();
		char ch = readFirstNonSpaceChar();
		while (!Character.isWhitespace(ch)) {
			buf.append(ch);
			ch = readChar();
		}
		return new String(buf);
	}

	public int readPositiveInt() throws IOException {
		int n = 0;
		char ch = readFirstNonSpaceChar();
		while (Character.isDigit(ch)) {
			n = 10 * n + (int) (ch - OFFSET_DIGIT);
			ch = readChar();
		}
		return n;
	}

	public int readInt() throws IOException {
		int n = 0;
		char ch = readFirstNonSpaceChar();

		boolean isNeg = false;
		if (ch == '-') {
			isNeg = true;
			ch = readChar();
		}

		while (Character.isDigit(ch)) {
			n = 10 * n + (int) (ch - OFFSET_DIGIT);
			ch = readChar();
		}

		if (isNeg) {
			n = -n;
		}
		return n;
	}

	public long readPositiveLong() throws IOException {
		long n = 0;
		char ch = readFirstNonSpaceChar();
		while (Character.isDigit(ch)) {
			n = 10 * n + (int) (ch - OFFSET_DIGIT);
			ch = readChar();
		}
		return n;
	}
}