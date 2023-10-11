
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a[] = Arrays.stream(new int[n]).map(e -> sc.nextInt()).toArray();

        char[] str = sc.next().toCharArray();

        //右側のXに対応する数字について
        int[][] dp1 = new int[n][3];
        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j < 3; j++) {
                dp1[i][j] = dp1[i + 1][j];
            }

            if(str[i + 1] == 'X'){
                dp1[i][a[i + 1]]++;
            }
        }

        long[][][] dp2 = new long[n][3][3];
        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    dp2[i][j][k] = dp2[i + 1][j][k];
                }
            }

            if(str[i + 1] == 'E') {
                for(int j = 0; j < 3; j++) {
                    dp2[i][a[i + 1]][j] += dp1[i + 1][j];
                }
            }
        }

        long[][][][] dp3 = new long[n][3][3][3];
        for(int i = n - 3; i >= 0; i--) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        dp3[i][j][k][l] = dp3[i + 1][j][k][l];
                    }
                }
            }

            if(str[i] == 'M') {
                for(int j = 0; j < 3; j++) {
                    for(int k = 0; k < 3; k++) {
                        dp3[i][a[i]][j][k] += dp2[i][j][k];
                    }
                }
            }
        }

        long result = 0;
        result = dp3[0][0][0][0] + dp3[0][0][0][1]*2 + dp3[0][0][0][2]
                + dp3[0][0][1][0]*2 + dp3[0][0][1][1]*2 + dp3[0][0][1][2]*3
                + dp3[0][0][2][0] + dp3[0][0][2][1]*3 + dp3[0][0][2][2]
                + dp3[0][1][0][0]*2 + dp3[0][1][0][1]*2 + dp3[0][1][0][2]*3
                + dp3[0][1][1][0]*2 + dp3[0][1][1][1]*0 + dp3[0][1][1][2]*0
                + dp3[0][1][2][0]*3 + dp3[0][1][2][1]*0 + dp3[0][1][2][2]*0
                + dp3[0][2][0][0]*1 + dp3[0][2][0][1]*3 + dp3[0][2][0][2]*1
                + dp3[0][2][1][0]*3 + dp3[0][2][1][1]*0 + dp3[0][2][1][2]*0
                + dp3[0][2][2][0]*1 + dp3[0][2][2][1]*0 + dp3[0][2][2][2]*0;
        System.out.println(result);
    }
}
