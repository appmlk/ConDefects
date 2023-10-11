import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int h = sc.nextInt();
        int K = sc.nextInt();

        String S = sc.next();

        Map<Integer, ArrayList<Integer>> ITEMS = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> DEFAULT_VALUE = new ArrayList<Integer>();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            ArrayList<Integer> y = ITEMS.getOrDefault(x, (ArrayList<Integer>) DEFAULT_VALUE.clone());
            y.add(sc.nextInt());
            ITEMS.put(x, y);
        }

        sc.close();

        int[] pos = {0, 0};
        for (int i=0; i<N; i++) {
            // 移動
            if (S.charAt(i) == 'R') {pos[0]++;}
            else if (S.charAt(i) == 'L') {pos[0]--;}
            else if (S.charAt(i) == 'U') {pos[1]++;}
            else if (S.charAt(i) == 'D') {pos[1]--;}

            // 体力計算
            h--;
            if (h<0) {
                System.out.println("No");
                return;
            } else {
                if (ITEMS.getOrDefault(pos[0], DEFAULT_VALUE).contains(pos[1]) && h < K) {
                    h = K;
                }
            }
        }
        System.out.println("Yes");

    }
    
}
