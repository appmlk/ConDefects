
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = sc.nextInt();

        int a[] = Arrays.stream(new int[n]).map(e -> sc.nextInt()).toArray();
        long sum = Arrays.stream(a).sum();

        long dpMax[] = new long[n];
        long dpMin[] = new long[n];

        dpMax[0] = dpMin[0] = a[0];

        for(int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMax[i - 1] + a[i], a[i]);
            dpMin[i] = Math.min(dpMin[i - 1] + a[i], a[i]);

//            System.out.println("min " + dpMin[i]);
        }

        long result = sum;
        if(c > 0) {
            long max = Arrays.stream(dpMax).max().getAsLong();
            if(max > 0) {
                result = sum + (c - 1) * max;
            }
        }
        else {
            long min = Arrays.stream(dpMin).min().getAsLong();
//            System.out.println(sum);
//            System.out.println(min);
            if(min <= 0) {
                result = sum + (c - 1) * min;
            }
        }

        System.out.println(result);
    }


}
