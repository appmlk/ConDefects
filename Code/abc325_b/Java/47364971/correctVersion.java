import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
// import inputAll.inputAll;

public class Main {

    public static void main(String... args) {
        // inputAll inputAll = new inputAll();
        // int i = inputAll.inputnum();
        Scanner sc = new Scanner(System.in);
        Main ma = new Main();
        int inputnum = ma.inputnum(sc);
        int[][] inputArray = new int[inputnum][2];
        for (int i = 0; i < inputnum; i++) {
            for (int j = 0; j < 2; j++) {
                inputArray[i][j] = sc.nextInt();
            }
        }
        // 会議開始時刻 i
        // 現地時間 (i+inputArray[i][1])%24
        //
        int max = 0;
        for (int i = 0; i < 24; i++) {
            int count = 0;
            for (int j = 0; j < inputnum; j++) {
                if (i + 8 >= 24) {
                    if (i <= inputArray[j][1] || inputArray[j][1] <= (i + 8) % 24) {
                        count += inputArray[j][0];
                    }
                } else {
                    if (i <= inputArray[j][1] && inputArray[j][1] <= i + 8) {
                        count += inputArray[j][0];
                    }
                }
            }
            if (max < count) {
                // System.out.println(i + "時から" + (i + 8) + "時の間で更新");
                max = count;
            }
        }
        System.out.println(max);
        sc.close();
    }

    public int inputnum(Scanner sc) {

        // 整数の入力
        int a = sc.nextInt();
        // sc.close();
        return a;
    }

    public ArrayList<Integer> inputIntegerArray(Scanner sc, int n) {
        // Scanner sc = new Scanner(System.in);
        // 整数の入力
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        // sc.close();
        return a;
    }

    public String inputString(Scanner sc) {
        // Scanner sc = new Scanner(System.in);
        // 整数の入力
        String a = sc.next();
        // sc.close();
        return a;
    }

    public ArrayList<String> inputStringArray(Scanner sc, int n) {
        // Scanner sc = new Scanner(System.in);
        // 整数の入力
        ArrayList<String> a = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            a.add(sc.next());
        }
        // sc.close();
        return a;
    }
}
