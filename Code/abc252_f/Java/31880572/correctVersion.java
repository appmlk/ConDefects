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

	PrintWriter writer = new PrintWriter(System.out);
	BufferedReader in = null;
	int N;
	long L;
	long[] A;

	Main(BufferedReader in) throws IOException {
		String[] tokens = in.readLine().split(" ");
		this.N = Integer.parseInt(tokens[0]);
		this.L = Long.parseLong(tokens[1]);
		this.A = new long[N];
		tokens = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			this.A[i] = Long.parseLong(tokens[i]);
		}
	}

	void calc() {
		Queue<Long> que = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return Long.compare(o1, o2);
			}
		});
		long sum = 0L;
		for (int i = 0; i < N; ++i) {
			que.add(A[i]);
			sum += A[i];
		}
		if (sum != L) {
			que.add(L - sum);
		}
		long result = 0L;
		while (que.size() >= 2) {
			Long val0 = que.poll();
			Long val1 = que.poll();
			long val = val0 + val1;
			result += val;
			que.add(val);
		}
		this.writer.println(result);
	}

	void showResult() {
		writer.flush();
	}
}