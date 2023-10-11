import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        long[] arr = new long[n];
        long k=scan.nextLong();
        long x=scan.nextLong();
        for(int i=0;i<arr.length;i++){
            arr[i]=scan.nextLong();
        }

        for(int i=0;i<arr.length;i++){
            if(arr[i]>x){
                long p=Math.min(arr[i]/x, k);
                arr[i]-=(p*x);
                k-=p;
            }
        }
        int total=0;
        Arrays.sort(arr);
        for(int i=0;i<n-k;i++){
            total+=arr[i];
        }
        System.out.println(total);
    }
}

