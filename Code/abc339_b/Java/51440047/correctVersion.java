
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int height = sc.nextInt();
            int width = sc.nextInt();
            Board board = new Board(width, height);

            int number = sc.nextInt();

            Point now = new Point(0, 0);

            for (int i = 0; i < number; i++) {
                if (board.isTileCovered(now.getX(), now.getY())) {
                    board.tileUncovering(now.getX(), now.getY());
                    now.setRadius(now.getRadius() + 90);
                } else {
                    board.tileCovering(now.getX(), now.getY());
                    now.setRadius(now.getRadius() - 90);
                }
                now.walkRotate();
            }

            board.print();
        }
    }

    static class Point {
        private int y;
        private int x;
        private int radius; // 反時計回りの回転角度、x軸の右側を0とおく

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.radius = 90;
        }

        void walkRotate() {
            while (radius < 0)
                radius += 360;
            while (radius >= 360)
                radius -= 360;

            switch (radius) {
                case 0:
                    x += 1;
                    break;
                case 90:
                    y -= 1;
                    break;
                case 180:
                    x -= 1;
                    break;
                case 270:
                    y += 1;

            }
        }

        void setRadius(int radius) {
            this.radius = radius;
        }

        int getRadius() {
            return radius;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }

    static class Board {
        private boolean[][] isCovered;
        private int width;
        private int height;

        Board(int width, int height) {
            isCovered = new boolean[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    isCovered[i][j] = false;
                }
            }
            this.width = width;
            this.height = height;
        }

        int getValidWidth(int i) {
            int result = i;
            while (result < 0)
                result += width;
            while (result >= width)
                result -= width;
            return result;
        }

        int getValidHeight(int j) {
            int result = j;
            while (result < 0)
                result += height;
            while (result >= height)
                result -= height;
            return result;
        }

        boolean isTileCovered(int i, int j) {
            return isCovered[getValidWidth(i)][getValidHeight(j)];
        }

        void tileCovering(int i, int j) {
            isCovered[getValidWidth(i)][getValidHeight(j)] = true;
        }

        void tileUncovering(int i, int j) {
            isCovered[getValidWidth(i)][getValidHeight(j)] = false;
        }

        void print() {
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    String print = isTileCovered(i, j) ? "#" : ".";
                    System.out.print(print);
                }
                System.out.println();
            }
        }
    }
}
