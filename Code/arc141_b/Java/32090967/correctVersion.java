import java.util.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		Main ins = new Main(in);
		ins.calc();
		ins.showResult();
	}

	static class Tuple {
		int n; // n番目
		int firstBitPos; // 最上位bitの位置
		long patternNum;

		Tuple(int n, int firstBitPos, long patternNum) {
			this.n = n;
			this.firstBitPos = firstBitPos;
			this.patternNum = patternNum;
		}

		public String toString() {
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append(this.n + " ");
			strBuilder.append(this.firstBitPos + " ");
			strBuilder.append(this.patternNum);
			return strBuilder.toString();
		}
	}

	PrintWriter writer = new PrintWriter(System.out);
	BufferedReader in = null;
	long N, M;
	final long MOD = 998244353L;
	long[] basePatterns;
	int maxBitPos;

	Main(BufferedReader in) throws IOException {
		String[] tokens = in.readLine().split(" ");
		this.N = Long.parseLong(tokens[0]);
		this.M = Long.parseLong(tokens[1]);
		this.basePatterns = new long[62];
		this.basePatterns[0] = 0L;
		this.basePatterns[1] = 1L;
		for (int i = 2; i < basePatterns.length; ++i) {
			basePatterns[i] = basePatterns[i - 1] * 2L;
		}
		this.maxBitPos = Long.toString(this.M, 2).length();
		for (int i = 1; i < basePatterns.length; ++i) {
			if (i < this.maxBitPos) {
				this.basePatterns[i] %= MOD;
			} else if (i == this.maxBitPos) {
				long p = this.M ^ basePatterns[i];
				p++;
				p %= MOD;
				this.basePatterns[i] = p;
			} else {
				this.basePatterns[i] = 0L;
			}
		}
	}

	void calc() {
		if (N > 70L) {
			this.writer.println(0L);
			return;
		}
		long[] patterns = Arrays.copyOf(basePatterns, this.basePatterns.length);
		for (int i = 1; i < this.N; ++i) {
			long[] nexts = new long[patterns.length];
			Arrays.fill(nexts, 0L);
			long sum = 0L;
			for (int j = i; j + 1 < patterns.length; ++j) {
				sum += patterns[j];
				sum %= MOD;
				nexts[j + 1] = sum * basePatterns[j + 1] % MOD;
			}
			patterns = nexts;
		}
		long result = 0L;
		for (int i = 0; i < patterns.length; ++i) {
			result += patterns[i];
			result %= MOD;
		}
		this.writer.println(result);
	}

	void showResult() {
		writer.flush();
	}
}