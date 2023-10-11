//package EveryDayProblem_0x3f;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[] a = new int[n];
        List<Integer>[] cord = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            cord[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            if (i < n / 2) {
                cord[a[i]].add(i);
            }else {
                cord[a[i]].add(n - 1 - i);
            }
        }
        //先计算没有一个一样的值
        long res = 0;
        for (int len = 2; len <= n; len++) {
            res = res + (len / 2) * 1l * (n - len + 1);
        }
        //System.out.println("res = " + res);
        for (int i = 0; i <= n; i++) {
            //System.out.println(cord[i]);
            //每一对相同的贡献了多少相同
            List<Integer> list = cord[i];
            Collections.sort(list);
            long ans = 0;
            for (int j = 0; j < list.size(); j++) {
                res -= ans;
                ans += list.get(j) + 1;
            }
        }
        System.out.println(res);
    }

}
