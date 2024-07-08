import java.util.*;

public class Main {
    public Scanner sc = new Scanner(System.in);
    public int N;
    public int T;
    Map<Integer, Integer> rowCntMp = new HashMap<>();
    Map<Integer, Integer> columnCntMp = new HashMap<>();
    int leftDiagonalCnt = 0;
    int rightDiagonalCnt = 0;

    void init() {
        N = sc.nextInt();
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int t = sc.nextInt();
            int I = geti(t);
            int J = getj(t);
            // System.out.printf("I = %d, J = %d\n", I, J);
            if (!rowCntMp.containsKey(I)) {
                rowCntMp.put(I, 0);
            }
            if (!columnCntMp.containsKey(J)) {
                columnCntMp.put(J, 0);
            }
            rowCntMp.put(I, rowCntMp.get(I) + 1);
            if (rowCntMp.get(I).equals(N)) {
                System.out.println(i + 1);
                return;
            }
            columnCntMp.put(J, columnCntMp.get(J) + 1);
            if (columnCntMp.get(J).equals(N)) {
                System.out.println(i + 1);
                return;
            }
            if (I == J) {
                leftDiagonalCnt += 1;
                if (leftDiagonalCnt == N) {
                    System.out.println(i + 1);
                    return;
                }
            }
            if (I + J == N + 1) {
                rightDiagonalCnt += 1;
                if (rightDiagonalCnt == N) {

                    System.out.println(i + 1);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    int geti(int num) {
        return (num - 1) / N + 1;
    }

    int getj(int num) {
        return (num - 1) % N + 1;
    }


    public static void main(String[] args) {
        Main obj = new Main();
        obj.init();
    }
}
