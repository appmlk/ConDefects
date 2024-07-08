import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int v1 = sc.nextInt();
		int v2 = sc.nextInt();
		int v3 = sc.nextInt();
		sc.close();

		for (int a2 = 0; a2 < 15; a2++) {
			for (int b2 = 0; b2 < 15; b2++) {
				for (int c2 = 0; c2 < 15; c2++) {
					for (int a3 = 0; a3 < 15; a3++) {
						for (int b3 = 0; b3 < 15; b3++) {
							for (int c3 = 0; c3 < 15; c3++) {
								int d3 = calc(7, 7, 7, a2, b2, c2, a3, b3, c3);
								int d21 = calc(7, 7, 7, a2, b2, c2);
								int d22 = calc(7, 7, 7, a3, b3, c3);
								int d23 = calc(a2, b2, c2, a3, b3, c3);
								int d2 = d21 + d22 + d23 - d3 * 3;
								int d1 = 1029 - d2 * 2 - d3 * 3;
								if (d1 == v1 && d2 == v2 && d3 == v3) {
									System.out.println("Yes");
									StringBuilder sb = new StringBuilder();
									sb.append("0 0 0 ");
									sb.append(a2).append(' ');
									sb.append(b2).append(' ');
									sb.append(c2).append(' ');
									sb.append(a3).append(' ');
									sb.append(b3).append(' ');
									sb.append(c3);
									System.out.println(sb.toString());
									return;
								}
							}
						}
					}
				}
			}
		}
		System.out.println("No");
	}

	static int calc(int a1, int b1, int c1, int a2, int b2, int c2) {
		int ret = 1;
		ret *= Math.max(Math.min(a1, a2) + 7 - Math.max(a1, a2), 0);
		ret *= Math.max(Math.min(b1, b2) + 7 - Math.max(b1, b2), 0);
		ret *= Math.max(Math.min(c1, c2) + 7 - Math.max(c1, c2), 0);
		return ret;
	}

	static int calc(int a1, int b1, int c1, int a2, int b2, int c2,
			int a3, int b3, int c3) {
		int ret = 1;
		ret *= Math.max(Math.min(a1, Math.min(a2, a3)) + 7 - Math.max(a1, Math.max(a2, a3)), 0);
		ret *= Math.max(Math.min(b1, Math.min(b2, b3)) + 7 - Math.max(b1, Math.max(b2, b3)), 0);
		ret *= Math.max(Math.min(c1, Math.min(c2, c3)) + 7 - Math.max(c1, Math.max(c2, c3)), 0);
		return ret;
	}
}
