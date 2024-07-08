import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var n = sc.nextInt();
		var a = IntStream.range(0, n).map(x -> sc.nextInt()).toArray();
		Arrays.sort(a);
		sc.close();

		var pq = new PriorityQueue<Item2>();
		var result = 0L;
		for (var i = 0; i < n; i++) {
			result += a[i];
			pq.add(new Main().new Item2(a[i], 1));
		}
		for (var i = 0; i < n - 2; i++) {
			var item = pq.poll();
			result += item.value;
			pq.add(new Main().new Item2(item.key, item.c + 1));
		}
		System.out.print(result);
	}

	public class Item2 implements Comparable<Item2> {
		private int key;
		private int c;
		private long value;

		public Item2(int key, int c) {
			this.key = key;
			this.c = c;
			this.value = ((c + 1) * (c + 1) - c * c) * key;
		}

		@Override
		public int compareTo(Item2 target) {
			if (this.value - target.value > 0) {
				return 1;
			} else if (this.value - target.value < 0) {
				return -1;
			}
			if (this.key - target.key > 0) {
				return 1;
			} else if (this.key - target.key < 0) {
				return -1;
			}
			return 0;
		}
	}
}
