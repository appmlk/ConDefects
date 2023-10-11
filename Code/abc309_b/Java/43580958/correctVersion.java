import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '0') {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = 1;
                }
            }
        }
        sc.close();

        int[][] newGrid = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    if (j != 0) {
                        newGrid[i][j] = grid[i][j - 1];
                    } else {
                        newGrid[i][j] = grid[i + 1][j];
                    }
                } else if (i == N - 1) {
                    if (j != N - 1) {
                        newGrid[i][j] = grid[i][j + 1];
                    } else {
                        newGrid[i][j] = grid[i - 1][j];
                    }
                } else {
                    if (j == 0) {
                        newGrid[i][j] = grid[i + 1][j];
                    } else if (j == N - 1) {
                        newGrid[i][j] = grid[i - 1][j];
                    } else {
                        newGrid[i][j] = grid[i][j];
                    }
                }
            }
        }
        // Print the result
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(newGrid[i][j]);
            }
            System.out.println();
        }
    }
}