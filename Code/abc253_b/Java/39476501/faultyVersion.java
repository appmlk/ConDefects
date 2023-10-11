import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
        for (int i = 0; i < h; i++) {
            String row = sc.next();
            for (int j = 0; j < w; j++) {
                if (row.charAt(j) == 'o') {
                    if (x1 == -1) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }
        int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        System.out.println(distance - 1);  // 1回目の移動は駒同士が重なっているため、必要な回数から除外
    }
}
