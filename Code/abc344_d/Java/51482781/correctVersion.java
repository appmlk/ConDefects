

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String strT = sc.next();

        int n = sc.nextInt();

        String[][] strArray = new String[100][10];

        for(int i = 0; i < n; i++) {
            int m = sc.nextInt();

            for(int j = 0; j < m; j++) {
                strArray[i][j] = sc.next();
            }
            for(int j = m; j < 10; j++) {
                strArray[i][j] = "";
            }
        }

        int dp[][] = new int[n + 1][strT.length() + 1];
        int INF = 1_000_000_000;
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= strT.length(); j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for(int j = 0; j < strT.length(); j++) {
                for(int k = 0; k < 10; k++) {
                    if(strArray[i - 1][k].equals("")) {
                        break;
                    }
                    int tmpLen = j + strArray[i - 1][k].length();
                    if(tmpLen > strT.length()) {
                        continue;
                    }
//                    System.out.println(strT.substring(j, tmpLen) + " " + strArray[i - 1][k]);

                    if(strT.substring(j, tmpLen).equals(strArray[i - 1][k])) {
//                        System.out.println("update i = " + i + " j = " + j + " k = " + k + " tmpLen " + tmpLen + " substr " + strT.substring(j, tmpLen));
                        dp[i][tmpLen] = Math.min(Math.min(dp[i - 1][tmpLen], dp[i - 1][j] + 1), dp[i][tmpLen]);
//                        System.out.println("A " + dp[i - 1][tmpLen] + " B " + dp[i - 1][j] + " result " + dp[i][tmpLen]);
                    }
                }
            }
        }

//        for(int i = 0; i <= n; i++) {
//            for(int j = 0; j <= strT.length(); j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[n][strT.length()] < INF ? dp[n][strT.length()] : -1);
    }
}