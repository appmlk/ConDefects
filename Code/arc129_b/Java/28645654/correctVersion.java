import java.util.*;
public class Main {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxA = Integer.MIN_VALUE, minB = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < n;i++ ) {
            maxA = Math.max(maxA, sc.nextInt());
            minB = Math.min(minB, sc.nextInt());
            if (maxA <= minB) sb.append(0).append('\n');
            else sb.append((int) Math.ceil((double) (maxA - minB) / 2)).append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}