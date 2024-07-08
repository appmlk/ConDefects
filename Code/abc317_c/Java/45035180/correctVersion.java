
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        route = new int[n][n];
        used = new boolean[n];

        for(int i = 0; i < m; i++){
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();

            route[a][b] = c;
            route[b][a] = c;
        }

//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < n; j++){
//                System.out.print(route[i][j] + " ");
//            }
//            System.out.println();
//        }

        for(int i = 0; i < n; i++){
            dfs(i, 0, n, 0);
        }

        System.out.println(result);
    }

    static int route[][];
    static boolean used[];

    static long result = -1;

    static void dfs (int pos, int depth, int n, long total) {
//        System.out.println("pos : " + pos + " depth: " + depth + " total: " + total);
        if(depth == n) {
//            result = Math.max(result, total);
            return;
        }

        used[pos] = true;
        for(int i = 0; i < n; i++) {
            if(!used[i] && route[pos][i] > 0) {
                result = Math.max(result, total + route[pos][i]);
                dfs(i, depth + 1, n, total + route[pos][i]);
            }
        }
        used[pos] = false;
    }
}
