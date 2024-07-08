import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    int N = 2001;
    boolean[][] occ = new boolean[N][N];
    boolean[][] vis = new boolean[N][N];

    public class Tuple<X, Y> {
        public final X x;
        public final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }


    private void solve() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Tuple<Integer, Integer>> pos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt() + 1000;
            int y = sc.nextInt() + 1000;
            occ[x][y] = true;
            pos.add(new Tuple<Integer, Integer>(x, y));
        }
        int count = 0;
        for (Tuple t : pos) {
            if (!vis[(int) t.x][(int) t.y]) {
                dfs((Integer) t.x, (Integer) t.y);
                count++;
            }
        }
        System.out.println(count);
    }

    private void dfs(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return;
        }
        if(!occ[x][y] || vis[x][y]){
            return;
        }

        vis[x][y] = true;
        int[] dirX = new int[]{-1, -1, 0, 0, 1, 1};
        int[] dirY = new int[]{-1, 0, -1, 1, 0, 1};
        for (int i = 0; i < dirX.length; i++) {
            dfs(x + dirX[i], y + dirY[i]);
        }
    }

    public static void main(String[] args) {
        (new Main()).solve();
    }
}
