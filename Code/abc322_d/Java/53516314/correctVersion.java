import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author YC
 * @version 1.0
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][][] g = new char[4][5][5]; // 第一维表示是哪个矩形,
    static char[][][] temp = new char[4][5][5]; // 临时图, 防止原图被修改
    static int[] tx = new int[4]; // 记录移动变量
    static int[] ty = new int[4];
    static int[][] v = new int[10][10]; // 一张新图 用来判断答案是否正确
    static char[][][] op = new char[4][5][5]; // 暂时记录某一种合法情况 因为旋转会改变原数组
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 4; j++) {
                char[] ch = br.readLine().toCharArray();
                for (int k = 1; k <= 4; k++) {
                    g[i][j][k] = ch[k - 1];
                }
            }
        }

        dfs(1);
        System.out.println(flag ? "Yes" : "No");
    }

    public static boolean check() {
        for(int i = 1 ; i <= 4 ; i ++) {
            for(int j = 1 ; j <= 4 ; j ++) {
                if(v[i][j] != 1) return false;
            }
        }
        return true;
    }

    public static void init() {
        for(int i = 1 ; i <= 4 ; i ++) {
            Arrays.fill(v[i],0);
        }
        for(int k = 1 ; k <= 3 ; k ++) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    int newX = i + tx[k];
                    int newY = j + ty[k];
                    if(newX < 0 || newY < 0) return;
                    v[newX][newY] += op[k][i][j] == '#' ? 1 : 0;
                }
            }
        }
    }

    // u 代表现在正在移动第u个的方块
    public static void dfs(int u) {
        if(flag) return;
        if (u > 3) {
            init();
            if(check()) {
                flag = true;
            }
            return;
        }

        for(int i = 1 ; i <= 4 ; i ++) {
            temp[u][i] = g[u][i].clone();
        }
        // 枚举旋转次数
        for (int t = 1; t <= 4; t++) {
            // 枚举移动距离
            for (int i = -3; i <= 3; i++) {
                for (int j = -3; j <= 3; j++) {
                    // 如果移动合法
                    if (move(u, i, j)) {
                        // 存储当前图
                        for(int x = 1 ; x <= 4 ; x ++) {
                            op[u][x] = temp[u][x].clone();
                        }
                        // 记录偏移量
                        tx[u] = i;
                        ty[u] = j;
                        dfs(u + 1);
                    }
                }
            }
            // 进行旋转
            circle(u);
        }
    }

    public static void circle(int u) {
        char[][] t = new char[5][5];
        for(int i = 1; i <= 4 ; i ++) {
            t[i] = temp[u][i].clone();
        }

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
//                temp[u][i][j] = t[4-j+1][i];//旋转
                temp[u][j][4 - i + 1] = t[i][j];
//                temp[u][i][j] = t[4 - j + 1][i];
            }
        }
    }

    // 查看移动是否合法 x 表示x方向移动的距离
    public static boolean move(int u, int x, int y) {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                // 只关心为#的移动 如果移动不合法 return false
                if (temp[u][i][j] == '#' && !(i + x >= 1 && i + x <= 4 && j + y <= 4 && j + y >= 1)) return false;
            }
        }
        return true;
    }
}
