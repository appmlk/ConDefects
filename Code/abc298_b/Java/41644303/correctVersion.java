import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];

        for (int i = 0; i < n * 2; i++){
            for (int j = 0; j < n; j++){
                if (i < n){
                    a[i][j] = sc.nextInt();
                }else{
                    b[i - n][j] = sc.nextInt();
                }
            }
        }
        sc.close();

        for(int i = 0; i < 4; i++) {
            boolean bool = true;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(a[j][k] ==1 && b[j][k] != 1) {
                        bool = false;
                    }
                }
            }
            if(bool) {
                System.out.println("Yes");
                return;
            }
            rotate(a, n);
        }
        System.out.println("No");
    }

    public static void rotate(int array[][], int n){
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = array[(n - 1) - j][i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = temp[i][j];
            }
        }
    }
}