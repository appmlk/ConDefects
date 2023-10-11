import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];
        for (int i = 0;i < n;i++) s[i] = sc.nextInt();
        int[] a = new int[n + 2];
        a[0] = a[1] = 0;
        for (int i = 2;i < a.length;i ++) {
            a[i] = s[i - 2] - a[i - 1] - a[i - 2];
        }
        int[] one = new int[(n + 2 + 2) / 3];
        int[] two = new int[(n + 2 + 1) / 3];
        int[] three = new int[(n + 2) / 3];
        for (int i = 0;i < a.length;i ++) {
            if ((i + 1) % 3 == 1) one[i / 3] = a[i];
            else if ((i + 1) % 3 == 2) two[i / 3] = a[i];
            else three[i / 3] = a[i];
        }
        int minOne = Integer.MAX_VALUE;
        for (int i = 0;i < one.length;i ++) {
            minOne = Math.min(one[i], minOne);
        }
        int minTwo = Integer.MAX_VALUE;
        for (int i = 0;i < two.length;i ++)
            minTwo = Math.min(minTwo, two[i]);
        int minThree = Integer.MAX_VALUE;
        for (int i = 0;i < three.length;i ++)
            minThree = Math.min(three[i], minThree);
        if ((long) -minOne - minTwo > minThree) {
            System.out.println("No");
            return;
        }
        a[0] = -minOne;
        a[1] = -minTwo;
        for (int i = 2;i < a.length;i ++) a[i] = s[i - 2] - a[i - 1] - a[i - 2];
        System.out.println("Yes");
        StringBuilder sb = new StringBuilder();
        for (int i : a) sb.append(i).append(' ');
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}