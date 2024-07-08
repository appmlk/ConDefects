
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        long a[] = Arrays.stream(new long[n]).map(e-> sc.nextLong()).sorted().toArray();

//        Vector<Long> vecNonNeg = new Vector<>();
//        Vector<Long> vecNeg = new Vector<>();
        long nonNegSum = 0;
        long negSum = 0;
        for(int i = 0; i < n; i++) {
            if(a[i] >= 0) {
//                vecNonNeg.add(a[i]);
                nonNegSum += a[i];
            }
            else {
//                vecNeg.add(a[i]);
                negSum += a[i];
            }
        }


        if(k <= 0 && nonNegSum + negSum < k) {
            System.out.println("No");
            return;
        }

        System.out.println("Yes");
        for(int i = 0; i < n; i++) {
            if(k > 0) {
                System.out.print(a[i] + " ");

            }
            else {
                System.out.print(a[n - i - 1] + " ");
            }
        }
    }
}
