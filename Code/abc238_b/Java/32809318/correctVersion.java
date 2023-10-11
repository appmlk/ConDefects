import java.util.*;

class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] a = new int[n+2];
            a[n] = 0;
            a[n+1] = 360;
            int s = 0;
            for(int i=0; i<n; i++) {
                int nxt = sc.nextInt();
                s += nxt;
                a[i] = s%360;
            }
            Arrays.sort(a);
            int ans = 0;
            for(int i=0; i<n+1; i++) {
                ans = Math.max(ans, a[i+1]-a[i]);
            }
            System.out.println(ans);
        }
    }
}