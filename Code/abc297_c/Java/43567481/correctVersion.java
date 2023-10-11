import java.util.*;

public class Main {

    static int N = 110;
    static char a[][] = new char[N][N];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int h = sc.nextInt(),w = sc.nextInt();
        for(int i = 0 ;i < h ;i ++){
            String str = sc.next();
            a[i] = str.toCharArray();
        }
        for(int i = 0 ;i < h ;i ++)
            for (int j = 0; j < w - 1; j++)
                if (a[i][j] == 'T' && a[i][j + 1] == 'T') {
                    a[i][j] = 'P';
                    a[i][j + 1] = 'C';
                }

        for(int i = 0 ;i < h ;i ++) {
            for (int j = 0; j < w ; j++)
                System.out.print(a[i][j]);
            System.out.println();
        }
    }
}