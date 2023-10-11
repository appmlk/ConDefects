import java.io.File;
import java.io.PrintWriter;
import java.util.*;




public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] arr = new long[n];



        for( int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long summ = 0;
        long subsum = 0;

        for (int i = 1; i <= k; i++) {
            subsum += arr[i-1]*i;
            summ += arr[i-1];


        }
        long max = subsum;
        for (int i = k; i < n; i++) {

            subsum = subsum-summ+arr[i]*k;
            summ += arr[i]-arr[i-k];
            max = Math.max(max, subsum);

        }
        System.out.println(max);




    }

}

