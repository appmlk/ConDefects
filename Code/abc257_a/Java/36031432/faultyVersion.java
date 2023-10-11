import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 入力読み込み
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        int v = X/N-1;
        char a = 'A';
        for (int i = 0; i < v; i++) {
            a++;
        }

        System.out.println(a);
        sc.close();
    }
}

//    Set<Long> list = new HashSet<>();