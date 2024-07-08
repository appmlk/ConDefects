import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) throws IOException {

		final BufferedReader reader =
				new BufferedReader(new InputStreamReader(System.in));

		System.out.println(execute(reader));
	}

	static String execute(final BufferedReader reader) throws IOException {

		final Scanner sc = new Scanner(reader);
		final int boxNum = sc.nextInt();
		final int memberNum = sc.nextInt();
		final int[] boxCapacities = getInts(sc, boxNum);
		final int[] memberMins = getInts(sc, memberNum);

		final NavigableMap<Integer, AtomicInteger> boxCapMap = new TreeMap<>();
		for (int j = 0; j < boxCapacities.length; j++) {
			final var val = Integer.valueOf(boxCapacities[j]);
			if (!boxCapMap.containsKey(val)) {
				boxCapMap.put(val, new AtomicInteger());
			}
			boxCapMap.get(val).incrementAndGet();
		}

		final List<Integer> sortedMemMins =
				Arrays.stream(memberMins).boxed().sorted(Comparator.comparingInt(i -> -i.intValue())).toList();

		long result = 0;

		for (final var memMin : sortedMemMins) {
			final Entry<Integer, AtomicInteger> box = boxCapMap.ceilingEntry(memMin);
			if (box == null) return "-1";
			final AtomicInteger rest = box.getValue();
			if (rest.intValue() <= 0) return "-1";
			result += box.getKey().intValue();
			if (rest.decrementAndGet() <= 0) {
				boxCapMap.remove(box.getKey());
			}
		}

		return Long.toString(result);
	}

	/**
	 * @param scanner スキャナーオブジェクト
	 * @param amount 要素の数
	 * @return スキャナーから読取った int 配列
	 */
	private static int[] getInts(
			final Scanner scanner,
			final int amount) {

		final int[] result = new int[amount];
		for (int i = 0; i < amount; i++) {
			result[i] = scanner.nextInt();
		}
		return result;
	}
}
