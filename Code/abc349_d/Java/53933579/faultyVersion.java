import java.util.*;

class Pair {
	private long x;
	private long y;

	public Pair(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public void setX(long x) {
		this.x = x;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getX() {
		return this.x;
	}

	public long getY() {
		return this.y;
	}
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long left = Long.parseLong(sc.next());
		long right = Long.parseLong(sc.next());

		List<Pair> answer = new ArrayList<>();

		while (left < right) {
			for (int i = 60; i >= 0; i--) {
				long w = 1 << i;
				
				if (left % w != 0) {
					continue;
				}
				
				if (left + w > right) {
					continue;
				}

				answer.add(new Pair(left, left + w));

				left += w;

				break;
			}
		}

		System.out.println(answer.size());
		for (int i = 0; i < answer.size(); i++) {
			System.out.println(answer.get(i).getX() + " " + answer.get(i).getY());
		}
	}
}

