import java.util.*;

class Pair {
	private int x;
	private int y;

	Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	int getX() {
		return this.x;
	}

	int getY() {
		return this.y;
	}
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());

		List<Pair> score = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(sc.next());
			int y = Integer.parseInt(sc.next());
			score.add(new Pair(x, y));
		}

		int totalX = 0;
		int totalY = 0;
		for (int i = 0; i < n; i++) {
			totalX += score.get(i).getX();
			totalY += score.get(i).getY();
		}

		if (totalX < totalY) {
			System.out.println("Aoki");
			System.exit(0);
		} else if (totalX > totalY) {
			System.out.println("Takahashi");
			System.exit(0);
		} else if (totalX == totalY) {
			System.out.println("Draw");
			System.exit(0);
		}
	}
}
			


