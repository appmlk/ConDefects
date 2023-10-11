
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int maxRangeX = 0;
		int maxRangeY = 0;
		boolean[][] dpX = new boolean[20001][n + 1];
		boolean[][] dpY = new boolean[20001][n + 1];
		int a = sc.nextInt();
		int whichXY = -1;
		int lastX = 0;
		int lastY = 0;
		dpX[10000 + a][1] = true;
		//dpX[10000 - a][1] = true;
		dpY[10000][0] = true;
		maxRangeX += a;
		for(int i = 2;i < n + 1;i++) {
			a = Integer.parseInt(sc.next());
			switch(whichXY) {
			case 1:
				for(int j = 10000 - maxRangeX;j < 10001 + maxRangeX; j++) {
					//System.out.println(dpX[j][i - 2]);
					if(dpX[j][i - 2]) {
						dpX[j + a][i] = true;
						dpX[j - a][i] = true;
					}
				}maxRangeX += a;
				whichXY *= (-1);
				lastX = i;
				break;
				
			case -1:
					for(int j = 10000 - maxRangeY;j < 10001 + maxRangeY; j++) {
						if(dpY[j][i - 2]) {
							//System.out.print("j" + j + "a" + a);
							dpY[j + a][i] = true;
							dpY[j - a][i] = true;
						}
					}
					maxRangeY += a;
					whichXY *= (-1);
					lastY = i;
					break;
			}
		}
		//System.out.print(dpX[10006][3]);
		if(dpX[10000 + x][lastX] && dpY[10000 + y][lastY])System.out.print("Yes");
		else System.out.print("No");
	}

}