import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		//		int n = 8;
		//		int k = 3;
		int[] a = new int[k];

		for (int i = 0; i < a.length; i++) {
			a[i] = sc.nextInt();
		}

		//		a[0] = 2;
		//		a[1] = 6;
		//		a[2] = 8;

		int[][] xy = new int[n][2];
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < 2; i++) {
				xy[j][i] = sc.nextInt();
			}
		}
		sc.close();

		//		xy[0][0] = -17683; 
		//		xy[0][1] = 17993; 
		//		xy[1][0] = 93038; 
		//		xy[1][1] = 47074; 
		//		xy[2][0] = 58079; 
		//		xy[2][1] = -57520; 
		//		xy[3][0] = -41515; 
		//		xy[3][1] = -89802; 
		//		xy[4][0] = -72739; 
		//		xy[4][1] = 68805; 
		//		xy[5][0] = 24324; 
		//		xy[5][1] = -73073; 
		//		xy[6][0] = 71049; 
		//		xy[6][1] = 72103; 
		//		xy[7][0] = 47863; 
		//		xy[7][1] = 19268; 

		double[] total = new double[n];
		for (int i = 0; i < total.length; i++) {
			total[i] = 1000000;
		}

		for (int i = 0; i < n; i++) {
			for (int l: a) {
				if ((i + 1) == l) {
					for (int j = 0; j < n; j++) {
						boolean bo = false;
						for (int m : a) {
							if (j == (m -1)) {
								bo  = true;
							}
						}
						if (bo != true) {
							double xTotal = xy[i][0] - xy[j][0];
							double yTotal = xy[i][1] - xy[j][1];
							double totalSub = 0;
							totalSub = Math.sqrt((xTotal * xTotal) + (yTotal * yTotal));
							if (total[j] > totalSub) {
								total[j] = totalSub;
							}
						}
					}
				}
			}
		}
		double ans = 0;
		for (double i :total) {
			if (i < 1000000) {
				if (ans < i) {
					ans = i;
				}
			}
		}
		System.out.println(ans);
	}
}
