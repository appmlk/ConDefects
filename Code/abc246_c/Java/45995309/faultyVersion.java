import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        long[] items = new long[l];

        for(int i = 0;i<l;i++) {
            items[i] = sc.nextLong();
            if(items[i]>=x) {
                long remainder = Math.min(items[i]/x, k);
                k-=remainder;
                items[i]=x*remainder;
            }
        }

        Arrays.sort(items);

        long sum = 0;
        for(int i = 0;i<l-k;i++) {
            sum+=items[i];
        }
        System.out.println(sum);
    }
}